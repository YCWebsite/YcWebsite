package com.yc.biz;

import java.util.List;

import com.yc.bean.Employ;
import com.yc.bean.PaginationBean;

public interface EmployBiz {

	public List<Employ> selectAllEmploy(Integer id);
	
	public boolean addEmploy(Employ employ);
	
	public boolean updateEmployById(Employ employ);
	
	public boolean deleteEmployById(String id);
	
	public boolean updateEmployByTime(Employ employ);
	//分页
	public PaginationBean<Employ> getPartJob(String size,String page);
}
