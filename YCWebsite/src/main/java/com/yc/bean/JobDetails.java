package com.yc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class JobDetails implements Serializable {

	private static final long serialVersionUID = -1722024606175467050L;
	private Integer jd_id;  
	private String jd_pic;     //照片   对应数据库的字段
	private String jd_name;    //学员姓名
	private Integer jd_salary;	//学员薪资
	//private Date jd_emptime;	//就业时间
	private String jd_emptime;
	private String jd_company;   //所在公司
	private String jd_school;	 //毕业学校
	private String jd_profession;  //专业
	private String todoa;
	private String todob;
	
	//分页
	private Integer pageSize;
	
	private Double minSalary;		//最少薪资
	private Double maxSalary;		//最高薪资
	
	private Date startTime;
	private Date endTime;
	
	
	private MultipartFile jd_picURL;//对应界面的url地址
		
	//在标准的javabean中，可以利用${pdfsStringList}获取
	public List<String> getjd_picURLStringList(){
		List<String> list=new ArrayList<String>();
		if(jd_pic!=null&&jd_pic.length()>0){
			String[]  strs=jd_pic.split(",");
			for(String s:strs){
				list.add(s);
			}
			return list;
		}
		return null;
	}
	
	
	public Double getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(Double minSalary) {
		this.minSalary = minSalary;
	}
	public Double getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(Double maxSalary) {
		this.maxSalary = maxSalary;
	}
	public Date getStartTime() {
		return startTime;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getTodoa() {
		return todoa;
	}
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public MultipartFile getJd_picURL() {
		return jd_picURL;
	}

	public void setJd_picURL(MultipartFile jd_picURL) {
		this.jd_picURL = jd_picURL;
	}

	public void setTodoa(String todoa) {
		this.todoa = todoa;
	}
	public String getTodob() {
		return todob;
	}
	public void setTodob(String todob) {
		this.todob = todob;
	}
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
	public Integer getJd_salary() {
		return jd_salary;
	}
	public void setJd_salary(Integer jd_salary) {
		this.jd_salary = jd_salary;
	}
	public String getJd_emptime() {
		return jd_emptime;
	}
	//@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setJd_emptime(String jd_emptime) {
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
	@Override
	public String toString() {
		return "JobDetails [jd_id=" + jd_id + ", jd_pic=" + jd_pic + ", jd_name=" + jd_name + ", jd_salary=" + jd_salary
				+ ", jd_emptime=" + jd_emptime + ", jd_company=" + jd_company + ", jd_school=" + jd_school
				+ ", jd_profession=" + jd_profession + ", todoa=" + todoa + ", todob=" + todob + ", pageSize="
				+ pageSize + ", jd_picURL=" + jd_picURL + ", minSalary=" + minSalary + ", maxSalary=" + maxSalary
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	

}
