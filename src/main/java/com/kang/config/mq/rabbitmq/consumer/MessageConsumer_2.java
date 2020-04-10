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
@RabbitListener(bindings ={@QueueBinding(value = @Queue(value = RabbitConstants.MESSAGE_QUEUE,durable = "true"),
exchange =@Exchange(value = RabbitConstants.DIRECT_EXCHANGE,durable = "true"),key = RabbitConstants.MESSAGE_ROUTING_KEY)})
public class MessageConsumer_2 {

	private Logger log = LoggerFactory.getLogger(MessageConsumer_2.class);
	
	@RabbitHandler
	public String processMessage1(String jsonData) {
    	log.info("Message2接受到数据为:{}" , jsonData);
        String response = MessageFormat.format("Message2收到{0}队列的消息:{1}", RabbitConstants.MESSAGE_QUEUE, jsonData);
        return response.toUpperCase();
    }
}
