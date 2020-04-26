package com.kang.config.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;

/**
　 * <p>Title: EmailConfig</p> 
　 * <p>Description: 邮件配置</p> 
　 * @author CK 
　 * @date 2020年4月26日
 */
@Component
@Getter
@PropertySource(value = "classpath:config/email.properties")
public class EmailConfig {
	
	
	@Value("${email.username}")
	private String username;

	@Value("${email.password}")
	private String password;
	
	@Value("${email.host}")
	private String host;
	
	@Value("${email.port}")
	private String port;
	
	@Value("${email.timeout}")
	private String timeout;
	
}
