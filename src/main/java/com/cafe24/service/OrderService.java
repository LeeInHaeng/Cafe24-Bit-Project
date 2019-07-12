package com.cafe24.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.dao.OrderDao;
import com.cafe24.dao.ProductOptionDao;
import com.cafe24.dto.ProductInfo;
import com.cafe24.dto.ProductOrder;
import com.cafe24.vo.OrderVo;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private ProductOptionDao productOptionDao;

	public void checkProductQuantityAndQuantityReduce(List<ProductInfo> infos) {

	}

	public void BuyProducts(List<Object> productInfo) {
		// TODO Auto-generated method stub
		
	}

	public void showProductHistory() {
		// TODO Auto-generated method stub
		
	}

	public boolean changeProductStatus(ProductOrder productOrder) {
		
		int queryResult = orderDao.update(productOrder);
		
		return false;
	}
}
