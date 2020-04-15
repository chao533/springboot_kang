package com.kang.config.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * redisson分布式锁配置文件
 * @author
 * @Date 
 */
@Configuration
@Component
@PropertySource(value = "classpath:config/redis.properties")
public class RedissonConfig {
	@Value("${spring.redis.host}")
    private String host;

	@Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.dbIndex}")
    private int database;

    @Bean
    public RedissonClient getRedisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port).setDatabase(database);
        //一共有三种模式：单击、哨兵、集群，根据实际需要配置即可
        return Redisson.create(config);
    }
}

