package com.kang.config.mq.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kang.common.constant.RabbitConstants;

/**
　 * <p>Title: ExchangeQueueConfig</p> 
　 * <p>Description: RabbitMQ 的几种模式</p> 
　 * @author CK 
　 * @date 2020年4月9日
 */
@Configuration
public class ExchangeQueueConfig {
    /**
     *<p>Title: emailQueue</p> 
     *<p>Description: 邮件队列</p> 
     * @return
     */
    @Bean
    public Queue emailQueue() {
        return new Queue(RabbitConstants.EMAIL_QUEUE, true);//true表示持久化该队列
    }

    /**
     *<p>Title: httpRequestQueue</p> 
     *<p>Description: 短信队列</p> 
     * @return
     */
    @Bean
    public Queue messageQueue() {
        return new Queue(RabbitConstants.MESSAGE_QUEUE, true);
    }
    
    /**
     *<p>Title: fanoutExchange</p> 
     *<p>Description: 发布和订阅模式</p> 
     * @return
     */
    @Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(RabbitConstants.FANOUT_EXCHANGE);
	}
    
    /**
     *<p>Title: directExchange</p> 
     *<p>Description: 路由模式</p> 
     * @return
     */
    @Bean
	public DirectExchange directExchange() {
		return new DirectExchange(RabbitConstants.DIRECT_EXCHANGE);
	}

    /**
     *<p>Title: topicExchange</p> 
     *<p>Description: 通配符模式</p> 
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitConstants.TOPIC_EXCHANGE);
    }

    /**
     *<p>Title: bindingMessage</p> 
     *<p>Description: 使用FanoutExchange交换机绑定短信队列</p> 
     * @return
     */
    @Bean
    public Binding fanoutExchangeBindingMessage() {
        return BindingBuilder.bind(messageQueue()).to(fanoutExchange());
    }
    
    /**
     *<p>Title: directExchangeBindingEmail</p> 
     *<p>Description: 使用DirectExchange交换机绑定邮件队列</p> 
     * @return
     */
    @Bean
    public Binding directExchangeBindingEmail() {
    	 return BindingBuilder.bind(emailQueue()).to(directExchange()).with(RabbitConstants.EMAIL_ROUTING_KEY);
    }
    
    /**
     *<p>Title: bindingEmail</p> 
     *<p>Description: 使用TopicExchange交换机绑定邮件队列</p> 
     * @return
     */
    @Bean
    public Binding topicExchangeBindingEmail() {
    	 return BindingBuilder.bind(emailQueue()).to(directExchange()).with(RabbitConstants.EMAIL_ROUTING_KEY);//*表示一个词,#表示零个或多个词
    }
    
}
