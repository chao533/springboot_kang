/*
 * Copyright @ 2014 com.iflytek
 * oa 下午04:40:02
 * All right reserved.
 *
 */
package com.szdtoo.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * <p>Title: ValidateUtils</p>  
 * <p>Description: 判空</p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
public class ValidateUtils extends StringUtils {
	/**
	 * 判断id是否合法
	 * 
	 * @author: binyang
	 * @createTime: 2014-9-26 下午04:40:35
	 * @param id
	 * @return boolean
	 */
	public static boolean isIllegalId(Long id) {
		if (null == id || id <= 0) {
			return true;
		}
		return false;
	}
	
	/**
	* 判断Integer类型是否合法
	* @author: weixu
	* @createTime: 2016年4月12日 下午4:03:28
	* @param id
	* @return boolean
	*/
	public static boolean isIllegalInt(Integer id) {
		if (null == id || id <= 0) {
			return true;
		}
		return false;
	}

	public static boolean isIllegalLong(Long id) {
		if (null == id || id <= 0) {
			return true;
		}
		return false;
	}
	
	/**
	* 判断字符串是否合法
	* @author: weixu
	* @createTime: 2016年4月5日 下午2:20:52
	* @param str
	* @return boolean
	*/
	public static boolean isIllegalString(String str) {
		if(str == null || str.trim().equals("")){
			return true;
		}
		return false;
	}

	/**
	 * 判断数组是否为空或零
	 * 
	 * @author: binyang4
	 * @createTime: 2014-6-23 下午08:23:06
	 * @param objs
	 * @return boolean
	 */
	public static boolean isIllegalArray(Object[] objs) {

		if (objs == null || objs.length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断list列表是否合法
	 * 
	 * @author: binyang
	 * @createTime: 2014-9-26 下午04:41:32
	 * @param list
	 * @return boolean
	 */
	public static boolean isIllegalList(List<?> list) {

		if (null == list || list.size() <= 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断float类型是否合法
	 * @param id
	 * @return
	 */
	public static boolean isIllegalFloat(Float id) {
		if (null == id || id < 0) {
			return true;
		}
		return false;
	}
}
