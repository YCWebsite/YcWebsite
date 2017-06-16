package com.yc.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.bean.PaginationBean;
import com.yc.bean.Students;
import com.yc.biz.StudentsBiz;
import com.yc.dao.BaseDao;

@Service
public class StudentsBizImpl implements StudentsBiz {

	private BaseDao baseDao;

	@Resource(name = "baseDaoMybatisImpl")
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	// 添加学生报名信息
	@Override
	public boolean addStudents(Students students) {
		return this.baseDao.add(students, "addStudents") > 0;
	}

	// 查询所有学生报名信息/通过状态查找
	@Override
	public List<Students> selectAllStudents(String choice) {
		Students students = new Students();
		if (!"".equals(choice) && choice != null) {
			if (choice.equals("有效")) {
				students.setS_status(choice);
			} else if (choice.equals("无效")) {
				students.setS_status(choice);
			}
		}
		return this.baseDao.findAll(students, "selectAllStudents");
	}

	// 根据id修改学生报名信息
	@Override
	public boolean updateStudentsById(Students students) {
		return this.baseDao.update(students, "updateStudentsById") > 0;
	}

	// 根据id删除学生报名信息（可多选）
	@Override
	public boolean deleteStudentsById(String s_id) {
		List<Students> studentsList = new ArrayList<Students>();
		Students students = null;
		String[] id = s_id.split(",");
		for (int i = 0; i < id.length; i++) {
			students = new Students();
			students.setS_id(Integer.parseInt(id[i]));
			studentsList.add(students);
		}
		return this.baseDao.delete(students, "deleteStudentsById") > 0;
	}

	// 分页
	@Override
	public PaginationBean<Students> getPartJob(String size, String page, Students students) {
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		if (students.getS_status() != null && !"".equals(students.getS_status())) {
			map.put("s_status", students.getS_status());
		}
		List<Students> list = this.baseDao.findAll(students, map, "findByPages");
		return new PaginationBean<Students>(currPage, (int) totalPage, list,totalSize);
	}

	// 总页数
	private int getTotalPage(int pageSize) {
		Students students = new Students();
		students.setPageSize(pageSize);
		return (int) this.baseDao.findAll(students, "getTotalPage").get(0);
	}

	// 根据id发送短信
	@Override
	public List<Students> selectStudentsById(Integer id) {
		Students students = new Students();
		if (!"".equals(id) && id != null) {
			students.setS_id(id);
		}
		return this.baseDao.findAll(students, "selectStudentsById");
	}

	// 获取总条数
	private int getTotalSize() {
		Students students = new Students();
		return (int) this.baseDao.findAll(students, "getTotalSize").get(0);
	}

}
