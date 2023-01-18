package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
public class FoodDAO {
    private Connection conn;
    private PreparedStatement ps;
    // DBCP => 웹에서만 사용이 가능 (Connection => tomcat에서 생성 )=>접속시간을 줄일 수 있다(연결속도가 향상),보안
    // Connection의 개수를 관리할 수 있기 때문에 쉽게 다운도르가 되지 않는다
    /*
     *  select * from project_category
     *  where cno=1;
     *  insert into ~~
     *  => void save()
     *  => CategoryVO findBycno(int cno);
     */
    public void getConnection() // 미리 만들어진 Connection 객체를 가지고 온다
    {
       try {
          //등록된 위치로 접속
          Context init=new InitialContext(); //JNDI (폴더형식)
          Context c=(Context)init.lookup("java://comp/env");
          DataSource ds=(DataSource)c.lookup("jdbc/oracle");
          conn=ds.getConnection();
       }catch(Exception e)
       {
          System.out.println(e.getMessage());
       }
    }
    public void disConnection() //재사용
    {
       try {
          if(ps!=null)ps.close();
          if(conn!=null)conn.close();
          
       }catch(Exception e){}
    }
    public ArrayList<CategoryVO> categoryAllData()
    {
       ArrayList<CategoryVO> list=new ArrayList<CategoryVO>();
       try {
         getConnection(); //주소얻기
         String sql="SELECT cno,title,subject,poster "
                  +"FROM project_category "
                  +"ORDER BY cno ASC";
         ps=conn.prepareStatement(sql);
         ResultSet rs=ps.executeQuery();
         while(rs.next())
         {
            CategoryVO vo=new CategoryVO();
            vo.setCno(rs.getInt(1));
            vo.setTitle(rs.getString(2));
            vo.setSubject(rs.getString(3));
            vo.setPoster(rs.getString(4));
            list.add(vo);
            
         }
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         disConnection();
      }
       return list;
    }
}