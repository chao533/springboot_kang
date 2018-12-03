package com.szdtoo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.szdtoo.common.exception.TokenValidationException;
import com.szdtoo.common.msg.ErrorCode;
import com.szdtoo.common.msg.Message;
import com.szdtoo.common.utils.JwtUtil;
import com.szdtoo.model.User;
import com.szdtoo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="用户controller",description="用户操作",tags={"用户操作接口"})
@RestController
@RequestMapping(value="/user")
public class UserController {
    protected Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation("获取用户信息")
    @ApiImplicitParam(name = "id",value = "用户id",dataType = "Long",required = true,paramType = "path")
    @RequestMapping(value = "/{id}",method=RequestMethod.GET)
    public Message<User> findOneUser(@PathVariable Long id){
    	if(id == 2l) {
    		throw new TokenValidationException("token异常");	
    	}
        return new Message<User>(ErrorCode.SUCCESS,userService.findUserById(id));
    }
    
    @ApiOperation("根据token获取用户信息")
    @RequestMapping(value = "/tokenUserInfo",method=RequestMethod.GET)
    public Message<Map<String,Object>> findOneUser(HttpServletRequest request){
        return new Message<Map<String,Object>>(ErrorCode.SUCCESS,JwtUtil.getUser(request));
    }


    @ApiOperation("获取所有用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "第几页", required = true,dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "一页几行", required = true,dataType = "int", paramType = "path")
    })
    @RequestMapping(value = "/{pageNo}/{pageSize}",method=RequestMethod.GET)
    public Message<PageInfo<User>> findAllUser(@PathVariable int pageNo,@PathVariable int pageSize){
        return userService.findUserList(null,pageNo,pageSize);
    }


}
