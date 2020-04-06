package com.szdtoo.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.szdtoo.common.msg.Message;
import com.szdtoo.model.param.ModifyPwdParam;

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
    Message<?> findUserList(Map<String,Object> params);

    /**
     * <p>Title: findUserById</p>  
     * <p>Description: 用户信息</p>  
     * @param id
     * @return
     */
    Message<?> findUserById(Long id);

    /**
     * <p>Title: login</p>
     * <p>Description: 登录</p>
     * @param @param params
     * @param @return
     */
    Message<?> login(Map<String,Object> params);


    /**
     * <p>Title: modifyPwd</p>
     * <p>Description: 修改密码</p>
     * @param @param params
     * @param @return
     */
    public Message<?> modifyPwd(ModifyPwdParam params);


    /**
     * <p>Title: loginOut</p>  
     * <p>Description: 退出登录</p>  
     * @param request
     * @return
     */
    public Message<String> loginOut(HttpServletRequest request);
    
    /**
     * <p>Title: getMongoUserList</p>  
     * <p>Description: 获取MongoUser对象</p>  
     * @param request
     * @return
     */
    public Message<?> getMongoUserList();

}
