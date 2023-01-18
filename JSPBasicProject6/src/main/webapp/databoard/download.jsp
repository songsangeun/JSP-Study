<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*"%>
<%
   String fn=request.getParameter("fn");
   String path="c:\\download";
   try
   {
	   //1.파일 정보
	   File file=new File(path+"\\"+fn);
	   response.setContentLengthLong(file.length());
	   response.setHeader("content-Disposition", "attachment;filename="
			                 +URLEncoder.encode(fn,"UTF-8"));
	   BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file)); //서버에서 파일 읽기
	   BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream()); //클라이언트 =>파일 복사
	   int i=0;
	   byte[] buffer=new byte[1024];
	   //한번 전송 =>1024byte씩 전송
	   while((i=bis.readNBytes(buffer,0,1024))!=-1) //-1은 EOF(파일 종료)
	   {
		   // i=읽은 바이트 수
		   bos.write(buffer,0,i);
	   }
	   out.clear();
	   out=pageContext.pushBody();
	   bis.close();
	   bos.close();
   }catch(Exception ex){}
   
%>