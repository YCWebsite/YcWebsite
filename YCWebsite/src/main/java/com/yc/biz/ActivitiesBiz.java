package com.yc.biz;

import java.util.List;

import com.yc.bean.Activities;
import com.yc.bean.PaginationBean;

public interface ActivitiesBiz {

	public List<Activities> selectAllActivities(Integer id);
	
	public boolean addActivities(Activities activities);
	
	public boolean updateActivitiesById(Activities activities);
	
	public boolean deleteActivitiesById(String id);
	
	//分页
	public PaginationBean<Activities> getPartJob(String size,String page);
}
