package com.bit2016.jdbc.dao.test;

import com.bit2016.jdbc.dao.AuthorDao;
import com.bit2016.jdbc.vo.AuthorVo;

public class AuthorDaoTest {

	public static void main(String[] args) {
		//insert test
		insertTest();
	}
	public static void insertTest(){
		//가짜 데이터 (Mock data)
		AuthorVo vo = new AuthorVo();
		vo.setNo(200L);
		vo.setName("공자");
		
		AuthorDao dao = new AuthorDao();
		dao.insert(vo);
	}

}
