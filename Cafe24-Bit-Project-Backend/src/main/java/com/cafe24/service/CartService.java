package com.cafe24.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.dao.CartDao;
import com.cafe24.dto.CartDetailDto;
import com.cafe24.dto.CartOptionUpdateDto;
import com.cafe24.vo.CartVo;
import com.cafe24.vo.ProductOptionVo;

@Service
public class CartService {

	@Autowired
	private CartDao cartDao;

	public boolean addCart(CartVo cartVo) {
		
		int queryResult = cartDao.insert(cartVo);
		
		return queryResult == cartVo.getProductOptionDetailNo().size();
	}
	
	public boolean isValidCartAddRequest(CartVo cartVo) {
		int queryResult = cartDao.isValidAdd(cartVo);
		return queryResult>0;
	}

	public List<CartDetailDto> showCartDetail(String userid) {
		
		List<CartDetailDto> carts = cartDao.getList(userid);
		
		return carts;
	}

	public boolean updateProductCountInCart(long cartNo, long updateCount) {
		
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("cartNo", cartNo);
		params.put("updateCount", updateCount);
		
		int queryResult = cartDao.updateCount(params);
		return queryResult==1;
	}
	
	public List<ProductOptionVo> getProductOptionList(long productNo) {
		return cartDao.getOptionList(productNo);
	}
	
	public boolean updateProductOptionInCart(CartOptionUpdateDto cartOptionUpdateDto) {
		
		int queryResult = cartDao.updateOption(cartOptionUpdateDto);
		return queryResult==cartOptionUpdateDto.getProductOptionDetailNo().size();
	}

	public boolean deleteCheckedCartProduct(List<Long> cartNo) {
		
		int queryResult = cartDao.delete(cartNo);
		return queryResult==cartNo.size();
	}

	public int isExistProductOptionDetailNo(CartOptionUpdateDto cartOptionUpdateDto) {
		return cartDao.isExistOptionDetailNo(cartOptionUpdateDto);
	}

}
