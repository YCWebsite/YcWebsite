package com.yc.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.bean.Courses;
import com.yc.bean.PaginationBean;
import com.yc.bean.Teachers;
import com.yc.biz.CoursesBiz;
import com.yc.dao.BaseDao;
@Service
public class CoursesBizImpl implements CoursesBiz {
	private BaseDao baseDao;
	@Resource(name="baseDaoMybatisImpl")
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	@Override
	public boolean add(Courses courses) {
		return this.baseDao.add(courses, "addCourses")>0;
	}

	@Override
	public List<Courses> findCourses(Integer id) {
		Courses courses=new Courses();
		if(id!=null && !"".equals(id)){
			courses.setCs_id(id);
		}
		return this.baseDao.findAll(courses, "findCourses");
	}
	
	//根据id删除学员项目/可删除多个
	@Override
	public boolean deleteCourses(String cs_id) {
		List<Courses> coursesList =new ArrayList<Courses>();
		Courses courses=null;
		String [] cs_ids=cs_id.split(",");
		for(int i=0;i<cs_ids.length;i++){
			courses=new Courses();
			courses.setCs_id(Integer.parseInt(cs_ids[i]));
			coursesList.add(courses);
		}
		return this.baseDao.deleteMany(courses, coursesList, "deleteCoursesById")>0;
	}
	@Override
	public boolean updateCourses(Courses courses) {
		return this.baseDao.update(courses, "updateCourses")>0;
	}
	@Override
	public PaginationBean<Courses> getPartJob(String size, String page){
		int currPage = 1;// 默认当前页为第一页
		int pageSize = 10;// 默认页面显示的页数为10条
		if (size != null) {
			pageSize = Integer.parseInt(size);// 取到页面传过来的页面条数
		}
		int totalPage = getTotalPage(pageSize);
		int totalSize=getTotalSize();
		if (page != null) {
			currPage = Integer.parseInt(page); // 取到当前页面
			if (currPage > totalPage) {// 表示最后一页
				currPage = (int) totalPage;
			} else if (currPage < 1) {// 第一页
				currPage = 1;
			}
		}
		int start = (currPage - 1) * pageSize;
		int end = pageSize;
		Courses courses= new Courses();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		List<Courses> list = this.baseDao.findAll(courses, map, "findByPages");
		return new PaginationBean<Courses>(currPage, (int) totalPage, list,totalSize);
	}
	
	//获取总页数
	private int getTotalPage(int pageSize) {
		Courses courses= new Courses();
		courses.setPageSize(pageSize);
		return (int) this.baseDao.findAll(courses, "getTotalPage").get(0);
	}
			
	//获取总条数
	private int getTotalSize() {
		Courses courses= new Courses();
		return (int) this.baseDao.findAll(courses, "getTotalSize").get(0);
	}

}
