package com.kang.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kang.common.msg.ErrorCode;
import com.kang.common.msg.Message;
import com.kang.mapper.mybaits.SeckillingGoodsMapper;
import com.kang.service.SeckillingGoodsService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SeckillingGoodsServiceImpl implements SeckillingGoodsService{
	
	/**
	 * 默认是单例的，并发下lock只有一个实例
	 */
	private Lock lock = new ReentrantLock(true);//互斥锁 参数默认false，不公平锁  
	
	private static int corePoolSize = Runtime.getRuntime().availableProcessors();
	//创建线程池  调整队列数 拒绝服务
	private static ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, corePoolSize + 1, 10l, TimeUnit.SECONDS,
			new LinkedBlockingQueue<>(1000));

	@Autowired
	private SeckillingGoodsMapper seckillingGoodsMapper;
	
	@Override
	public void resetData(Long id,Integer number) {
		seckillingGoodsMapper.deleteGoodsSuccDetail(id);
		seckillingGoodsMapper.updateGoodsStock(id,number);
	}
	
	@Override
	public Message<?> seckillingGoods(Long goodsId,Integer goodsNum, List<Long> userIdList) {
		CountDownLatch latch = new CountDownLatch(userIdList.size());//100个参与秒杀的用户
		this.resetData(goodsId,goodsNum); // 重置数据
		
		log.info("开始秒杀二(正常)");
		List<String> msgList = CollUtil.newArrayList();
		
		for(Long userId : userIdList){ // 模拟100个用户并发抢购10个商品
			executor.submit(() -> {
				Message<?> result = this.seckillingGoods_Lock(goodsId,userId);
				latch.countDown();
				log.info("{}用户ID:{}，{}",Thread.currentThread().getName(),userId,result.getMsg());
				msgList.add(Thread.currentThread().getName() + "用户ID:" + userId + "," + result.getMsg());
			});
		}
		
		try {
			latch.await();// 等待所有人任务结束
			int count = seckillingGoodsMapper.getGoodsSuccDetailCount(goodsId);
			log.info("一共秒杀出{}件商品",count);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new Message<>(ErrorCode.SUCCESS,msgList);
	}
	

	@Transactional
	@Override
	public Message<?> seckillingGoods_Lock(Long goodsId, Long userId) {
		try {
			lock.lock(); // 加锁
			int number = seckillingGoodsMapper.getGoodsStock(goodsId); // 获取库存
			if (number > 0) {
				seckillingGoodsMapper.subGoodsStock(goodsId); // 库存-1
				
				Map<String,Object> params = MapUtil.builder(new HashMap<String,Object>())
						.put("goodsId", goodsId).put("userId", userId).put("state", 0).build();
				seckillingGoodsMapper.insertGoodsSuccDetail(params); // 添加秒杀成功记录
				
				// 支付
			} else {
				return new Message<>(ErrorCode.ERROR_END);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock(); // 解锁
		}
		return new Message<>(ErrorCode.SUCESS_END);
	}


	
		
}