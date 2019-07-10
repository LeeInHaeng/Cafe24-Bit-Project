package com.cafe24.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.vo.OrderVo;
import com.cafe24.vo.ProductVo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@ApiOperation(value = "상품 재고 확인")
	@RequestMapping(value= "/check/quantity", method=RequestMethod.POST)
	public Map<String, Object> orderBefore(List<ProductVo> productVo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		// 재고 검사
		
		// 주문 작성 페이지로 포워딩
		
		return result;
	}
	
	@ApiOperation(value = "주문 작성 페이지")
	@RequestMapping(value= "/ordering", method=RequestMethod.GET)
	public Map<String, Object> ordering(@ModelAttribute List<ProductVo> productVo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "주문완료 버튼 클릭")
	@RequestMapping(value= "/ordering", method=RequestMethod.POST)
	public Map<String, Object> ordering(
			@ModelAttribute List<ProductVo> productVo,
			@ModelAttribute OrderVo orderVo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "주문완료 후 화면")
	@RequestMapping(value= "/success", method=RequestMethod.GET)
	public Map<String, Object> success() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "주문 내역 조회")
	@RequestMapping(value= "/history", method=RequestMethod.GET)
	public Map<String, Object> history() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "주문 상태 변경 페이지")
	@RequestMapping(value= "/status/change/page", method=RequestMethod.POST)
	public Map<String, Object> statusChangePage(
			@ModelAttribute OrderVo orderVo,
			@RequestParam(value="changeValue", required=true, defaultValue="") String changeValue) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "주문 상태 변경 요청")
	@RequestMapping(value= "/status/change", method=RequestMethod.POST)
	public Map<String, Object> statusChange(
			@ModelAttribute ProductVo productVo,
			@RequestParam(value="reason", required=true, defaultValue="") String reason) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}

}
