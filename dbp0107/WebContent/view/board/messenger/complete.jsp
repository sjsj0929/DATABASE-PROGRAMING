<!-- 메신저 연결 신청 완료  -->
<%@page contentType="text/html; charset=utf-8" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	department dept = (department)request.getAttribute("department");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>메신저 연결 신청 완료</title>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
  <a>메신저 연결 신청이 완료되었습니다.</a>  
  <br>
  <a href="<c:url value='/view/board/messenger/connect'>
  			<c:param name='department_no' value='<%= dept.getDept_no() %>'/>
  		   </c:url>">연결</a>
</body>
</html>