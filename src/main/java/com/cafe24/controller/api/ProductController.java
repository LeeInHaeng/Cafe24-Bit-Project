package com.cafe24.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.service.ProductService;
import com.cafe24.vo.ProductVo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@ApiOperation(value = "상품 리스트 페이지")
	@RequestMapping(value= {"/{categoryName}", "/{categoryName}/{pageNo}"},
		method=RequestMethod.GET)
	public Map<String, Object> list(
			@PathVariable(value="categoryName") Optional<String> categoryName,
			@PathVariable(value="pageNo") Optional<String> pageNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<ProductVo> products = productService.getProductList(categoryName, pageNo);
		
		return result;
		
		// 카테고리를 적지 않은 경우 처리
		
		// 사용자가 요청한 카테고리가 데이터베이스에 없는 경우 처리
		
		// 페이지번호를 적지 않은 경우 처리
		
		// 페이지 번호가 숫자가 아닌 경우 처리
		
		// 페이지의 숫자가 1보다 작은 경우 처리
		
		// 페이지의 숫자가 최대 페이지의 값보다 큰 경우 처리
	}
	
	@ApiOperation(value = "상품 리스트 검색 페이지")
	@RequestMapping(value= {"/search", "/search/{keyword}", "/search/{keyword}/{pageNo}"}, method=RequestMethod.GET)
	public Map<String, Object> searchList(
			@PathVariable(value="keyword") Optional<String> keyword,
			@PathVariable(value="pageNo") Optional<String> pageNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<ProductVo> products = productService.getProductListWithSearch(keyword, pageNo);
		
		return result;
		
		// 검색 키워드를 적지 않은 경우 처리
		
		// 검색 키워드에 악의적인 공격이 있을만한 특수문자 등의 경우 처리
		
		// 페이지번호를 적지 않은 경우 처리
		
		// 페이지 번호가 숫자가 아닌 경우 처리
		
		// 페이지의 숫자가 1보다 작은 경우 처리
		
		// 페이지의 숫자가 최대 페이지의 값보다 큰 경우 처리
	}
	
	@ApiOperation(value = "상품 상세 조회")
	@RequestMapping(value="/detail/{productNo}", method=RequestMethod.GET)
	public Map<String, Object> detail(
			@PathVariable(value="productNo") Optional<String> productNo){
		Map<String, Object> result = new HashMap<String, Object>();
		
		ProductVo product = productService.getProductDetailInfoByNo(productNo);
		
		return result;
		
		// 상품 번호가 비어있는 경우 처리
		
		// 상품 번호에 악의적인 공격이 있을만한 특수문자 등의 경우 처리
	}
	
}
