<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
   String name="신진호";
%>    
<c:set var="name" value="<%=name %>"/> <%-- request.setAttribute("name","신진호") --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>&lt;c:set var="key" value="실제값"&gt; = request.setAttribute("key","value"):544p</h1>
${name }<br>
<c:out value="${name }"/> <%-- <c:out>: 자바스크립트에서 출력: $=>JQurey --%>
</body>
</html>