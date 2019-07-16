package com.cafe24.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.dto.ProductInfo;
import com.cafe24.dto.ProductOrder;
import com.cafe24.service.OrderService;
import com.cafe24.vo.OrderVo;
import com.google.gson.Gson;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@ApiOperation(value = "상품 재고 확인")
	@RequestMapping(value= "/check/quantity", method=RequestMethod.POST)
	public Map<String, Object> checkQuantity(@RequestBody List<ProductInfo> productInfo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		orderService.checkProductQuantityAndQuantityReduce(productInfo);
		
		return result;
		
		// POST로 넘어오는 파라미터인 productInfo가 비어있는 경우 처리
		
		// POST로 넘어오는 파라미터에 악의적인 공격이 있을만한 특수문자 등의 경우 처리
		
		// 상품의 판매 가능 수량이 정상적으로 있는 경우 아직 주문 전이더라도 상품 옵션 상세 테이블의 판매 가능 수량의 값을 미리 감소시켜 놓는다
		
		// 이후에 실제 주문이 다 이루어지면 상품 옵션 상세 테이블에서 재고의 값을 감소 시킨다
	}
	
	@ApiOperation(value = "주문 작성 페이지")
	@RequestMapping(value= "", method=RequestMethod.POST)
	public Map<String, Object> orderPage(@RequestBody List<ProductInfo> infos) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
		
		// 재고 확인 후의 데이터가 그대로 넘어오는지 확인
		
		// 회원인 경우 추가 입력폼 생성
		
		// 비회원인 경우 추가 입력폼 생성
	}
	
	@ApiOperation(value = "주문완료 버튼 클릭")
	@RequestMapping(value= "/buy", method=RequestMethod.POST)
	public Map<String, Object> ordering(
			@RequestBody Map<String, Object> params) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		/* 서비스 부분에서 json으로 넘어온 Map 데이터를 제대로된 타입으로 변경
		List<ProductInfo> productInfos = new ArrayList<ProductInfo>();
		for(Object productInfo : (ArrayList<Object>) params.get("ProductInfo")) {
			productInfos.add(new Gson().fromJson(productInfo.toString(), ProductInfo.class));
		}
		
		// 데이터에 string 타입으로 인해 space(공백)가 있으면 toJson으로 한번 더 묶어주어야 제대로 파싱이 동작
		OrderVo orderVo = new Gson().fromJson(
				new Gson().toJson(params.get("OrderVo")), OrderVo.class);
		*/
		
		orderService.BuyProducts(params);
		
		return result;
		
		// 주문 페이지에서의 주문하는 상품 데이터가 그대로 넘어오는지 확인
		
		// OrderVo 객체의 유효성 검사
		
		// 회원인 경우 세션에 저장되어있는 아이디와 요청하는 회원아이디가 같은지 확인
		
		// 비회원인 경우 세션에 저장되어있는 맥주소와 요청하는 맥주소가 같은지 확인
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
		
		orderService.showProductHistory();
		
		return result;
	}
	
	@ApiOperation(value = "주문 상태 변경 페이지")
	@RequestMapping(value= "/status/change", method=RequestMethod.POST)
	public Map<String, Object> statusChangePage(
			@RequestParam(value="orderNo", required=true, defaultValue="") String orderNo,
			@RequestParam(value="productNo", required=true, defaultValue="") String productNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		return result;
		
		// orderNo와 productNo가 비어있는지 확인
		
		// orderNo와 productNo에 악의적인 공격에 사용할 수 있는 특수 문자 등을 입력했는지 검사
		
		// 회원인 경우 세션에 저장되어 있는 아이디와 orderNo를 바탕으로 주문 테이블의 회원 아이디와  비교하여 일치하는지 확인 

		// 비회원인 경우 세션에 저장되어 있는 맥주소와 orderNo를 바탕으로 주문 테이블의 맥주소와 비교하여 일치하는지 확인
	}
	
	@ApiOperation(value = "주문 상태 변경 요청")
	@RequestMapping(value= "/status/change", method=RequestMethod.PUT)
	public Map<String, Object> statusChangeRequest(@ModelAttribute ProductOrder productOrder) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		boolean isChange = orderService.changeProductStatus(productOrder);
		
		return result;
		
		// productOrder 객체가 비어있는지 검사
		
		// productOrder 객체의 형식 유효성 검사
		
		// 회원인 경우 세션에 저장되어 있는 아이디와 orderNo를 바탕으로 주문 테이블의 회원 아이디와  비교하여 일치하는지 확인 

		// 비회원인 경우 세션에 저장되어 있는 맥주소와 orderNo를 바탕으로 주문 테이블의 맥주소와 비교하여 일치하는지 확인
	}

}
