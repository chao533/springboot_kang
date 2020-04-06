package com.szdtoo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.szdtoo.common.constant.RedisConstants;
import com.szdtoo.common.msg.ErrorCode;
import com.szdtoo.common.msg.Message;
import com.szdtoo.common.utils.RedisUtil;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;

/**
　 * <p>Title: RedisController</p> 
　 * <p>Description: redis常用操作</p> 
　 * @author CK 
　 * @date 2020年4月6日
 */
@RestController
@RequestMapping(value="/redis")
public class RedisController {
    protected Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
	private RedisUtil redisUtil;
    
    /**
     *<p>Title: userOne</p> 
     *<p>Description: Hash类型的使用</p> 
     * @return
     */
    @RequestMapping(value="/userOne1",method=RequestMethod.GET)
    public Message<String> userOne(){
    	Map<String,Object> userMap = MapUtil.builder(new HashMap<String,Object>())
    		.put("id", 1001)
    		.put("name", "张三")
    		.put("gender", true)
    		.put("birthday", new Date()).build();
    	
    	// 存入用户信息
    	redisUtil.hmset(RedisConstants.USERINFO + userMap.get("id"), userMap, 120);
    	
    	// 取出用户信息
    	Map<Object, Object> result = redisUtil.hmget(RedisConstants.USERINFO + userMap.get("id"));
    	logger.info(result.toString());
    	return new Message<String>(ErrorCode.SUCCESS,result.toString());
    }
    
    /**
     *<p>Title: jsonTest</p> 
     *<p>Description: string类型的使用</p> 
     * @return
     */
    @RequestMapping(value="/userOne2",method=RequestMethod.GET)
    public Message<String> userOne2(){
    	Map<String,Object> userMap = MapUtil.builder(new HashMap<String,Object>())
    		.put("id", 1002)
    		.put("name", "李四")
    		.put("gender", false)
    		.put("birthday", new Date()).build();
    	
    	// 存入用户信息
    	redisUtil.set(RedisConstants.USERINFO + userMap.get("id"), JSONUtil.parseFromMap(userMap), 300);
    	
    	// 取出用户信息
    	Object jsonObj = redisUtil.get(RedisConstants.USERINFO + userMap.get("id"));
    	Map<?,?> result = JSONUtil.toBean(jsonObj.toString(), Map.class);
    	
    	return new Message<String>(ErrorCode.SUCCESS,result.toString());
    }
    
}
