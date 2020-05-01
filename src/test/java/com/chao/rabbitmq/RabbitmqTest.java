package com.chao.rabbitmq;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chao.BaseTest;
import com.kang.service.RabbitmqProducerService;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;

/**
 * <p>Title: RabbitmqTest</p>  
 * <p>Description: rabbitmq消息队列测试</p>  
 * @author chaokang  
 * @date 2018年12月5日
 */
public class RabbitmqTest extends BaseTest{
	
	@Autowired
	private RabbitmqProducerService rabbitmqProducerService;

	@Test
	public void testRabbitmq_1() {
		Map<String, Object> params = MapUtil.builder(new HashMap<String,Object>())
			.put("to", "chao533@qq.com").put("subject", "合肥").put("html", "合肥天气预报").build();
		rabbitmqProducerService.sendEmail(JSONUtil.toJsonStr(params));
	}
	
	@Test
	public void testRabbitmq_2() {
		Map<String, Object> params = MapUtil.builder(new HashMap<String,Object>())
			.put("id", IdUtil.simpleUUID()).put("name", "张三").put("birthday", DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN)).build();
		rabbitmqProducerService.sendMessage(JSONUtil.toJsonStr(params));
	}
	
	@Test
	public void testRabbitmq_3() {
		Map<String, Object> params = MapUtil.builder(new HashMap<String,Object>())
			.put("id", IdUtil.simpleUUID()).put("name", "李四").put("birthday", DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN)).build();
		rabbitmqProducerService.sendText(JSONUtil.toJsonStr(params));
	}
}
