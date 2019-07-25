package com.cafe24.dao;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.dto.AdminCheckedProductsDisplayUpdateDto;
import com.cafe24.dto.AdminProductRegisterDto;
import com.cafe24.dto.AdminProductSearchDto;
import com.cafe24.dto.AdminProductSearchResultDto;
import com.cafe24.vo.CategoryVo;

@Repository
public class ProductManageDao {

	@Autowired
	private SqlSession sqlSession;

	public boolean insert(AdminProductRegisterDto adminProductRegisterDto) {
		int queryResult = sqlSession.insert("productmanage.insert", adminProductRegisterDto);
		return queryResult == adminProductRegisterDto.getProductQuantityVo().size();
	}

	public List<AdminProductSearchResultDto> searchProductList(AdminProductSearchDto adminProductSearchDto) {
		return sqlSession.selectList("productmanage.searchProductList", adminProductSearchDto);
	}

	public AdminProductRegisterDto getOneProduct(long productNo) {
		return sqlSession.selectOne("productmanage.getOneProduct", productNo);
	}

	public boolean update(AdminProductRegisterDto adminProductRegisterDto) {
		int queryResult = sqlSession.update("productmanage.update", adminProductRegisterDto);
		return queryResult==1;
	}

	public boolean updateListDisplay(AdminCheckedProductsDisplayUpdateDto dto) {
		int queryResult = sqlSession.update("productmanage.updateListDisplay", dto);
		return queryResult==dto.getProductNo().size();
	}

	public boolean deleteList(List<Long> productNo) {
		int queryResult = sqlSession.update("productmanage.deleteList", productNo);
		return queryResult==productNo.size();
	}

	public List<CategoryVo> getCategoryList() {
		return sqlSession.selectList("productmanage.getCategoryList");
	}
	
	public boolean insertCategoryParent(@Valid CategoryVo categoryVo) {
		int queryResult = sqlSession.insert("productmanage.insertCategoryParent", categoryVo);
		return queryResult==1;
	}

	public boolean insertCategory(CategoryVo categoryVo) {
		int queryResult = sqlSession.insert("productmanage.insertCategory", categoryVo);
		return true;
	}

	public boolean existCategory(long categoryNo) {
		int queryResult = sqlSession.selectOne("productmanage.isExistCategory", categoryNo);
		return queryResult>0;
	}

	public boolean updateCategory(CategoryVo categoryVo) {
		int queryResult = sqlSession.update("productmanage.updateCategory", categoryVo);
		return queryResult==1;
	}

	public void deleteCategory(long productNo) {
		sqlSession.delete("productmanage.deleteCategory", productNo);
	}
	
	public List<Long> getDeleteCategoryNo(long categoryNo) {
		return sqlSession.selectList("productmanage.getDeleteCategoryNo", categoryNo);
	}

	public boolean deleteCategoryChild(List<Long> deleteCategoryNo) {
		int queryResult = sqlSession.delete("productmanage.deleteCategoryChild", deleteCategoryNo);
		return true;
	}

}
