package com.szdtoo.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szdtoo.common.exception.TokenValidationException;
import com.szdtoo.common.msg.ErrorCode;
import com.szdtoo.common.msg.Message;
import com.szdtoo.common.utils.JwtUtil;
import com.szdtoo.common.utils.MD5Util;
import com.szdtoo.mapper.UserMapper;
import com.szdtoo.model.User;
import com.szdtoo.model.param.ModifyPwdParam;
import com.szdtoo.service.UserService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.log.StaticLog;

/**
 * <p>Title: UserServiceImpl</p>  
 * <p>Description: 用户管理实现类</p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Message<?> findUserList(Map<String,Object> params) {
    	logger.info("开始：------------");
    	Page<User> page = PageHelper.startPage(MapUtil.getInt(params, "pageNo"), MapUtil.getInt(params, "pageSize"));
		Page<Map<String,Object>> datas = (Page<Map<String,Object>>) userMapper.getUserList(params);
		datas.setTotal(page.getTotal());
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(datas);
		logger.info("结束：------------");
		return new Message<>(ErrorCode.SUCCESS,pageInfo);
    }

    //@Cacheable(value="user",key="#id",unless="#result == null")
    @Override
    public Message<?> findUserById(Long id) {
    	Assert.notNull(id,"参数异常");
    	Map<String,Object> params = CollUtil.newHashMap();
    	params.put("id", id);
        List<Map<String, Object>> userList = userMapper.getUserList(params);
        if(userList != null && userList.size() == 1) {
        	return new Message<>(ErrorCode.SUCCESS,userList.get(0));
        }
        return new Message<>(ErrorCode.ERROR);
    }

    @Override
    public Message<?> login(Map<String,Object> params) {
    	Map<String,Object> result = CollUtil.newHashMap();
    	
    	String loginName = MapUtil.getStr(params, "loginName");
    	String pwd = MapUtil.getStr(params, "pwd");
    	Assert.notBlank(loginName, "用户名不能为空");
    	Assert.notBlank(pwd, "密码不能为空");
    	StaticLog.info("日志{},{}", "1234","gdf");
    	
    	params.put("pwd", SecureUtil.md5(pwd));
        List<Map<String, Object>> userList = userMapper.getUserList(params);
        if(userList == null || userList.size() != 1) {
           throw new ServiceException("用户名或密码错误");
        }
        result.put("userInfo", userList.get(0));
        
        String jwt = JwtUtil.generateToken(userList.get(0));
        if(jwt == null) {
        	throw new TokenValidationException("认证失败");
        }
        result.put("jwt", jwt);
        return new Message<>(ErrorCode.SUCCESS,result);
    }

    @Override
    public Message<?> modifyPwd(ModifyPwdParam params) {
    	Assert.notBlank(params.getUsername(), "用户名不能为空");
    	Assert.notBlank(params.getOldPwd(), "原密码不能为空");
    	Assert.notBlank(params.getNewPwd(), "新密码不能为空");
    	Map<String, Object> userParams = CollUtil.newHashMap();
    	userParams.put("loginName", params.getUsername());
    	userParams.put("pwd", MD5Util.MD5(params.getOldPwd()));
        List<Map<String, Object>> userList = userMapper.getUserList(userParams);
        if(userList == null || userList.size() != 1) {
        	throw new ServiceException("用户名或密码错误");
        }
        // 根据用户名修改密码（推荐使用id作为修改的唯一标识）
        if(userMapper.updatePasswordByUsername(params.getUsername(), MD5Util.MD5(params.getNewPwd())) < 0) {
        	throw new ServiceException("修改密码失败");
        }
        return new Message<>(ErrorCode.SUCCESS);
    }

    @Override
    public Message<String> loginOut(HttpServletRequest request) {
        return new Message<String>(ErrorCode.SUCCESS);
    }
}
