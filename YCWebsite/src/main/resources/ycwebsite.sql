--管理员登录表
create table users(
	u_id integer primary key auto_increment,
	u_name varchar(300),   
	u_password varchar(300)  
)

--角色表
create table roles(
	r_id integer primary key auto_increment,
	r_name varchar(300)   
)

--新闻表
create table news(
	n_id integer primary key auto_increment,
	n_title varchar(300),    
	n_content varchar(5000), 
	n_click integer,         
	n_time timestamp not null default current_timestamp,       
	n_sort integer,  	     
	n_reportor varchar(300),  
	todoa varchar(5000),
	todob varchar(5000)
)

--学员项目表
create table projects(
	p_id integer primary key auto_increment,
	p_name varchar(300),     
	p_pic varchar(5000),     
	p_developer varchar(300),  
	p_time date,             
	p_addr varchar(500),    
	todoa varchar(5000),
	todob varchar(5000)
)

--就业详情表
create table jobdetails(
	jd_id integer primary key auto_increment,
	jd_pic varchar(5000),       
	jd_name varchar(300),       
	jd_salary numeric,          
	jd_emptime date,            
	jd_company varchar(300),    
	jd_school varchar(500),	   
	jd_profession varchar(500) , 
	todoa varchar(5000),
	todob varchar(5000)
)

--关于公司表 
create table aboutyc(
	a_id integer primary key auto_increment,
	a_title varchar(300),	   
	a_reptor varchar(300),		
	a_source varchar(5000),		
	a_time time,				
	a_content varchar(65535),	
	a_tel integer,				
	a_qq varchar(300),			
	a_address varchar(5000),	
	a_code integer,				
	a_copr varchar(500)	,		
	todoa varchar(5000),
	todob varchar(5000)
);
 --标题--发布者--来源--发布时间--内容--电话--QQ--地址--邮编--版权
--公司历史表
create table history(
	h_id integer primary key auto_increment,
	h_createtime time,			 
	h_content varchar(5000),	
	h_creator varchar(300) ,   	 
	h_reg  numeric,         	
	todoa varchar(5000),
	todob varchar(5000)
)
--创办时间 --介绍--创办者 --注册资本
--标题栏表
create table titles(
	t_id integer primary key auto_increment,
	t_name varchar(300)	,		 --标题名
	todoa varchar(5000),
	todob varchar(5000)
)

--公司活动表
create table activities(
	ac_id integer primary key auto_increment,
	ac_time date, 				
	ac_illus varchar(5000),	 	
	ac_pic varchar(5000),	     
	todoa varchar(5000),
	todob varchar(5000)
)

--师资表
create table teachers(
	t_id integer primary key auto_increment,
	t_title varchar(300), 
	t_name varchar(300),   
	t_job varchar(300),    
	t_motto varchar(500), 
	t_skill varchar(500), 
	t_pic varchar(5000),    
	r_id integer  ,         
	todoa varchar(500),  
	todob varchar(500)
)

--课程体系表
create table courses(
	cs_id integer primary key auto_increment,
	cs_name varchar(500),
	cs_namePic varchar(5000),
	cs_pic varchar(5000),		 
	cs_version varchar(500),     
	cs_text varchar(5000),	     
	todoa varchar(500),
	todob varchar(500)
)

--招聘表
create table employ(
	e_id integer primary key auto_increment,
	e_position varchar(300),	
	e_validtime date,			 
	e_amount integer,			 
	e_salary varchar(300),		    
	e_detail varchar(5000),		
	e_addr varchar(5000),		
	todoa varchar(5000) default '有效',
	todob varchar(5000)
);
select e_id,e_position,e_validtime,e_amount,e_salary,e_detail,e_addr,todoa from Employ 
			where e_validtime<=NOW()
--技术支持
create table technology(
	te_id integer primary key auto_increment,
	te_name varchar(300),		 --发表人
	te_time time,				 --时间
	te_content varchar(5000),	 --内容
	te_click integer,			 --浏览次数
	te_title varchar(300),       --标题
	todoa varchar(5000),
	todob varchar(5000)
)

--学生报名表
create table students(
	s_id integer primary key auto_increment,
	s_name varchar(300),       
	s_tel varchar(300),			   
	s_direction varchar(500),  
	s_time date,
	s_status varchar(300) default '有效'
)

-- 数据字典表
create table datadict(
	d_id varchar(40) primary key,
	d_type varchar(300),
	d_desc varchar(8000),
	todoa varchar(3000),
	todob varchar(3000),
	todoc varchar(3000)
)