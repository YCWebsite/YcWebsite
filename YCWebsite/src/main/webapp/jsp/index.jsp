<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Cache-Control" content="max-age=7200" />
	<meta http-equiv="expires" Content="Fri, 01 Sep 2017 08:00:00 GMT">
	<meta name="Keywords" Content="源辰,源辰信息,源辰信息科技,源辰信息科技官网,源辰信息科技有限公司">
	<meta name="Description" Content="公司是由多名资深项目经理共同组建而成，主要技术骨干在国内外从事多年软件项目研发工作，有在大型软件公司担任多年项目经理的经验；紧跟国内外先进的主流技术，具有较强的软件开发管理和技术指导能力。公司的发展目标是成为领先的软件开发服务商和IT软件工程师的供应商，我们致力于融合先进管理理念和信息技术，为企业和学员创造价值。我们的宗旨是服务于学生，致力于企业。">
	<link rel="shortcut icon" href="images/logo.png">
	<title>源辰信息科技官网</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
	<link rel="stylesheet" media="screen and (min-width: 1024px)" href="css/newcomputer.css">
	<link rel="stylesheet" media="screen and (min-width: 768px) and (max-width: 1024px)" href="css/pad.css">
	<link rel="stylesheet" media="screen and (max-width: 768px)" href="css/phone.css">
