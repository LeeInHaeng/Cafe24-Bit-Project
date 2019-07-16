package com.cafe24.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.dto.ProductDetailDto;
import com.cafe24.vo.ProductVo;

@Repository
public class ProductDao {

	@Autowired
	private SqlSession sqlSession;

	public List<ProductVo> getSearchList(String keyword, Long pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	public ProductDetailDto get(Long productNo) {
		ProductDetailDto productDto = sqlSession.selectOne("product.getDetail", productNo);
		return productDto;
	}

	public int getTotalCount(String categoryName) {
		int count = sqlSession.selectOne("product.getTotalCount", categoryName);
		return count;
	}
	
	public List<ProductVo> getList(String categoryName, long pageNo, int showSize) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "category", categoryName );
		map.put( "startIndex", (pageNo-1)*showSize );
		map.put( "size", showSize );
		
		return sqlSession.selectList( "product.getList", map );
	}

	public int getTotalCountWithSearch(String keyword) {
		int count = sqlSession.selectOne("product.getTotalCountWithSearch", keyword);
		return count;
	}

	public List<ProductVo> getListWithSearch(String keyword, Long pageNo, int showSize) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "keyword", keyword );
		map.put( "startIndex", (pageNo-1)*showSize );
		map.put( "size", showSize );
		
		return sqlSession.selectList( "product.getListWithSearch", map );
	}
}
