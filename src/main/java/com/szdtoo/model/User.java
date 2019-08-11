package com.szdtoo.model;

import java.io.Serializable;
import java.util.Date;
/**
 * <p>Title: User.java</p>  
 * <p>Description: 用户实体类</p>  
 * <p>Company: www.szdtoo.com</p>  
 * @author chaokang  
 * @date 2018年12月2日
 */
public class User implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String loginName;
	private String pwd;
	private String userName;
	private String tel;

	private Boolean gender;
	private Date birthday;
	private String email;
	private String addr;
	private Boolean isDel;
	
	private String roleName;
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Boolean getDel() {
		return isDel;
	}

	public void setDel(Boolean del) {
		isDel = del;
	}
}
