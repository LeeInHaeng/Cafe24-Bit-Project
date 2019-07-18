package com.cafe24.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.dto.CartDetailDto;
import com.cafe24.dto.CartOptionUpdateDto;
import com.cafe24.dto.JSONResult;
import com.cafe24.service.CartService;
import com.cafe24.service.MemberService;
import com.cafe24.vo.CartVo;
import com.cafe24.vo.ProductOptionVo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private MemberService memberService;
	
	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}
	
	@ApiOperation(value = "장바구니 담기")
	@RequestMapping(value= "", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> cartAdd(@RequestBody CartVo cartVo) {

		// 회원인 경우 올바른 사용자인지 확인
		if(cartVo.getMemberId()!=null && !memberService.checkId(cartVo.getMemberId()))
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("존재하지 않는 사용자가 요청"));
		
		// 회원 아이디도 적혀있지 않고 비회원 맥주소도 적혀있지 않은 경우
		if(cartVo.getMemberId()==null && cartVo.getNonmemberMac()==null)
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("사용자 정보가 없음"));
		
		// 상품 번호나 수량이 적혀있지 않은 경우
		if(cartVo.getProductNo()==0 || (Long)cartVo.getQuantity()==0)
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("잘못된 요청"));
		
		// 해당 상품의 존재 여부, 진열 되어있는 상품인지, 해당 상품이 갖고 있는 상세 옵션인지 확인
		if(!cartService.isValidCartAddRequest(cartVo))
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("유효하지 않은 요청"));
		
		boolean queryResult = cartService.addCart(cartVo);
		if(queryResult)
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.success(queryResult));
		else
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.fail("데이터베이스 쿼리 실패"));
	}
	
	@ApiOperation(value = "장바구니 조회")
	@RequestMapping(value= "", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> cartDetail() {
		
		// 세션에대한 예외 처리는 프론트엔드의 컨트롤러에서 처리
		
		// 세션에서 얻어온 사용자가 회원인 경우
		String userid = "user1";
		List<CartDetailDto> carts = cartService.showCartDetail(userid);
		
		// 세션에서 얻어온 사용자가 비회원인 경우
//		String userid = "non1-mac-address";
//		List<CartDetailDto> carts = cartService.showCartDetail(userid);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(carts));
	}
	
	@ApiOperation(value = "장바구니 내에서 수량 변경")
	@RequestMapping(value= "/count", method=RequestMethod.PUT)
	public ResponseEntity<JSONResult> cartCountUpdate(
			@RequestParam(value="cartNo", required=true) long cartNo,
			@RequestParam(value="count", required=true, defaultValue="1") String count) {
		
		long updateCount = 1L;
		
		// 변경하고자 하는 물품의 수량이 숫자가 아닌 경우 1로 셋팅
		// 변경하고자 하는 물품의 수량이 1보다 작은 경우 1로 셋팅
		// 변경하고자 하는 물품의 수량이 999보다 큰 경우 1로 셋팅
		if(!isNumeric(count) || Long.parseLong(count) < 1L)
			updateCount = 1L;
		else if(Long.parseLong(count) > 999L)
			updateCount = 1L;
		else
			updateCount = Long.parseLong(count);
		
		boolean queryResult = cartService.updateProductCountInCart(cartNo, updateCount);
		if(!queryResult)
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.fail("DB 쿼리 실패"));
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(queryResult));
	}
	
	@ApiOperation(value = "장바구니 내에서 옵션 조회")
	@RequestMapping(value= "/option/{productNo}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> getCartProductOptionList(
			@PathVariable(value="productNo") String productNo) {
		
		// productNo가 숫자가 아닌 경우
		// productNo가 1보다 작은 경우
		if(!isNumeric(productNo) || Long.parseLong(productNo) < 1)
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("productNo가 잘못된 요청"));
		
		// 정상 동작
		List<ProductOptionVo> productOptionVo = cartService.getProductOptionList(Long.parseLong(productNo));
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(productOptionVo));
	}
	
	@ApiOperation(value = "장바구니 내에서 옵션 변경")
	@RequestMapping(value= "/option", method=RequestMethod.PUT)
	public ResponseEntity<JSONResult> cartOptionUpdate(
			@RequestBody CartOptionUpdateDto cartOptionUpdateDto) {
				
		// 해당 카트의 상품이 전달 받은 상품 상세 옵션 번호가 존재하는지 여부 확인
		if(cartOptionUpdateDto.getProductOptionDetailNo().size()
				!= cartService.isExistProductOptionDetailNo(cartOptionUpdateDto)) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("해당 상품이 해당 상품 옵션 상세 번호를 갖고 있지 않음"));
		}
		
		// 객체 안의 데이터 존재여부 확인
		System.out.println(cartOptionUpdateDto.getCartNo());
		System.out.println(cartOptionUpdateDto.getProductOptionDetailNo());
		System.out.println(cartOptionUpdateDto.getProductOptionDetailNo().size());
		if((Long)cartOptionUpdateDto.getCartNo()==null || cartOptionUpdateDto.getProductOptionDetailNo() == null
				|| cartOptionUpdateDto.getProductOptionDetailNo().size()==0) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("잘못된 요청"));
		}
		
		// 정상 동작
		boolean queryResult = cartService.updateProductOptionInCart(cartOptionUpdateDto);
		if(!queryResult)
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.fail("DB 쿼리 실패"));
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(queryResult));
	}
	
	@ApiOperation(value = "장바구니 내의 물품 삭제")
	@RequestMapping(value= "", method=RequestMethod.DELETE)
	public ResponseEntity<JSONResult> cartDelete(
			@RequestBody List<Long> cartNo) {
		
		// cartNo에 대해 유효성 검사
		if(cartNo == null || cartNo.size()==0) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("잘못된 요청"));
		}

		// 정상 동작
		boolean queryResult = cartService.deleteCheckedCartProduct(cartNo);
		if(!queryResult)
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.fail("DB 쿼리 실패"));
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(queryResult));
	}
}
