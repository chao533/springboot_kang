package com.kang;


import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * <p>Title: Application</p>  
 * <p>Description: springboot启动类入口</p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
@Import(FdfsClientConfig.class)
@SpringBootApplication
@MapperScan("com.kang.mapper")
@EnableCaching
//@EnableAsync
public class KangApplication {

    public static void main(String[] args) {
        SpringApplication.run(KangApplication.class, args);
    }
}