package com.cafe24.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.dto.ProductInfo;
import com.cafe24.dto.ProductOrder;
import com.cafe24.vo.OrderVo;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void Test_1_CheckProductQuantity() throws Exception {
		
		ProductInfo info1 = new ProductInfo();
		info1.setProductNo(1L);
		info1.setProductOptionDetailNo(1L);
		info1.setQuantity(2L);
		
		ProductInfo info2 = new ProductInfo();
		info2.setProductNo(1L);
		info2.setProductOptionDetailNo(2L);
		info2.setQuantity(2L);
		
		List<ProductInfo> infos = new ArrayList<ProductInfo>();
		infos.add(info1);
		infos.add(info2);
	    
		ResultActions resultActions = 
				mockMvc
					.perform(post("/api/order/check/quantity")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(infos)));
		
		resultActions
			.andExpect(status().isOk());
		
		// POST로 넘어오는 파라미터인 productInfo가 비어있는 경우 처리
		
		// POST로 넘어오는 파라미터에 악의적인 공격이 있을만한 특수문자 등의 경우 처리
		
		// 상품의 판매 가능 수량이 정상적으로 있는 경우 아직 주문 전이더라도 상품 옵션 상세 테이블의 판매 가능 수량의 값을 미리 감소시켜 놓는다
		
		// 이후에 실제 주문이 다 이루어지면 상품 옵션 상세 테이블에서 재고의 값을 감소 시킨다
	}
	
	@Test
	public void Test_2_OrderPageConnect() throws Exception {
		
		ProductInfo info1 = new ProductInfo();
		info1.setProductNo(1L);
		info1.setProductOptionDetailNo(1L);
		info1.setQuantity(2L);
		
		ProductInfo info2 = new ProductInfo();
		info2.setProductNo(1L);
		info2.setProductOptionDetailNo(2L);
		info2.setQuantity(2L);
		
		List<ProductInfo> infos = new ArrayList<ProductInfo>();
		infos.add(info1);
		infos.add(info2);
	    
		ResultActions resultActions = 
				mockMvc
					.perform(post("/api/order")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(infos)));
		
		resultActions
			.andExpect(status().isOk());
		
		// 재고 확인 후의 데이터가 그대로 넘어오는지 확인
		
		// 회원인 경우 추가 입력폼 생성
		
		// 비회원인 경우 추가 입력폼 생성
	}
	
	@Test
	public void Test_3_BuyProducts() throws Exception {
		
		ProductInfo info1 = new ProductInfo();
		info1.setProductNo(1L);
		info1.setProductOptionDetailNo(1L);
		info1.setQuantity(2L);
		
		ProductInfo info2 = new ProductInfo();
		info2.setProductNo(1L);
		info2.setProductOptionDetailNo(2L);
		info2.setQuantity(2L);
		
		List<ProductInfo> infos = new ArrayList<ProductInfo>();
		infos.add(info1);
		infos.add(info2);
		
		OrderVo orderVo = new OrderVo();
		orderVo.setMemberId("user1");
		orderVo.setReciever("홍길동");
		orderVo.setRecieverAddress("한국 강남역");
		orderVo.setMessage("빨리 배송해주세요");
		orderVo.setTotalPrice(100000L);
		orderVo.setPaymethod("무통장 입금");
		orderVo.setPayStatus("입금전");
	
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ProductInfo", infos);
		params.put("OrderVo", orderVo);
		
		ResultActions resultActions = 
				mockMvc
					.perform(post("/api/order/buy")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(params)));
		
		resultActions
			.andExpect(status().isOk());
		
		// 주문 페이지에서의 주문하는 상품 데이터가 그대로 넘어오는지 확인
		
		// OrderVo 객체의 유효성 검사
		
		// 회원인 경우 세션에 저장되어있는 아이디와 요청하는 회원아이디가 같은지 확인
		
		// 비회원인 경우 세션에 저장되어있는 맥주소와 요청하는 맥주소가 같은지 확인
	}
	
	@Test
	public void Test_4_OrderSuccessPageConnect() throws Exception {
		
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/order/success")
							.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk());
	}
	
	@Test
	public void Test_5_ShowProductHistory() throws Exception {
		
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/order/history")
							.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk());
		
		// 회원인 경우 회원 아이디와 주문 번호를 바탕으로 주문 내역 조회 페이지를 보여준다
		
		// 비회원인 경우 휴대폰 번호와 주문 조회 비밀번호를 입력하여 주문 내역 조회 페이지를 보여준다
		
		// 비회원이 입력하는 휴대폰 번호와 주문 조회 비밀번호에 악의적인 공격에 사용할 수 있는 특수 문자 등을 입력했는지 검사한다
	}
	
	@Test
	public void Test_6_ProductStatusChangePageConnect() throws Exception {
		
		ResultActions resultActions = 
				mockMvc
					.perform(post("/api/order/status/change")
							.contentType(MediaType.APPLICATION_JSON)
							.param("orderNo", "1")
							.param("productNo", "1"));
		
		resultActions
			.andExpect(status().isOk());
		
		// orderNo와 productNo가 비어있는지 확인
		
		// orderNo와 productNo에 악의적인 공격에 사용할 수 있는 특수 문자 등을 입력했는지 검사
		
		// 회원인 경우 세션에 저장되어 있는 아이디와 orderNo를 바탕으로 주문 테이블의 회원 아이디와  비교하여 일치하는지 확인 

		// 비회원인 경우 세션에 저장되어 있는 맥주소와 orderNo를 바탕으로 주문 테이블의 맥주소와 비교하여 일치하는지 확인
	}
	
	@Test
	public void Test_7_ProductStatusChangeRequest() throws Exception {
		
		ProductOrder productOrder = new ProductOrder();
		productOrder.setOrderNo(1L);
		productOrder.setProductNo(1L);
		productOrder.setOrderStatus("교환");
		productOrder.setOrderStatusChangeReason("작아요");
		
		ResultActions resultActions = 
				mockMvc
					.perform(put("/api/order/status/change")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(productOrder)));
		
		resultActions
			.andExpect(status().isOk());
		
		// productOrder 객체가 비어있는지 검사
		
		// productOrder 객체의 형식 유효성 검사
		
		// 회원인 경우 세션에 저장되어 있는 아이디와 orderNo를 바탕으로 주문 테이블의 회원 아이디와  비교하여 일치하는지 확인 

		// 비회원인 경우 세션에 저장되어 있는 맥주소와 orderNo를 바탕으로 주문 테이블의 맥주소와 비교하여 일치하는지 확인
	}
}
