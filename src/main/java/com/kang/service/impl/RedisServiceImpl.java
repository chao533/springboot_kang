package com.kang.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kang.common.constant.RedisConstants;
import com.kang.common.msg.ErrorCode;
import com.kang.common.msg.Message;
import com.kang.mapper.redis.RedisMapper;
import com.kang.service.RedisService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RedisServiceImpl implements RedisService{

	@Autowired
	private RedisMapper redisMapper;
	
	@Override
	public Message<?> redisUser() {
		// ------第一种方式
    	Map<String,Object> userMap_1 = MapUtil.builder(new HashMap<String,Object>())
    		.put("id", 1002)
    		.put("name", "李四")
    		.put("gender", false)
    		.put("birthday", new Date()).build();
    	
    	// 存入用户信息
    	redisMapper.set(RedisConstants.USERINFO + userMap_1.get("id"), JSONUtil.toJsonStr(userMap_1), 300);
    	
    	// 取出用户信息
    	Object jsonObj_1 = redisMapper.get(RedisConstants.USERINFO + userMap_1.get("id"));
    	Map<String,Object> result_1 = JSONUtil.toBean(jsonObj_1.toString(), new TypeReference<Map<String,Object>>(){},true);
    	
    	log.info("string类型获取用户信息：{}" ,result_1);
    	
    	
    	// ------第二种方式
    	Map<String,Object> userMap_2 = MapUtil.builder(new HashMap<String,Object>())
    		.put("id", 1001)
    		.put("name", "张三")
    		.put("gender", true)
    		.put("birthday", new Date()).build();
    	
    	// 存入用户信息
    	redisMapper.hmset(RedisConstants.USERINFO + userMap_2.get("id"), userMap_2, 300);
    	
    	// 取出用户信息
    	Map<Object, Object> result_2 = redisMapper.hmget(RedisConstants.USERINFO + userMap_2.get("id"));
    	log.info("hash类型获取用户信息：{}" ,result_2);
    	
    	return new Message<>(ErrorCode.SUCCESS,CollUtil.newArrayList(result_1, result_2));
	}

	@Override
	public Message<?> redisStatistics() {
		// 进入一次查看文章，则+1次
		int randomInt = RandomUtil.randomInt(1000);
		for(int i = 0; i < randomInt; i++) {
			redisMapper.incr(RedisConstants.ARTICLE_READ_COUNT, 1l);
		}
		
		// 统计文章的阅读量统计
		Object readCount = redisMapper.get(RedisConstants.ARTICLE_READ_COUNT);
		log.info("本次阅读了{}次，总阅读数量：{}次" ,randomInt -1, readCount);
		return new Message<>(ErrorCode.SUCCESS,readCount);
	}

	@Override
	public Message<?> redisGlobalId() {
		int randomInt = RandomUtil.randomInt(1000);
		for(int i = 0; i < randomInt; i++) {
			long incr = redisMapper.incr(RedisConstants.ORDER_ID, 1000l);
			log.info("第{}生成全局性Id值为：{}", i, incr);
		}
		return new Message<>(ErrorCode.SUCCESS,"本次生成数量为" + (randomInt - 1) + "个ID");
	}

	@Override
	public Message<?> redisCart() {
		// 添加第一个商品1001数量为1
		redisMapper.hset(RedisConstants.USER_CART, "1001", 1, 120l);
		// 添加第二个商品1002数量为1
		redisMapper.hset(RedisConstants.USER_CART, "1002", 1, 120l);
		// 添加第三个商品1003数量为1
		redisMapper.hset(RedisConstants.USER_CART, "1003", 1, 120l);
		
		log.info("添加三商品个后，此时的购物车中商品有：{}" , redisMapper.hmget(RedisConstants.USER_CART));
		
		// 增加商品1002数量为1~3
		redisMapper.hincr(RedisConstants.USER_CART, "1002", 2);
		log.info("增加商品1002数量为1~3，此时购物车中商品有：{}" , redisMapper.hmget(RedisConstants.USER_CART));
		
		// 1002商品总数
		long size = redisMapper.size(RedisConstants.USER_CART, "1002");
		log.info("查看购物车1002商品的数量为:{}" , size);
		
		// 删除1001商品
		redisMapper.hdel(RedisConstants.USER_CART, "1001");
		log.info("删除1001商品，此时购物车中商品有：{}" , redisMapper.hmget(RedisConstants.USER_CART));
		
		// 获取购物车所有商品
		Map<Object, Object> goodsList = redisMapper.hmget(RedisConstants.USER_CART);
		log.info("获取购物车所有商品，此时购物车中商品有：{}" , goodsList);
		
		return new Message<>(ErrorCode.SUCCESS,goodsList);
	}

	@Override
	public Message<?> redisFollowFans() {
		// zhangsan用户关注了lisi用户
		redisMapper.leftPush(RedisConstants.FOLLOW + "zhangsan", "lisi", 300l);
		// lisi用户的粉丝有zhangsan用户
		redisMapper.leftPush(RedisConstants.FANS + "lisi", "zhangsan", 300l);
		
		// zhangsan用户关注了wangwu用户
		redisMapper.leftPush(RedisConstants.FOLLOW + "zhangsan", "wangwu", 300l);
		// wangwu用户的粉丝有zhangsan用户
		redisMapper.leftPush(RedisConstants.FANS + "wangwu", "zhangsan", 300l);
		
		// lisi用户关注了zhangsan用户
		redisMapper.leftPush(RedisConstants.FOLLOW + "lisi", "zhangsan", 300l);
		// zhangsan用户的粉丝有lisi用户
		redisMapper.leftPush(RedisConstants.FANS + "zhangsan", "lisi", 300l);
		
		log.info("此时用户{}的关注列表为:{}","zhangsan", redisMapper.range(RedisConstants.FOLLOW + "zhangsan", 0, -1));
		log.info("此时用户{}的粉丝列表为:{}","zhangsan", redisMapper.range(RedisConstants.FANS + "zhangsan", 0, -1));
		
		log.info("此时用户{}的关注列表为:{}","lisi", redisMapper.range(RedisConstants.FOLLOW + "lisi", 0, -1));
		log.info("此时用户{}的粉丝列表为:{}","lisi", redisMapper.range(RedisConstants.FANS + "lisi", 0, -1));

		log.info("此时用户{}的关注列表为:{}","wangwu", redisMapper.range(RedisConstants.FOLLOW + "wangwu", 0, -1));
		log.info("此时用户{}的粉丝列表为:{}","wangwu", redisMapper.range(RedisConstants.FANS + "wangwu", 0, -1));
		
		return new Message<>(ErrorCode.SUCCESS);
	}

}
