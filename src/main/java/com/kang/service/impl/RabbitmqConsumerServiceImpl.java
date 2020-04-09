package com.kang.service.impl;

import java.text.MessageFormat;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
     * 处理短信队列消息
     *
     * @param msg
     */
    @RabbitListener(queues = RabbitConstants.MESSAGE_QUEUE)
    public String processMessage(Object jsonData) {
        String response = MessageFormat.format("收到{0}队列的消息:{1}", RabbitConstants.MESSAGE_QUEUE, jsonData);
        return response.toUpperCase();
    }
}
