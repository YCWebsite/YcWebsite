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
import com.yc.biz.TeachersBiz;
import com.yc.dao.BaseDao;
@Service
public class TeachersBizImpl implements TeachersBiz {
	private BaseDao baseDao;
	@Resource(name="baseDaoMybatisImpl")
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public boolean addTeachers(Teachers teachers) {
		return this.baseDao.add(teachers, "addTeachers")>0;
	}
	
	@Override
	public boolean addTeam(Teachers teachers) {
		return this.baseDao.add(teachers, "addTeam")>0;
	}

	@Override
	public List<Teachers> findTeachers(Integer id) {
		Teachers teachers=new Teachers();
		if(id!=null && !"".equals(id)){
			teachers.setT_id(id);
		}
		return this.baseDao.findAll(teachers, "findTeachers");
	}
	
	@Override
	public List<Teachers> findTeam(Integer id) {
		Teachers teachers=new Teachers();
		if(id!=null && !"".equals(id)){
			teachers.setT_id(id);
		}
		return this.baseDao.findAll(teachers, "findTeam");
	}

	
	//根据id删除学员项目/可删除多个
	@Override
	public boolean deleteTeachers(String t_id) {
		List<Teachers> teachersList =new ArrayList<Teachers>();
		Teachers teachers=null;
		String [] t_ids=t_id.split(",");
		for(int i=0;i<t_ids.length;i++){
			teachers=new Teachers();
			teachers.setT_id(Integer.parseInt(t_ids[i]));
			teachersList.add(teachers);
		}
		return this.baseDao.deleteMany(teachers, teachersList, "deleteTeachersById")>0;
	}

	@Override
	public boolean updateTeachers(Teachers teachers) {
		return this.baseDao.update(teachers, "updateTeachers")>0;
		
	}

	//获取总页数
	private int getTotalPage(int pageSize) {
		Teachers teachers = new Teachers();
		teachers.setPageSize(pageSize);
		return (int) this.baseDao.findAll(teachers, "getTotalPage").get(0);
	}
		
	//获取总条数
	private int getTotalSize1() {
		Teachers teachers = new Teachers();
		return (int) this.baseDao.findAll(teachers, "getTotalSize1").get(0);
	}
	
	//获取总条数
	private int getTotalSize2() {
		Teachers teachers = new Teachers();
		return (int) this.baseDao.findAll(teachers, "getTotalSize2").get(0);
	}
	
	//老师分页
	@Override
	public PaginationBean<Teachers> getPartJob(String size, String page) {
		int currPage = 1;// 默认当前页为第一页
		int pageSize = 10;// 默认页面显示的页数为10条
		if (size != null) {
			pageSize = Integer.parseInt(size);// 取到页面传过来的页面条数
		}
		int totalPage = getTotalPage(pageSize);
		int totalSize=getTotalSize1();
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
		Teachers teachers = new Teachers();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		List<Teachers> list = this.baseDao.findAll(teachers, map, "findByPagesTeachers");
		return new PaginationBean<Teachers>(currPage, (int) totalPage, list,totalSize);
	}
	//管理分页
	@Override
	public PaginationBean<Teachers> getPartJob1(String size, String page) {
		int currPage = 1;// 默认当前页为第一页
		int pageSize = 10;// 默认页面显示的页数为10条
		if (size != null) {
			pageSize = Integer.parseInt(size);// 取到页面传过来的页面条数
		}
		int totalPage = getTotalPage(pageSize);
		int totalSize=getTotalSize2();
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
		Teachers teachers = new Teachers();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		List<Teachers> list = this.baseDao.findAll(teachers, map, "findByPagesTeam");
		return new PaginationBean<Teachers>(currPage, (int) totalPage, list,totalSize);
	}

}
