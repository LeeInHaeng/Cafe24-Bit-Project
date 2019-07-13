package com.cafe24.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.dto.ProductSearch;
import com.cafe24.service.ProductManageService;
import com.cafe24.vo.CategoryVo;
import com.cafe24.vo.ProductVo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/admin/manage/product")
public class ProductManageController {
	
	@Autowired
	private ProductManageService productManageService;
	
	@ApiOperation(value = "상품 관리 메인 페이지")
	@RequestMapping(value= {"/main", "/index"}, method=RequestMethod.GET)
	public Map<String, Object> main() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
		
		// 관리자 계정으로 접속 되어있는지 확인
	}

	@ApiOperation(value = "상품 등록 페이지")
	@RequestMapping(value= "/register", method=RequestMethod.GET)
	public Map<String, Object> register() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
		
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@ApiOperation(value = "상품 등록 페이지에서 상품 등록")
	@RequestMapping(value= "/register", method=RequestMethod.POST)
	public Map<String, Object> register(@RequestBody Object registerInfo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		productManageService.registerNewProduct(registerInfo);
		
		return result;
		
		// POST로 넘어오는 모든 객체에 대해 유효성 검사
		
		// POST로 넘어오는 객체 안에서 필수 요소들이 모두 입력되어 있는지 검사
		
		// 진열 여부, 판매 여부의 전달되는 값이 true 일 경우 진열과 판매가 가능한지 체크한다
		
		// 관리자 권한으로 요청했는지 검사
	}
	
	@ApiOperation(value = "상품 목록 페이지")
	@RequestMapping(value= "/list", method=RequestMethod.GET)
	public Map<String, Object> list() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
		
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@ApiOperation(value = "상품 목록 페이지 검색")
	@RequestMapping(value= "/list", method=RequestMethod.POST)
	public Map<String, Object> list(@ModelAttribute ProductSearch searchParams) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<ProductVo> products = productManageService.getProductListWithSearch(searchParams);
		
		return result;
		
		// 관리자 계정으로 접속 되어있는지 확인
		
		// 모든 입력값에 대해 악의적인 공격이 있을 수 있는 데이터가 들어있는지 확인
	}
	
	@ApiOperation(value = "하나의 상품의 전체 정보 업데이트 페이지")
	@RequestMapping(value= "/{productNo}", method=RequestMethod.GET)
	public Map<String, Object> update(@PathVariable(value="productNo") String productNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		productManageService.getOneProductEntierInfo(productNo);
		
		return result;
		
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@ApiOperation(value = "하나의 상품의 전체 정보 업데이트")
	@RequestMapping(value= "/{productNo}", method=RequestMethod.PUT)
	public Map<String, Object> update(@RequestBody Object updateInfo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		productManageService.updateOneProductEntierInfo(updateInfo);
		
		return result;
		
		// 파라미터로 넘어오는 모든 객체에 대해 유효성 검사
		
		// 파라미터로 넘어오는 객체 안에서 필수 요소들이 모두 입력되어 있는지 검사
		
		// 진열 여부, 판매 여부의 전달되는 값이 true 일 경우 진열과 판매가 가능한지 체크한다
		
		// 관리자 권한으로 요청했는지 검사
	}
	
	@ApiOperation(value = "여러개의 상품 진열 설정 정보 업데이트")
	@RequestMapping(value= "/display", method=RequestMethod.PUT)
	public Map<String, Object> updateMultiple(@RequestBody Object updateInfo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		productManageService.updateDisplaySelectedProducts(updateInfo);
		
		return result;
		
		// 상품 번호, 진열 여부, 판매 여부가 비어있지 않은지 확인
		
		// 상품 번호, 진열 여부, 판매 여부에 악의적인 공격이 있을 수 있는 데이터가 들어있는지 확인
		
		// 진열 여부, 판매 여부의 전달되는 값이 true 일 경우 진열과 판매가 가능한지 체크한다
		
		// 관리자 권한으로 요청했는지 검사
	}
	
	@ApiOperation(value = "여러개의 상품 삭제")
	@RequestMapping(value= "", method=RequestMethod.DELETE)
	public Map<String, Object> delete(@RequestParam(value="productNo") List<String> productNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		productManageService.deleteSelectedProducts(productNo);
		
		return result;
		
		// 넘어오는 상품 번호가 비어있지 않은지 확인
		
		// 넘어오는 상품 번호에 악의적인 공격이 있을만한 특수문자 등의 경우 처리
		
		// 관리자 권한으로 요청했는지 검사
	}
	
	@ApiOperation(value = "상품 분류 관리 페이지")
	@RequestMapping(value= "/category", method=RequestMethod.GET)
	public Map<String, Object> category() {
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<CategoryVo> categorys = productManageService.getCategoryList();
		
		return result;
		
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@ApiOperation(value = "상품 분류 관리 페이지에서 카테고리 추가")
	@RequestMapping(value= "/category", method=RequestMethod.POST)
	public Map<String, Object> category(CategoryVo categoryVo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		productManageService.addCategory(categoryVo);
		
		return result;
		
		// 카테고리 객체가 제대로 넘어오는지 확인
		
		// 카테고리 객체의 내용이 비어있지 않은지 확인
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@ApiOperation(value = "상품 분류 관리 페이지에서 카테고리 수정")
	@RequestMapping(value= "/category", method=RequestMethod.PUT)
	public Map<String, Object> categoryUpdate(CategoryVo categoryVo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		productManageService.updateCategory(categoryVo);
		
		return result;
		
		// 카테고리 객체가 제대로 넘어오는지 확인
		
		// 카테고리 객체의 내용이 비어있지 않은지 확인
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@ApiOperation(value = "상품 분류 관리 페이지에서 카테고리 삭제")
	@RequestMapping(value= "/category/{categoryNo}", method=RequestMethod.DELETE)
	public Map<String, Object> categoryDelete(@PathVariable(value="categoryNo") String categoryNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		productManageService.deleteCategory(categoryNo);
		
		return result;
		
		// 카테고리 번호가 제대로 넘어오는지 확인
		
		// 카테고리 번호가 비어있지 않은지 확인
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
}
