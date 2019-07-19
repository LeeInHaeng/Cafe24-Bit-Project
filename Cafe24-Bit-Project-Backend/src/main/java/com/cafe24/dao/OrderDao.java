package com.cafe24.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.dto.OrderPageDto;
import com.cafe24.dto.OrderProductDto;
import com.cafe24.dto.ProductDetailDto;
import com.cafe24.dto.ProductOptionDto;
import com.cafe24.dto.ProductOrder;
import com.cafe24.vo.ProductQuantityVo;

@Repository
public class OrderDao {

	@Autowired
	private SqlSession sqlSession;

	public List<String> getOptionDetailValue(List<Long> productOptionDetailNo) {
		return sqlSession.selectList("order.getOptionValue", productOptionDetailNo);
	}
	
	public ProductQuantityVo getProductQuantity(Map<String, Object> quantityParam) {
		return sqlSession.selectOne("order.getProductQuantity", quantityParam);
	}
	
	public boolean isExistOptionDetailNo(ProductOptionDto dto) {
		int count = sqlSession.selectOne("order.isExistOptionDetailNo", dto);
		return count==dto.getProductOptionDetailNo().size();
	}
	
	public OrderProductDto getProductList(ProductOptionDto dto) {
		return sqlSession.selectOne("order.getProduct", dto);
	}
	
	public boolean decreaseQuantity(Map<String, Object> quantityDto) {
		int queryResult = sqlSession.update("order.decreaseQuantity", quantityDto);
		return queryResult==1;
	}
	
	public int update(ProductOrder productOrder) {
		// TODO Auto-generated method stub
		return 0;
	}

}
