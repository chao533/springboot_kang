package com.szdtoo.model;

import java.io.Serializable;

public class RoleGroup implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 0启用 1 禁用
     */
    private Boolean close;

    /**
     * 是否删除 0：正常 -1：已删除
     */
    private Boolean del;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Boolean getClose() {
        return close;
    }

    public void setClose(Boolean close) {
        this.close = close;
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }
}