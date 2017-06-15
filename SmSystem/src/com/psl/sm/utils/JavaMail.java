package com.psl.sm.utils;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class JavaMail {
	// SMTP服务齐地址
	public static final String STR_SMTP_HOST = "smtp.ym.163.com";
	// 端口号
	public static final String STR_SMTP_PORT = "25";
	// 类型：SMTP
	public static final String STR_SMTP_PROTOCOL = "SMTP";
	// SMTP检查校验
	public static final String STR_SMTP_AUTH = "true";
	// 校验用户名
	public static final String STR_AUTH_USER = "regist@yiixing.com";
	// 校验密码
	public static final String STR_AUTH_PASSWORD = "123456";
	// 发信方邮箱地址
	public static final String STR_FROM_ADDRESS = "regist@yiixing.com";	
	public  void sendMessage(String strToEmailAddr,String strEmailTitle, String strEmailContent) {
		// 发信成功与否，默认为false
		@SuppressWarnings("unused")
		boolean isSuccess = false;
		// 检查传入参数非空性
		if (strToEmailAddr != null && strEmailTitle != null
				&& strEmailContent != null) {
			// 生成发送的消息
			Message messageObject = buildMSG();
			try {
				// 形成发送的mail地址
				InternetAddress fromAddress = new InternetAddress(
						STR_FROM_ADDRESS);
				messageObject.setFrom(fromAddress);
				InternetAddress toAddress = new InternetAddress(strToEmailAddr);
				// 加入发送消息的目的地址addRecipients()两个重载函数
				messageObject.addRecipient(Message.RecipientType.TO, toAddress);
				// 设置消息题
				messageObject.setSubject(strEmailTitle);
				// 设置消息主题
				messageObject.setText(strEmailContent);
				// 保存
				messageObject.saveChanges();
				// 发送mail
				Transport.send(messageObject, messageObject
						.getRecipients(Message.RecipientType.TO));
				isSuccess = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static Message buildMSG() {
		// 构造邮件Properties
		Properties emailProperty = new Properties();
		// 设置SMTP服务器参数
		emailProperty.put("mail.smtp.host", STR_SMTP_HOST);
		emailProperty.put("mail.smtp.port", STR_SMTP_PORT);
		emailProperty.put("mail.smtp.protocol", STR_SMTP_PROTOCOL);
		emailProperty.put("mail.smtp.auth", true);
		MyAuthenticator myauthBean =new MyAuthenticator(STR_AUTH_USER,STR_AUTH_PASSWORD);
		// 获得发送邮件的会话
		Session mailSession = Session.getDefaultInstance(emailProperty,myauthBean);
		// 生成发送的消息
		Message messageObject = new MimeMessage(mailSession);
		return messageObject;
	}
}





