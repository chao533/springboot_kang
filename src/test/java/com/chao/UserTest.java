package com.chao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.szdtoo.Application;
import com.szdtoo.common.msg.Message;
import com.szdtoo.mapper.MongoUserRepository;
import com.szdtoo.model.MongoUser;
import com.szdtoo.service.UserService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;

@SpringBootTest(classes=Application.class)
@RunWith(SpringRunner.class)
public class UserTest {
	
	@Autowired
	private UserService userService;
	@Autowired
	private MongoUserRepository mongoUserRepository;
	

	@Test
	public void test1() {
		Map<String,Object> params = MapUtil.builder(new HashMap<String,Object>()).put("pageNo", 1).put("pageSize", 10).build();
		Message<?> userList = userService.findUserList(params);
		Map<String, Object> beanToMap = BeanUtil.beanToMap(userList);
		List<Map<String, Object>> list = MapUtil.get(beanToMap, "data", new TypeReference<List<Map<String,Object>>>() {});
		Console.log("集合数据为：{}", list);
		Console.log("集合数据为：{},{},{},{}", 12,22,3,4);
	}
	
	@Test
	public void test2() {
		Console.log(mongoUserRepository.findOne("004"));
		Console.log(userService.getMongoUserList());
		
		Console.log(mongoUserRepository.findByName("王五"));
	}
	
	@Test
	public void test3() {
		Map<String,Object> goods1 = MapUtil.builder(new HashMap<String,Object>()).put("goodsId", "1001").put("goodsName", "商品1").build();
		Map<String,Object> goods2 = MapUtil.builder(new HashMap<String,Object>()).put("goodsId", "1002").put("goodsName", "商品2").build();
		MongoUser mongoUser = MongoUser.builder().id("004").name("王五").gender(false).goodsList(CollUtil.newArrayList(goods1,goods2)).build();
		mongoUserRepository.save(mongoUser);
	} 
}
