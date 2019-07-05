package com.szdtoo.config.jwt;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.szdtoo.common.filter.JwtAuthenticationFilter;


/**
 * <p>Title: SecurityConfigurer</p>  
 * <p>Description: 权限验证</p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	
        httpSecurity.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                // 请求放行,（没有配置放行，则表示无权限访问该接口）
                .antMatchers("/login").permitAll()
                .antMatchers("/upload").permitAll()
                .antMatchers("/scale").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/test/**").permitAll()
                // swagger start
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/configuration/security").permitAll()
                // swagger end
                // 所有请求都需要认证
                .anyRequest().authenticated();
                httpSecurity.headers().cacheControl();
                httpSecurity.csrf().disable();
                httpSecurity.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

}