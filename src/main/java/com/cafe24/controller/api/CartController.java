package com.cafe24.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.service.CartService;
import com.cafe24.vo.CartVo;
import com.cafe24.vo.ProductVo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@ApiOperation(value = "장바구니 담기")
	@RequestMapping(value= "", method=RequestMethod.POST)
	public Map<String, Object> cartAdd(
			@ModelAttribute ProductVo productVo,
			@RequestParam(value="quantity", required=true, defaultValue="1") String quantity,
			@RequestParam(value="productOptionDetailNo") List<String> productOptionDetailNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		boolean queryResult = cartService.addCart(productVo.getProductNo(), quantity, productOptionDetailNo);
		
		return result;
	}
	
	@ApiOperation(value = "장바구니 조회")
	@RequestMapping(value= "", method=RequestMethod.GET)
	public Map<String, Object> detail(@ModelAttribute List<CartVo> cartVo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "장바구니 내의 물품 내용 변경")
	@RequestMapping(value= "", method=RequestMethod.PUT)
	public Map<String, Object> update(@ModelAttribute List<CartVo> cartVo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "장바구니 내의 물품 삭제")
	@RequestMapping(value= "", method=RequestMethod.DELETE)
	public Map<String, Object> delete(List<String> cartNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
}
