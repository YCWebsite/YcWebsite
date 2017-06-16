package com.yc.web.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.yc.bean.JobDetails;
import com.yc.bean.PaginationBean;
import com.yc.biz.JobDetailsBiz;
import com.yc.web.utils.UploadFileUtil;
import com.yc.web.utils.UploadFileUtil.UploadFile;

@Controller
public class JobDetailsController {
	private static final Log logger=LogFactory.getLog(ProjectsController.class);
	
	private JobDetailsBiz jobDetailsBiz;
	//文件服务器名
	private String picRootName="uploadImages";
	
	@Resource(name="jobDetailsBizImpl")
	public void setJobDetails(JobDetailsBiz jobDetailsBiz) {
		this.jobDetailsBiz = jobDetailsBiz;
	}
	
	@InitBinder    
	public void initBinder(WebDataBinder binder) {    
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
	        dateFormat.setLenient(false);    
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
	} 

	//去显示页面
	@RequestMapping(value="/toJobDetails.action")
	public String toJobDetails(){
		return "backjsp/jobDetails";
	}
	
	//去添加界面
	@RequestMapping(value="/toAddJobDetails.action")
	public String toAddJobDetails(){
		return "backjsp/addJobDetails";
	}
	
	//显示数据，并分页
	@RequestMapping(value="/backShow_jobDetails.action",produces="application/json;charset=utf-8")
	public @ResponseBody String backShowjobDetails(String page,String rows,JobDetails jobDetails) {
		PaginationBean<JobDetails> paginationBean =jobDetailsBiz.getPartJob(page, rows,jobDetails);
		Gson gson=new Gson();
		return gson.toJson(paginationBean);
	}
	
	@RequestMapping(value="/show_jobDetails.action",produces="application/json;charset=utf-8")
	public @ResponseBody String showjobDetails(String page,String rows,JobDetails jobDetails) {
		List<JobDetails> jobDetailsList =jobDetailsBiz.selectJobDetails();
		Gson gson=new Gson();
		return gson.toJson(jobDetailsList);
	}
	
	
	//添加
	@RequestMapping(value="/add_jobDetails.action")
	@ResponseBody
	public boolean addJobDetails(JobDetails jobDetails,
			HttpServletRequest request){
		
		String images="";
		Map<String, UploadFile> map=UploadFileUtil.uploadFile(request, jobDetails.getJd_picURL(), picRootName);
		for(Entry<String,UploadFile> entry:map.entrySet()){
			UploadFile uploadFile =entry.getValue();
			images+=uploadFile.getNewFileUrl();
		}
		jobDetails.setJd_pic(images);
		return jobDetailsBiz.addJobDetails(jobDetails);
	}
	
	//删除
	@RequestMapping(value="/delete_jobDetails.action")
	@ResponseBody
	public boolean deleteJobDetails(String jd_id){
		logger.info("去删除就业详情...");
		return this.jobDetailsBiz.deleteManyJobDetails(jd_id);
	}
	
	@RequestMapping(value="/toUpdateJobDetails.action")
	public String toUpdateJobDetails(Integer jd_id,Model model){
		logger.info("去updateJobDetails页面...");
		List<JobDetails> jobDetails=this.jobDetailsBiz.selectAllJobDetails(jd_id);
		System.out.println("就业时间："+jobDetails.get(0).getJd_emptime());
		model.addAttribute("jobDetails", jobDetails.get(0));
		return "backjsp/updateJobDetails";
	}
	
	//修改
	@RequestMapping(value="/update_jobDetails.action")
	@ResponseBody
	public boolean update_JobDetails(
			 JobDetails jobDetails, @RequestParam("jd_picURL")MultipartFile pic,
			HttpServletRequest request){
		logger.info("去修改就业详情..." + jobDetails);//
		
		if(pic != null && !pic.isEmpty()){
			String images="";
			Map<String, UploadFile> map=UploadFileUtil.uploadFile(request, jobDetails.getJd_picURL(), picRootName);
			for(Entry<String,UploadFile> entry:map.entrySet()){
				UploadFile uploadFile =entry.getValue();
				images+=uploadFile.getNewFileUrl();
			}
			jobDetails.setJd_pic(images);
		}
		
		return jobDetailsBiz.updateJobDetailsByJbId(jobDetails);
	}
	
	//根据文本框中输入的条件进行查询
	@RequestMapping(value="/search_jobDetails.action")
	@ResponseBody
	public PaginationBean<JobDetails> findByCondition(@RequestParam String value,
			@RequestParam String choice,
			@RequestParam String value2,HttpServletRequest request){
		logger.info("去分页查询就业详情...");
		String pageSize=request.getParameter("rows");//页面大小
		String currPage=request.getParameter("page");//当前页数
		JobDetails jobDetails =new JobDetails();
		if(!"empty".equals(choice)){
			if(value.contains("-")){
				try {
					//转换时间
					SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
					Date startTime= format.parse(value);
					jobDetails.setStartTime(startTime);
					Date endTime=format.parse(value2);
					jobDetails.setEndTime(endTime);
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else{
				jobDetails.setMinSalary(Double.parseDouble(value));
				jobDetails.setMaxSalary(Double.parseDouble(value2));
			}
		}
		return jobDetailsBiz.getPartJob(pageSize, currPage,jobDetails);
		
	}
	
	//显示原图
	@RequestMapping(value="/toSeeJdPic.action")
	public String toShowPic(Integer jd_id,Model model){
		logger.info("去查看图片的页面...");
		List<JobDetails> jobDetailsList=this.jobDetailsBiz.selectAllJobDetails(jd_id);
		model.addAttribute("jobDetails", jobDetailsList.get(0));
		return "backjsp/seeJbPic";
	}
	
}
