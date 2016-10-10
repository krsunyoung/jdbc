package com.bit2016.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest2 {

	public static void main(String[] args) {
		
		Connection conn =null;
		PreparedStatement pstmt = null;

		try {
			//1.JDBC 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.connection 얻어오기
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			 conn = DriverManager.getConnection(url,"bitdb","bitdb");

			 //3.statement 생성
			 String sql = "insert into book Values(?, ?,sysdate,?,?)";
			 pstmt = conn.prepareStatement(sql);
			 
			 //4.바인딩
			 Long no = 10L;
			 String title = "토지10";
			 //String date = "2016-10-1";
			 String state = "대여중";
			 Long authorNo=1L;
			 
			 pstmt.setLong(1, no);
			 pstmt.setString(2, title);
			 pstmt.setString(3, state);
			 pstmt.setLong(4, authorNo);
			 
			 int count = pstmt.executeUpdate(sql);
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