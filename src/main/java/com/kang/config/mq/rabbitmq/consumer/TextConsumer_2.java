package com.kang.config.mq.rabbitmq.consumer;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.kang.common.constant.RabbitConstants;

@Component
@RabbitListener(queues = RabbitConstants.TEXT_QUEUE_2)
public class TextConsumer_2 {
	
	private Logger log = LoggerFactory.getLogger(TextConsumer_2.class);
	
	 @RabbitHandler
	 public String process(String jsonData) {
		 log.info("Text2接受到数据为:{}" , jsonData);
		 String response = MessageFormat.format("Text2收到{0}队列的消息:{1}", RabbitConstants.TEXT_QUEUE_2, jsonData);
		 return response.toUpperCase();
	 }

	
}
