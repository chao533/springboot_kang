package com.kang;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * <p>Title: Application</p>  
 * <p>Description: springboot启动类入口</p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
@SpringBootApplication
@MapperScan("com.kang.mapper")
public class KangApplication {

    public static void main(String[] args) {
        SpringApplication.run(KangApplication.class, args);
    }
}
