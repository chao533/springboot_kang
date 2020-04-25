package com.kang.config.fast;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix="fdfs")
public class FastConfig {
	
	private String webserver;
	
	/**
	 * <p>Title: getFullPush</p>
	 * <p>Description: 获取完整地址</p>
	 * @param @param path
	 * @param @return
	 */
	public String getFullPush(String path) {
		if(StringUtils.isBlank(path)) {
			return "";
		}
		return webserver + path;
	}
	
	/**
	 * <p>Title: getSuffixPath</p>
	 * <p>Description: 获取后缀地址</p>
	 * @param @param path
	 * @param @return
	 */
	public String getSuffixPath(String path) {
		if(StringUtils.isBlank(path)) {
			return null;
		}
		return path.substring(path.lastIndexOf("/group1"));
	}
	
}
