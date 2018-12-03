package com.szdtoo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.szdtoo.model.User;

/**
 * <p>Title: UserMapper</p>  
 * <p>Description: 用户Mapper</p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
public interface UserMapper {

    /**
     * <p>Title: findUserList</p>  
     * <p>Description: 按条件分页取得用户</p>  
     * @param searchContent
     * @return
     */
    List<User> findUserList(@Param("searchContent")String searchContent);


    /**
     * <p>Title: findUserById</p>  
     * <p>Description: 根据主键取得一个用户</p>  
     * @param id
     * @return
     */
    User findUserById(@Param("id")Long id);

    /**
     * <p>Title: findUsernameAndPassword</p>  
     * <p>Description: 通过用户名和密码查询该用户是否存在</p>  
     * @param username
     * @param password
     * @return
     */
    public User findUsernameAndPassword(@Param("username") String username,@Param("password")String password);


    /**
     * <p>Title: updatePasswordByUsername</p>  
     * <p>Description: 根据用户名修改密码</p>  
     * @param username
     * @param password
     * @return
     */
    public int updatePasswordByUsername(@Param("username") String username, @Param("password")String password);
}
