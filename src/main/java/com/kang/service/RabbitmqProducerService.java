package com.kang.service;

public interface RabbitmqProducerService {

	/**
	 *<p>Title: sendEmail</p> 
	 *<p>Description: 发送信息到email队列(使用简单模式)</p> 
	 * @param jsonObj
	 */
	public void sendEmail(Object jsonObj);
	
	/**
	 *<p>Title: sendMessage</p> 
	 *<p>Description:发送信息到message队列(使用路由模式) </p> 
	 * @param jsonObj
	 */
	public void sendMessage(Object jsonObj);
	
	/**
	 *<p>Title: sendText</p> 
	 *<p>Description: 发送信息到Text队列(使用通配符模式)</p> 
	 * @param jsonObj
	 */
	public void sendText(Object jsonObj);
	
}
