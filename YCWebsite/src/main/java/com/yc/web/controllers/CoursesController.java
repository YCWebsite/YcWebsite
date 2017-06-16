package com.yc.web.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.yc.bean.Courses;
import com.yc.bean.PaginationBean;
import com.yc.bean.Teachers;
import com.yc.biz.CoursesBiz;
import com.yc.web.utils.UploadFileUtil;
import com.yc.web.utils.UploadFileUtil.UploadFile;


@Controller
public class CoursesController {
	private static final Log logger=LogFactory.getLog(CoursesController.class);
	private CoursesBiz coursesBiz;
	@Resource(name="coursesBizImpl")
	public void setCourses(CoursesBiz coursesBiz) {
		this.coursesBiz = coursesBiz;
	}
	//去显示页面
	@RequestMapping(value="/toBackCourses.action")
	public String toStudentListBook( ){
		return "backjsp/courses";
	}
	//去添加界面
	@RequestMapping(value="/toAddCourses.action")
	public String toAddCourses(){
		return "backjsp/addCourses";
	}
	
	
	//显示数据
	//TODO	:这里的异常要不要捕获
	@RequestMapping(value="/show_courses.action",produces="application/json;charset=utf-8")
	public @ResponseBody String showbackCourses(Model model){
		List<Courses> listCourses=coursesBiz.findCourses(null);
		Gson gson=new Gson();
		String list=gson.toJson(listCourses);
		//直接以流的方式返回
		model.addAttribute("courses", list);
		return gson.toJson(listCourses);
	}
	
	//课程分页
	@RequestMapping(value="/show_courses2.action")
	public @ResponseBody void show_Courses(HttpServletRequest request,HttpServletResponse response) throws IOException{
		logger.info("去查看所有课程内容...");
		String pageSize=request.getParameter("rows");//页面大小
		String currPage=request.getParameter("page");//当前页数
		PaginationBean<Courses> paginationBean =this.coursesBiz.getPartJob(pageSize, currPage);
		Gson gson=new Gson();
		//直接以流的方式返回
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print(gson.toJson(paginationBean));
	}

		
		//文件服务器名
		private String picRootName="uploadImages";
		
		//添加
		@RequestMapping(value="/add_courses.action")
		@ResponseBody
		public boolean addCourses(Courses courses,
				HttpServletRequest request){
			System.out.println("1=====---->"+courses);
			//设置name的图片
			String nameImage="";
			//uploadFile方法的三个参数分别是：请求，要上传的文件名，文件的根路径
			Map<String, UploadFile> nameMap=UploadFileUtil.uploadFile(request,courses.getCs_nameURL(), picRootName);
			
			for(Entry<String,UploadFile> entry:nameMap.entrySet()){
				UploadFile uploadFile =entry.getValue();
				nameImage+=uploadFile.getNewFileUrl();
			}
			courses.setCs_namePic(nameImage);
			System.out.println(courses.getCs_namePic());
			
			//设置pic的图片
			String picImage="";
			//uploadFile方法的三个参数分别是：请求，要上传的文件名，文件的根路径
			Map<String, UploadFile> picMap=UploadFileUtil.uploadFile(request,courses.getCs_picURL(), picRootName);
			
			for(Entry<String,UploadFile> entry:picMap.entrySet()){
				UploadFile uploadFile =entry.getValue();
				picImage+=uploadFile.getNewFileUrl();
			}
			courses.setCs_pic(picImage);
			//调用添加的方法
			return coursesBiz.add(courses);
		}
		
		//去删除界面
		@RequestMapping(value="/delete_courses.action")
		@ResponseBody
		public String deleteCourses(String cs_id){
			System.out.println("============"+cs_id);
			coursesBiz.deleteCourses(cs_id);
			Gson gson=new Gson();
			return gson.toJson("success");
		}
		//修改
		@RequestMapping(value="/toUpdateCourses.action")
		public String toUpdateCourses(Integer cs_id,Model model){
			logger.info("去updateCourses页面...");
			List<Courses> courses=this.coursesBiz.findCourses(cs_id);
			model.addAttribute("courses", courses.get(0));
			return "backjsp/updateCourses";
		}
		
		@RequestMapping(value="/update_courses.action")
		@ResponseBody
		public boolean update_projects(HttpServletRequest request,Courses courses,@RequestParam("cs_nameURL") MultipartFile cs_namePic,@RequestParam("cs_picURL") MultipartFile cs_pic){
			logger.info("去修改课程详情...");
			
			//上传
			if(cs_namePic!=null && !cs_namePic.isEmpty() ){
				String nameImages="";
				Map<String,UploadFile> nameMap=UploadFileUtil.uploadFile(request, courses.getCs_nameURL(), picRootName);
				for(Entry<String,UploadFile> entry:nameMap.entrySet()){
					UploadFile uploadFile=entry.getValue();
					nameImages+=uploadFile.getNewFileUrl();
				}
				courses.setCs_namePic(nameImages);
			}
			
			if(cs_pic!=null && !cs_pic.isEmpty() ){
				String picImages="";
				Map<String,UploadFile> picMap=UploadFileUtil.uploadFile(request, courses.getCs_picURL(), picRootName);
				for(Entry<String,UploadFile> entry:picMap.entrySet()){
					UploadFile uploadFile=entry.getValue();
					picImages+=uploadFile.getNewFileUrl();
				}
				courses.setCs_pic(picImages);
			}
			System.out.println("========="+courses);
			return coursesBiz.updateCourses(courses);
		}
		//小窗口显示查看图片
		@RequestMapping(value="/toSeeCoursesNamePic.action")
		public String toSeeCoursesNamePic(Integer cs_id,Model model){
			logger.info("去查看课程图片....");
			List<Courses> courses=this.coursesBiz.findCourses(cs_id);
			model.addAttribute("courses",courses.get(0));
			return 	"backjsp/seeCoursesNamePic";
		}
		//小窗口显示查看图片
		@RequestMapping(value="/toSeeCoursesPic.action")
		public String toSeeCoursesPic(Integer cs_id,Model model){
			logger.info("去查看图片....");
			List<Courses> courses=this.coursesBiz.findCourses(cs_id);
			model.addAttribute("courses",courses.get(0));
			return 	"backjsp/seeCoursesPic";
		}
		
}
