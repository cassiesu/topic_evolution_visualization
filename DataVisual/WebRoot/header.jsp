<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900|Quicksand:400,700|Questrial" rel="stylesheet" />
<link href="css/default.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/fonts.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/slides.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/flip.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/doclist.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/zoomcircle.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/jquery-ui.css" type="text/css" rel="stylesheet" media="all" />
<link href="css/langnet.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/d3/d3.v3.min.js"></script>
<script type="text/javascript" src="js/d3/d3.layout.cloud.js"></script>
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="js/highcharts/modules/exporting.js"></script>
<script type="text/javascript" src="js/highcharts/highcharts-more.js"></script>

<!-- 注意网址上引用的必须联网才能正常加载css/js文件 -->
</head>

<body>


<div id="header-wrapper">
	<div class="headcontainer">
		<div id="logo">
			<h1><a href="#"><div>Topic Evolution</div>
											<div> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&& Visulization</div></a></h1>
		</div>
		<div id="menu">
			<ul id="menuul">
				 <li><a href="#">About Me</a>
                    <ul>
                        <li><a href="#">Lorem ipsum dolor</a></li>
                        <li><a href="#">Maecenas lacinia sem</a></li>
                        <li><a href="#">Suspendisse fringilla</a></li>
                    </ul>
                </li>
                <li><a href="${pageContext.request.contextPath }/servlet/GraphServlet?display=LH-whole">两会</a>
                    <ul>
                        <li><a href="${pageContext.request.contextPath }/servlet/GraphServlet?display=LH-jiaoyu">科技教育</a></li>
                        <li><a href="${pageContext.request.contextPath }/servlet/GraphServlet?display=LH-jingji">经济发展</a></li>
                        <li><a href="${pageContext.request.contextPath }/servlet/GraphServlet?display=LH-caizheng">政府财政</a></li>
                        <li><a href="${pageContext.request.contextPath }/servlet/GraphServlet?display=LH-falv">法律法规</a></li>
                        <li><a href="${pageContext.request.contextPath }/servlet/GraphServlet?display=LH-huanbao">环保问题</a></li>
                        <li><a href="${pageContext.request.contextPath }/servlet/GraphServlet?display=LH-jiuye">劳动就业</a></li>
                        <li><a href="${pageContext.request.contextPath }/servlet/GraphServlet?display=LH-junshi">军事领域</a></li>
                        <li><a href="${pageContext.request.contextPath }/servlet/GraphServlet?display=LH-sannong">三农问题</a></li>
                        <li><a href="${pageContext.request.contextPath }/servlet/GraphServlet?display=LH-yiliao">医疗疾病</a></li>
                        <li><a href="${pageContext.request.contextPath }/servlet/GraphServlet?display=LH-zhufang">住房问题</a></li>
                    </ul>
                </li>
                <li><a href="${pageContext.request.contextPath }/servlet/GraphServlet?display=MHSL">马航失联</a>
                    <ul>
                        <li><a href="#">Lorem ipsum dolor</a></li>
                        <li><a href="#">Maecenas lacinia sem</a></li>
                        <li><a href="#">Suspendisse fringilla</a></li>
                    </ul>
                </li>
                <li><a href="${pageContext.request.contextPath }/servlet/GraphServlet?display=NIPS">NIPS</a>
                    <ul>
                        <li><a href="#">Lorem ipsum dolor</a></li>
                        <li><a href="#">Maecenas dignissim fermentum luctus</a></li>
                        <li><a href="#">Suspendisse fringilla</a></li>
                    </ul>
                </li>
                <li><a href="#">Support</a></li>
                <li><form action="" id="search-form">
	                    <input type="text">
	                    <a href="#" onClick="document.getElementById('search-form').submit()">Search</a>
                     </form>
                </li>        
			</ul>  
		</div>	     
	</div>
</div>
