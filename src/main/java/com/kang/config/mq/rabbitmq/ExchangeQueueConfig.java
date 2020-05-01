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
     *<p>Title: messageQueue</p> 
     *<p>Description: 短信队列</p> 
     * @return
     */
    @Bean
    public Queue messageQueue() {
        return new Queue(RabbitConstants.MESSAGE_QUEUE, true);
    }
    /**
     *<p>Title: messageQueue_2</p> 
     *<p>Description: 短信队列2</p> 
     * @return
     */
    @Bean
    public Queue messageQueue_2() {
        return new Queue(RabbitConstants.MESSAGE_QUEUE_2, true);
    }
    /**
     *<p>Title: textQueue</p> 
     *<p>Description: 文本队列</p> 
     * @return
     */
    @Bean
    public Queue textQueue() {
        return new Queue(RabbitConstants.TEXT_QUEUE, true);
    }
    /**
     *<p>Title: textQueue</p> 
     *<p>Description: 文本队列2</p> 
     * @return
     */
    @Bean
    public Queue textQueue_2() {
        return new Queue(RabbitConstants.TEXT_QUEUE_2, true);
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
     *<p>Title: directExchangeBindingEmail</p> 
     *<p>Description: 使用fanoutExchange交换机绑定邮件队列</p> 
     * @return
     */
    @Bean
    public Binding fanoutExchangeBindingEmail() {
    	 return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
    }
    
    /**
     *<p>Title: bindingMessage</p> 
     *<p>Description: 使用directExchange交换机绑定短信队列</p> 
     * @return
     */
    @Bean
    public Binding directExchangeBindingMessage() {
    	 return BindingBuilder.bind(messageQueue()).to(directExchange()).with(RabbitConstants.MESSAGE_ROUTING_KEY);
    }
    
    @Bean
    public Binding directExchangeBindingMessage_2() {
    	 return BindingBuilder.bind(messageQueue_2()).to(directExchange()).with(RabbitConstants.MESSAGE_ROUTING_KEY);
    }
    
    /**
     *<p>Title: bindingText</p> 
     *<p>Description: 使用topicExchange交换机绑定邮件队列</p> 
     * @return
     */
    @Bean
    public Binding topicExchangeBindingText() {
    	 return BindingBuilder.bind(textQueue()).to(topicExchange()).with(RabbitConstants.TEXT_TOPIC_KEY);//*表示一个词,#表示零个或多个词
    }
    /**
     *<p>Title: bindingText_2</p> 
     *<p>Description: 使用topicExchange交换机绑定邮件队列</p> 
     * @return
     */
    @Bean
    public Binding topicExchangeBindingText_2() {
    	 return BindingBuilder.bind(textQueue_2()).to(topicExchange()).with(RabbitConstants.TEXT_TOPIC_KEY);//*表示一个词,#表示零个或多个词
    }
    
}
