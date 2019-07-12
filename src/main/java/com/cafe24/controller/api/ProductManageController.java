package com.cafe24.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.vo.AdminSearchVo;
import com.cafe24.vo.CategoryVo;
import com.cafe24.vo.ProductVo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/admin/manage/product")
public class ProductManageController {
	
	@ApiOperation(value = "상품 관리 메인 페이지")
	@RequestMapping(value= "", method=RequestMethod.GET)
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
	public Map<String, Object> register(Object registerInfo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "상품 목록 페이지")
	@RequestMapping(value= "/list", method=RequestMethod.GET)
	public Map<String, Object> list() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "상품 목록 페이지 검색")
	@RequestMapping(value= "/list", method=RequestMethod.POST)
	public Map<String, Object> list(@ModelAttribute AdminSearchVo searchVo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "하나의 상품의 전체 정보 업데이트 페이지")
	@RequestMapping(value= "/update/{productNo}", method=RequestMethod.GET)
	public Map<String, Object> update(@PathVariable(value="productNo") String productNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "하나의 상품의 전체 정보 업데이트")
	@RequestMapping(value= "/update", method=RequestMethod.PUT)
	public Map<String, Object> update(@ModelAttribute ProductVo productVo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "여러개의 상품 진열 설정 정보 업데이트")
	@RequestMapping(value= "/update/display", method=RequestMethod.PUT)
	public Map<String, Object> updateMultiple(List<String> productNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "여러개의 상품 삭제")
	@RequestMapping(value= "/delete", method=RequestMethod.DELETE)
	public Map<String, Object> delete(List<String> productNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "상품 분류 관리 페이지")
	@RequestMapping(value= "/category", method=RequestMethod.GET)
	public Map<String, Object> category(@ModelAttribute List<ProductVo> productVo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "상품 분류 관리 페이지에서 카테고리 추가")
	@RequestMapping(value= "/category", method=RequestMethod.POST)
	public Map<String, Object> category(@ModelAttribute CategoryVo categoryVo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "상품 분류 관리 페이지에서 카테고리 수정")
	@RequestMapping(value= "/category/update", method=RequestMethod.PUT)
	public Map<String, Object> categoryUpdate(@ModelAttribute CategoryVo categoryVo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "상품 분류 관리 페이지에서 카테고리 삭제")
	@RequestMapping(value= "/category/delete", method=RequestMethod.DELETE)
	public Map<String, Object> categoryDelete(long categoryNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
}
