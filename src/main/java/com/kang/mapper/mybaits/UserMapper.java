package com.kang.mapper.mybaits;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * <p>Title: UserMapper</p>  
 * <p>Description: 用户Mapper</p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
public interface UserMapper {

    /**
     * <p>Title: getUserList</p>
     * <p>Description: 用户列表</p>
     * @param @param params
     * @param @return
     */
    List<Map<String,Object>> getUserList(Map<String,Object> params);


    /**
     * <p>Title: updatePasswordByUsername</p>  
     * <p>Description: 根据用户名修改密码</p>  
     * @param username
     * @param password
     * @return
     */
    public int updatePasswordByUsername(@Param("username") String username, @Param("password")String password);
    
    List<Map<String,Object>> getPermissionsList(@Param("userId") Long userId);
    
    List<Map<String,Object>> getPermissionsList2();
}