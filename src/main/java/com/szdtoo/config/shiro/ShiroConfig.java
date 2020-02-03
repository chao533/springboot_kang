package com.szdtoo.config.shiro;

import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
	@Bean
	UserRealm userRealm() {
		return new UserRealm();
	}

	@Bean
	DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(userRealm());
		return manager;
	}

	@Bean
	ShiroFilterChainDefinition shiroFilterChainDefinition() {
		DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
		definition.addPathDefinition("/login", "anon");
		definition.addPathDefinition("/swagger-ui.html", "anon");
		definition.addPathDefinition("/swagger-resources/**", "anon");
		definition.addPathDefinition("/images/**", "anon");
		definition.addPathDefinition("/webjars/**", "anon");
		definition.addPathDefinition("/v2/api-docs", "anon");
		definition.addPathDefinition("/configuration/ui", "anon");
		definition.addPathDefinition("/**", "authc");
		return definition;
	}
}
