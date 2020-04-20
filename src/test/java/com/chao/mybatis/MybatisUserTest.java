package com.chao.mybatis;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chao.BaseTest;
import com.kang.model.mybatis.User;
import com.kang.service.UserService;

public class MybatisUserTest extends BaseTest{

	@Autowired
	private UserService userService;
	
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
}
