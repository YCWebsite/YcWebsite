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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.yc.bean.PaginationBean;
import com.yc.bean.Teachers;
import com.yc.biz.TeachersBiz;
import com.yc.web.utils.UploadFileUtil;
import com.yc.web.utils.UploadFileUtil.UploadFile;

@Controller
public class TeachersController {
	private static final Log logger=LogFactory.getLog(TeachersController.class);
	private TeachersBiz teachersBiz;
	@Resource(name="teachersBizImpl")
	public void setTeachers(TeachersBiz teachersBiz) {
		this.teachersBiz = teachersBiz;
	}
	//去显示教师管理页面
	@RequestMapping(value="/toBackTeachers.action")
	public String toTeachersListBook( ){
		return "backjsp/teachers";
	}
	//去显示团队管理页面
	@RequestMapping(value="/toBackTeam.action")
	public String toTeamListBook( ){
		return "backjsp/team";
	}
	//显示教师团队数据
	//TODO	:这里的异常要不要捕获
	@RequestMapping(value="/show_teachers.action",produces="application/json;charset=utf-8")
	public @ResponseBody String showbackTeachers(Model model) {
		List<Teachers> listTeachers=teachersBiz.findTeachers(null);
		Gson gson=new Gson();
		String list=gson.toJson(listTeachers);
		//直接以流的方式返回
		model.addAttribute("teachers", list);
		return gson.toJson(listTeachers);
	}
	//教师分页
	@RequestMapping(value="/show_teachers2.action")
	public @ResponseBody void show_Teachers(HttpServletRequest request,HttpServletResponse response) throws IOException{
		logger.info("去查看所有项目内容...");
		String pageSize=request.getParameter("rows");//页面大小
		String currPage=request.getParameter("page");//当前页数
		PaginationBean<Teachers> paginationBean =this.teachersBiz.getPartJob(pageSize, currPage);
		Gson gson=new Gson();
		//直接以流的方式返回
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print(gson.toJson(paginationBean));
	}
	
	//显示管理团队数据
	//TODO	:这里的异常要不要捕获
	@RequestMapping(value="/show_team.action",produces="application/json;charset=utf-8")
	public @ResponseBody String showbackTeam(Model model) {
		List<Teachers> listTeachers=teachersBiz.findTeam(null);
		Gson gson=new Gson();
		String list=gson.toJson(listTeachers);
		//直接以流的方式返回
		model.addAttribute("teachers", list);
		return gson.toJson(listTeachers);
	}
	//团队分页
	@RequestMapping(value="/show_team2.action")
	public @ResponseBody void show_Team(HttpServletRequest request,HttpServletResponse response) throws IOException{
		logger.info("去查看所有项目内容...");
		String pageSize=request.getParameter("rows");//页面大小
		String currPage=request.getParameter("page");//当前页数
		PaginationBean<Teachers> paginationBean =this.teachersBiz.getPartJob1(pageSize, currPage);
		Gson gson=new Gson();
		//直接以流的方式返回
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print(gson.toJson(paginationBean));
	}
	
	
	//文件服务器名
	private String picRootName="uploadImages";
	//添加教师
	@RequestMapping(value="/add_teachers.action")
	@ResponseBody
	public boolean addTeachers(Teachers teachers,
		HttpServletRequest request){
		//设置pic的图片
		String picImage="";
		//uploadFile方法的三个参数分别是：请求，要上传的文件名，文件的根路径
		Map<String, UploadFile> picMap=UploadFileUtil.uploadFile(request,teachers.getT_picURL(), picRootName);
		for(Entry<String,UploadFile> entry:picMap.entrySet()){
			UploadFile uploadFile =entry.getValue();
			picImage+=uploadFile.getNewFileUrl();
		}
		teachers.setT_pic(picImage);
		//调用添加的方法
		return teachersBiz.addTeachers(teachers);
	}
	
