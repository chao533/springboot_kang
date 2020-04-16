package com.kang.service;

import java.util.List;

import com.kang.common.msg.Message;

/**
　 * <p>Title: SeckillingGoodsService</p> 
　 * <p>Description: 秒杀商品</p> 
　 * @author CK 
　 * @date 2020年4月14日
 */
public interface SeckillingGoodsService {

	/**
	 *<p>Title: resetData</p> 
	 *<p>Description: 重置数据</p> 
	 * @return
	 */
	void resetData(Long id,Integer number);
	
	/**
	 *<p>Title: beforeData</p> 
	 *<p>Description: 准备环境和数据</p> 
	 * @param goodsId 商品id
	 * @param userId 用户id
	 */
	Message<?> seckillingGoods(Long goodsId,Integer goodsNum,List<Long> userIdList);
	
	/**
	 *<p>Title: seckillingGoods_Lock</p> 
	 *<p>Description: 程序锁（ReentrantLock）</p> 
	 * @param goodsId
	 * @param userId
	 * @return
	 */
	Message<?> seckillingGoods_Lock(Long goodsId,Long userId);
	
	/**
	 *<p>Title: seckillingGoods_AOP</p> 
	 *<p>Description: 程序锁（AOP）</p> 
	 * @param goodsId
	 * @param userId
	 * @return
	 */
	Message<?> seckillingGoods_AOP(Long goodsId,Long userId);
	
	/**
	 *<p>Title: seckillingGoods_Pessimism</p> 
	 *<p>Description: 数据库悲观锁</p> 
	 * @param goodsId
	 * @param userId
	 * @return
	 */
	Message<?> seckillingGoods_Pess(Long goodsId,Long userId);
	
	/**
	 *<p>Title: seckillingGoods_Opti</p> 
	 *<p>Description: 数据库乐观锁</p> 
	 * @param goodsId
	 * @param userId
	 * @return
	 */
	Message<?> seckillingGoods_Opti(Long goodsId,Long userId);
	
	/**
	 *<p>Title: seckillingGoods_RedissLock</p> 
	 *<p>Description: redisson分布式锁</p> 
	 * @param goodsId
	 * @param userId
	 * @return
	 */
	Message<?> seckillingGoods_RedissonLock(Long goodsId,Long userId);
	
	/**
	 *<p>Title: seckillingGoods_ZkLock</p> 
	 *<p>Description: Zookeeper 分布式锁</p> 
	 * @param goodsId
	 * @param userId
	 * @return
	 */
	Message<?> seckillingGoods_ZkLock(Long goodsId,Long userId);
}
