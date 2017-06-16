package com.yc.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.bean.PaginationBean;
import com.yc.bean.Projects;
import com.yc.biz.ProjectsBiz;
import com.yc.dao.BaseDao;

@Service
public class ProjectsBizImpl implements ProjectsBiz {

	private BaseDao baseDao;
	
	@Resource(name="baseDaoMybatisImpl")
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	//添加学员项目
	@Override
	public boolean addProjects(Projects projects) {
		return this.baseDao.add(projects, "addProjects")>0;
	}

	//查询所有学员项目/也可根据id查找
	@Override
	public List<Projects> selectAllProjects(Integer id) {
		Projects projects=new Projects();
		if(id!=null && !"".equals(id)){
			projects.setP_id(id);
		}
		return  this.baseDao.findAll(projects, "selectAllProjects");
	}

	//根据id修改学员项目
	@Override
	public boolean updateProjectsById(Projects projects) {
		return this.baseDao.update(projects, "updateProjectsById")>0;
	}

	//根据id删除学员项目/可删除多个
	@Override
	public boolean deleteProjectsById(String id) {
		List<Projects> projectsList =new ArrayList<Projects>();
		Projects projects=null;
		String [] ids=id.split(",");
		for(int i=0;i<ids.length;i++){
			projects=new Projects();
			projects.setP_id(Integer.parseInt(ids[i]));
			projectsList.add(projects);
		}
		return this.baseDao.deleteMany(projects, projectsList, "deleteProjectsById")>0;
	}

	// 分页
	@Override
	public PaginationBean<Projects> getPartJob(String size, String page) {
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
			Projects projects = new Projects();
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("start", start);
			map.put("end", end);
			List<Projects> list = this.baseDao.findAll(projects, map, "findByPages");
			return new PaginationBean<Projects>(currPage, (int) totalPage, list,totalSize);
		}

	//总页数
	private int getTotalPage(int pageSize) {
		Projects projects = new Projects();
		projects.setPageSize(pageSize);
		return (int) this.baseDao.findAll(projects, "getTotalPage").get(0);
	}
	
	//获取总条数
	private int getTotalSize() {
		Projects projects = new Projects();
		return (int) this.baseDao.findAll(projects, "getTotalSize").get(0);
	}
}