	//添加管理团队
	@RequestMapping(value="/add_team.action")
	@ResponseBody
	public boolean addTeam(Teachers teachers,
		HttpServletRequest request){
		//设置pic的图片
		String picImage="";
		//uploadFile方法的三个参数分别是：请求，要上传的文件名，文件的根路径
		Map<String, UploadFile> picMap=UploadFileUtil.uploadFile(request,teachers.getT_picURL(), picRootName);
		for(Entry<String,UploadFile> entry:picMap.entrySet()){
			UploadFile uploadFile =entry.getValue();
			picImage+=uploadFile.getNewFileUrl();
		}
		teachers.setT_pic(picImage);
		//调用添加的方法
		return teachersBiz.addTeam(teachers);
	}
			
	//去删除界面
	@RequestMapping(value="/delete_teachers.action")
	@ResponseBody
	public String deleteTeachers(String t_id){
		teachersBiz.deleteTeachers(t_id);
		Gson gson=new Gson();
		return gson.toJson("success");
	}
			
	@RequestMapping(value="/toUpdateTeachers.action")
	public String toUpdateTeachers(Integer t_id,Model model){
		logger.info("去updateTeachers页面...");
		List<Teachers> teachers=this.teachersBiz.findTeachers(t_id);
		model.addAttribute("teachers", teachers.get(0));
		return "backjsp/updateTeachers";
	}
	
	@RequestMapping(value="/toUpdateTeam.action")
	public String toUpdateTeam(Integer t_id,Model model){
		logger.info("去updateTeam页面...");
		List<Teachers> team=this.teachersBiz.findTeam(t_id);
		model.addAttribute("team", team.get(0));
		return "backjsp/updateTeam";
	}
	
	//修改老师
	@RequestMapping(value="/update_teachers.action")
	@ResponseBody
	public boolean update_teachers(HttpServletRequest request,Teachers teachers,@RequestParam("t_picURL") MultipartFile t_pic){
		logger.info("去修改教师详情...");
		if(t_pic!=null && !t_pic.isEmpty()){
			String picImages="";
			//上传
			Map<String,UploadFile> picMap=UploadFileUtil.uploadFile(request, teachers.getT_picURL(), picRootName);
			for(Entry<String,UploadFile> entry:picMap.entrySet()){
				UploadFile uploadFile=entry.getValue();
				picImages+=uploadFile.getNewFileUrl();
			}
			teachers.setT_pic(picImages);
		}
		return teachersBiz.updateTeachers(teachers);
	}
	
	//修改团队
	@RequestMapping(value="/update_team.action")
	@ResponseBody
	public boolean update_team(HttpServletRequest request,Teachers teachers,@RequestParam("t_picURL") MultipartFile t_pic){
		logger.info("去修改团队详情...");
		if(t_pic!=null && !t_pic.isEmpty()){
			String picImages="";
			//上传
			Map<String,UploadFile> picMap=UploadFileUtil.uploadFile(request, teachers.getT_picURL(), picRootName);
			for(Entry<String,UploadFile> entry:picMap.entrySet()){
				UploadFile uploadFile=entry.getValue();
				picImages+=uploadFile.getNewFileUrl();
			}
			teachers.setT_pic(picImages);
		}
		return teachersBiz.updateTeachers(teachers);
	}
	
	//小窗口显示教师查看图片
	@RequestMapping(value="/toSeeTeachersPic.action")
	public String toSeeTeachersPic(Integer t_id,Model model){
		logger.info("去查看图片....");
		List<Teachers> teachers=this.teachersBiz.findTeachers(t_id);
		model.addAttribute("teachers",teachers.get(0));
		return 	"backjsp/seeTeachersPic";
	}
	
	//小窗口显示团队查看图片
	@RequestMapping(value="/toSeeTeamPic.action")
	public String toSeeTeamPic(Integer t_id,Model model){
		logger.info("去查看图片....");
		List<Teachers> team=this.teachersBiz.findTeam(t_id);
		model.addAttribute("team",team.get(0));
		return 	"backjsp/seeTeamPic";
		}
}
