package com.kang.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kang.common.msg.Message;
import com.kang.service.SeckillingGoodsService;

/**
　 * <p>Title: SeckillingController</p> 
　 * <p>Description: 秒杀</p> 
　 * @author CK 
　 * @date 2020年4月14日
 */
@RestController
@RequestMapping(value="/goods")
public class SeckillingController {

	@Autowired
	private SeckillingGoodsService seckillingGoodsService;
	
	/**
	 *<p>Title: seckillingGoods</p> 
	 *<p>Description: </p> 
	 * @param goodsId 商品id
	 * @param goodsNum 初始化库存的数量
	 * @param userCount 初始化抢购商品的人数
	 * @return
	 */
	@RequestMapping(value="/seckillingGoods",method=RequestMethod.GET)
    public Message<?> seckillingGoods(Long goodsId,Integer goodsNum,Integer userCount){
    	return seckillingGoodsService.seckillingGoods(goodsId,goodsNum,getUseridList(userCount));
    }
	
	private List<Long> getUseridList(Integer userCount){
		List<Long> userIdList = new ArrayList<>();
		for(long i = 1; i<= userCount; i++) {
			userIdList.add(i);
		}
		return userIdList;
	}
}
