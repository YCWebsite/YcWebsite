drop table member;
drop table pay_vip;
drop table rule;
drop table consume;
drop table consume_cp ;
drop table total_consume ;
drop table  product;

drop sequence seq_member_id;
--会员表
--会员编号--名称--电话--密码--余额--注册时间（例如 2017-04-08）--状态
create table member(
	id int primary key,		
	name varchar2(20),		
	tel varchar2(11),		
	pwd varchar2(16),		
	money float,			
	reg_date date,	
	userid int,  --操作人
	status int default 1 check (status in (0,1))
	
);
create sequence seq_member_id increment by 1 start with 100001;
select * from member where name ='皮皮虾';
insert into member values(seq_member_id.nextval,'象拔蚌','13974812568','123456',1000,sysdate,default);
insert into member values(seq_member_id.nextval,'象拔蚌2','13974812567','123456',1000,sysdate,default);

update member set id=100020 where name ='象拔蚌';
update member set tel ='13974812568' where id=2;
--套餐表
--套餐编号--套餐名称--套餐详情--会员价--非会员价
create table rule(
	rid int primary key,	
	rname varchar2(20),		
	rnote varchar2(200),	
	mem_money float,		
	pub_money float,
	userid int  
);

select * from rule ;
create sequence seq_rule_rid start with 2;
select rid,rname,rnote,mem_money,pub_money,userid from rule;
select * from USERINFO;

insert into rule values(1,'套餐一','洗脚',100,125,1);
select  rid,rname,mem_money,(2*mem_money) money from  rule  where rname ='套餐一';
drop table rule;
--套餐消费详情单表
--消费总单号--套餐编号 --套餐消费数量 
create table consume(
	tc_id int,						
	rid int,				
	cnum int			
);

select * from consume where tc_id = 100001
insert into consume values(100001,1,1);
create sequence seq_consume_id increment by 1 start with 100001;
select * from consume where tc_id = '100015';
select * from consume;

alter table consume ADD CONSTRAINT FK_consume_tc_id foreign key(tc_id) references total_consume(tc_id) on delete cascade;
alter table consume ADD CONSTRAINT FK_consume_rid foreign key(rid) references rule(rid) on delete cascade;
alter table consume ADD CONSTRAINT FK_consume_pid foreign key(pid) references product(pid) on delete cascade;

--产品消费详情单
--产品编号--产品消费数量--消费总单号
create table consume_cp(
	pid int,				
	cs_num float,			
	tc_id int				
);

select * from consume_cp where tc_id = 100001
create sequence seq_consume_cp_id increment by 1 start with 100001;
insert into consume_cp values(1,2,1001);

select tc.tc_id,tc_date,ys_money,sh_money,userid,u.name uname,pid,cs_num from total_consume tc
left join userinfo u on tc.userId=u.id 
left join consume_cp cc on cc.tc_id=tc.tc_id
where tc.id is null and  tc_date between to_date('2017-04-15','yyyy-MM-dd')  and to_date('2017-05-15','yyyy-MM-dd')
order by tc_date desc

select * from consume_cp where tc_id = '100042';
drop table total_consume;
--消费总单表
--消费总单号--会员编号(不设外键)  --如果是会员则写，如果不是会员则为空  这里不设约束
--消费时间（例如 2017-04-08）--应收金额--实收金额--操作人
create table total_consume(
	tc_id int primary key,	
	id int,					
	tc_date date,	
	ys_money float,			
	sh_money float, 		
	userid int 
);

select * from total_consume;

create sequence seq_tc_id increment by 1 start with 100001;
insert into total_consume values(100001,null,sysdate,380,400,1);

insert into total_consume values(100002,null,to_date('2017-04-15','yyyy-MM-dd'),3000,3000,1);
insert into total_consume values(100003,null,sysdate,580,600,1);
insert into total_consume values(100004,100020,sysdate,580,600,1);

select * from MEMBER;

select distinct sum(ys_money) money 
from total_consume 
where id is null and  tc_date between to_date('2017-04-15','yyyy-MM-dd')  and to_date('2017-05-15','yyyy-MM-dd')
order by tc_date desc


--产品表
--产品编号--产品名称--库存--产品计数类型（比如 克，斤，片）--单价
create table product(
	pid int primary key,	
	pname varchar2(20),		
	pnum float,				
	ptype varchar2(10),		
	price float,
	userid int  --操作人
);
create sequence seq_product_id start with 2;

insert into product values(1,'药水',10,'克',100);
select  * from  product;
--会员充值表
--充值编号--会员编号--充值金额--充值日期 --操作人
create table pay_vip(
	id int primary key,		
	vid int,				
	ch_money float,			
	ch_date date,
	userid int
);


