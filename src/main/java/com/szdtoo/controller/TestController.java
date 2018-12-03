package com.szdtoo.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.szdtoo.common.msg.ErrorCode;
import com.szdtoo.common.msg.Message;
import com.szdtoo.common.utils.RedisUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Testcontroller",description="Test操作",tags={"Test操作接口"})
@RestController
@RequestMapping(value="/test")
public class TestController {
    protected Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
	private RedisUtil redisUtil;
    
    @ApiOperation("Test测试")
    @RequestMapping(value = "/testOne",method=RequestMethod.GET)
    public Message<String> testOne(){
    	// string类型
    	redisUtil.set("sms_phone", "13665510132", 120l);
    	
    	// hash类型
    	Map<String,Object> userMap = new HashMap<String,Object>();
    	userMap.put("id", "001");
    	userMap.put("name", "张三");
    	userMap.put("age", 18);
    	redisUtil.hmset("userInfo", userMap, 120l);
    	
    	// 获取
    	logger.info("sms_phone:" + redisUtil.get("sms_phone") + "-----------");
    	logger.info("userInfo:" + redisUtil.hmget("userInfo").toString() + "-----------");
        return new Message<String>(ErrorCode.SUCCESS);
    }
    

}
