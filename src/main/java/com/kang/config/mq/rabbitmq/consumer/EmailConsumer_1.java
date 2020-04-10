package com.kang.config.mq.rabbitmq.consumer;

import java.text.MessageFormat;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kang.common.constant.RabbitConstants;
import com.kang.service.EmailService;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;

@Component
@RabbitListener(queues = RabbitConstants.EMAIL_QUEUE)
public class EmailConsumer_1 {

	private Logger log = LoggerFactory.getLogger(EmailConsumer_1.class);
	
	@Autowired
	private EmailService emailService;
	
	@RabbitHandler
    public String processEmail(String jsonData) {
    	log.info("jsonData:{}" , jsonData);
    	Map<?,?> dataMap = JSONUtil.toBean(jsonData, Map.class);
//    	Map<?,?> dataMap = JSONUtil.toBean(JSONUtil.parseObj(jsonData), Map.class);
        emailService.sendHtmlMail(MapUtil.getStr(dataMap, "to"), MapUtil.getStr(dataMap, "subject"), MapUtil.getStr(dataMap, "html"));
        return MessageFormat.format("成功发送邮件给{0}", dataMap);
    }
}
