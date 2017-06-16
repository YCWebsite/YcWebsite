package com.yc.biz;

import java.util.List;

import com.yc.bean.PaginationBean;
import com.yc.bean.Projects;

public interface ProjectsBiz {

	public List<Projects> selectAllProjects(Integer id);
	
	public boolean addProjects(Projects projects);
	
	public boolean updateProjectsById(Projects projects);
	
	public boolean deleteProjectsById(String id);
	
	//分页
	public PaginationBean<Projects> getPartJob(String size,String page);
	
}
