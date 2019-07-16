package com.cafe24.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.dao.ProductDao;
import com.cafe24.vo.ProductVo;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;

	public List<ProductVo> getProductList(Optional<String> categoryName, Optional<String> pageNo) {
		
		List<ProductVo> products = productDao.getList(categoryName, pageNo);
		
		return products;
	}

	public List<ProductVo> getProductListWithSearch(Optional<String> keyword, Optional<String> pageNo) {
		
		List<ProductVo> products = productDao.getSearchList(keyword, pageNo);
		
		return null;
	}

	public ProductVo getProductDetailInfoByNo(Optional<String> productNo) {
		
		ProductVo product = productDao.get(productNo);
		
		return null;
	}
}
