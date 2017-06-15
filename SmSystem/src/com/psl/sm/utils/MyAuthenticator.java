package com.psl.sm.utils;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
public class MyAuthenticator extends Authenticator {
	// 登录邮箱用用户名
	private String strUserName;     

	// 登录邮箱密码
	private String strPassword; 

	public MyAuthenticator(String strUserName, String strPassword) {
		this.strUserName = strUserName;
		this.strPassword = strPassword;
	}
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(strUserName, strPassword);
	}
}
