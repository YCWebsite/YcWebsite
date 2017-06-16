package com.yc.biz.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.bean.JobDetails;
import com.yc.bean.PaginationBean;
import com.yc.bean.Projects;
import com.yc.biz.JobDetailsBiz;
import com.yc.dao.BaseDao;

@Service
public class JobDetailsBizImpl implements JobDetailsBiz {

	private BaseDao basedao;

	@Resource(name = "baseDaoMybatisImpl")
	public void setBasedao(BaseDao basedao) {
		this.basedao = basedao;
	}

	// 添加学员就业情况
	@Override
	public boolean addJobDetails(JobDetails jobDetails) {
		return basedao.add(jobDetails, "addJobDetails")>0;
	}

	// 根据id修改学员信息
	@Override
	public boolean updateJobDetailsByJbId(JobDetails jobDetails) {
		return basedao.update(jobDetails, "updateJobDetailsByJbId")>0;

	}

	// 删除学员信息,可以删除单个或多个
	@Override
	public boolean deleteManyJobDetails(String jd_id) {
		List<JobDetails> jobDetailsList = new ArrayList<JobDetails>();
		JobDetails jobDetails = null;
		String[] id = jd_id.split(",");
		for (int i = 0; i < id.length; i++) {
			jobDetails = new JobDetails();
			jobDetails.setJd_id(Integer.parseInt(id[i]));
			jobDetailsList.add(jobDetails);
		}
		return this.basedao.deleteMany(jobDetails, jobDetailsList, "deleteManyJobDetails")>0;
	}

	/**
	 * page:是页面传过来的第几页
	 * size:下拉框中的大小
	 * 
	 */
	// 分页
	@Override
	public PaginationBean<JobDetails> getPartJob(String page,String size,JobDetails jobDetails) {
		Map<String, Object> map=null;
		int currPage = 1;// 默认当前页为第一页
		int pageSize = 10;// 默认页面显示的页数为10条
		if (size != null) {
			pageSize = Integer.parseInt(size);// 取到页面传过来的页面条数
		}
		int totalPage = getTotalPage(pageSize);
		int totalSize=getTotalSize();
		if (page != null) {
			currPage = Integer.parseInt(page);
			if (currPage > totalPage) {// 表示最后一页
				currPage = (int) totalPage;
			} else if (currPage < 1) {// 第一页
				currPage = 1;
			}
		}
		int start = ((currPage - 1) * pageSize);
		int end =  pageSize;
		map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		if(jobDetails.getMinSalary()!=null&&jobDetails.getMinSalary().SIZE>0
				&&jobDetails.getMaxSalary()!=null&&jobDetails.getMaxSalary().SIZE>0){
			map.put("maxSalary", jobDetails.getMaxSalary());
			map.put("minSalary", jobDetails.getMinSalary());
		}
		if(jobDetails.getStartTime()!=null&&!"".equals(jobDetails.getStartTime())
				&&jobDetails.getEndTime()!=null&&!"".equals(jobDetails.getEndTime())){
			map.put("startTime", jobDetails.getStartTime());
			map.put("endTime", jobDetails.getEndTime());
		}
		
		List<JobDetails> list = this.basedao.findAll(jobDetails, map, "findByPages");
		return new PaginationBean<JobDetails>(currPage, (int) totalPage, list,totalSize);
	}

	//获取每页总条数
	private int getTotalPage(int pageSize) {
		JobDetails jobDetails = new JobDetails();
		jobDetails.setPageSize(pageSize);
		return (int) this.basedao.findAll(jobDetails, "getTotalPage").get(0);
	}
	
	private int getTotalSize(){
		JobDetails jobDetails = new JobDetails();
		return (int) this.basedao.findAll(jobDetails, "getTotalSize").get(0);
	}

	// 查询所有就业详情/也可根据id查找
	@Override
	public List<JobDetails> selectAllJobDetails(Integer id) {
		JobDetails jobDetails = new JobDetails();
		if (id != null && !"".equals(id)) {
			jobDetails.setJd_id(id);
		}
		return this.basedao.findAll(jobDetails, "findJobDetails");
	}

	@Override
	public List<JobDetails> selectJobDetails() {
		JobDetails jobDetails = new JobDetails();
		return this.basedao.findAll(jobDetails, "findJobDetails");
	}

	/**
	 * 
	 * @param list要存到EXCEL中的数据  map中的键就是表的标题  map的值就是插入的内容
	 * @param titlename
	 * @param filepath
	 * @throws Exception
	 */
	//导入Excel表
	@Override
	public void loadExcel(List<Map<String, Object>> list, String[] titlename, String filepath) {

		/*int titleColNum=0;
		//open file.  
		WritableWorkbook book = Workbook.createWorkbook(new File(filepath));  
		//create Sheet named "Sheet_1". 0 means this is 1st page.  
		WritableSheet sheet = book.createSheet("Sheet_1", 0); 
		for(int i=0;i<titlename.length;i++){
			Label label=new Label(i,titleColNum,titlename[i]);
			sheet.addCell(label);
		}
		int j=titleColNum+1;
		for(Map<String,Object> map:list){
			for(int i=0;i<titlename.length;i++){
				Label labels=new Label(i,j,String.valueOf(map.get(titlename[i])));
				sheet.addCell(labels);
			}
			j++;
		}

		//add defined all cell above to case.  
		book.write();  
		//close file case.  
		book.close(); */
	}
}
