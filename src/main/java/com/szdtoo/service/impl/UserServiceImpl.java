package com.szdtoo.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szdtoo.common.constant.Constants;
import com.szdtoo.common.exception.TokenValidationException;
import com.szdtoo.common.msg.ErrorCode;
import com.szdtoo.common.msg.Message;
import com.szdtoo.common.utils.JwtUtil;
import com.szdtoo.common.utils.MD5Util;
import com.szdtoo.mapper.UserMapper;
import com.szdtoo.model.User;
import com.szdtoo.service.UserService;

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
    public Message<PageInfo<User>> findUserList(String searchContent, int pageNo, int pageSize) {
    	logger.info("开始：------------");
    	Page<User> page = PageHelper.startPage(pageNo, pageSize);
		Page<User> datas = (Page<User>) userMapper.findUserList(searchContent);
		datas.setTotal(page.getTotal());
		PageInfo<User> pageInfo = new PageInfo<User>(datas);
		logger.info("结束：------------");
		return new Message<PageInfo<User>>(ErrorCode.SUCCESS,pageInfo);
    }

    @Cacheable(value="user",key="#id",unless="#result == null")
    @Override
    public User findUserById(Long id) {
        return userMapper.findUserById(id);
    }

    @Override
    public Map<String,Object> login(HttpServletRequest request, String username, String password) {
        // 用户名或密码不能为空
    	if(StringUtils.isBlank(username)) {
    		throw new ServiceException("用户名不能为空");
    	}
    	if(StringUtils.isBlank(password)) {
    		throw new ServiceException("密码不能为空");
    	}
    	
    	Map<String,Object> result = new HashMap<String,Object>();
    	
        User user = userMapper.findUsernameAndPassword(username, MD5Util.MD5(password));
        if(user == null) {
           throw new ServiceException("用户名或密码错误");
        }
        result.put("user", user);
        
        String jwt = JwtUtil.generateToken(user);
        if(jwt == null) {
        	throw new TokenValidationException("认证失败");
        }
        result.put("jwt", jwt);
        return result;
    }

    @Override
    public Message<User> modifyPwd(HttpServletRequest request, String username, String oldPwd, String newPwd) {
        User user = userMapper.findUsernameAndPassword(username, MD5Util.MD5(oldPwd));
        if(user == null) {
        	throw new ServiceException("用户名或密码错误");
        }
        // 根据用户名修改密码
        userMapper.updatePasswordByUsername(user.getLoginName(), MD5Util.MD5(newPwd));
        user = userMapper.findUsernameAndPassword(username, MD5Util.MD5(newPwd));
        // 将用户信息再次替换并保存到Session对象中
        HttpSession session = request.getSession();
        return new Message<User>(ErrorCode.SUCCESS,user);
    }

    @Override
    public Message<String> loginOut(HttpServletRequest request) {
        //HttpSession session = request.getSession();
        return new Message<String>(ErrorCode.SUCCESS);
    }
}
