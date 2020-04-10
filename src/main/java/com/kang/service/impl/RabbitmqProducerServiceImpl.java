package com.kang.service.impl;

import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

import com.kang.common.constant.RabbitConstants;
import com.kang.service.RabbitmqProducerService;

/**
　 * <p>Title: RabbitmqProducerServiceImpl</p> 
　 * <p>Description: 消息生产方</p> 
　 * @author CK 
　 * @date 2020年4月9日
 */
@Service
public class RabbitmqProducerServiceImpl implements RabbitmqProducerService{

	private Logger log = LoggerFactory.getLogger(RabbitmqProducerServiceImpl.class);

	@Resource
	private RabbitTemplate rabbitTemplate;
	
	@Override
	public void sendEmail(Object jsonObj) {
		send(jsonObj, RabbitConstants.EMAIL_QUEUE);
	}

	@Override
	public void sendMessage(Object jsonObj) {
		send(jsonObj, RabbitConstants.DIRECT_EXCHANGE, RabbitConstants.MESSAGE_ROUTING_KEY);
	}
	
	private void send(Object jsonObj, String exchange, String routingKey) {
		CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
		log.info("开始发送消息:{}", jsonObj);
		// 转换并发送消息,且等待消息者返回响应消息。
		Object response = rabbitTemplate.convertSendAndReceive(exchange, routingKey, jsonObj, correlationId);
		if (response != null) {
			log.info("消费者响应:{}", response.toString());
		}
		log.info("{}消息发送结束", jsonObj);
	}
	
	private void send(Object jsonObj, String routingKey) {
		CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
		Object response = rabbitTemplate.convertSendAndReceive(routingKey, jsonObj, correlationId);
		if (response != null) {
			log.info("消费者响应:{}", response.toString());
		}
		log.info("{}消息发送结束", jsonObj);
	}

	@Override
	public void sendText(Object jsonObj) {
		send(jsonObj, RabbitConstants.TOPIC_EXCHANGE, RabbitConstants.TEXT_TOPIC_KEY);
	}
}
