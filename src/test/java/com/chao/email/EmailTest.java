package com.chao.email;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chao.BaseTest;
import com.kang.service.EmailService;

public class EmailTest extends BaseTest {
	
	@Autowired
	private EmailService emailService;
	
	@Test
	public void testSendEmail() {
//		emailService.sendTextMail("chao533@qq.com", "测试邮件主题", "这是一条主体内容");
		emailService.sendHtmlMail("chao533@qq.com", "测试邮件主题", "<font color='red'>这是一条主体内容</font>");
	}

}
