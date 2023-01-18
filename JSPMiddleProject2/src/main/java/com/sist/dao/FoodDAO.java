package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
//Connection을 미리 생성 =>생성된 Connection의 주소를 얻어서 사용
//Connection의 객체 생성을 제한 =>메모리 절약
//연결에 소요되는 시간을 없앨 수 있다 =>연결 속도를 빠르게
//쉽게 서버가 다운되지 않는다
//웹 프로그램의 일반화

import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;
public class FoodDAO {
	private Connection conn; //미리 연결됨 (톰캣)
	private PreparedStatement ps; //송수신
	//Connection의 주소 얻기
	public void getConnection()
	{
		try
		{
			Context init=new InitialContext();
			Context c=(Context)init.lookup("java://comp/env");
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
		}catch(Exception ex) {}
	}
	//반환
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
			//POOL에 반환(POOL: Connection객체가 저장된 메모리 공간)
		}catch(Exception ex) {}
	}
	//DB연동 =>기능
	//1.카테고리 읽기
	public ArrayList<CategoryVO> categoryListData()
	{
		ArrayList<CategoryVO> list=new ArrayList<CategoryVO>();
		try
		{
			getConnection();
			String sql="SELECT /*+ INDEX_ASC(project_category pc_cno_pk)*/cno,title,subject,poster "
					+"FROM project_category";
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
	//카테고리별 맛집 목록
	public ArrayList<FoodVO> foodListData(int cno)
	{
		ArrayList<FoodVO> list=new ArrayList<FoodVO>();
		try
		{
			getConnection();
			String sql="SELECT /*+ INDEX_ASC(project_food pf_fno_pk)*/ fno,name,score,address,tel,type,poster "
					+"FROM project_food "
					+"WHERE cno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FoodVO vo=new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setScore(rs.getDouble(3));
				String addr=rs.getString(4);
				addr=addr.substring(0,addr.lastIndexOf("지")); //${fn:substring()}
				vo.setAddress(addr.trim());
				vo.setTel(rs.getString(5));
				vo.setType(rs.getString(6));
				String poster=rs.getString(7);
				poster=poster.substring(0,poster.indexOf("^"));
				vo.setPoster(poster);
				
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
	//카테고리 정보
	public CategoryVO categoryInfoData(int cno)
	{
		CategoryVO vo=new CategoryVO();
		try 
		{
			getConnection();
			String sql="SELECT title,subject FROM project_category "
					+ "WHERE cno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ResultSet rs=ps.executeQuery();
			vo.setTitle(rs.getString(1));
			vo.setSubject(rs.getString(2));
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return vo;
	}
	
	//DAO(오라클에 저장된 데이터를 가지고 온다) <=====> JSP
	
	 public FoodVO foodDetailData(int fno)
	   {
		   FoodVO vo=new FoodVO();
		   try
		   {
			   getConnection();
			   String sql="SELECT fno,name,score,poster,tel,type,time,parking,menu,price,address "
					     +"FROM food_location "
					     +"WHERE fno=?";
			   ps=conn.prepareStatement(sql);
			   ps.setInt(1, fno);
			   ResultSet rs=ps.executeQuery();
			   rs.next();
			   vo.setFno(rs.getInt(1));
			   vo.setName(rs.getString(2));
			   vo.setScore(rs.getDouble(3));
			   vo.setPoster(rs.getString(4));
			   vo.setTel(rs.getString(5));
			   vo.setType(rs.getString(6));
			   vo.setTime(rs.getString(7));
			   vo.setParking(rs.getString(8));
			   vo.setMenu(rs.getString(9));
			   vo.setPrice(rs.getString(10));
			   vo.setAddress(rs.getString(11));
			   rs.close();
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
		   finally
		   {
			   disConnection();
		   }
		   return vo;
	   }
     
}


