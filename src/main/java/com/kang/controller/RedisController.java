package com.kang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kang.common.msg.Message;
import com.kang.common.utils.SpringUtil;
import com.kang.service.RedisService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
　 * <p>Title: RedisController</p> 
　 * <p>Description: redis常用操作</p> 
　 * @author CK 
　 * @date 2020年4月6日
 */
@Api(value="redis",tags={"redis操作接口"})
@RestController
@RequestMapping(value="/redis")
public class RedisController {

    @Autowired
	private RedisService redisService;
    
    /**
     *<p>Title: userOne</p> 
     *<p>Description: redis用户操作</p> 
     * @return
     */
    @ApiOperation(value="用户信息")
    @RequestMapping(value="/redisUser",method=RequestMethod.GET)
    public Message<?> redisUser(){
    	return SpringUtil.getBean(RedisService.class).redisUser();
    }
    
    /**
     *<p>Title: redisStatistics</p> 
     *<p>Description: 统计在Redis中的使用</p> 
     * @return
     */
    @ApiOperation(value="统计")
    @RequestMapping(value="/redisStatistics",method=RequestMethod.GET)
    public Message<?> redisStatistics(){
    	return redisService.redisStatistics();
    }
    
    /**
     *<p>Title: redisGlobalId</p> 
     *<p>Description: redis生成序列号，提升性能。</p> 
     * @return
     */
    @ApiOperation(value="生成序列号")
    @RequestMapping(value="/redisGlobalId",method=RequestMethod.GET)
    public Message<?> redisGlobalId(){
    	return redisService.redisGlobalId();
    }
    
    /**
     *<p>Title: redisCart</p> 
     *<p>Description: Redis购物车操作</p> 
     * @return
     */
    @ApiOperation(value="购物车")
    @RequestMapping(value="/redisCart",method=RequestMethod.GET)
    public Message<?> redisCart(){
    	return redisService.redisCart();
    }
    
    /**
     *<p>Title: redisFollowFans</p> 
     *<p>Description: 我的关注和我的粉丝操作</p> 
     * @return
     */
    @ApiOperation(value="我的关注和我的粉丝")
    @RequestMapping(value="/redisFollowFans",method=RequestMethod.GET)
    public Message<?> redisFollowFans(){
    	return redisService.redisFollowFans();
    }
    
    
}
