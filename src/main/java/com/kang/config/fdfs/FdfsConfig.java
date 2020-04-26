package com.kang.config.fdfs;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.stereotype.Component;

import com.github.tobato.fastdfs.FdfsClientConfig;

import lombok.Getter;
import lombok.Setter;

/**
　 * <p>Title: FdfsConfig</p> 
　 * <p>Description: FastDFS配置</p> 
　 * @author CK 
　 * @date 2020年4月26日
 */
@Setter
@Getter
@Component
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING) //解决jmx重复注册bean的问题
@ConfigurationProperties(prefix="fdfs")
public class FdfsConfig {
	
	private String webserver;
	
	/**
	 * <p>Title: getFullPush</p>
	 * <p>Description: 获取完整地址</p>
	 * @param @param path
	 * @param @return
	 */
	public String getFullPush(String path) {
		return StringUtils.isBlank(path) ? "" : webserver + path;
	}
	
	/**
	 * <p>Title: getSuffixPath</p>
	 * <p>Description: 获取后缀地址</p>
	 * @param @param path
	 * @param @return
	 */
	public String getSuffixPath(String path) {
		return StringUtils.isBlank(path) ? null : path.substring(path.lastIndexOf("group"));
	}
	
}
