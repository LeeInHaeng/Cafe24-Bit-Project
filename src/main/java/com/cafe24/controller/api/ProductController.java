package com.cafe24.controller.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@ApiOperation(value = "상품 리스트 페이지")
	@RequestMapping(value= {"/list", "/list/{categoryName}", "/list/{categoryName}/{pageNo}"},
		method=RequestMethod.GET)
	public Map<String, Object> list(
			@PathVariable(value="categoryName") Optional<String> categoryName,
			@PathVariable(value="pageNo") Optional<String> pageNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "상품 리스트 검색 페이지")
	@RequestMapping(value= {"/list/search", "/list/search/{keyword}", "/list/search/{keyword}/{pageNo}"}, method=RequestMethod.GET)
	public Map<String, Object> searchList(
			@PathVariable(value="keyword") Optional<String> keyword,
			@PathVariable(value="pageNo") Optional<String> pageNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "상품 상세 조회")
	@RequestMapping(value="/detail/{productName}", method=RequestMethod.GET)
	public Map<String, Object> detail(
			@PathVariable(value="productName") Optional<String> productName){
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
}
