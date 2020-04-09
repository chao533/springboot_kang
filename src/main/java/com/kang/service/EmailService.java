package com.kang.service;

import com.kang.common.msg.Message;

/**
　 * <p>Title: EmailService</p> 
　 * <p>Description: 邮件服务接口</p> 
　 * @author CK 
　 * @date 2020年4月9日
 */
public interface EmailService {

	/**
	 *<p>Title: sendHtmlMail</p> 
	 *<p>Description: 发送含HTML格式的邮件</p> 
	 * @param to 接受人
	 * @param subject 主题
	 * @param html 发送内容
	 * @return
	 */
	public Message<String> sendHtmlMail(String to, String subject, String html);
	
	/**
	 *<p>Title: sendTextMail</p> 
	 *<p>Description: 发送文本格式的邮件</p> 
	 * @param email
	 * @param subject
	 * @param text
	 * @return
	 */
	public Message<String> sendTextMail(String email, String subject, String text);
}
