package com.psl.sm.bean;

import java.io.Serializable;

public class Userinfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;	//用户名
	private String pwd;		//密码
	private String email;	//邮箱，主要用于修改密码
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Userinfo [id=" + id + ", name=" + name + ", pwd=" + pwd + ", email=" + email + "]";
	}
	
	
}
