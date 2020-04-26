package com.kang.model.mybatis;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

/**
　 * <p>Title: User</p> 
　 * <p>Description: 用户实体类</p> 
　 * @author CK 
　 * @date 2020年4月20日
 */
@Data
@Entity
@Table(name="tb_user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String loginName;

	private String pwd;
	
	
	private String userName;

	private String tel;
	
	private String icon;

	private Boolean gender;

	private Date birthday;

	private String email;

	private String addr;

	private Date createTime;

	private Boolean isDel;
	
	private Long roleId;
	
	@Transient
	private String roleName;

}
