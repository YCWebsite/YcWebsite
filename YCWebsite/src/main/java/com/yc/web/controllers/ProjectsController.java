package com.yc.web.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.yc.bean.PaginationBean;
import com.yc.bean.Projects;
import com.yc.biz.ProjectsBiz;
import com.yc.web.utils.UploadFileUtil;
import com.yc.web.utils.UploadFileUtil.UploadFile;

@Controller
public class ProjectsController {
	private static final Log logger=LogFactory.getLog(ProjectsController.class);
	private String imagesRootName="uploadImages";
	
	private ProjectsBiz projectsBiz;
	
	@Resource(name="projectsBizImpl")
	public void setProjectsbiz(ProjectsBiz projectsBiz) {
		this.projectsBiz = projectsBiz;
	}

	@RequestMapping(value="/toProjects.action")
	public String toProjects(){
		logger.info("去projects页面...");
		return "backjsp/projects";
	}
	
	@RequestMapping(value="/show_projects.action")
	public @ResponseBody void show_projects(HttpServletRequest request,HttpServletResponse response) throws IOException{
		logger.info("去查看所有项目内容...");
		String pageSize=request.getParameter("rows");//页面大小
		String currPage=request.getParameter("page");//当前页数
		PaginationBean<Projects> paginationBean =this.projectsBiz.getPartJob(pageSize, currPage);
		Gson gson=new Gson();
		//直接以流的方式返回
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print(gson.toJson(paginationBean));
	}
	
	@RequestMapping(value="/toAddProjects.action")
	public String toAddProjects(){
		logger.info("去addProjects页面...");
		return "backjsp/addProjects";
	}
	
	@RequestMapping(value="/add_projects.action")
	@ResponseBody
	public boolean add_projects(HttpServletRequest request,Projects projects){
		logger.info("去添加项目...");
		String images="";
		//上传
		Map<String,UploadFile> map=UploadFileUtil.uploadFile(request, projects.getPicUrl(), imagesRootName);
		for(Entry<String,UploadFile> entry:map.entrySet()){
			UploadFile uploadFile=entry.getValue();
			images+=uploadFile.getNewFileUrl();
		}
		projects.setP_pic(images);
		return this.projectsBiz.addProjects(projects);
	}
	
	/*@RequestMapping(value="/add_projects.action")
	@ResponseBody
	public boolean add_projects(@RequestParam("picUrl")MultipartFile pic,Projects projects, HttpServletRequest request){
		logger.info("去添加项目...");
		if(pic != null && !pic.isEmpty()){
			ServletContext application = request.getServletContext();
			String virtualPath = imagesRootName +FileUtils.getNowDateStr()+FileUtils.getUniqueFileName() +pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf("."));
			String filePath = application.getRealPath(virtualPath).replace(application.getContextPath().substring(1) + "\\", "");
			try {
				pic.transferTo(new File(filePath));
				projects.setP_pic(virtualPath);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			} 
		}
		return this.projectsBiz.addProjects(projects);
	}*/
	
	@RequestMapping(value="/toUpdateProjects.action")
	public String toUpdateProjects(Integer p_id,Model model){
		logger.info("去updateProjects页面...");
		List<Projects> projects=this.projectsBiz.selectAllProjects(p_id);
		model.addAttribute("projects", projects.get(0));
		return "backjsp/updateProjects";
	}
	
	@RequestMapping(value="/toSeeProjectsPic.action")
	public String toSeePic(Integer p_id,Model model){
		logger.info("去查看图片...");
		List<Projects> projects=this.projectsBiz.selectAllProjects(p_id);
		model.addAttribute("projects", projects.get(0));
		return "backjsp/seeProjectsPic";
	}
	
	/*@RequestMapping(value="/update_projects.action")
	@ResponseBody
	public boolean update_projects(@RequestParam("picUrl")MultipartFile pic,Projects projects, HttpServletRequest request){
		logger.info("去修改项目...");
		if(pic != null && !pic.isEmpty()){
			ServletContext application = request.getServletContext();
			String virtualPath = imagesRootName +FileUtils.getNowDateStr()+FileUtils.getUniqueFileName() +pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf("."));
			String filePath = application.getRealPath(virtualPath).replace(application.getContextPath().substring(1) + "\\", "");
			try {
				pic.transferTo(new File(filePath));
				projects.setP_pic(virtualPath);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			} 
		}
		return projectsBiz.updateProjectsById(projects);
	}*/
	@RequestMapping(value="/update_projects.action")
	@ResponseBody
	public boolean update_projects(HttpServletRequest request,Projects projects){
		logger.info("去修改项目...");
		String images="";
		//上传
		Map<String,UploadFile> map=UploadFileUtil.uploadFile(request, projects.getPicUrl(), imagesRootName);
		for(Entry<String,UploadFile> entry:map.entrySet()){
			UploadFile uploadFile=entry.getValue();
			images+=uploadFile.getNewFileUrl();
		}
		projects.setP_pic(images);
		return this.projectsBiz.updateProjectsById(projects);
	}
	
	@RequestMapping(value="/delete_projects.action")
	@ResponseBody
	public boolean delete_projects(String p_id){
		logger.info("去删除项目...");
		return this.projectsBiz.deleteProjectsById(p_id);
	}
	
}
