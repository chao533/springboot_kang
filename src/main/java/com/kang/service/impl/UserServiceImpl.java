package com.kang.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kang.common.exception.ServiceException;
import com.kang.common.exception.TokenValidationException;
import com.kang.common.msg.ErrorCode;
import com.kang.common.msg.Message;
import com.kang.common.utils.JwtUtil;
import com.kang.mapper.MongoUserRepository;
import com.kang.mapper.UserMapper;
import com.kang.model.User;
import com.kang.model.param.ModifyPwdParam;
import com.kang.service.UserService;

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
    @Autowired
    private MongoUserRepository mongoUserRepository;
    
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
    	String loginName = MapUtil.getStr(params, "loginName");
    	String pwd = MapUtil.getStr(params, "pwd");
    	Assert.notBlank(loginName, "用户名不能为空");
    	Assert.notBlank(pwd, "密码不能为空");
    	
    	Subject subject = SecurityUtils.getSubject();
    	String md5Pwd = SecureUtil.md5(pwd);
    	StaticLog.info("加密密码:{}", md5Pwd);
		subject.login(new UsernamePasswordToken(loginName, md5Pwd));
    	Map<String,Object> result = CollUtil.newHashMap();
    	StaticLog.info("用户名:{},密码:{}", loginName,pwd);
    	
    	params.put("pwd", md5Pwd);
        List<Map<String, Object>> userList = userMapper.getUserList(params);
        Map<String, Object> userMap = userList.get(0);
        userMap.remove("pwd");
        result.put("userInfo", userMap);
        
        String jwt = JwtUtil.generateToken(userMap);
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
    	
    	Map<String,Object> userParams = MapUtil.builder(new HashMap<String,Object>())
    			.put("loginName", params.getUsername()).put("pwd", SecureUtil.md5(params.getOldPwd())).build();
        List<Map<String, Object>> userList = userMapper.getUserList(userParams);
        if(userList == null || userList.size() != 1) {
        	throw new ServiceException("用户名或密码错误");
        }
        // 根据用户名修改密码（推荐使用id作为修改的唯一标识）
        if(userMapper.updatePasswordByUsername(params.getUsername(), SecureUtil.md5(params.getNewPwd())) < 0) {
        	throw new ServiceException("修改密码失败");
        }
        return new Message<>(ErrorCode.SUCCESS);
    }

    @Override
    public Message<String> loginOut(HttpServletRequest request) {
    	Subject subject = SecurityUtils.getSubject();
    	subject.logout();
        return new Message<String>(ErrorCode.SUCCESS);
    }

	@Override
	public Message<?> getMongoUserList() {
		return new Message<>(ErrorCode.SUCCESS,mongoUserRepository.findAll());
	}
}