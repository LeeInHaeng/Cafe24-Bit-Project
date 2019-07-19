package com.cafe24.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.dto.JSONResult;
import com.cafe24.dto.OrderPageDto;
import com.cafe24.dto.ProductOptionDto;
import com.cafe24.dto.ProductOrder;
import com.cafe24.service.OrderService;
import com.cafe24.vo.ProductQuantityVo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@ApiOperation(value = "상품 재고 확인")
	@RequestMapping(value= "/check/quantity", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> checkQuantity(
			@RequestBody List<ProductOptionDto> productOptionDto) {
		
		if(!orderService.isValidProductOptionDto(productOptionDto)) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("잘못된 요청"));
		}
		
		// 정상 동작
		List<ProductQuantityVo> productQuantity = orderService.getProductQuantity(productOptionDto);
		
		List<HashMap<String, Object>> quantityResult = new ArrayList<HashMap<String, Object>>();
		for(ProductQuantityVo quantity : productQuantity) {
			HashMap<String, Object> quantityInfo = new HashMap<String, Object>();
			quantityInfo.put("상품 번호", quantity.getProductNo());
			quantityInfo.put("옵션 코드", quantity.getOptionCode());
			if(quantity.getAvailableQuantity()>0 && quantity.getRealQuantity()>0)
				quantityInfo.put("재고", "있음");
			else
				quantityInfo.put("재고", "없음");
			
			quantityResult.add(quantityInfo);
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(quantityResult));
	}
	
	@ApiOperation(value = "주문 작성 페이지")
	@RequestMapping(value= "", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> orderPage(
			@RequestBody OrderPageDto orderPageDto) {
		
		if(orderPageDto.getMemberId()==null && orderPageDto.getNonmemberMac()==null)
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("잘못된 요청"));
		
		if(orderPageDto.getProductOptionDto()==null || orderPageDto.getProductOptionDto().size()==0)
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("잘못된 요청"));
		
		// 정상 동작
		Map<String, Object> queryResult = orderService.orderPageConnectWithOrderProducts(orderPageDto);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(queryResult));
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
