package com.kang.service.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.kang.common.msg.ErrorCode;
import com.kang.common.msg.Message;
import com.kang.config.email.EmailConfig;
import com.kang.service.EmailService;

import lombok.SneakyThrows;

@Service
public class EmailServiceImpl implements EmailService{
	
	private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	@Autowired
	private EmailConfig emailConfig;

	@SneakyThrows(value = MessagingException.class)
	@Override
	public Message<String> sendHtmlMail(String to, String subject, String html) {
		JavaMailSenderImpl mailSender = createMailSender();
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		// 设置utf-8或GBK编码，否则邮件会有乱码
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		messageHelper.setFrom(emailConfig.getUsername());
		//抄送给地址
		//messageHelper.setCc(new InternetAddress("test@163.com"));
		messageHelper.setTo(to);
		messageHelper.setSubject(subject);
		messageHelper.setText(html, true);
		logger.info("---now send mail---");
		mailSender.send(mimeMessage);
		return new Message<String>(ErrorCode.SUCCESS);
	}

	@SneakyThrows(value = Exception.class)
	@Override
	public Message<String> sendTextMail(String email, String subject, String text) {

		// 创建一个程序与邮件服务器会话对象 Session
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "SMTP");
		props.setProperty("mail.smtp.host", emailConfig.getHost());
		props.setProperty("mail.smtp.port", emailConfig.getPort());
		// 指定验证为true
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.timeout", emailConfig.getTimeout());
		// 验证账号及密码，密码需要是第三方授权码

		Authenticator auth = new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailConfig.getUsername(), emailConfig.getPassword());
			}
		};
		Session session = Session.getDefaultInstance(props, auth);

		// 创建一个Message，它相当于是邮件内容
		javax.mail.Message message = new MimeMessage(session);
		// 设置发送者
		message.setFrom(new InternetAddress(emailConfig.getUsername()));
		// 设置发送方式与接收者
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email));
		// 设置主题
		message.setSubject(subject);
		// 设置内容
		message.setText(text);
		message.saveChanges();
		// 3.创建 Transport用于将邮件发送
		Transport.send(message);
		return new Message<String>(ErrorCode.SUCCESS);
	}
	
	
	private JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(emailConfig.getHost());
        sender.setPort(Integer.parseInt(emailConfig.getPort()));
        sender.setUsername(emailConfig.getUsername());
        sender.setPassword(emailConfig.getPassword());
        sender.setDefaultEncoding("utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", emailConfig.getTimeout());
        p.setProperty("mail.smtp.auth", "true");
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        sender.setJavaMailProperties(p);
        logger.info("---create sender---");
        return sender;
    }

}
