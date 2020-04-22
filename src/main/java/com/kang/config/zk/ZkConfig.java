package com.kang.config.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
　 * <p>Title: ZkConfig</p> 
　 * <p>Description: Zookeeper初始化配置</p> 
　 * @author CK 
　 * @date 2020年4月16日
 */
@Configuration
@PropertySource(value = "classpath:config/zk.properties")
public class ZkConfig {

	
	@Value("${spring.zk.host}")
    private String host;

	@Value("${spring.zk.port}")
    private String port;
	
	@Bean
	public InterProcessMutex getCuratorFramework() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3); 
		CuratorFramework client = CuratorFrameworkFactory.newClient(host + ":" + port, retryPolicy); 
		client.start();
		InterProcessMutex interProcessMutex = new InterProcessMutex(client, "/curator/lock"); 
		return interProcessMutex;
	}
}
