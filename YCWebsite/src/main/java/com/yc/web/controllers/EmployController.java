package com.yc.web.controllers;


import java.io.IOException;
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

import com.google.gson.Gson;
import com.yc.bean.Employ;
import com.yc.bean.PaginationBean;
import com.yc.bean.Projects;
import com.yc.biz.EmployBiz;
import com.yc.web.utils.UploadFileUtil;
import com.yc.web.utils.UploadFileUtil.UploadFile;


@Controller
public class EmployController {
	private static final Log logger=LogFactory.getLog(ProjectsController.class);
	
	private EmployBiz employBiz;
	private String imagesRootName="uploadImages";
	

	@Resource(name="employBizImpl")
	public void setEmployBiz(EmployBiz employBiz) {
		this.employBiz = employBiz;
	}


	@InitBinder    
	public void initBinder(WebDataBinder binder) {    
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
	        dateFormat.setLenient(false);    
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
	}  
	
	
	@RequestMapping(value="/toEmploy.action")
	public String toProjects(){
		logger.info("去employ页面...");
		return "backjsp/employ";
	}
	
	@RequestMapping(value="/add_employ.action")
	@ResponseBody
	public  boolean add_employ(Employ employ){
		logger.info("添加项目...");
		/*SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");*/
		return employBiz.addEmploy(employ);
	}
	
	@RequestMapping(value="/toUpdateEmploy.action")
	public String toUpdateEmploy(Integer e_id,Model model){
		logger.info("去修改页面...");
		List<Employ> employList=this.employBiz.selectAllEmploy(e_id);
		model.addAttribute(employList.get(0));
		return "backjsp/updateEmploy";
	}
	
	@RequestMapping(value="/update_employ.action")
	@ResponseBody
	public  boolean update_employ(Employ employ){
		logger.info("去修改项目...");
		return employBiz.updateEmployById(employ);
	}
	
	
	@RequestMapping(value="/delete_employ.action")
	@ResponseBody
	public  boolean delete_employ(String e_id){
		logger.info("去删除项目...");
		return employBiz.deleteEmployById(e_id);
	}
	
	@RequestMapping(value="/show_employ.action")
	public @ResponseBody void show_employ(HttpServletRequest request,HttpServletResponse response) throws IOException{
		logger.info("去查看所有招聘内容...");
		String pageSize=request.getParameter("rows");//页面大小
		String currPage=request.getParameter("page");//当前页数
		PaginationBean<Employ> paginationBean =this.employBiz.getPartJob(pageSize, currPage);
		Gson gson=new Gson();
		//直接以流的方式返回
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print(gson.toJson(paginationBean));
	}
	
	//查看详情
	@RequestMapping(value="/toSeeDetails.action")
	public String show_details(@RequestParam("e_id")Integer id,Model model) throws IOException{
		logger.info("去查看详情...");
		List<Employ> employList=this.employBiz.selectAllEmploy(id);
		model.addAttribute(employList.get(0));
		return "backjsp/updateEmploy";
	}
	
}
