package com.cafe24.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.dao.ProductManageDao;
import com.cafe24.dto.AdminProductRegisterDto;
import com.cafe24.dto.AdminProductSearchDto;
import com.cafe24.dto.AdminProductSearchResultDto;
import com.cafe24.vo.CategoryVo;

@Service
public class ProductManageService {

	@Autowired
	private ProductManageDao productManageDao;

	public boolean registerNewProduct(AdminProductRegisterDto adminProductRegisterDto) {
		boolean queryResult = productManageDao.insert(adminProductRegisterDto);
		return queryResult;
	}

	public List<AdminProductSearchResultDto> getProductListWithSearch(AdminProductSearchDto adminProductSearchDto) {

		return productManageDao.searchProductList(adminProductSearchDto);
	}

	public AdminProductRegisterDto getOneProductEntierInfo(long productNo) {
		return productManageDao.getOneProduct(productNo);
	}

	public boolean updateOneProductEntierInfo(AdminProductRegisterDto adminProductRegisterDto) {
		boolean queryResult = productManageDao.update(adminProductRegisterDto);
		return queryResult;
	}

	public void updateDisplaySelectedProducts(Object updateInfo) {
		// TODO Auto-generated method stub
		
	}

	public void deleteSelectedProducts(List<String> productNo) {
		// TODO Auto-generated method stub
		
	}

	public List<CategoryVo> getCategoryList() {
		
		// List<CategoryVo> categorys = categoryDao.getList();
		
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
