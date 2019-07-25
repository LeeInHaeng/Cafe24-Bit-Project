package com.cafe24.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.dao.ProductManageDao;
import com.cafe24.dto.AdminCheckedProductsDisplayUpdateDto;
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

	public boolean updateDisplaySelectedProducts(AdminCheckedProductsDisplayUpdateDto dto) {
		boolean queryResult = productManageDao.updateListDisplay(dto);
		return queryResult;
	}

	public boolean deleteSelectedProducts(List<Long> productNo) {
		boolean queryResult = productManageDao.deleteList(productNo);
		return queryResult;
	}

	public List<CategoryVo> getCategoryList() {
		return productManageDao.getCategoryList();
	}
	
	public boolean addCategoryParent(@Valid CategoryVo categoryVo) {
		boolean queryResult = productManageDao.insertCategoryParent(categoryVo);
		return queryResult;
	}

	public boolean addCategory(CategoryVo categoryVo) {
		if(!productManageDao.existCategory(categoryVo.getCategoryNo()))
			return false;
		boolean queryResult = productManageDao.insertCategory(categoryVo);
		return queryResult;
	}

	public boolean updateCategory(CategoryVo categoryVo) {
		if(!productManageDao.existCategory(categoryVo.getCategoryNo()))
			return false;
		boolean queryResult = productManageDao.updateCategory(categoryVo);
		return queryResult;
	}

	public boolean deleteCategory(String categoryNo) {
		if(!productManageDao.existCategory(Long.parseLong(categoryNo)))
			return false;
		
		List<Long> deleteCategoryNo = productManageDao.getDeleteCategoryNo(Long.parseLong(categoryNo));
		boolean queryResult = productManageDao.deleteCategoryChild(deleteCategoryNo);
		productManageDao.deleteCategory(Long.parseLong(categoryNo));
		
		return queryResult;
	}

}
