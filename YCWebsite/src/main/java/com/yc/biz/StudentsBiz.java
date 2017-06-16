package com.yc.biz;

import java.util.List;

import com.yc.bean.PaginationBean;
import com.yc.bean.Students;

public interface StudentsBiz {

	public boolean addStudents(Students students);
	
	public List<Students> selectAllStudents(String value);
	
	public boolean updateStudentsById(Students students);
	
	public boolean deleteStudentsById(String s_id);
	
	//分页
	public PaginationBean<Students> getPartJob(String size,String page,Students students);
	
	public List<Students> selectStudentsById(Integer id);
}
