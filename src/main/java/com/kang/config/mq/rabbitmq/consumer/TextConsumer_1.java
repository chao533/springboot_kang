package com.kang.config.mq.rabbitmq.consumer;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.kang.common.constant.RabbitConstants;

@Component
@RabbitListener(queues = RabbitConstants.TEXT_QUEUE)
public class TextConsumer_1 {
	
	private Logger log = LoggerFactory.getLogger(TextConsumer_1.class);
	
	 @RabbitHandler
	 public String process(String jsonData) {
		 log.info("Text1接受到数据为:{}" , jsonData);
		 String response = MessageFormat.format("Text1收到{0}队列的消息:{1}", RabbitConstants.TEXT_QUEUE, jsonData);
		 return response.toUpperCase();
	 }

	
}
