package com.cafe24.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.dto.AdminProductRegisterDto;
import com.cafe24.dto.AdminProductSearchDto;
import com.cafe24.dto.AdminProductSearchResultDto;

@Repository
public class ProductManageDao {

	@Autowired
	private SqlSession sqlSession;

	public boolean insert(AdminProductRegisterDto adminProductRegisterDto) {
		int queryResult = sqlSession.insert("productmanage.insert", adminProductRegisterDto);
		return queryResult == adminProductRegisterDto.getProductQuantityVo().size();
	}

	public List<AdminProductSearchResultDto> searchProductList(AdminProductSearchDto adminProductSearchDto) {
		return sqlSession.selectList("productmanage.searchProductList", adminProductSearchDto);
	}

	public AdminProductRegisterDto getOneProduct(long productNo) {
		return sqlSession.selectOne("productmanage.getOneProduct", productNo);
	}

	public boolean update(AdminProductRegisterDto adminProductRegisterDto) {
		int queryResult = sqlSession.update("productmanage.update", adminProductRegisterDto);
		System.out.println(queryResult);
		return true;
	}
	
}
