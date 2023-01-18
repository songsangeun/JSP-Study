package com.sist.dao3;
import java.util.*;
import java.sql.*;
import com.sist.common.*;
/*
 *   중복 : 한개파일  ====> 메소드 
 *         여러개 파일 ==> 클래스 (.jar)
 *         --------------------------- 재사용 
 */
public class SeoulDAO {
   private Connection conn;
   private PreparedStatement ps;
   private CreateConnection dbConn=new CreateConnection();
   public void getConnection()
   {
	   conn=dbConn.getConnection();
   }
   public void disConnection()
   {
	   dbConn.disConnection(ps, conn);
   }
   public ArrayList<SeoulVO> seoulData(String table)
   {
	   ArrayList<SeoulVO> list=new ArrayList<SeoulVO>();
	   try
	   {
		   getConnection();
		   String sql="SELECT no,title,poster,rownum "
				     +"FROM seoul_"+table
		             +" WHERE rownum<=20";
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   SeoulVO vo=new SeoulVO();
			   vo.setNo(rs.getInt(1));
			   vo.setTitle(rs.getString(2));
			   vo.setPoster(rs.getString(3));
			   list.add(vo);
		   }
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   disConnection();
	   }
	   return list;
   }
   
}