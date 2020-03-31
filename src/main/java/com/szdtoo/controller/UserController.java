package com.szdtoo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.szdtoo.common.msg.ErrorCode;
import com.szdtoo.common.msg.Message;
import com.szdtoo.common.utils.JwtUtil;
import com.szdtoo.model.param.UserListParam;
import com.szdtoo.service.UserService;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="用户controller",description="用户操作",tags={"用户操作接口"})
@RestController
@RequestMapping(value="/user")
public class UserController {
    protected Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    //@RequiresPermissions(value= {"user:get","user:add"})
    @ApiOperation("获取用户信息")
    @RequestMapping(value = "/{id}",method=RequestMethod.GET)
    public Message<?> findUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }
    
    @RequiresPermissions("user:get")
    @ApiOperation("根据token获取用户信息")
    @RequestMapping(value = "/tokenUserInfo",method=RequestMethod.GET)
    public Message<Map<String,Object>> findOneUser(){
        return new Message<Map<String,Object>>(ErrorCode.SUCCESS,JwtUtil.getUser());
    }

    @RequiresPermissions(value= {"user:get","user:add"}) // 同时满足 
    @ApiOperation("获取所有用户信息")
    @RequestMapping(value = "/userList",method=RequestMethod.GET)
    public Message<?> userList(UserListParam params){
        return userService.findUserList(BeanUtil.beanToMap(params));
    }


}
