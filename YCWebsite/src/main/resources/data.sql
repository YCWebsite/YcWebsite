insert into projects(p_name,p_pic,p_developer,p_time,p_addr,todoa,todob) values('a',null,'a','2017-01-01','a',null,null);
select * from projects;
ALTER TABLE projects MODIFY COLUMN p_time date;
commit;
select p_id,p_name,p_pic,p_developer,p_time,p_addr from projects;
delete from projects where p_id in(3,4)

insert into news(n_title,n_content,n_click,n_reportor,todoa,todob) values('刘市长看望孤独老人','昨日下午，刘市长亲自看望孤独老人',1,'谊宝',null,null);
select * from news;
drop table news;
select n_id,n_title,n_content,n_click,n_time,n_sort,n_reportor from news;
select n_id,n_title,n_content,n_click,n_time,n_sort,n_reportor from news where n_id=13 order by n_sort desc    

insert into students(s_name,s_tel,s_direction,s_time,s_status) values('李谊','18274770753','java工程师','2017-03-15',default);
insert into students(s_name,s_tel,s_direction,s_time,s_status) values('刘雅倩','18888888888','软件开发','2017-03-11',default);
insert into students(s_name,s_tel,s_direction,s_time,s_status) values('大熊','16666666666','嵌入式系统','2017-03-16',default);
insert into students(s_name,s_tel,s_direction,s_time,s_status) values('凌栋','15675451679','java工程师','2017-03-18',default);
ALTER TABLE students MODIFY COLUMN s_tel varchar(300);
select s_id,s_name,s_tel,s_direction,s_time,s_status from students  where s_status='有效' order by s_time desc
delete from students where s_id in(19,20,21,22,23,24);

insert into activities(ac_time,ac_illus,ac_pic) values('2016-06-01','全体学生一起参加春游活动',null);
ALTER TABLE activities MODIFY COLUMN ac_time date;

drop table datadict;
commit;
insert into datadict(d_id,d_type,d_desc,todoa,todob,todoc) values('1','页脚文字','电话：0734-8355998,QQ：1728952785,邮政编码：421141,版权所有 &copy; CopyRight 2016 源辰信息科技有限公司,地址：衡阳市解放西路丽天名园905室',null,null,null);
insert into datadict(d_id,d_type,d_desc,todoa,todob,todoc) values('2','关于公司','发布：衡阳源辰IT培训学校   来源：关于源辰   时间：2016-08-03,源辰信息科技是一家定位于大学生软件开发实战培训和企事业单位系统集成、项目研发于一体的综合性软件公司，以大学生高起点就业和为企事业单位提供信息化解决方案为目标。/ 公司是由多名资深项目经理共同组建而成，主要技术骨干在国内外从事多年软件项目研发工作，有在大型软件公司担任多年项目经理的经验；紧跟国内外先进的主流技术，具有较强的软件开发管理和技术指导能力。公司的发展目标是成为领先的软件开发服务商和IT软件工程师的供应商，我们致力于融合先进管理理念和信息技术，为企业和学员创造价值。 /我们的宗旨是服务于学生，致力于企业。',null,null,null);
insert into datadict(d_id,d_type,d_desc,todoa,todob,todoc) values('3','公司历史','创办时间：2014-08-08,介绍：无,创办者：张影,注册资本：不知道',null,null,null);

insert into datadict(d_id,d_type,d_desc,todoa,todob,todoc) values ('c0','首页','index.jsp',null,null,null);
insert into datadict(d_id,d_type,d_desc,todoa,todob,todoc) values ('c1','师资介绍','teacher.jsp',null,null,null);
insert into datadict(d_id,d_type,d_desc,todoa,todob,todoc) values ('c2','课程体系','subject.jsp',null,null,null);
insert into datadict(d_id,d_type,d_desc,todoa,todob,todoc) values ('c3','学员项目','studentProject.jsp',null,null,null);
insert into datadict(d_id,d_type,d_desc,todoa,todob,todoc) values ('c4','就业详情','findWork.jsp',null,null,null);
insert into datadict(d_id,d_type,d_desc,todoa,todob,todoc) values ('c5','关于源辰','about.jsp',null,null,null);
insert into datadict(d_id,d_type,d_desc,todoa,todob,todoc) values ('c6','公司历史','company.jsp',null,null,null);
insert into datadict(d_id,d_type,d_desc,todoa,todob,todoc) values ('c7','技术支持','technology.jsp',null,null,null);
select d_id,d_type,d_desc from datadict where d_id like 'c%'
update datadict set d_desc='about' where d_id='c5'

select count(*) from courses

ALTER TABLE jobdetails MODIFY COLUMN jd_salary numeric;

select * from Employ;

select e_id,e_position,e_validtime,e_amount,e_salary,e_detail,e_addr from Employ where 1=1 
order by e_id desc limit 0,5; 

insert into users(u_name,u_password) values('admin','a');
commit;

