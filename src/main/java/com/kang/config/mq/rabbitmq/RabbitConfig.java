package com.kang.config.mq.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

/**
　 * <p>Title: RabbitConfig</p> 
　 * <p>Description: rabbitmq配置</p> 
　 * @author CK 
　 * @date 2020年4月9日
 */
@Configuration
@PropertySource(value = "classpath:config/rabbitmq.properties")
public class RabbitConfig {
	
	private Logger log = LoggerFactory.getLogger(RabbitConfig.class);
	
	@Value("${rabbitmq.host}")
    private String host;
	
	@Value("${rabbitmq.port}")
    private String port;
	
	@Value("${rabbitmq.username}")
    private String username;
	
	@Value("${rabbitmq.password}")
    private String password;
	
	@Value("${rabbitmq.virtual.host}")
    private String virtualHost;
	
	@Value("${rabbitmq.connection.timeout}")
    private Integer connectionTimeout;
	
	@Value("${rabbitmq.publisher.confirms}")
    private boolean publisherConfirms;
	
	@Value("${rabbitmq.publisher.returns}")
    private boolean publisherReturns;
	
	@Value("${rabbitmq.cache.channel.size}")
	private Integer sessionCacheSize;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(host + ":" + port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setConnectionTimeout(connectionTimeout);
        //如果要进行消息回调,则这里必须要设置为true
        connectionFactory.setPublisherConfirms(publisherConfirms);
        connectionFactory.setPublisherReturns(publisherReturns);
        connectionFactory.setChannelCacheSize(sessionCacheSize);
        return connectionFactory;
    }

    /**
     * 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置
     * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调   即消息发送到exchange  ack
     * ReturnCallback接口用于实现消息发送到RabbitMQ 交换器，但无相应队列与交换器绑定时的回调  即消息发送不到任何一个队列中  ack
     */
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setEncoding("UTF-8");
        rabbitTemplate.setMandatory(true);
        //相应交换机接收后异步回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("交换机接收信息成功,id:{}", correlationData.getId());
            } else {
                log.error("交换机接收信息失败:{}", cause);
            }
        });
        //无相应队列与交换机绑定异步回调
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String msg = new String(message.getBody());
            log.error("消息:{} 发送失败, 应答码:{} 原因:{} 交换机:{} 路由键:{}", msg, replyCode, replyText, exchange, routingKey);
        });
        return rabbitTemplate;
    }

}
