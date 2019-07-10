package com.cafe24.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.vo.CartVo;
import com.cafe24.vo.ProductVo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@ApiOperation(value = "장바구니 담기")
	@RequestMapping(value= "/add/{productNo}", method=RequestMethod.GET)
	public Map<String, Object> add(@PathVariable(value="productNo") String productNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "장바구니 조회")
	@RequestMapping(value= "/detail", method=RequestMethod.GET)
	public Map<String, Object> detail(@ModelAttribute List<CartVo> cartVo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "장바구니 내의 물품 내용 변경")
	@RequestMapping(value= "/update", method=RequestMethod.PUT)
	public Map<String, Object> update(@ModelAttribute CartVo cartVo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "장바구니 내의 물품 삭제")
	@RequestMapping(value= "/delete", method=RequestMethod.DELETE)
	public Map<String, Object> delete(List<String> cartNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
}
