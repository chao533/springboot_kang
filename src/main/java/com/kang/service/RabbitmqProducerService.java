package com.kang.service;

public interface RabbitmqProducerService {

	/**
	 *<p>Title: sendEmail</p> 
	 *<p>Description: 发送信息到email队列</p> 
	 * @param jsonObj
	 */
	public void sendEmail(Object jsonObj);
	
	/**
	 *<p>Title: sendMessage</p> 
	 *<p>Description:发送信息到message队列 </p> 
	 * @param jsonObj
	 */
	public void sendMessage(Object jsonObj);
}
