package com.yc.biz;

import java.util.List;
import java.util.Map;

import com.yc.bean.JobDetails;
import com.yc.bean.PaginationBean;

public interface JobDetailsBiz {

	//添加学员就业情况
	public boolean addJobDetails(JobDetails jobDetails);
	
	//根据id修改学员信息
	public boolean updateJobDetailsByJbId(JobDetails jobDetails);
	
	//删除学员信息,可以删除单个或多个
	public boolean deleteManyJobDetails(String jd_id);
	
	//分页
	public PaginationBean<JobDetails> getPartJob(String size,String page,JobDetails jobDetails);
		
	public List<JobDetails> selectAllJobDetails(Integer id);
	
	public List<JobDetails> selectJobDetails();
	
	//将Excel表导入
	public void loadExcel(List<Map<String,Object>> list,String[] titlename,String filepath);
}
