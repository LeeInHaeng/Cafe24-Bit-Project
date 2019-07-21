package com.cafe24.dao;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.dto.OrderBuyDto;
import com.cafe24.dto.OrderHistoryDto;
import com.cafe24.dto.OrderProductDto;
import com.cafe24.dto.ProductOptionDto;
import com.cafe24.vo.NonmemberVo;
import com.cafe24.vo.OrderProductVo;
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
	
	public boolean memberBuy(OrderBuyDto orderBuyDto) {
		int queryResult = sqlSession.insert("order.memberBuy", orderBuyDto);
		return queryResult==1;
	}
	
	public boolean nonmemberBuy(OrderBuyDto orderBuyDto) {
		int queryResult = sqlSession.insert("order.nonmemberBuy", orderBuyDto);
		return queryResult==1;
	}
	
	public long checkPrice(List<ProductOptionDto> productOptionDto) {
		long totalPrice = sqlSession.selectOne("order.checkPrice", productOptionDto);
		return totalPrice;
	}
	
	public void clear() {
		sqlSession.delete("order.clear");
	}

	public OrderHistoryDto showProductHistoryMember(String memberId) {
		return sqlSession.selectOne("order.showHistoryMember", memberId);
	}

	public OrderHistoryDto showProductHistoryNonmember(NonmemberVo nonmemberVo) {
		return sqlSession.selectOne("order.showHistoryNonmember", nonmemberVo);
	}

	public boolean updateOrderProduct(@Valid OrderProductVo orderProductVo) {
		int queryResult = sqlSession.update("order.updateOrderProduct", orderProductVo);
		return queryResult==1;
	}

}
