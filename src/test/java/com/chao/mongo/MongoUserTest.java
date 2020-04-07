package com.chao.mongo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.chao.BaseTest;
import com.kang.mapper.mongo.MongoUserRepository;
import com.kang.model.mongo.MongoUser;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.map.MapUtil;

public class MongoUserTest extends BaseTest{
	@Autowired
	private MongoUserRepository mongoUserRepository;
	

	/**
	 *<p>Title: testSaveMongoUser</p> 
	 *<p>Description: 测试保存MongoUser</p>
	 */
	@Test
	public void testSaveMongoUser() {
		Map<String,Object> goods1 = MapUtil.builder(new HashMap<String,Object>()).put("goodsId", "1001").put("goodsName", "商品1").build();
		Map<String,Object> goods2 = MapUtil.builder(new HashMap<String,Object>()).put("goodsId", "1002").put("goodsName", "商品2").build();
		MongoUser mongoUser1 = MongoUser.builder().id("001").name("张三").gender(false).goodsList(CollUtil.newArrayList(goods1,goods2)).build();
		mongoUserRepository.save(mongoUser1);
	}
	
	/**
	 *<p>Title: testBatchSaveMongoUser</p> 
	 *<p>Description: 批量保存MongoUser</p>
	 */
	public void testBatchSaveMongoUser() {
		Map<String,Object> goods3 = MapUtil.builder(new HashMap<String,Object>()).put("goodsId", "1003").put("goodsName", "商品3").build();
		Map<String,Object> goods4 = MapUtil.builder(new HashMap<String,Object>()).put("goodsId", "1004").put("goodsName", "商品4").build();
		MongoUser mongoUser2 = MongoUser.builder().id("003").name("李四").gender(false).goodsList(CollUtil.newArrayList(goods3,goods4)).build();
		
		Map<String,Object> goods5 = MapUtil.builder(new HashMap<String,Object>()).put("goodsId", "1005").put("goodsName", "商品5").build();
		Map<String,Object> goods6 = MapUtil.builder(new HashMap<String,Object>()).put("goodsId", "1006").put("goodsName", "商品6").build();
		MongoUser mongoUser3 = MongoUser.builder().id("005").name("王五").gender(false).goodsList(CollUtil.newArrayList(goods5,goods6)).build();
		mongoUserRepository.save(CollUtil.newArrayList(mongoUser2,mongoUser3));
	}
	
	/**
	 *<p>Title: testGetMongoUser</p> 
	 *<p>Description: 测试id查询</p>
	 */
	@Test
	public void testGetMongoUser() {
		MongoUser mongoUser = mongoUserRepository.findOne("001");
		Console.log(mongoUser);
	} 
	
	/**
	 *<p>Title: testGetMongoUserAll</p> 
	 *<p>Description: 查询所有</p>
	 */
	@Test
	public void testGetMongoUserAll() {
		List<MongoUser> mongoUserList = mongoUserRepository.findAll();
		Console.log(mongoUserList);
	}
	
	/**
	 *<p>Title: testGetMongoUserCondition</p> 
	 *<p>Description: 条件查询</p>
	 */
	@Test
	public void testGetMongoUserCondition() {
		MongoUser mongoUser = MongoUser.builder().name("张三").build();
		List<MongoUser> mongoUserList1 = mongoUserRepository.findAll(Example.of(mongoUser));
		Console.log(mongoUserList1);
		
		List<MongoUser> mongoUserList2 = mongoUserRepository.findByName("王五");
		Console.log(mongoUserList2);
	}
}
