package com.szdtoo.config.fast;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ConfigurationProperties(prefix="fdfs")
public class FastConfig {
	
	private String webserver;
	
	private String trackerList;

}
