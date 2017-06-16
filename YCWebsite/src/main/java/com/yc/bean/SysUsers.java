package com.yc.bean;

import java.io.Serializable;

public class SysUsers implements Serializable {

	private static final long serialVersionUID = 1580042080015532771L;
	private Integer u_id;
	private String u_name;
	private String u_password;
	public Integer getU_id() {
		return u_id;
	}
	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_password() {
		return u_password;
	}
	public void setU_password(String u_password) {
		this.u_password = u_password;
	}
	
}
