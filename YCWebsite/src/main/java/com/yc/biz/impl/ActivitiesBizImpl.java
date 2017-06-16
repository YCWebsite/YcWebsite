package com.yc.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.bean.Activities;
import com.yc.bean.PaginationBean;
import com.yc.biz.ActivitiesBiz;
import com.yc.dao.BaseDao;

@Service
public class ActivitiesBizImpl implements ActivitiesBiz {

	private BaseDao baseDao;

	@Resource(name = "baseDaoMybatisImpl")
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	// 添加学员项目
	@Override
	public boolean addActivities(Activities activities) {
		return baseDao.add(activities, "addActivities") > 0;
	}

	// 查询所有学员项目/也可根据id查找
	@Override
	public List<Activities> selectAllActivities(Integer id) {
		Activities activities = new Activities();
		if (id != null && !"".equals(id)) {
			activities.setAc_id(id);
		}
		return this.baseDao.findAll(activities, "selectAllActivities");
	}

	// 根据id修改学员项目
	@Override
	public boolean updateActivitiesById(Activities activities) {
		return this.baseDao.update(activities, "updateActivitiesById") > 0;
	}

	// 根据id删除学员项目/可删除多个
	@Override
	public boolean deleteActivitiesById(String id) {
		List<Activities> activitiesList = new ArrayList<Activities>();
		Activities activities = null;
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			activities = new Activities();
			activities.setAc_id(Integer.parseInt(ids[i]));
			activitiesList.add(activities);
		}
		return this.baseDao.deleteMany(activities, activitiesList, "deleteActivitiesById") > 0;
	}

	// 分页
	@Override
	public PaginationBean<Activities> getPartJob(String size, String page) {
		System.out.println("我传过来的每页大小：" + size + "当前页数：" + page);
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
		Activities activities = new Activities();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		List<Activities> list = this.baseDao.findAll(activities, map, "findByPages");
		return new PaginationBean<Activities>(currPage, (int) totalPage, list, totalSize);
	}

	// 总页数
	private int getTotalPage(int pageSize) {
		Activities activities = new Activities();
		activities.setPageSize(pageSize);
		return (int) this.baseDao.findAll(activities, "getTotalPage").get(0);
	}

	// 获取总条数
	private int getTotalSize() {
		Activities activities = new Activities();
		return (int) this.baseDao.findAll(activities, "getTotalSize").get(0);
	}
}