</head>
<body>
	<div id="header">
		<div id="logo" class="site-logo">
			<div>
				<h1>源辰信息科技</h1>
				<h3>yuan chen info tech</h3>
			</div>
		</div>
		<ul id="nav">
			<li class="li1"><a href="index.action">${titles[0].d_type}</a></li><h1 class="h1"></h1>
			<li class="li2"><a href="teacher.action">${titles[1].d_type}</a></li>
			<li class="li3"><a href="subject.action">${titles[2].d_type}</a></li>
			<li class="li4"><a href="studentProject.action">${titles[3].d_type}</a></li>
			<li class="li5"><a href="findWork.action">${titles[4].d_type}</a></li>
			<li class="li6"><a href="about.action">${titles[5].d_type}</a></li>
			<li class="li7"><a href="company.action">${titles[6].d_type}</a></li>
			<li class="li8"><a href="technology.action">${titles[7].d_type}</a></li>
		</ul>
	</div>
	<div id="banner">
		<div id="banner_content">
			<input type="hidden" id="ac" name="ac" value="${aboutCompany}"/>
			<p class="first_des">${aboutCompany.split(',')[1].split('/')[0]}</p>
			<p>${aboutCompany.split('/')[1]}</p>
			<p>${aboutCompany.split('/')[2]}</p>
		</div>
	</div>
	<div id="content">
		<div id="teacher">
			<h3>师资介绍</h3>

            <div id="toggle_team">
                <span class="choice_team">教学团队</span>
                <span>管理团队</span>
                <i id="team_underline"></i>
            </div>

			<div id="teacher_content">
				<div id="teacher_team">
					<ul >
					  	 <li id="zy">
                            <img src="images/loading.gif" data-original="${teachers[0].t_pic}" />
                            <h4>${teachers[0].t_name}老师</h4>
                            <p>${teachers[0].t_motto}</p>
                            <p id="zy_intro" class="teacher_intro"><span>技术方向：</span>${teachers[0].t_skill}</p>
                        </li>
						<li id="zhj">
                            <img src="images/loading.gif" data-original="${teachers[1].t_pic}" />
                            <h4>${teachers[1].t_name}老师</h4>
                            <p>${teachers[1].t_motto}</p>
                            <p id="zhj_intro" class="teacher_intro"><span>技术方向：</span>${teachers[1].t_skill}</p>
                        </li>
                        <li id="wx">
                            <img src="images/loading.gif" data-original="${teachers[2].t_pic}" />
                            <h4>${teachers[2].t_name}老师</h4>
                            <p>${teachers[2].t_motto}</p>
                            <p id="wx_intro" class="teacher_intro"><span>技术方向：</span>${teachers[2].t_skill}</p>
                        </li>
                        <li id="jp">
                            <img src="images/loading.gif" data-original="${teachers[3].t_pic}" />
                            <h4>${teachers[3].t_name}老师</h4>
                            <p>${teachers[3].t_motto}</p>
                            <p id="jp_intro" class="teacher_intro"><span>技术方向：</span>${teachers[3].t_skill}</p>
                        </li>
                        <li id="yt">
                            <img src="images/loading.gif" data-original="${teachers[4].t_pic}" />
                            <h4>${teachers[4].t_name}老师</h4>
                            <p>${teachers[4].t_motto}</p>
                            <p id="yt_intro" class="teacher_intro"><span>技术方向：</span>${teachers[4].t_skill}</p>
                        </li>
						<li id="fpc">
                            <img src="images/loading.gif" data-original="${teachers[5].t_pic}" />
                            <h4>${teachers[5].t_name}老师</h4>
                            <p>${teachers[5].t_motto}</p>
                            <p id="fpc_intro" class="teacher_intro"><span>技术方向：</span>${teachers[5].t_skill}</p>
                        </li>
                        <li id="ll">
                            <img src="images/loading.gif" data-original="${teachers[6].t_pic}" />
                            <h4>${teachers[6].t_name}老师</h4>
                            <p>${teachers[6].t_motto}</p>
                            <p id="ll_intro" class="teacher_intro"><span>技术方向：</span>${teachers[6].t_skill}</p>
                        </li>
                        <li id="ljh">
                            <img src="images/loading.gif" data-original="${teachers[7].t_pic}" />
                            <h4>${teachers[7].t_name}老师</h4>
                            <p>${teachers[7].t_motto}</p>
                            <p id="ljh_intro" class="teacher_intro"><span>技术方向：</span>${teachers[7].t_skill}</p>
                        </li>
                        
					</ul>
                </div>
                <div id="manage_team" style="display: none;">
					<ul class="team_zjsz">
						 <li id="ly">
							<img src="images/loading.gif" data-original="${team[0].t_pic}" />
							<h4>${team[0].t_name}老师</h4>
							<p>${team[0].t_job}</p>
							<p id="ly_intro" class="teacher_intro"><span>经验：</span>${team[0].t_skill}</p>
						</li>
						<li id="hcy">
							<img src="images/loading.gif" data-original="${team[1].t_pic}" />
							<h4>${team[1].t_name}老师</h4>
							<p>${team[1].t_job}</p>
							<p id="hcy_intro" class="teacher_intro"><span>经验：</span>${team[1].t_skill}</p>
						</li>
					</ul>
                </div>
			</div>
		</div>
		<div id="course">
			<h3>课程体系</h3>
			<div id="course_content">
				<ul>
					<li class="j2ee course_current">
						<span>${courses[0].cs_name}</span>
					</li>
					<li class="html5">
						<span>${courses[1].cs_name}</span>
					</li>
					<li class="ubuntu">
						<span>${courses[3].cs_name}</span>
					</li>
					<li class="cloud">
						<span>${courses[2].cs_name}</span>
					</li>
				</ul>
				<div id="course_img">
					<img src="images/loading.gif" data-original="${courses[0].cs_pic}" alt="${courses[0].cs_name}">
				</div>
			</div>
		</div>
		<div id="project">
			<h3>学员项目</h3>
			<ul>
				<c:if test="${projects!=null}">
			        <c:forEach items="${projects}" var="p">
			        	<li><img src="images/loading.gif" data-original="${p.p_pic}" alt="${p.p_name}"></li>
			        </c:forEach>
            	</c:if>
			</ul>
		</div>
		<div id="work">
			<h3>就业详情</h3>
			<div id="jobbox">
				<ul>
					<c:if test="${jobDetails!=null}">
				        <c:forEach items="${jobDetails}" var="jd">
				        	<li><img src="images/loading.gif" data-original="${jd.jd_pic }" alt="">
								<div>
									<p>${jd.jd_name } ${jd.jd_salary }/月</p>
								</div>
							</li>
				        </c:forEach>
            		</c:if>
				</ul>
			</div>
		</div>

	</div>
	<div id="active">
		<h1>公司活动</h1>
		<div id="act_content">
			<ul>
				<c:if test="${pic!=null}">
				        <c:forEach items="${pic.split(',')}" var="pi">
				        	<li><a target="_blank" href="${pi}"><img src="${pi}"></a></li>
				        </c:forEach>
            	</c:if>
			</ul>
		</div>
	</div>
	<div id="footer">
		<div class="address">
			<p>
				<span>${footWords.split(',')[0]}</span>
				<span>${footWords.split(',')[1]}</span>
				<span>${footWords.split(',')[2]}</span>
			</p>
			<p>
				<span>${footWords.split(',')[3]}</span>
				<span>${footWords.split(',')[4]}</span>
			</p>
		</div>
	</div>


	<div id="floatBox">
        <a id="arrow_up">
            <span class="gotop"></span>
            <span class="text">回到顶部</span>
        </a>
        <a id="code"   class="a2">
            <span class="code"></span>
            <span class="text">扫码重播</span>
            <img src="images/code.png"/>
        </a>
        <a id="qq_converse" class="a3" href="tencent://Message/?Uin=1728952785&websiteName=www.hyycinfo.com&Menu=yes">
            <span class="kefu"></span>
            <span class="text">Q Q交谈</span>
        </a>
		<!--<a id="qq_converse"  class="a3" href="tencent://Message/?Uin=1728952785&websiteName=www.hyycinfo.com&Menu=yes"></a>-->
	</div>
	<script src="../js/jquery.js" type="text/javascript"></script>
	<script src="../js/index.js" type="text/javascript"></script>
	<script src="../js/jquery.lazyload.js" type="text/javascript"></script>
	<script src="../js/newindex.js" type="text/javascript"></script>
</body>
</html>