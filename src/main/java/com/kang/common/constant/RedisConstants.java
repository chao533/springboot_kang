package com.kang.common.constant;

/**
　 * <p>Title: RedisConstants</p> 
　 * <p>Description: Redis常量</p> 
　 * @author CK 
　 * @date 2020年4月26日
 */
public class RedisConstants {

	/**
	 * 用户信息前缀
	 */
	public static final String USERINFO = "KANG:USER:";
	
	/**
	 * 文章的阅读量
	 */
	public static final String ARTICLE_READ_COUNT = "KANG:ARTICLE:READ:COUNT";
	
	/**
	 * 订单orderId
	 */
	public static final String ORDER_ID = "ORDER:ID";
	
	/**
	 * 用户购物车
	 */
	public static final String USER_CART = "KANG:USER:CART:";
	
	/**
	 * 我的关注
	 */
	public static final String FOLLOW = "KANG:FOLLOW:";
	
	/**
	 * 我的粉丝
	 */
	public static final String FANS = "KANG:FANS:";
	
	/**
	 * 分布式锁
	 */
	public static final String KANG_REDISSON_LOCK = "KANG:REDISSON:LOCK:";
	
	/**
	 * IP限流
	 */
	public static final String KANG_REQUEST_LIMIT = "KANG:REQUEST:LIMIT:";	
}
