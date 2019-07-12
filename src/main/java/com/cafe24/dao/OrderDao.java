package com.cafe24.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.dto.ProductOrder;

@Repository
public class OrderDao {

	@Autowired
	private SqlSession sqlSession;

	public int update(ProductOrder productOrder) {
		// TODO Auto-generated method stub
		return 0;
	}
}
