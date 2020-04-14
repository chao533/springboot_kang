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
}
