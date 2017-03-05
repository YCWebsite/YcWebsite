package com.yc.bean;

import java.io.Serializable;

public class Teachers implements Serializable {

	private static final long serialVersionUID = 398487080674808003L;
	private Integer t_id;
	private String t_title;
	private String t_name;
	private String t_job;
	private String t_motto;
	private String t_skill;
	private Integer r_id;
	private String t_pic;
	public Integer getT_id() {
		return t_id;
	}
	public void setT_id(Integer t_id) {
		this.t_id = t_id;
	}
	public String getT_title() {
		return t_title;
	}
	public void setT_title(String t_title) {
		this.t_title = t_title;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getT_job() {
		return t_job;
	}
	public void setT_job(String t_job) {
		this.t_job = t_job;
	}
	public String getT_motto() {
		return t_motto;
	}
	public void setT_motto(String t_motto) {
		this.t_motto = t_motto;
	}
	public String getT_skill() {
		return t_skill;
	}
	public void setT_skill(String t_skill) {
		this.t_skill = t_skill;
	}
	public Integer getR_id() {
		return r_id;
	}
	public void setR_id(Integer r_id) {
		this.r_id = r_id;
	}
	public String getT_pic() {
		return t_pic;
	}
	public void setT_pic(String t_pic) {
		this.t_pic = t_pic;
	}
	
}
