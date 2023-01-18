<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
      EL => <%= %>을 대체 (화면에 데이터 출력): 표현식
      -- Spring, 실무
      자바 제어문: =>JSTL
      --------------------- 자바를 최소화
      1) 복잡 (HTML/자바가 동시에 적용되어서)
      2) Front/Back =>공동 작업이 가능하게 만듦
      -------------------------------------
      1. 내장객체 (525p)
         1) requestScope: request.getAttribute
         2) sessionScope: session.getAttribute
         3) param: request.getParameter()
         =>사용 방식
           <%= %> ==> ${}
           <%
              String name="신진호";
              request.setAttribute{"name",name}
                                    ---- key
           %>
           ${requestScope.name}
             ------------ ---- key
             생략이 가능 => ${name}
           ${name} =>출력 X
             ---- 변수가 아니다
           <%= name%>
      2. 연산자
      
 --%>
 <%
    String name="신진호";
    // ${}를 이용해서 출력 =>request,session =>추가적으로 담아서 출력 =>setAttribute()
    request.setAttribute("name","신진호");	
    session.setAttribute("name1","송상은");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>일반 변수일 때 출력</h1>
이름: <%=name%> <br> 
<h1>EL을 이용</h1>
이름: ${requestScope.name } <%-- <%= request.getAttribute("name")%> --%>
<br>
이름: ${name } <%-- requestScope 생략 가능 --%>
<br>
<h1>Session에 저장된 데이터 읽기</h1>
이름: ${sessionScope.name }<br>
이름: ${name1 }
</body>
</html>