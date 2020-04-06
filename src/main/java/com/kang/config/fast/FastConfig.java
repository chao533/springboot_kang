package com.kang.config.fast;

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
	
}
