package com.bit2016.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit2016.jdbc.vo.AuthorVo;

public class AuthorDao3 {
	public List<AuthorVo> getList(){
		List<AuthorVo> list = new ArrayList<AuthorVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url,"bitdb","bitdb");
			stmt = conn.createStatement();
			
			String sql = "select no, name from author order by no asc";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				AuthorVo vo = new AuthorVo();
				vo.setNo(no);
				vo.setName(name);
				
				list.add(vo);
			}
			
		}catch(ClassNotFoundException e){
			System.out.println("드라이버 로딩 실패 :" + e);
		}catch(SQLException e){
			System.out.println("error :"+e);
		}finally{
			try{
				if(rs != null){
					rs.close();
				}
				if(stmt != null){
					stmt.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(SQLException ex){
				System.out.println("error:"+ex);
			}
		}
		
		return list;
	}
	public void insert(AuthorVo vo){
	
			
			Connection conn =null;
			PreparedStatement pstmt = null;

			try {
				//1.JDBC 드라이버 로딩
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//2.connection 얻어오기
				String url="jdbc:oracle:thin:@localhost:1521:xe";
				 conn = DriverManager.getConnection(url,"bitdb","bitdb");

				 //3.statement 생성
				 String sql = "insert into author Values(author_seq.nextval, ?)";
				 pstmt = conn.prepareStatement(sql);
				 
				 //4.바인딩
				 pstmt.setString(1, vo.getName());
				 
				 int count = pstmt.executeUpdate();
				 System.out.println( count );
				 
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패 :" +e);
			} catch (SQLException e ){
				System.out.println("error" +e);;
				
			} finally{
				try {
					//3 자원정리
				
					if(pstmt != null ){
						pstmt.close();}
					if(conn != null ){
					conn.close();}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}
}
