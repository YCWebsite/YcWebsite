package com.yc.biz;

import java.util.List;

import com.yc.bean.Courses;
import com.yc.bean.PaginationBean;
import com.yc.bean.Teachers;

public interface CoursesBiz {
	//添加课程体系
	public boolean add(Courses courses);
	//查询课程体系
	public List<Courses> findCourses(Integer id);
	//删除课程体系
	public boolean deleteCourses(String cs_id);
	//修改课程体系
	public boolean updateCourses(Courses courses);
	//课程分页
	public PaginationBean<Courses> getPartJob(String size,String page);
}
