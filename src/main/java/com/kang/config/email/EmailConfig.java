package com.kang.config.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;

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
	private Integer port;
	
	@Value("${email.timeout}")
	private String timeout;
	
}
