package com.cafe24.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.dao.CategoryDao;
import com.cafe24.dao.ProductDao;
import com.cafe24.dao.ProductImageDao;
import com.cafe24.dao.ProductOptionDao;
import com.cafe24.dto.ProductSearch;
import com.cafe24.vo.CategoryVo;
import com.cafe24.vo.ProductVo;

@Service
public class ProductManageService {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductImageDao productImageDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ProductOptionDao productOptionDao;

	public void registerNewProduct(Object registerInfo) {
		// TODO Auto-generated method stub
		
	}

	public List<ProductVo> getProductListWithSearch(ProductSearch searchParams) {
		
		List<ProductVo> products = productDao.getList(searchParams);
		
		return null;
	}

	public void getOneProductEntierInfo(String productNo) {
		// TODO Auto-generated method stub
		
	}

	public void updateOneProductEntierInfo(Object updateInfo) {
		// TODO Auto-generated method stub
		
	}

	public void updateDisplaySelectedProducts(Object updateInfo) {
		// TODO Auto-generated method stub
		
	}

	public void deleteSelectedProducts(List<String> productNo) {
		// TODO Auto-generated method stub
		
	}

	public List<CategoryVo> getCategoryList() {
		
		List<CategoryVo> categorys = categoryDao.getList();
		
		return null;
	}

	public void addCategory(CategoryVo categoryVo) {
		// TODO Auto-generated method stub
		
	}

	public void updateCategory(CategoryVo categoryVo) {
		// TODO Auto-generated method stub
		
	}

	public void deleteCategory(String categoryNo) {
		// TODO Auto-generated method stub
		
	}
	
}
