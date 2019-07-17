package com.cafe24.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.dto.ProductInfo;
import com.cafe24.vo.CartVo;

@Repository
public class CartDao {

	@Autowired
	private SqlSession sqlSession;


	public int insert(CartVo cartVo) {
		int queryResult = sqlSession.insert("cart.insert", cartVo);
		return queryResult;
	}

	public List<CartVo> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	public int update(CartVo cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(List<String> cartNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
