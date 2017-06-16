package com.yc.biz;

import java.util.List;

import com.yc.bean.PaginationBean;
import com.yc.bean.Teachers;

public interface TeachersBiz {
	//添加教师
	public boolean addTeachers(Teachers teachers);
	//添加教师
	public boolean addTeam(Teachers teachers);
	//查询教师
	public List<Teachers> findTeachers(Integer id);
	//查询团队
	public List<Teachers> findTeam(Integer id);
	//删除教师
	public boolean deleteTeachers(String t_id);
	//修改教师
	public boolean updateTeachers(Teachers teachers);
	//教师分页
	public PaginationBean<Teachers> getPartJob(String size,String page);
	//管理团队分页
	public PaginationBean<Teachers> getPartJob1(String size,String page);
}	
