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
    <link rel="stylesheet" href="css/about.css">
    <link rel="stylesheet" media="screen and (min-width: 768px) and (max-width: 1024px)" href="css/pad.css">
    <link rel="stylesheet" media="screen and (max-width: 768px)" href="css/phone.css">

    <script src="js/jquery.js" type="text/javascript"></script>
    <script src="js/jquery.lazyload.js" type="text/javascript"></script>
    <script src="js/teacher.js" type="text/javascript"></script>
    <script src="js/about.js" type="text/javascript"></script>
    
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

<div id="main">
    <div id="main_L">
        <a href="javascript:void(0)"><img src="images/topAd.jpg"/></a>
        <div class="main_L_d">
            <p class="teacher_title">衡阳源辰IT培训学校 > 关于源辰</p>
            <div class="aboutMe">
                <h3>关于源辰</h3>
                <p class="about_title">${aboutCompany.split(',')[0]}</p>
                <p>
                   ${aboutCompany.split(',')[1].split('/')[0]}
                   ${aboutCompany.split(',')[1].split('/')[1]}
                   ${aboutCompany.split(',')[1].split('/')[2]} 
                </p>
            </div>
        </div>
        <div class="main_L_d2">
            <p class="teacher_title">报名入口</p>
            <div class="aboutMe">
                <h3>报名申请</h3>
                <form action="add_students.action" onSubmit="return sureApply()"  method="post">
                	<table width="520" height="250" cellspacing="0" cellpadding="0" border="0">
                		<tr>
                			<td style="width:70px;">姓名:</td>
                			<td><input type="text" name="s_name" id="s_name" onBlur="javascript:checkName()"/>&nbsp;&nbsp;<label id="check1" style="width:120px;"></label></td>
                		</tr>
                		<tr>
                			<td style="width:70px;">手机号:</td>
                			<td><input type="text" name="s_tel" id="s_tel" onBlur="javascript:checkTel()"/>&nbsp;&nbsp;<label id="check2" style="width:120px;"></label></td>
                		</tr>
                		<tr>
                			<td style="width:70px;">班级:</td>
                			<td><input type="text" name="s_direction" id="s_direction" readOnly="true"/>&nbsp;&nbsp;<label style="font-size:12px;">注意：班级不可更改</label></td>
                		</tr>
                		<tr>
                			<td></td>
                			<td colspan="2"><input type="submit" value="确认报名"/></td>
                		</tr>
                	</table>
                </form>
            </div>
        </div>
    </div>
    <div id="main_R">
        <div class="kaiban">
            <p class="kbtitle">最新开班日期 | <a href="">更多</a></p>
            <div class="kb1">
                <dd><img src="images/qianrushi.jpg"/></dd>
                <dd class="kbtext">
                    <h5>嵌入式培训班</h5>
                    <p>开班日期：2月28日</p>
                    <a href="" class="kbtextInp1">我要咨询</a>
                    <a href="javascript:void(0)" onclick="apply()" class="kbtextInp2">我要报名</a>
                </dd>
            </div>
            <div class="kb1">
                <dd><img src="images/bigdata.jpg"/></dd>
                <dd class="kbtext">
                    <h5>大数据培训班</h5>
                    <p>开班日期：2月28日</p>
                    <a href="" class="kbtextInp1">我要咨询</a>
                    <a href="javascript:void(0)" onclick="apply()" class="kbtextInp2">我要报名</a>
                </dd>
            </div>
            <div class="kb1">
                <dd><img src="images/qianduan.jpg"/></dd>
                <dd class="kbtext">
                    <h5>Web前端培训班</h5>
                    <p>开班日期：2月28日</p>
                    <a href="" class="kbtextInp1">我要咨询</a>
                    <a href="javascript:void(0)" onclick="apply()" class="kbtextInp2">我要报名</a>
                </dd>
            </div>
            <div class="kb1">
                <dd><img src="images/javaLogo.jpg"/></dd>
                <dd class="kbtext">
                    <h5>JAVA培训班</h5>
                    <p>开班日期：2月28日</p>
                    <a href="#" class="kbtextInp1">我要咨询</a>
                    <a href="javascript:void(0)" onclick="apply()" class="kbtextInp2">我要报名</a>
                </dd>
            </div>

            <p class="ycNews">源辰新闻 | <a href="">更多</a></p>
            <ul id="news">
                <c:if test="${news!=null}">
				        <c:forEach items="${news}" var="n">
				        	<li><a href="">+${n.n_title}</a></li>
				        </c:forEach>
            	</c:if>
            </ul>
        </div>
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