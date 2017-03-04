--管理员登录表
create table users(
	u_id integer primary key auto_increment,
	u_name varchar(300),    --姓名
	u_password varchar(300)  --密码
)

--角色表
create table roles(
	r_id integer primary key auto_increment,
	r_name varchar(300)   --角色名
)

--师资表
create table teachers(
	t_id integer primary key auto_increment,
	t_title varchar(300),   --头衔
	t_name varchar(300),    --姓名
	t_job varchar(300),     --职位
	t_motto varchar(5000),  --座右铭
	t_skill varchar(5000),  --技术方向/经验
	t_pic varchar(5000),     --头像
	r_id integer            --所属角色
)

--新闻表
create table news(
	n_id integer primary key auto_increment,
	n_title varchar(300),    --标题
	n_content varchar(5000), --内容
	n_click integer,         --点击次数
	n_time time,        --创建时间
	n_sort integer  	     --排序
)

--学员项目表
create table projects(
	p_id integer primary key auto_increment,
	p_name varchar(300),    --项目名
	p_pic varchar(5000),    --项目图片
	p_developer varchar(300),  --开发者
	p_time time             --开发时间
)

--就业详情表
create table jobdetails(
	jd_id integer primary key auto_increment,
	jd_pic varchar(5000),    --照片
	jd_name varchar(300),    --学员名字
	jd_salary numeric,       --学员薪资
	jd_emptime time,         --就业时间
	jd_company varchar(300)	 --所在公司
)

--班级表
create table classes(
	cl_id integer primary key auto_increment,
	cl_name varchar(300),     --班级名
	cl_starttime time,		  --开班时间
	cl_number integer,		  --班级人数
	t_id integer			  --班主任编号
)

--课程表
create table courses(
	c_id integer primary key auto_increment,
	c_name varchar(300),     --课程名
	c_desc varchar(5000),	 --课程描述
	cl_pic varchar(5000)	 --课程图片
)

--关于公司表 
create table aboutyc(
	a_id integer primary key auto_increment,
	a_title varchar(300),	    --标题
	a_reptor varchar(300),		--发布者
	a_source varchar(5000),		--来源
	a_time time,				--发布时间
	a_content varchar(10000),	--内容
	a_tel integer,				--电话
	a_qq varchar(300),			--QQ
	a_address varchar(5000),	--地址
	a_code integer,				--邮编
	a_copr varchar(500)			--版权
)

--公司历史表
create table history(
	h_id integer primary key auto_increment,
	h_createtime time,			 --创办时间
	h_content varchar(5000),	 --介绍
	h_creator varchar(300) ,   	 --创办者
	h_reg  numeric            	 --注册资本
)
--标题栏表
create table titles(
	t_id integer primary key auto_increment,
	t_name varchar(300)			 --标题名
)

--公司活动表
create table activities(
	ac_id integer primary key auto_increment,
	ac_time time, 				 --活动时间
	ac_illus varchar(5000)	 	 --说明
)
--课程体系表
create table coursys(
	cs_id integer primary key auto_increment,
	cs_name varchar(5000),		 --课程名
	cs_pic varchar(5000)		 --图片说明
)
--招聘表
create table employ(
	e_id integer primary key auto_increment,
	e_position varchar(300),	 --职位
	e_validtime time,			 --有效时间
	e_amount integer,			 --招聘人数
	e_salary numeric,		     --工资待遇
	e_detail varchar(5000)		 --详细说明 			
)

--应聘表
create table applicant(
	ap_id integer primary key auto_increment, 
	e_id integer,				 --职位编号
	ap_aname varchar(300),		 --应聘者
	ap_pic varchar(5000),		 --照片
	ap_tel integer,				 --电话
	ap_address varchar(5000),	 --地址
	ap_email varchar(500),	 	 --邮件
	ap_remarks varchar(5000)     --评论	
)

--技术支持
create table technology(
	te_id integer primary key auto_increment,
	te_name varchar(300),		 --发表人
	te_time time,				 --时间
	te_content varchar(5000),	 --内容
	te_click integer,			 --浏览次数
	te_books varchar(5000)		 --推荐书籍
)

--学生表
create table students(
	s_id integer primary key auto_increment,
	s_name varchar(300),      --姓名
	cl_id integer,			  --班级
	s_tel integer,			  --电话
	s_addr varchar(5000),	  --地址
	s_email varchar(500),	  --邮箱
	s_idcard varchar(5000)	  --身份证号码
)






