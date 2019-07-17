package com.cafe24.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.dao.CartDao;
import com.cafe24.dto.ProductInfo;
import com.cafe24.vo.CartVo;

@Service
public class CartService {

	@Autowired
	private CartDao cartDao;

	public boolean addCart(CartVo cartVo) {
		
		int queryResult = cartDao.insert(cartVo);
		
		return queryResult == cartVo.getProductOptionDetailNo().size();
	}

	public List<CartVo> showCartDetail() {
		
		List<CartVo> queryResult = cartDao.getList();
		
		return null;
	}

	public boolean updateCartProductImmediately(CartVo cartVo) {
		
		int queryResult = cartDao.update(cartVo);
		
		return false;
	}

	public boolean deleteCheckedCartProduct(List<String> cartNo) {
		
		int queryResult = cartDao.delete(cartNo);
		
		return false;
	}
}
