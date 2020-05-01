package com.kang.common.constant;

/**
 * <p>Title: RabbitConstant</p>  
 * <p>Description: Rabbit常量</p>  
 * @author chaokang  
 * @date 2018年12月10日
 */
public class RabbitConstants {
	
	//------------------队列---------------------------------
    /**
     * 邮件队列
     */
    public static final String EMAIL_QUEUE = "kang.email";
    
    /**
     * 短信队列
     */
    public static final String MESSAGE_QUEUE = "kang.message.one";
    /**
     * 短信队列2
     */
    public static final String MESSAGE_QUEUE_2 = "kang.message.two";
    
    /**
     * 文本队列
     */
    public static final String TEXT_QUEUE = "kang.text.one";
    /**
     * 文本队列2
     */
    public static final String TEXT_QUEUE_2 = "kang.text.two";
    
    
	//------------------路由键Key---------------------------------
    /**
     * 邮件队列路由键（*表示一个词,#表示零个或多个词）
     */
    public static final String EMAIL_ROUTING_KEY = "kang.email.key";
    
    /**
     * 短信队列路由键(路由模式Key)
     */
    public static final String MESSAGE_ROUTING_KEY = "kang.message.routing.key";
    
    /**
     * 文本队列路由键(通配符模式Key)
     */
    public static final String TEXT_TOPIC_KEY = "kang.text.topic.key";
    
    
  //------------------交换机---------------------------------
    /**
     * 发布和订阅模式
     */
    public static final String FANOUT_EXCHANGE = "kang.fanout.exchange";
    
    /**
     * 路由模式
     */
    public static final String DIRECT_EXCHANGE = "kang.direct.exchange";
    
    /**
     * 通配符模式
     */
    public static final String TOPIC_EXCHANGE = "kang.topic.exchange";
}
