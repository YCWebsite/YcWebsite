package com.yc.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Employ implements Serializable {

	private static final long serialVersionUID = 339502828447332803L;
	private Integer e_id;
	private String e_position;    //职位
	private Date e_validtime;     //有效时间
	private Integer e_amount;	  //招聘人数
	private String e_salary;	  //工资待遇
	private String e_detail;	  //详细说明
	private String e_addr;        //简历投递地址
	private String todoa;		  //状态
	
	// 分页
	private Integer pageSize;
	
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getTodoa() {
		return todoa;
	}
	public void setTodoa(String todoa) {
		this.todoa = todoa;
	}
	public Integer getE_id() {
		return e_id;
	}
	public void setE_id(Integer e_id) {
		this.e_id = e_id;
	}
	public String getE_position() {
		return e_position;
	}
	public void setE_position(String e_position) {
		this.e_position = e_position;
	}
	public Date getE_validtime() {
		return e_validtime;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setE_validtime(Date e_validtime) {
		this.e_validtime = e_validtime;
	}
	public Integer getE_amount() {
		return e_amount;
	}
	public void setE_amount(Integer e_amount) {
		this.e_amount = e_amount;
	}
	public String getE_salary() {
		return e_salary;
	}
	public void setE_salary(String e_salary) {
		this.e_salary = e_salary;
	}
	public String getE_detail() {
		return e_detail;
	}
	public void setE_detail(String e_detail) {
		this.e_detail = e_detail;
	}
	public String getE_addr() {
		return e_addr;
	}
	public void setE_addr(String e_addr) {
		this.e_addr = e_addr;
	}
	@Override
	public String toString() {
		return "Employ [e_id=" + e_id + ", e_position=" + e_position + ", e_validtime=" + e_validtime + ", e_amount="
				+ e_amount + ", e_salary=" + e_salary + ", e_detail=" + e_detail + ", e_addr=" + e_addr + ", todoa="
				+ todoa + ", pageSize=" + pageSize + "]";
	}
	
}
