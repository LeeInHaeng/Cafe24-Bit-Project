package com.cafe24.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDao {

	@Autowired
	private SqlSession sqlSession;

	public int insert(long productNo, String quantity, List<String> productOptionDetailNo) {
		// TODO Auto-generated method stub
		return 0;
	}
}
