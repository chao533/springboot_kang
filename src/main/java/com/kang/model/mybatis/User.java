package com.kang.model.mybatis;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * Title: User.java
 * </p>
 * <p>
 * Description: 用户实体类
 * </p>
 * <p>
 * Company: www.szdtoo.com
 * </p>
 * 
 * @author chaokang
 * @date 2018年12月2日
 */
@Data
public class User implements Serializable {

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

}
