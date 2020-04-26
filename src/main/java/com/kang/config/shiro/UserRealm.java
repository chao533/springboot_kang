package com.kang.config.shiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kang.mapper.mybatis.UserMapper;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.log.StaticLog;

/**
　 * <p>Title: UserRealm</p> 
　 * <p>Description: 自定义Realm，用户认证和用户授权</p> 
　 * @author CK 
　 * @date 2020年4月26日
 */
@Component
public class UserRealm extends AuthorizingRealm {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		Map<String,Object> userMap = Convert.convert(new TypeReference<Map<String,Object>>() {}, principals.getPrimaryPrincipal());
		List<Map<String, Object>> permList = null;
		if(StringUtils.equals(MapUtil.getStr(userMap, "roleName"), "master")) {
			permList = userMapper.getPermissionsList2();
		} else {
			permList = userMapper.getPermissionsList(MapUtil.getLong(userMap, "id"));
		}
		List<String> permStrList = permList.stream().distinct().map(user -> MapUtil.getStr(user, "url")).filter(user -> StringUtils.isNotBlank(user)).collect(Collectors.toList());
		
		info.addStringPermissions(permStrList);
		return info; 
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		Object username = token.getPrincipal();
		Object pwd = token.getCredentials();
		Map<String,Object> params1 = MapUtil.builder(new HashMap<String,Object>()).put("loginName", username).build();
		List<Map<String, Object>> userList = userMapper.getUserList(params1);
		if(CollUtil.isEmpty(userList)) {
			throw new AuthenticationException("用户名不存在");
		}
		StaticLog.info("UserRealm加密密码:{}", ArrayUtil.join(pwd, ""));
		Map<String,Object> params2 = MapUtil.builder(new HashMap<String,Object>()).put("loginName", username).put("pwd", ArrayUtil.join(pwd, "")).build();
		userList = userMapper.getUserList(params2);
		if(CollUtil.isEmpty(userList)) {
			throw new AuthenticationException("密码输入错误");
		}
		return new SimpleAuthenticationInfo(userList.get(0), token.getCredentials(), getName());
	}
}
