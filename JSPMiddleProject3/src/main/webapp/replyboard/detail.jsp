<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
<jsp:useBean id="model" class="com.sist.model.BoardModel"/>
<%
   model.boardDetailData(request, response);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div class="container">
   <div class="row">
     <img src="QnA.jpg" style="width: 800px;height: 150px;" class="img-rounded">
   </div>
   <div style="height: 5px"></div>
  <div class="row">
  <table class="table">
      <tr>
        <th width=20% class="text-center success">번호</th>
        <td width=30% class="text-center">${vo.no }</td>
        <th width=20% class="text-center success">작성일</th>
        <td width=30% class="text-center">${vo.dbday }</td>
      </tr>
       <tr>
        <th width=20% class="text-center success">작성자</th>
        <td width=30% class="text-center">${vo.name }</td>
        <th width=20% class="text-center success">조회수</th>
        <td width=30% class="text-center">${vo.hit }</td>
      </tr>
      <tr>
        <th width=20% class="text-center success">제목</th>
        <td width=30% class="text-center">${vo.subject }</td>
      </tr>  
      
 
      <tr>
        <td colspan="4" class="text-left" valign="top" height="200">
          <pre style="white-space: pre-wrap;border: none;background-color: white;">${vo.content }</pre>
        </td>
      </tr> 
      <tr>
        <td colspan="4" class="text-right">
        <a href="reply.jsp?pno=${vo.no }" class="btn btn-xs btn-warning">답변</a>
          <a href="update.jsp?no=${vo.no }" class="btn btn-xs btn-success">수정</a>
          <a href="delete.jsp?no=${vo.no }" class="btn btn-xs btn-info">삭제</a>
          <a href="list.jsp" class="btn btn-xs btn-warning">목록</a>
        </td>
      </tr>
    </table>
  </div>
  </div>
</body>
</html>