select * from member;
select * from pay_vip;

update pay_vip set vid=100021 where id in(3,4);

insert into member values(seq_member_id.nextval,'象拔蚌','13974812568','123456',1000,sysdate,default);


--月报表
select p.id pid,vid,ch_money,ch_date,userId,
u.name uname,m.tel mtel,
m.name mname from PAY_VIP p 
left join member m on vid=m.id 
left join userinfo u on p.userId=u.id 
where ch_date between to_date('2017-01-01','yyyy-MM-dd')  and to_date('2018-01-01','yyyy-MM-dd') order by ch_date desc


select * from PAY_VIP

delete from PAY_VIP where vid is null;

create sequence seq_pay_vip_id increment by 1 start with 100001;
alter table pay_vip ADD CONSTRAINT FK_pay_vip_vid foreign key(vid) references member(id) on delete cascade;

insert into pay_vip values(seq_pay_vip_id.nextval,(select id from member where tel = '18873462694'),100,sysdate);
insert into pay_vip values(seq_pay_vip_id.nextval,(select id from member where name = '皮皮虾' and tel = '18873462694'),100,sysdate)
select * from pay_vip;
-- 100002 芽儿哦  18873462694 123456  1000 2017-04-12 10:51:07.0      1
-- 100022   NULL 18873462694 2017-04-12 10:51:07.0

select distinct sum(ch_money) money from PAY_VIP where ch_date between to_date('2017-01-01','yyyy-MM-dd')  and to_date('2017-12-23','yyyy-MM-dd') 
--支出表
drop table expend;
--支出编号--支出详情
create table expend(
	ex_id int primary key,	
	ex_name varchar2(50),	
	ex_money float,			
	ex_date date	,
	userid int
)
select * from expend;
create sequence seq_expend_id start with 1;

select  distinct sum(ex_money)money,ex_name,ex_money from expend 
where ex_date between to_date('2017-02-01','yyyy-MM-dd')  
and to_date('2017-04-11','yyyy-MM-dd')
group by ex_name,ex_money


select ex_name,ex_money,ex_date,us.name from expend ex 
inner join userinfo us on us.id=ex.userid ;

delete expend where ex_id=8;
insert into expend values(1,'工资',10000,to_date('2017-01-06','yyyy-mm-dd'),1);
insert into expend values(2,'工资',10300,to_date('2017-02-06','yyyy-mm-dd'),1)
insert into expend values(3,'工资',10020,to_date('2017-03-06','yyyy-mm-dd'),1);
insert into expend values(4,'工资',900,to_date('2017-02-07','yyyy-mm-dd'),1);

insert into expend values(5,'工资',900,to_date('2016-02-07','yyyy-mm-dd'),1);
insert into expend values(6,'工资',800,to_date('2016-03-07','yyyy-mm-dd'),1);
insert into expend values(7,'工资',900,to_date('2016-02-09','yyyy-mm-dd'),1);

insert into expend values(9,'工资',10000,to_date('2017-04-09','yyyy-mm-dd'),1);

--admin
drop table userinfo
create table userinfo(
	id int primary key,		--用户编号
	name varchar2(16),		--用户名
	pwd varchar2(16),		--密码
	email varchar2(30)		--绑定邮箱
)
select * from userinfo ;
insert into userinfo(id,name,pwd,email) values(1,'shumei1','123456','1184131378@qq.com');
insert into userinfo(id,name,pwd,email) values(2,'shumei2','123456','1184131378@qq.com');
update  userinfo set  name='shumei1' where  email='1184131377@qq.com';


select c.tc_id,m.name,tc_date,r.rname,p.pname,c.cs_num,r.mem_money tc_money,p.price*c.cs_num cp_money from total_consume tc 
inner join consume c
on tc.tc_id=c.tc_id
inner join member m
on tc.id = m.id
inner join rule r
on c.rid=r.rid
left join product p
on c.pid = p.pid
where m.name='皮皮虾'
order by c.tc_id desc


select m.name,tc_date,r.rname,p.pname,c.cs_num,r.mem_money tc_money,p.price*c.cs_num cp_money from total_consume tc 
inner join consume c
on tc.tc_id=c.tc_id
inner join member m
on tc.id = m.id
inner join rule r
on c.rid=r.rid
left join product p
on c.pid = p.pid
where m.tel='18873462694'
order by tc_date desc

13974812568

select m.id,m.name,r.rname,r.mem_money,c.cnum,r.mem_money*c.cnum,tc.tc_date
from total_consume tc inner join consume c on tc.tc_id=c.tc_id inner join member m on tc.id = m.id inner join product p
on c.pid = p.pid  where m.name='芽儿哦' order by c.tc_id desc

