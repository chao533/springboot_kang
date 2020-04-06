package com.kang.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kang.common.msg.ErrorCode;
import com.kang.common.msg.Message;
import com.kang.model.param.ModifyPwdParam;
import com.kang.model.param.UserLoginParam;
import com.kang.service.UserService;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiOperation;

/**
　 * <p>Title: LoginController</p> 
　 * <p>Description: 登录操作</p> 
　 * @author CK 
　 * @date 2020年4月6日
 */
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
