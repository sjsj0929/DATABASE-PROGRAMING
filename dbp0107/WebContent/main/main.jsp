<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>동덕여대 신입생 홈페이지</title>
<link rel=stylesheet href="<c:url value='/css/main.css' />" type="text/css">
</head>
<body>
<!-- 상단 -->
<header> 
<%-- <img src="<c:url value='/images/logo.png' />" />   <img src="logo.png"/>  --%>

<img src="<c:url value='/images/main_logo.png' />" />
 </header>

<!-- 상단 밑 배너 메뉴-->
<nav id="menu">
	<ul>
		<li><a class="menulink" href="<c:url value='/board/notice' />">학과</a></li>
		<li><a class="menulink" href="<c:url value='/club/recommend' />">동아리</a></li>
		<li><a class="menulink" href="<c:url value='/lm/list' />">소모임</a></li>
		<li><a class="menulink" href="#">마이페이지</a></li>		
	</ul>
</nav>


<div align="center" class="text">
<a>동덕여대 신입생을 위한 홈페이지입니다.</a>
<br>
<img src="<c:url value='/images/somsom.png' />" />

</div>


</body>
</html>