--consume_cp
select m.id,m.name,p.pname,p.price,cp.cs_num,p.price*cp.cs_num,tc.tc_date
from total_consume tc  inner join consume_cp cp on tc.tc_id=cp.tc_id inner join member m on tc.id = m.id inner join product p     
on cp.pid = p.pid   where m.name='芽儿哦' order by cp.tc_id desc


select m.id,m.name,r.rname,r.mem_money,c.cnum,(r.mem_money*c.cnum)allmoney,tc.tc_date
from total_consume tc inner join consume c on tc.tc_id=c.tc_id inner join member m on tc.id = m.id  
inner join rule r on c.rid=r.rid  where m.tel='18873462694' order by c.tc_id desc


select m.id,m.name,r.rname,r.mem_money,c.cnum,(r.mem_money*c.cnum)allmoney,tc.tc_date
from total_consume tc inner join consume c on tc.tc_id=c.tc_id inner join member m on tc.id = m.id
inner join rule r on c.rid=r.rid  where m.name='象拔蚌' order by c.tc_id desc


--查询产品
select m.id,m.name,r.rname,r.mem_money,c.cnum,(r.mem_money*c.cnum)allmoney,tc.tc_date
from total_consume tc inner join consume c on tc.tc_id=c.tc_id inner join member m on tc.id = m.id
inner join rule r on c.rid=r.rid  where m.name='象拔蚌' order by c.tc_id desc

--product 产品表  total_consume 消费总单信息  consume_cp 消费产品详情单 


select tc_date ,rname,cnum,pub_money,(cnum * pub_money)money,us.name uname from userinfo us 
					inner join total_consume tc on us.id = tc.userid 
					inner join consume c on c.tc_id = tc.tc_id 
					inner join  rule r on r.rid=c.rid where tc.id is null and 
					tc_date between to_date('2017-02-01','yyyy-MM-dd')  
					and to_date('2018-02-01','yyyy-MM-dd')


select  * from product;
select * from rule;

delete rule where rid=1;
insert into rule values(1,'套餐二','洗脸',100,130,1);

delete product where pid=1;
insert into product values(1,'套餐一',1,'a',300);


insert into product values(10,'洗脚',10,'次',300);

select * from total_consume;
select * from member;

select  * from consume_cp;
insert into consume_cp values(1,1,100001);

select tc_date ,rname,cnum,pub_money,(cnum * pub_money)money,us.name uname from userinfo us 
inner join total_consume tc on us.id = tc.userid 
					inner join consume c on c.tc_id = tc.tc_id 
					inner join  rule r on r.rid=c.rid where tc.id is null and 
					tc_date between to_date('2017-01-01','yyyy-MM-dd')  
					and to_date('2018-01-01','yyyy-MM-dd')
					
					
					
select m.id,m.name,r.rname,r.mem_money,c.cnum,(r.mem_money*c.cnum)allmoney,tc.tc_date,us.name uname from userinfo us
inner join total_consume tc on us.id = tc.userid inner join consume c on tc.tc_id=c.tc_id 
inner join member m on tc.id = m.id inner join rule r on c.rid=r.rid  where m.name='啊' order by c.tc_id desc;	

select m.id,m.name,p.pname,p.price,cp.cs_num,(p.price*cp.cs_num)allmoney,tc.tc_date,us.name uname from userinfo us
inner join total_consume tc on us.id = tc.userid inner join consume_cp cp on tc.tc_id=cp.tc_id inner join member m on tc.id = m.id
inner join product p on cp.pid = p.pid   where m.name='啊' order by cp.tc_id desc



select m.id id,m.name name ,p.pname pname ,p.price price,cp.cs_num cs_num,(p.price*cp.cs_num)allmoney,tc.tc_date tc_date,us.name uname from userinfo us 
inner join total_consume tc on us.id = tc.userid inner join consume_cp cp on tc.tc_id=cp.tc_id inner join member m on tc.id = m.id 
 inner join product p on cp.pid = p.pid   where m.name='啊' order by cp.tc_id desc
 
 
 select p.id pid,p.vid,ch_money,p.ch_date,p.userId,u.name uname,m.tel mtel,m.name mname from PAY_VIP p 
left join member m on p.vid=m.id left join userinfo u on p.userId=u.id 
where p.ch_date between to_date('2017-4-26','yyyy-MM-dd')  and to_date('2018-4-27','yyyy-MM-dd')
order by p.ch_date desc

 select p.id pid,p.vid,ch_money,p.ch_date,p.userId,u.name uname,m.tel mtel,m.name mname from PAY_VIP p 
left join member m on p.vid=m.id left join userinfo u on p.userId=u.id 
where to_char(p.ch_date,'yyyy-MM-dd') = '2017-04-26'  
order by p.ch_date desc

