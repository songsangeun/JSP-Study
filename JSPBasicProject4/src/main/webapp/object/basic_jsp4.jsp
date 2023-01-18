<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%--
    JSP가 변경되면 request는 초기화
 --%>
<body>
  <h1><a href="redirect.jsp?id=admin">클릭</a></h1>
  <%-- 사용자가 보내준 데이터를 유지 가능하게: forward(pageContext) --%>
</body>
</html>