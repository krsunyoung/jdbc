package com.bit2016.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HRSearchEmployee {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try {
			 String name =scan.nextLine();
			//1.JDBC 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.connection 얻어오기
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			 conn = DriverManager.getConnection(url,"hr","hr");

			 //3.statement 준비
			 String sql =
				"select employee_id, first_name,last_name ,hire_date from employees where first_name like ? OR last_name like ?";
			 pstmt = conn.prepareStatement(sql) ;
			 
			 //4. 값 바인딩

			 
			 pstmt.setString(1, "'%'"+name+"'%'");
//			 pstmt.setString(2, "%"+name+"%");
			 rs = pstmt.executeQuery(sql);
			 while(rs.next()){
				 //column 에 있는 데이터 타입대로 적어주면 됨. 
				 Long employeeId = rs.getLong(1);
				 String firstName = rs.getString(2);
				 String lastName = rs.getString(3);
				 Date date = rs.getDate(4);
				 
				 System.out.println(employeeId + ":" + firstName+":"+lastName+":"+date);
			 }
			 //5.sql 실행
//			 ResultSet count = pstmt.executeQuery(sql);
//			 System.out.println( count );
		
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 :" +e);;
		} catch (SQLException e ){
			System.out.println("error" +e);;
			
		} finally{
			try {
				//3 자원정리
				if(rs != null ){
					rs.close();
				}
				if(pstmt != null ){
					pstmt.close();}
				if(conn != null ){
				conn.close();}
				if(scan != null ){
					scan.close();}
			} catch (SQLException e) {
				System.out.println("error2" +e);;

			}
		}
		
	}
}
