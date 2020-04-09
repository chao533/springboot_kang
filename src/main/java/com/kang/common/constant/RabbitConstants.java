package com.kang.common.constant;

/**
 * <p>Title: RabbitConstant</p>  
 * <p>Description: Rabbit常量</p>  
 * @author chaokang  
 * @date 2018年12月10日
 */
public class RabbitConstants {
    /**
     * 邮件队列
     */
    public static final String EMAIL_QUEUE = "kang.email";
    
    /**
     * 短信队列
     */
    public static final String MESSAGE_QUEUE = "kang.message";
    
    /**
     * 邮件队列路由键（*表示一个词,#表示零个或多个词）
     */
    public static final String EMAIL_ROUTING_KEY = "kang.email.key";
    
    /**
     * 短信队列路由键
     */
    public static final String MESSAGE_ROUTING_KEY = "kang.message.key";
    
    /**
     * 通配符模式
     */
    public static final String TOPIC_EXCHANGE = "kang.topic.exchange";
    
    /**
     * 发布和订阅模式
     */
    public static final String FANOUT_EXCHANGE = "kang.fanout.exchange";
    
    /**
     * 发布和订阅模式
     */
    public static final String DIRECT_EXCHANGE = "kang.direct.exchange";
}
