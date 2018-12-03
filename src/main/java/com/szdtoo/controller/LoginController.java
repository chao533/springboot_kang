package com.szdtoo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.szdtoo.common.msg.ErrorCode;
import com.szdtoo.common.msg.Message;
import com.szdtoo.model.User;
import com.szdtoo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="登录controller",description="登录操作",tags={"登录操作接口"})
@RestController
public class LoginController {
    protected Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @ApiOperation("登录验证")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "用户名", required = true,dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "password", value = "密码", required = true,dataType = "string", paramType = "query")
    })
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public Message<Map<String,Object>> login(HttpServletRequest request,String username,String password){
    	return new Message<Map<String,Object>>(ErrorCode.SUCCESS,userService.login(request,username, password));
    }

    @ApiOperation("修改密码")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "用户名", required = true,dataType = "int", paramType = "query"),
        @ApiImplicitParam(name = "oldPwd", value = "密码", required = true,dataType = "int", paramType = "query"),
        @ApiImplicitParam(name = "newPwd", value = "确认密码", required = true,dataType = "int", paramType = "query")
    })
    @RequestMapping(value="/modifyPwd",method=RequestMethod.POST)
    public Message<User> modifyPwd(HttpServletRequest request,String username,String oldPwd,String newPwd){
    	return userService.modifyPwd(request,username,oldPwd,newPwd);
    }

    @ApiOperation("退出登录")
    @RequestMapping(value="/loginOut",method=RequestMethod.GET)
    public Message<String> loginOut(HttpServletRequest request){
    	return userService.loginOut(request);
    }
    
}
