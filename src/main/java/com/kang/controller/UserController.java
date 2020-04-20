package com.kang.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kang.common.msg.ErrorCode;
import com.kang.common.msg.Message;
import com.kang.common.utils.JwtUtil;
import com.kang.model.mybatis.User;
import com.kang.model.param.UserListParam;
import com.kang.service.UserService;

import cn.hutool.core.bean.BeanUtil;

/**
　 * <p>Title: UserController</p> 
　 * <p>Description: 用户操作</p> 
　 * @author CK 
　 * @date 2020年4月6日
 */
@RestController
@RequestMapping(value="/user")
public class UserController {
    protected Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    
    /**
     *<p>Title: findUserById</p> 
     *<p>Description: 获取用户信息</p> 
     * @param id
     * @return
     */
    //@RequiresPermissions(value= {"user:get","user:add"})
    @RequestMapping(value = "/{id}",method=RequestMethod.GET)
    public Message<?> findUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }
    
    /**
     *<p>Title: userInfo</p> 
     *<p>Description: 根据token获取用户信息</p> 
     * @return
     */
    @RequiresPermissions("user:get")
    @RequestMapping(value = "/userInfo",method=RequestMethod.GET)
    public Message<Map<String,Object>> userInfo(){
        return new Message<>(ErrorCode.SUCCESS,JwtUtil.getUser());
    }

    /**
     *<p>Title: userList</p> 
     *<p>Description: 获取所有用户信息</p> 
     * @param params
     * @return
     */
    @RequiresPermissions(value= {"user:get","user:add"}) // 同时满足 
    @RequestMapping(value = "/userList",method=RequestMethod.GET)
    public Message<?> userList(UserListParam params){
        return userService.findUserList(BeanUtil.beanToMap(params));
    }
    
    /**
     *<p>Title: insertUser</p> 
     *<p>Description: 新增用户信息</p> 
     * @param user
     * @return
     */
    @RequestMapping(value = "/insertUser",method=RequestMethod.POST)
    public Message<?> insertUser(@RequestBody User user){
        return userService.insertUser(user);
    }


}
