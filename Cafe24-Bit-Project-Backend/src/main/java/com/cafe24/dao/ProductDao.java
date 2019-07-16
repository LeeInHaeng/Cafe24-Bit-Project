package com.cafe24.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.dto.ProductSearch;
import com.cafe24.vo.ProductVo;

@Repository
public class ProductDao {

	@Autowired
	private SqlSession sqlSession;

	public List<ProductVo> getList(Optional<String> categoryName, Optional<String> pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProductVo> getSearchList(Optional<String> keyword, Optional<String> pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	public ProductVo get(Optional<String> productNo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProductVo> getList(ProductSearch searchParams) {
		// TODO Auto-generated method stub
		return null;
	}
}
