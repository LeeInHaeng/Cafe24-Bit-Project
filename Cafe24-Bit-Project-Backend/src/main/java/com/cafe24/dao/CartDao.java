package com.cafe24.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.dto.CartDetailDto;
import com.cafe24.dto.CartOptionUpdateDto;
import com.cafe24.vo.CartVo;
import com.cafe24.vo.ProductOptionVo;

@Repository
public class CartDao {

	@Autowired
	private SqlSession sqlSession;

	public int clear() {
		return sqlSession.delete("cart.clear");
	}

	public int insert(CartVo cartVo) {
		int queryResult = sqlSession.insert("cart.insert", cartVo);
		return queryResult;
	}
	
	public int isValidAdd(CartVo cartVo) {
		int queryResult = sqlSession.selectOne("cart.isValidAdd", cartVo);
		return queryResult;
	}

	public List<CartDetailDto> getList(String userid) {
		return sqlSession.selectList("cart.getList", userid);
	}

	public int updateCount(Map<String, Long> params) {
		int queryResult = sqlSession.update("cart.updateCount", params);
		return queryResult;
	}
	
	public List<ProductOptionVo> getOptionList(long productNo) {
		return sqlSession.selectList("cart.getOptionList", productNo);
	}
	
	public int updateOption(CartOptionUpdateDto cartOptionUpdateDto) {
		int queryResult = sqlSession.insert("cart.updateOption", cartOptionUpdateDto);
		return queryResult;
	}

	public int delete(List<Long> cartNo) {
		int queryResult = sqlSession.delete("cart.delete", cartNo);
		return queryResult;
	}

	public int isExistOptionDetailNo(CartOptionUpdateDto cartOptionUpdateDto) {
		int queryResult = sqlSession.selectOne("cart.isExistOptionDetailNo", cartOptionUpdateDto);
		return queryResult;
	}

}
