package com.chao.mybatis;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chao.BaseTest;
import com.kang.mapper.mybatis.UserMapper;
import com.kang.model.mybatis.User;
import com.kang.service.UserService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;

public class MybatisUserTest extends BaseTest{

	@Autowired
	private UserService userService;
	@Autowired
    private UserMapper userMapper;
	
	@Test
	public void testInsert() {
		User user = new User();
		user.setLoginName("test01");
		user.setPwd("123456");
		user.setUserName("测试用户");
		user.setIcon("http://group1/mm/1.jpg");
		user.setBirthday(new Date());
		user.setGender(false);
		userService.insertUser(user);
	}
	
	@Test
	public void testBatchInsert() {
		User user = new User();
		user.setLoginName("test03");
		user.setPwd("123456");
		user.setUserName("测试用户");
		user.setIcon("http://group1/mm/1.jpg");
		user.setBirthday(new Date());
		user.setGender(false);
		user.setIsDel(false);
		
		User user2 = new User();
		user2.setLoginName("test02");
		user2.setPwd("123456");
		user2.setUserName("测试用户");
		user2.setIcon("http://group1/mm/2.jpg");
		user2.setBirthday(new Date());
		user2.setGender(true);
		user.setIsDel(false);
		userMapper.insertList(CollUtil.newArrayList(user,user2));
	}
	
	@Test
	public void testfind() {
		User user2 = new User();
		user2.setLoginName("admin");
		List<User> userList = userMapper.select(user2);
		userList.forEach(user -> Console.log(user));
	}
	
	@Test
	public void testDelete() {
		User user2 = new User();
		user2.setUserName("测试用户");;
		userMapper.delete(user2);
	}
}
