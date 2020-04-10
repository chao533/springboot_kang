package com.kang.service.impl;

import java.text.MessageFormat;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kang.common.constant.RabbitConstants;
import com.kang.service.EmailService;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;

/**
　 * <p>Title: RabbitmqConsumerServiceImpl</p> 
　 * <p>Description: 消息消费方</p> 
　 * @author CK 
　 * @date 2020年4月9日
 */
@Service
public class RabbitmqConsumerServiceImpl {

	
	private Logger log = LoggerFactory.getLogger(RabbitmqConsumerServiceImpl.class);
	
	@Autowired
	private EmailService emailService;

	/**
	 *<p>Title: processEmail</p> 
	 *<p>Description: 处理邮件队列信息</p> 
	 * @param jsonData
	 * @return
	 */
    @RabbitListener(queues = RabbitConstants.EMAIL_QUEUE)
    public String processEmail(String jsonData) {
    	log.info("jsonData:{}" , jsonData);
    	Map<?,?> dataMap = JSONUtil.toBean(jsonData, Map.class);
//    	Map<?,?> dataMap = JSONUtil.toBean(JSONUtil.parseObj(jsonData), Map.class);
        emailService.sendHtmlMail(MapUtil.getStr(dataMap, "to"), MapUtil.getStr(dataMap, "subject"), MapUtil.getStr(dataMap, "html"));
        return MessageFormat.format("成功发送邮件给{0}", dataMap);
    }

    /**
     * 以下三个方法都应该被监听到，都被执行
     *<p>Title: processMessage1</p> 
     *<p>Description: 处理短信队列消息</p> 
     * @param jsonData
     * @return
     */
    @RabbitListener(bindings ={@QueueBinding(value = @Queue(value = RabbitConstants.MESSAGE_QUEUE,durable = "true"),
            exchange =@Exchange(value = RabbitConstants.DIRECT_EXCHANGE,durable = "true"),key = RabbitConstants.MESSAGE_ROUTING_KEY)})
    public String processMessage1(String jsonData) {
    	log.info("Message1接受到数据为:{}" , jsonData);
        String response = MessageFormat.format("Message1收到{0}队列的消息:{1}", RabbitConstants.MESSAGE_QUEUE, jsonData);
        return response.toUpperCase();
    }
    
    @RabbitListener(bindings ={@QueueBinding(value = @Queue(value = RabbitConstants.MESSAGE_QUEUE,durable = "true"),
            exchange =@Exchange(value = RabbitConstants.DIRECT_EXCHANGE,durable = "true"),key = RabbitConstants.MESSAGE_ROUTING_KEY)})
    public String processMessage2(String jsonData) {
    	log.info("Message2接受到数据为:{}" , jsonData);
        String response = MessageFormat.format("Message2收到{0}队列的消息:{1}", RabbitConstants.MESSAGE_QUEUE, jsonData);
        return response.toUpperCase();
    }
    
    @RabbitListener(bindings ={@QueueBinding(value = @Queue(value = RabbitConstants.MESSAGE_QUEUE,durable = "true"),
            exchange =@Exchange(value = RabbitConstants.DIRECT_EXCHANGE,durable = "true"),key = RabbitConstants.MESSAGE_ROUTING_KEY)})
    public String processMessage3(String jsonData) {
    	log.info("Message3接受到数据为:{}" , jsonData);
        String response = MessageFormat.format("Message3收到{0}队列的消息:{1}", RabbitConstants.MESSAGE_QUEUE, jsonData);
        return response.toUpperCase();
    }
    
    
    /**
     *<p>Title: processText1</p> 
     *<p>Description: 处理文本队列消息</p> 
     * @param jsonData
     * @return
     */
//    @RabbitListener(bindings ={@QueueBinding(value = @Queue(value = RabbitConstants.TEXT_QUEUE,durable = "false"),
//            exchange =@Exchange(value = RabbitConstants.TOPIC_EXCHANGE,durable = "false"),key = RabbitConstants.TEXT_TOPIC_KEY)})
    @RabbitListener(queues = RabbitConstants.TEXT_QUEUE)
    public String processText1(String jsonData) {
    	log.info("Text1接受到数据为:{}" , jsonData);
        String response = MessageFormat.format("Text1收到{0}队列的消息:{1}", RabbitConstants.TEXT_QUEUE, jsonData);
        return response.toUpperCase();
    }
    
//    @RabbitListener(bindings ={@QueueBinding(value = @Queue(value = RabbitConstants.TEXT_QUEUE,durable = "false"),
//            exchange =@Exchange(value = RabbitConstants.TOPIC_EXCHANGE,durable = "false"),key = RabbitConstants.TEXT_TOPIC_KEY)})
    @RabbitListener(queues = RabbitConstants.TEXT_QUEUE)
    public String processText2(String jsonData) {
    	log.info("Text2接受到数据为:{}" , jsonData);
        String response = MessageFormat.format("Text2收到{0}队列的消息:{1}", RabbitConstants.TEXT_QUEUE, jsonData);
        return response.toUpperCase();
    }
}
