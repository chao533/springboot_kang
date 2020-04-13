package com.kang.service;

import com.kang.common.msg.Message;

public interface RedisService {

	/**
	 *<p>Title: redis_type_String</p> 
	 *<p>Description: string类型的使用</p>
	 */
	public Message<?> redisUser();
	
	/**
	 *<p>Title: redisStatistics</p> 
	 *<p>Description: 统计在Redis中的使用</p> 
	 * @return
	 */
	public Message<?> redisStatistics();
	
	/**
	 *<p>Title: redisGlobalId</p> 
	 *<p>Description: Redis生成序列号，提升性能。</p> 
	 * @return
	 */
	public Message<?> redisGlobalId();
	
	/**
	 *<p>Title: redisCart</p> 
	 *<p>Description: Redis购物车操作</p> 
	 * @return
	 */
	public Message<?> redisCart();
	
	/**
	 *<p>Title: redisFollowFans</p> 
	 *<p>Description: 我的关注和我的粉丝操作</p> 
	 * @return
	 */
	public Message<?> redisFollowFans();
}
