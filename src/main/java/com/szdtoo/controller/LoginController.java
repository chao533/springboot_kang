package com.szdtoo.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.szdtoo.common.msg.ErrorCode;
import com.szdtoo.common.msg.Message;
import com.szdtoo.model.param.ModifyPwdParam;
import com.szdtoo.model.param.UserLoginParam;
import com.szdtoo.service.UserService;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiOperation;
@RestController
public class LoginController {
    protected Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @ApiOperation("登录验证")
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public Message<?> login(@RequestBody UserLoginParam params){
    	return userService.login(BeanUtil.beanToMap(params));
    }

    @ApiOperation("修改密码")
    @RequestMapping(value="/modifyPwd",method=RequestMethod.POST)
    public Message<?> modifyPwd(@RequestBody ModifyPwdParam params){
    	return userService.modifyPwd(params);
    }

    @ApiOperation("退出登录")
    @RequestMapping(value="/loginOut",method=RequestMethod.GET)
    public Message<String> loginOut(HttpServletRequest request){
    	return userService.loginOut(request);
    }
    
    @RequestMapping(value="/unauthorizedurl",method=RequestMethod.GET)
	public Message<?> unauthorizedurl() {
		return new Message<>(ErrorCode.ERROR_AUTH);
	}
    
}
