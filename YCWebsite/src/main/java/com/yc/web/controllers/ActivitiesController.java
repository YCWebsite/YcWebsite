package com.yc.web.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.yc.bean.Activities;
import com.yc.bean.PaginationBean;
import com.yc.biz.ActivitiesBiz;
import com.yc.web.utils.FileUtils;
import com.yc.web.utils.UploadFileUtil;
import com.yc.web.utils.UploadFileUtil.UploadFile;

@Controller
public class ActivitiesController {
	private static final Log logger=LogFactory.getLog(ActivitiesController.class);
	private String imagesRootName="uploadImages";
	
	private ActivitiesBiz activitiesBiz;
	
	@Resource(name="activitiesBizImpl")
	public void setActivitiesbiz(ActivitiesBiz activitiesBiz) {
		this.activitiesBiz = activitiesBiz;
	}

	@RequestMapping(value="/toActivities.action")
	public String toactivities(){
		logger.info("去activities页面...");
		return "backjsp/activities";
	}
	
	@RequestMapping(value="/show_activities.action")
	public @ResponseBody void show_activities(HttpServletRequest request,HttpServletResponse response) throws IOException{
		logger.info("去查看所有项目活动内容...");
		String pageSize=request.getParameter("rows");//页面大小
		String currPage=request.getParameter("page");//当前页数
		PaginationBean<Activities> paginationBean =this.activitiesBiz.getPartJob(pageSize, currPage);
		Gson gson=new Gson();
		//直接以流的方式返回
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print(gson.toJson(paginationBean));
	}
	
	@RequestMapping(value="/toAddActivities.action")
	public String toAddactivities(){
		logger.info("去addactivities页面...");
		return "backjsp/addactivities";
	}
	
	@RequestMapping(value="/add_activities.action")
	@ResponseBody
	public boolean add_activities(HttpServletRequest request,Activities activities){
		logger.info("去添加活动...");
		String images="";
		//上传
		Map<String,UploadFile> map=UploadFileUtil.uploadFile(request, activities.getPicUrl(), imagesRootName);
		for(Entry<String,UploadFile> entry:map.entrySet()){
			UploadFile uploadFile=entry.getValue();
			images+=uploadFile.getNewFileUrl()+",";
		}
		activities.setAc_pic(images);
		return activitiesBiz.addActivities(activities);
	}
	
	@RequestMapping(value="/toUpdateActivities.action")
	public String toUpdateActivities(Integer ac_id,Model model){
		logger.info("去updateActivities页面...");
		List<Activities> activities=this.activitiesBiz.selectAllActivities(ac_id);
		model.addAttribute("activities", activities.get(0));
		return "backjsp/updateActivities";
	}
	
	/*@RequestMapping(value="/toSeeActivitiesPic.action")
	public String toSeePic(Integer ac_id,Model model){
		logger.info("去查看图片...");
		List<Activities> activities=this.activitiesBiz.selectAllActivities(ac_id);
		model.addAttribute("activities",activities.get(0));
		return "backjsp/seeActivitiesPic";
	}*/
	
	@RequestMapping(value="/update_activities.action")
	@ResponseBody
	public boolean update_activities(HttpServletRequest request,Activities activities){
		logger.info("去修改活动...");
		String images="";
		//上传
		Map<String,UploadFile> map=UploadFileUtil.uploadFile(request, activities.getPicUrl(), imagesRootName);
		for(Entry<String,UploadFile> entry:map.entrySet()){
			UploadFile uploadFile=entry.getValue();
			images+=uploadFile.getNewFileUrl()+",";
		}
		activities.setAc_pic(images);
		return this.activitiesBiz.updateActivitiesById(activities);
	}
	
	@RequestMapping(value="/delete_activities.action")
	@ResponseBody
	public boolean delete_activities(String ac_id){
		logger.info("去删除活动...");
		return this.activitiesBiz.deleteActivitiesById(ac_id);
	}
}
