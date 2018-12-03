package com.szdtoo.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.szdtoo.common.msg.Message;
import com.szdtoo.model.User;

/**
 * <p>Title: UserService</p>  
 * <p>Description: 用户管理</p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
public interface UserService {

    /**
     * <p>Title: findUserList</p>  
     * <p>Description: 分页查询</p>  
     * @param searchContent
     * @param pageNo
     * @param pageSize
     * @return
     */
    Message<PageInfo<User>> findUserList(String searchContent,int pageNo, int pageSize);

    /**
     * <p>Title: findUserById</p>  
     * <p>Description: 用户信息</p>  
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * <p>Title: login</p>  
     * <p>Description: 登录功能</p>  
     * @param request
     * @param username
     * @param password
     * @return
     */
    public Map<String,Object> login(HttpServletRequest request, String username, String password);


    /**
     * <p>Title: modifyPwd</p>  
     * <p>Description: 修改密码功能</p>  
     * @param request
     * @param username
     * @param oldPwd
     * @param newPwd
     * @return
     */
    public Message<User> modifyPwd(HttpServletRequest request,String username,String oldPwd,String newPwd);


    /**
     * <p>Title: loginOut</p>  
     * <p>Description: 退出登录</p>  
     * @param request
     * @return
     */
    public Message<String> loginOut(HttpServletRequest request);

}
