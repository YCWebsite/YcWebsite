package com.yc.bean;

import java.io.Serializable;
import java.sql.Time;

public class Jobdetails implements Serializable {

	private static final long serialVersionUID = -1722024606175467050L;
	private Integer jd_id;  
	private String jd_pic;     //照片
	private String jd_name;    //学员姓名
	private Number jd_salary;	//学员薪资
	private Time jd_emptime;	//就业时间
	private String jd_company;   //所在公司
	private String jd_school;	 //毕业学校
	private String jd_profession;  //专业
	
	public Integer getJd_id() {
		return jd_id;
	}
	public void setJd_id(Integer jd_id) {
		this.jd_id = jd_id;
	}
	public String getJd_pic() {
		return jd_pic;
	}
	public void setJd_pic(String jd_pic) {
		this.jd_pic = jd_pic;
	}
	public String getJd_name() {
		return jd_name;
	}
	public void setJd_name(String jd_name) {
		this.jd_name = jd_name;
	}
	public Number getJd_salary() {
		return jd_salary;
	}
	public void setJd_salary(Number jd_salary) {
		this.jd_salary = jd_salary;
	}
	public Time getJd_emptime() {
		return jd_emptime;
	}
	public void setJd_emptime(Time jd_emptime) {
		this.jd_emptime = jd_emptime;
	}
	public String getJd_company() {
		return jd_company;
	}
	public void setJd_company(String jd_company) {
		this.jd_company = jd_company;
	}
	public String getJd_school() {
		return jd_school;
	}
	public void setJd_school(String jd_school) {
		this.jd_school = jd_school;
	}
	public String getJd_profession() {
		return jd_profession;
	}
	public void setJd_profession(String jd_profession) {
		this.jd_profession = jd_profession;
	}
	
}
