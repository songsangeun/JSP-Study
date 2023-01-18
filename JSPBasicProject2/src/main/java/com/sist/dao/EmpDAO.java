package com.sist.dao;
import java.util.*;
import java.sql.*;
/*
 *   public void insert()
 *   {
 *      try{
 *            getConnection()
 *            conn.setAutoCommit(false);
 *            String sql="";
 *            ps=conn.prepareStatement(sql);
 *            ?
 *            ps.executeUpdate()
 *            conn.commit();
 *      }catch(Exception ex)
 *      {
 *          ex.printStackTrace()
 *          try
 *          {
 *               conn.rollback();
 *          }catch(Exception e){}
 *          
 *      }
 *      finally
 *      {
 *         conn.setAutoCommit(true);
 *         disConnection()
 *      }
 *   }
 *   
 *   @Transactional
 *   public void insert()
 *   {
 *   }
 */
public class EmpDAO {
   private Connection conn;
   private PreparedStatement ps;
   private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
   public EmpDAO()
   {
	   try
	   {
		   Class.forName("oracle.jdbc.driver.OracleDriver");
	   }catch(Exception ex) {}
   }
   public void getConnection()
   {
	   try
	   {
		   conn=DriverManager.getConnection(URL,"hr","happy");
	   }catch(Exception ex) {}
   }
   public void disConnection()
   {
	   try
	   {
		   if(ps!=null) ps.close();
		   if(conn!=null) conn.close();
	   }catch(Exception ex) {}
   }
   // 전체목록 => selectList(sql,?)
   public ArrayList<EmpVO> empListData()
   {
	   ArrayList<EmpVO> list=new ArrayList<EmpVO>();
	   try
	   {
		   getConnection();
		   String sql="SELECT empno,ename,job,mgr,hiredate,sal,comm,deptno "
				     +"FROM emp";
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   EmpVO vo=new EmpVO();
			   vo.setEmpno(rs.getInt(1));
			   vo.setEname(rs.getString(2));
			   vo.setJob(rs.getString(3));
			   vo.setMgr(rs.getInt(4));
			   vo.setHiredate(rs.getDate(5));
			   vo.setSal(rs.getInt(6));
			   vo.setComm(rs.getInt(7));
			   vo.setDeptno(rs.getInt(8));
			   
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
   // 조인 
   public ArrayList<EmpVO> empJoinData()
   {
	   ArrayList<EmpVO> list=new ArrayList<EmpVO>();
	   try
	   {
		   getConnection();
		   String sql="SELECT empno,ename,job,mgr,hiredate,sal,comm,emp.deptno,dname,loc "
				     +"FROM emp,dept "
				     +"WHERE emp.deptno=dept.deptno";
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   EmpVO vo=new EmpVO();
			   vo.setEmpno(rs.getInt(1));
			   vo.setEname(rs.getString(2));
			   vo.setJob(rs.getString(3));
			   vo.setMgr(rs.getInt(4));
			   vo.setHiredate(rs.getDate(5));
			   vo.setSal(rs.getInt(6));
			   vo.setComm(rs.getInt(7));
			   vo.setDeptno(rs.getInt(8));
			   vo.getDvo().setDname(rs.getString(9));
			   vo.getDvo().setLoc(rs.getString(10));
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
