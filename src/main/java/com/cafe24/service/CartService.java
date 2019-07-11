package com.cafe24.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.dao.CartDao;

@Service
public class CartService {

	@Autowired
	private CartDao cartDao;

	public boolean addCart(long productNo, String quantity, List<String> productOptionDetailNo) {
		
		int queryResult = cartDao.insert(productNo, quantity, productOptionDetailNo);
		
		return false;
	}
}
