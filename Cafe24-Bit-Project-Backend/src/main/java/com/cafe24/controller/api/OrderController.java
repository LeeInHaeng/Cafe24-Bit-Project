package com.cafe24.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.dto.JSONResult;
import com.cafe24.dto.OrderBuyDto;
import com.cafe24.dto.OrderHistoryDto;
import com.cafe24.dto.OrderPageDto;
import com.cafe24.dto.ProductOptionDto;
import com.cafe24.service.OrderService;
import com.cafe24.vo.NonmemberVo;
import com.cafe24.vo.OrderProductVo;
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
		// 해당 부분에서 available_quantity 감소
		Map<String, Object> queryResult = orderService.orderPageConnectWithOrderProducts(orderPageDto);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(queryResult));
	}
	
	@ApiOperation(value = "주문완료 버튼 클릭")
	@RequestMapping(value= "/buy", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> productBuy(
			@RequestBody @Valid OrderBuyDto orderBuyDto,
			BindingResult br) {
	
		// 객체 Validation에 맞지 않는 경우
		if(br.hasErrors())
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail(br.getAllErrors().get(0).getDefaultMessage()));
		
		// 회원의 아이디, 비회원의 맥주소가 둘 다 없는 경우
		if(orderBuyDto.getMemberId()==null && orderBuyDto.getNonmemberMac()==null)
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("잘못된 요청"));
		
		// TotalPrice 유효성 검사
		if((Long)orderBuyDto.getTotalPrice()==null ||
				!orderService.isMatchPrice(orderBuyDto.getProductOptionDto(), orderBuyDto.getTotalPrice())) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("잘못된 요청"));
		}
		
		// 비회원 입력값 유효성 검사
		if(orderBuyDto.getNonmemberMac()!=null) {
			
			// 필수 입력 항목 검사
			if(orderBuyDto.getNonmemberPhone()==null || orderBuyDto.getNonmemberPass()==null ||
					orderBuyDto.getNonmemberName()==null || orderBuyDto.getNonmemberRefundName()==null ||
					orderBuyDto.getNonmemberRefundNumber()==null)
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(JSONResult.fail("필수 입력 항목을 입력해 주세요."));
			
			Pattern pattern = null;
			// 핸드폰 유효성 검사
			pattern = Pattern.compile("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$");
			if(!pattern.matcher(orderBuyDto.getNonmemberPhone()).matches())
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(JSONResult.fail("휴대폰번호 형식이 올바르지 않습니다."));
			
			// 이름 유효성 검사
			pattern = Pattern.compile("^[가-힣]{2,4}|[a-zA-Z]{2,10}\\s[a-zA-Z]{2,10}$");
			if(!pattern.matcher(orderBuyDto.getNonmemberName()).matches())
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(JSONResult.fail("이름의 형식이 올바르지 않습니다."));
		}
		
		// 정상 동작
		boolean queryResult = orderService.BuyProducts(orderBuyDto);
		if(!queryResult)
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.fail("데이터베이스 쿼리 실패"));
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(queryResult));
	}
	
	@ApiOperation(value = "주문완료 후 화면")
	@RequestMapping(value= "/success", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> success() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(null));
	}
	
	@ApiOperation(value = "회원 주문 내역 조회")
	@RequestMapping(value= "/history/{memberId}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> historyMember(
			@PathVariable(value="memberId") String memberId) {
		
		// 정상 동작
		OrderHistoryDto orderHistory = orderService.showProductHistoryMember(memberId);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(orderHistory));
	}
	
	@ApiOperation(value = "비회원 주문 내역 조회 요청 페이지")
	@RequestMapping(value= "/history/nonmember", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> historyNonmemberPage() {

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(null));
	}
	
	@ApiOperation(value = "비회원 주문 내역 조회 요청")
	@RequestMapping(value= "/history/nonmember", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> historyNonmember(
			@RequestBody @Valid NonmemberVo nonmemberVo,
			BindingResult br) {

		// nonmemberVo 객체의 유효성 검사
		if(br.hasErrors())
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail(br.getAllErrors().get(0).getDefaultMessage()));
		
		// 정상 동작
		OrderHistoryDto orderHistory = orderService.showProductHistoryNonmember(nonmemberVo);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(orderHistory));
	}
	
	@ApiOperation(value = "주문 상태 변경 요청")
	@RequestMapping(value= "/status", method=RequestMethod.PUT)
	public ResponseEntity<JSONResult> statusChangePage(
			@RequestBody @Valid OrderProductVo orderProductVo,
			BindingResult br) {

		// OrderProductVo 객체의 유효성 검사
		if(br.hasErrors())
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail(br.getAllErrors().get(0).getDefaultMessage()));
		
		if(orderProductVo.getOrderNo()==0 || orderProductVo.getProductNo()==0
				|| (Long)orderProductVo.getQuantity()!=0)
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("잘못된 요청 입니다."));
		
		// 정상 동작
		boolean queryResult = orderService.changeOrderStatusWithReason(orderProductVo);
		if(!queryResult)
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.fail("데이터베이스 쿼리 실패"));
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(queryResult));
	}
	
}
