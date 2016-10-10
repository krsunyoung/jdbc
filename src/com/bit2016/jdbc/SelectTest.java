package com.bit2016.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {
	public static void main(String[] args) {
		
		Connection conn =null;
		Statement stmt = null;
		ResultSet rs= null;
		try {
			//1.JDBC 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.connection 얻어오기
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			 conn = DriverManager.getConnection(url,"hr","hr");

			//3.Statement(문장) 객체 생성
			 stmt = conn.createStatement();
			 
			//4.sql문 실행
			 String sql = "select employee_id, first_name, salary from employees";
			 rs = stmt.executeQuery(sql);
			 
			 //5.데이터 결과(Result Set) 가져오기
			 while(rs.next()){
				 //column 에 있는 데이터 타입대로 적어주면 됨. 
				 Long employeeId = rs.getLong(1);
				 String firstName = rs.getString(2);
				 Integer salary = rs.getInt(3);
				 
				 System.out.println(employeeId + ":" + firstName+":"+salary);
			 }
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
				if(stmt != null ){
					stmt.close();
				}
				if(conn != null ){
				conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}