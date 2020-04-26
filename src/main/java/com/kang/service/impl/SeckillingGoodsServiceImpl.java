package com.kang.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kang.common.anno.LockAnnotation;
import com.kang.common.constant.RedisConstants;
import com.kang.common.msg.ErrorCode;
import com.kang.common.msg.Message;
import com.kang.config.redis.RedissonLock;
import com.kang.config.zk.ZkLock;
import com.kang.mapper.mybatis.SeckillingGoodsMapper;
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
	@Autowired
	private RedissonLock redissonLock;
	@Autowired
	private ZkLock zkLock;
	
	@Override
	public void resetData(Long id,Integer number) {
		seckillingGoodsMapper.deleteGoodsSuccDetail(id);
		seckillingGoodsMapper.updateGoodsStock(id,number);
	}
	
	@Override
	public Message<?> seckillingGoods(Long goodsId,Integer goodsNum, List<Long> userIdList) {
//		CountDownLatch latch = new CountDownLatch(userIdList.size());//100个参与秒杀的用户
		this.resetData(goodsId,goodsNum); // 重置数据
		
		log.info("开始秒杀");
		List<String> msgList = CollUtil.newArrayList();
		
		for(Long userId : userIdList){ // 模拟100个用户并发抢购10个商品
			executor.submit(() -> {
//				Message<?> result = this.seckillingGoods_Lock(goodsId,userId); // 程序锁Lock
//				Message<?> result = this.seckillingGoods_AOP(goodsId,userId); // 程序锁AOP
//				Message<?> result = this.seckillingGoods_Pess(goodsId, userId); // 数据库悲观锁
//				Message<?> result = this.seckillingGoods_Opti(goodsId, userId); // 数据库乐观锁
				Message<?> result = this.seckillingGoods_RedissonLock(goodsId, userId); // redisson分布式锁
//				Message<?> result = this.seckillingGoods_ZkLock(goodsId, userId); // Zookeeper 分布式锁
//				latch.countDown();
				log.info("{}用户ID:{}，{}",Thread.currentThread().getName(),userId,result.getMsg());
				msgList.add(Thread.currentThread().getName() + "用户ID:" + userId + "," + result.getMsg());
			});
		}
		
		try {
			Thread.sleep(8000); // 停5秒
//			latch.await();// 等待所有人任务结束
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

	
	@Override
	@Transactional
	@LockAnnotation
	public Message<?> seckillingGoods_AOP(Long goodsId, Long userId) {
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
		return new Message<>(ErrorCode.SUCESS_END);
	}
	

	@Transactional
	@Override
	public Message<?> seckillingGoods_Pess(Long goodsId, Long userId) {
		int number = seckillingGoodsMapper.getGoodsStock_Pess(goodsId); // 获取库存
		if (number > 0) {
			int count = seckillingGoodsMapper.subGoodsStock(goodsId); // 库存-1
			if(count > 0) {
				Map<String,Object> params = MapUtil.builder(new HashMap<String,Object>())
						.put("goodsId", goodsId).put("userId", userId).put("state", 0).build();
				seckillingGoodsMapper.insertGoodsSuccDetail(params); // 添加秒杀成功记录
			}
			// 支付
		} else {
			return new Message<>(ErrorCode.ERROR_END);
		}
		return new Message<>(ErrorCode.SUCESS_END);
	}

	@Transactional
	@Override
	public Message<?> seckillingGoods_Opti(Long goodsId, Long userId) {
		//这里使用的乐观锁、可以自定义抢购数量、如果配置的抢购人数比较少、比如120:100(人数:商品) 会出现少买的情况
		//用户同时进入会出现更新失败的情况
		Map<String,Object> goods = seckillingGoodsMapper.getGoodsStockData(goodsId); // 获取库存
		if (MapUtil.getInt(goods, "number") > 0) {
			int count = seckillingGoodsMapper.subGoodsStock_Opti(goodsId,MapUtil.getInt(goods, "version")); // 库存-1
			if(count > 0) {
				Map<String,Object> params = MapUtil.builder(new HashMap<String,Object>())
						.put("goodsId", goodsId).put("userId", userId).put("state", 0).build();
				seckillingGoodsMapper.insertGoodsSuccDetail(params); // 添加秒杀成功记录
				// 支付
				
				return new Message<>(ErrorCode.SUCESS_END);
			}
		} 
		return new Message<>(ErrorCode.ERROR_END);
	}

	@Transactional
	@Override
	public Message<?> seckillingGoods_RedissonLock(Long goodsId, Long userId) {
//		尝试获取锁，最多等待3秒，上锁以后20秒自动解锁（实际项目中推荐这种，以防出现死锁）、这里根据预估秒杀人数，设定自动释放锁时间.
		boolean res = false;
		try {
			res = redissonLock.tryLock(RedisConstants.KANG_REDISSON_LOCK + goodsId, TimeUnit.SECONDS, 3, 20);
			if(res) {
				int number = seckillingGoodsMapper.getGoodsStock(goodsId); // 获取库存
				if (number > 0) {
					seckillingGoodsMapper.subGoodsStock(goodsId); // 库存-1
					
					Map<String,Object> params = MapUtil.builder(new HashMap<String,Object>())
							.put("goodsId", goodsId).put("userId", userId).put("state", 0).build();
					seckillingGoodsMapper.insertGoodsSuccDetail(params); // 添加秒杀成功记录
					
					// 支付
					return new Message<>(ErrorCode.SUCESS_END);
				} else {
					return new Message<>(ErrorCode.ERROR_END);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			redissonLock.unlock(RedisConstants.KANG_REDISSON_LOCK + goodsId);
		}
		return new Message<>(ErrorCode.SUCESS_END);
	}

	@Transactional
	@Override
	public Message<?> seckillingGoods_ZkLock(Long goodsId, Long userId) {
//		尝试获取锁，最多等待3秒，上锁以后20秒自动解锁（实际项目中推荐这种，以防出现死锁）、这里根据预估秒杀人数，设定自动释放锁时间.
		boolean res = false;
		try {
			res = zkLock.acquire(3,TimeUnit.SECONDS);
			if(res) {
				int number = seckillingGoodsMapper.getGoodsStock(goodsId); // 获取库存
				if (number > 0) {
					seckillingGoodsMapper.subGoodsStock(goodsId); // 库存-1
					
					Map<String,Object> params = MapUtil.builder(new HashMap<String,Object>())
							.put("goodsId", goodsId).put("userId", userId).put("state", 0).build();
					seckillingGoodsMapper.insertGoodsSuccDetail(params); // 添加秒杀成功记录
					
					// 支付
					return new Message<>(ErrorCode.SUCESS_END);
				} else {
					return new Message<>(ErrorCode.ERROR_END);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(res){//释放锁
				zkLock.release();
			}
		}
		return new Message<>(ErrorCode.SUCESS_END);
	}


	
		
}
