package com.kang.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import com.kang.mapper.redis.RedisMapper;

import redis.clients.jedis.JedisPoolConfig;

/**
 * <p>Title: RedisConfig</p>  
 * <p>Description: redis初始化配置</p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
@Component
@PropertySource(value = "classpath:config/redis.properties")//配置文件路径  在resource目录下
public class RedisConfig {
	
	@Value("${spring.redis.host}")
	private String hostName;
	
	@Value("${spring.redis.port}")
	private Integer port;
	
	@Value("${spring.redis.dbIndex}")
	private Integer dbIndex;
	

	@Value("${redis.maxIdle}")
	private Integer maxIdle;

	@Value("${redis.maxTotal}")
	private Integer maxTotal;

	@Value("${redis.maxWaitMillis}")
	private Integer maxWaitMillis;

	@Value("${redis.minEvictableIdleTimeMillis}")
	private Integer minEvictableIdleTimeMillis;

	@Value("${redis.numTestsPerEvictionRun}")
	private Integer numTestsPerEvictionRun;

	@Value("${redis.timeBetweenEvictionRunsMillis}")
	private long timeBetweenEvictionRunsMillis;

	@Value("${redis.testOnBorrow}")
	private boolean testOnBorrow;

	@Value("${redis.testWhileIdle}")
	private boolean testWhileIdle;

	@Value("${redis.cluster.max-redirects}")
	private Integer mmaxRedirectsac;

	@Value("${redis.password}")
	private String redispwd;

	/**
	 * <p>Title: jedisPoolConfig</p>  
	 * <p>Description: JedisPoolConfig 连接池</p>  
	 * @return
	 */
	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// 最大空闲数
		jedisPoolConfig.setMaxIdle(maxIdle);
		// 连接池的最大数据库连接数
		jedisPoolConfig.setMaxTotal(maxTotal);
		// 最大建立连接等待时间
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		// 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
		jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		// 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
		jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
		// 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
		jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		// 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
		jedisPoolConfig.setTestOnBorrow(testOnBorrow);
		// 在空闲时检查有效性, 默认false
		jedisPoolConfig.setTestWhileIdle(testWhileIdle);
		return jedisPoolConfig;
	}



	
	/**
	 * <p>Title: JedisConnectionFactory</p>  
	 * <p>Description: 配置工厂</p>  
	 * @param jedisPoolConfig
	 * @return
	 */
	@Bean
	public JedisConnectionFactory JedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
		JedisConnectionFactory JedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
		/*
		  *    设置密码，如果为空，则不设置
		  *  可在 redis.conf 文件中设置: requirepass 密码
		 */
		
//		if (redispwd == null || redispwd.length() == 0) {
//			JedisConnectionFactory.setPassword(redispwd);
//		}
		
		JedisConnectionFactory.setHostName(hostName);
		JedisConnectionFactory.setPort(port);
		JedisConnectionFactory.setDatabase(dbIndex);
		return JedisConnectionFactory;
	}

	/**
	 * <p>Title: functionDomainRedisTemplate</p>  
	 * <p>Description: 实例化 RedisTemplate 对象</p>  
	 * @param redisConnectionFactory
	 * @return
	 */
	@Bean
	public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
		return redisTemplate;
	}

	/**
	 * <p>Title: initDomainRedisTemplate</p>  
	 * <p>Description: 设置数据存入 redis 的序列化方式,并开启事务</p>  
	 * @param redisTemplate
	 * @param factory
	 */
	private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
		/*
		 * 设置 序列化器 .
		 * 如果不设置，那么在用实体类(未序列化)进行存储的时候，会提示错误: Failed to serialize object using DefaultSerializer;
		 */
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		// 开启事务
		redisTemplate.setEnableTransactionSupport(true);
		// 将连接工厂设置到模板类中
		redisTemplate.setConnectionFactory(factory);
	}

	/**
	 * <p>Title: redisUtil</p>  
	 * <p>Description: 注入封装RedisTemplate</p>  
	 * @param redisTemplate
	 * @return
	 */
	@Bean(name = "redisMapper")
	public RedisMapper redisMapper(RedisTemplate<String, Object> redisTemplate) {
		RedisMapper redisMapper = new RedisMapper();
		redisMapper.setRedisTemplate(redisTemplate);
		return redisMapper;
	}
}