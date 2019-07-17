package com.cafe24.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.dto.JSONResult;
import com.cafe24.dto.ProductInfo;
import com.cafe24.service.CartService;
import com.cafe24.vo.CartVo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@ApiOperation(value = "장바구니 담기")
	@RequestMapping(value= "", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> cartAdd(@RequestBody CartVo cartVo) {

		boolean queryResult = cartService.addCart(cartVo);
		
		// POST로 넘어오는 파라미터인 productInfo가 비어있는 경우 처리
		
		// POST로 넘어오는 파라미터에 악의적인 공격이 있을만한 특수문자 등의 경우 처리
		
		// 회원인 경우 회원 아이디를 바탕으로 장바구니 정보를 저장
		
		// 회원인 경우 세션에 저장되어 있는 회원 아이디와 장바구니에 저장하고자 하는 회원 아이디가 같은지 확인
		
		// 비회원인 경우 접속한 맥주소를 바탕으로 장바구니 정보를 저장
		
		// 비회원인 경우 접속한 맥주소와 장바구니에 저장하고자 하는 맥주소가 같은지 확인
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(queryResult));
	}
	
	@ApiOperation(value = "장바구니 조회")
	@RequestMapping(value= "", method=RequestMethod.GET)
	public Map<String, Object> cartDetail() {
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<CartVo> carts = cartService.showCartDetail();
		
		return result;
		
		// 회원인 경우 세션에 저장되어 있는 회원 아이디를 바탕으로 장바구니 정보를 조회
		
		// 비회원인 경우 접속한 맥주소를 바탕으로 장바구니 정보를 조회
	}
	
	@ApiOperation(value = "장바구니 내의 물품 내용 변경")
	@RequestMapping(value= "", method=RequestMethod.PUT)
	public Map<String, Object> cartUpdate(CartVo cartVo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		boolean isUpdate = cartService.updateCartProductImmediately(cartVo);
		
		return result;
		
		// 넘어오는 CartVo의 값이 정확하게 들어있는지 확인
		
		// 회원인 경우 세션에 저장되어 있는 회원 아이디를 바탕으로 장바구니 업데이트 및 정보 조회
		
		// 회원인 경우 세션에 저장되어 있는 회원 아이디와 장바구니에 업데이트 하고자 하는 회원 아이디가 같은지 확인
		
		// 비회원인 경우 접속한 맥주소를 바탕으로 장바구니 업데이트 및 정보 조회
		
		// 비회원인 경우 접속한 맥주소와 장바구니에 업데이트 하고자 하는 맥주소가 같은지 확인
	}
	
	@ApiOperation(value = "장바구니 내의 물품 삭제")
	@RequestMapping(value= "", method=RequestMethod.DELETE)
	public Map<String, Object> cartDelete(@RequestParam(value="cartNo") List<String> cartNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		boolean isDelete = cartService.deleteCheckedCartProduct(cartNo);
		
		return result;
		
		// 넘어오는 카트 번호가 비어있지 않은지 확인
		
		// 넘어오는 카트 번호에 악의적인 공격이 있을만한 특수문자 등의 경우 처리
		
		// 회원인 경우 세션에 저장되어 있는 회원 아이디와 장바구니를 삭제 하고자 하는 회원 아이디가 같은지 확인
		
		// 비회원인 경우 접속한 맥주소와 장바구니에 삭제하고자 하는 맥주소가 같은지 확인
	}
}
