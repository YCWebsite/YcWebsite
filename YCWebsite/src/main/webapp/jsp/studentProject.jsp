<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>源辰信息科技官网</title>
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
	<link rel="stylesheet" href="css/subject.css">
	
    <script src="js/jquery.js" type="text/javascript"></script>
    <script src="js/jquery.lazyload.js" type="text/javascript"></script>
    <script src="js/teacher.js" type="text/javascript"></script>
    
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

<div class="kctx" style="height:1500px;font-size: 100px;">
	<div class="kctx_title">
		衡阳源辰IT培训学校 > 学员项目
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
</body>
</html>