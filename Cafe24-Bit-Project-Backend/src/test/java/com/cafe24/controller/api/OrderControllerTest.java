package com.cafe24.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import com.cafe24.dao.OrderDao;
import com.cafe24.dto.OrderBuyDto;
import com.cafe24.dto.OrderPageDto;
import com.cafe24.dto.ProductOptionDto;
import com.cafe24.vo.NonmemberVo;
import com.cafe24.vo.OrderProductVo;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private OrderDao orderDao;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void Test_1_CheckProductQuantity() throws Exception {

		// 정상 동작
		List<ProductOptionDto> params = new ArrayList<ProductOptionDto>();
		
		ProductOptionDto dto = new ProductOptionDto();
		dto.setProductNo(1L);
		dto.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		
		ProductOptionDto dto2 = new ProductOptionDto();
		dto2.setProductNo(2L);
		dto2.setProductOptionDetailNo(Arrays.asList(5L, 7L));
		
		params.add(dto);
		params.add(dto2);
		
		ResultActions resultActions = 
				mockMvc
					.perform(post("/api/order/check/quantity")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(params)));
		
		resultActions
			.andExpect(status().isOk())
			.andDo(print());
	
		
		// 해당 상품이 상품 옵션을 갖고 있지 않은 경우
		params = new ArrayList<ProductOptionDto>();
		
		dto = new ProductOptionDto();
		dto.setProductNo(999L);
		dto.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		
		params.add(dto);
		
		resultActions = 
				mockMvc
					.perform(post("/api/order/check/quantity")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(params)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 상품 번호가 비어있는 경우
		params = new ArrayList<ProductOptionDto>();
		
		dto = new ProductOptionDto();
		dto.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		
		params.add(dto);
		
		resultActions = 
				mockMvc
					.perform(post("/api/order/check/quantity")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(params)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 상품 옵션 번호가 비어있는 경우
		params = new ArrayList<ProductOptionDto>();
		
		dto = new ProductOptionDto();
		dto.setProductNo(1L);
		
		params.add(dto);
		
		resultActions = 
				mockMvc
					.perform(post("/api/order/check/quantity")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(params)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
	}
	
	@Test
	public void Test_2_OrderPageConnect() throws Exception {
		
		// 정상 동작 회원인 경우
		OrderPageDto orderPageDto = new OrderPageDto();
		orderPageDto.setMemberId("user1");
		
		ProductOptionDto productOptionDto1 = new ProductOptionDto();
		productOptionDto1.setProductNo(1L);
		productOptionDto1.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		productOptionDto1.setQuantity(2L);
		
		ProductOptionDto productOptionDto2 = new ProductOptionDto();
		productOptionDto2.setProductNo(2L);
		productOptionDto2.setProductOptionDetailNo(Arrays.asList(5L, 7L));
		productOptionDto2.setQuantity(1L);
		
		orderPageDto.setProductOptionDto(Arrays.asList(productOptionDto1, productOptionDto2));
	    
		ResultActions resultActions = 
				mockMvc
					.perform(post("/api/order")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(orderPageDto)));
		
		resultActions
			.andExpect(status().isOk())
			.andDo(print());
		
		// 정상 동작 비회원인 경우
		orderPageDto = new OrderPageDto();
		orderPageDto.setNonmemberMac("non1-mac-address");
		
		productOptionDto1 = new ProductOptionDto();
		productOptionDto1.setProductNo(1L);
		productOptionDto1.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		productOptionDto1.setQuantity(2L);
		
		productOptionDto2 = new ProductOptionDto();
		productOptionDto2.setProductNo(2L);
		productOptionDto2.setProductOptionDetailNo(Arrays.asList(5L, 7L));
		productOptionDto2.setQuantity(1L);
		
		orderPageDto.setProductOptionDto(Arrays.asList(productOptionDto1, productOptionDto2));
		
		resultActions = 
				mockMvc
					.perform(post("/api/order")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(orderPageDto)));
		
		resultActions
			.andExpect(status().isOk())
			.andDo(print());
		
		// 회원 비회원의 정보가 모두 없는 경우
		orderPageDto = new OrderPageDto();
		
		productOptionDto1 = new ProductOptionDto();
		productOptionDto1.setProductNo(1L);
		productOptionDto1.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		productOptionDto1.setQuantity(2L);
		
		productOptionDto2 = new ProductOptionDto();
		productOptionDto2.setProductNo(2L);
		productOptionDto2.setProductOptionDetailNo(Arrays.asList(1L, 2L));
		productOptionDto2.setQuantity(1L);
		
		orderPageDto.setProductOptionDto(Arrays.asList(productOptionDto1, productOptionDto2));
		
		resultActions = 
				mockMvc
					.perform(post("/api/order")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(orderPageDto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 주문하는 상품이 비어있는 경우
		orderPageDto = new OrderPageDto();
		orderPageDto.setMemberId("user1");

		resultActions = 
				mockMvc
					.perform(post("/api/order")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(orderPageDto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
	}
	
	@Test
	public void Test_3_BuyProducts() throws Exception {
		
		// orderDao.clear();
		
		// 정상 동작 - 회원 주문
		OrderBuyDto dto = new OrderBuyDto();
		dto.setMemberId("user1");
		dto.setReciever("user1");
		dto.setRecieverAddress("user1의 주소");
		dto.setMessage("신속한 배달 부탁해요");
		dto.setTotalPrice(30000L);
		dto.setPaymethod("무통장 입금");
		dto.setPayStatus("입금전");
		
		ProductOptionDto product1 = new ProductOptionDto();
		product1.setProductNo(1L);
		product1.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		product1.setQuantity(2L);
		
		ProductOptionDto product2 = new ProductOptionDto();
		product2.setProductNo(2L);
		product2.setProductOptionDetailNo(Arrays.asList(5L, 7L));
		product2.setQuantity(1L);
		
		dto.setProductOptionDto(Arrays.asList(product1, product2));
		
		ResultActions resultActions = 
				mockMvc
					.perform(post("/api/order/buy")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
		
		// 정상 동작 - 비회원
		dto = new OrderBuyDto();
		dto.setNonmemberMac("non1-mac-address");
		dto.setNonmemberPhone("010-1234-5678");
		dto.setNonmemberPass("1234");
		dto.setNonmemberName("홍길동");
		dto.setNonmemberRefundName("비회원1 은행");
		dto.setNonmemberRefundNumber("비회원1의 계좌번호");
		
		dto.setReciever("non1-mac-address");
		dto.setRecieverAddress("non1-mac-address 의 주소");
		dto.setMessage("신속한 배달 부탁해요");
		dto.setTotalPrice(30000L);
		dto.setPaymethod("무통장 입금");
		dto.setPayStatus("입금전");
		
		product1 = new ProductOptionDto();
		product1.setProductNo(1L);
		product1.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		product1.setQuantity(2L);
		
		product2 = new ProductOptionDto();
		product2.setProductNo(2L);
		product2.setProductOptionDetailNo(Arrays.asList(5L, 7L));
		product2.setQuantity(1L);
		
		dto.setProductOptionDto(Arrays.asList(product1, product2));
		
		resultActions = 
				mockMvc
					.perform(post("/api/order/buy")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
		
		// 회원의 아이디, 비회원의 맥주소가 둘 다 없는 경우
		dto = new OrderBuyDto();
		dto.setNonmemberPhone("010-1234-5678");
		dto.setNonmemberPass("1234");
		dto.setNonmemberName("홍길동");
		dto.setNonmemberRefundName("비회원1 은행");
		dto.setNonmemberRefundNumber("비회원1의 계좌번호");
		
		dto.setReciever("non1-mac-address");
		dto.setRecieverAddress("non1-mac-address 의 주소");
		dto.setMessage("신속한 배달 부탁해요");
		dto.setTotalPrice(30000L);
		dto.setPaymethod("무통장 입금");
		dto.setPayStatus("입금전");
		
		product1 = new ProductOptionDto();
		product1.setProductNo(1L);
		product1.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		product1.setQuantity(2L);
		
		product2 = new ProductOptionDto();
		product2.setProductNo(2L);
		product2.setProductOptionDetailNo(Arrays.asList(5L, 7L));
		product2.setQuantity(1L);
		
		dto.setProductOptionDto(Arrays.asList(product1, product2));
		
		resultActions = 
				mockMvc
					.perform(post("/api/order/buy")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 가격이 틀린 경우
		dto = new OrderBuyDto();
		dto.setNonmemberMac("non1-mac-address");
		dto.setNonmemberPhone("010-1234-5678");
		dto.setNonmemberPass("1234");
		dto.setNonmemberName("홍길동");
		dto.setNonmemberRefundName("비회원1 은행");
		dto.setNonmemberRefundNumber("비회원1의 계좌번호");
		
		dto.setReciever("non1-mac-address");
		dto.setRecieverAddress("non1-mac-address 의 주소");
		dto.setMessage("신속한 배달 부탁해요");
		dto.setTotalPrice(300L);
		dto.setPaymethod("무통장 입금");
		dto.setPayStatus("입금전");
		
		product1 = new ProductOptionDto();
		product1.setProductNo(1L);
		product1.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		product1.setQuantity(2L);
		
		product2 = new ProductOptionDto();
		product2.setProductNo(2L);
		product2.setProductOptionDetailNo(Arrays.asList(5L, 7L));
		product2.setQuantity(1L);
		
		dto.setProductOptionDto(Arrays.asList(product1, product2));
		
		resultActions = 
				mockMvc
					.perform(post("/api/order/buy")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
		
		// 비회원의 핸드폰번호 양식이 틀린 경우
		dto = new OrderBuyDto();
		dto.setNonmemberMac("non1-mac-address");
		dto.setNonmemberPhone("010-asd-asdf");
		dto.setNonmemberPass("1234");
		dto.setNonmemberName("홍길동");
		dto.setNonmemberRefundName("비회원1 은행");
		dto.setNonmemberRefundNumber("비회원1의 계좌번호");
		
		dto.setReciever("non1-mac-address");
		dto.setRecieverAddress("non1-mac-address 의 주소");
		dto.setMessage("신속한 배달 부탁해요");
		dto.setTotalPrice(30000L);
		dto.setPaymethod("무통장 입금");
		dto.setPayStatus("입금전");
		
		product1 = new ProductOptionDto();
		product1.setProductNo(1L);
		product1.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		product1.setQuantity(2L);
		
		product2 = new ProductOptionDto();
		product2.setProductNo(2L);
		product2.setProductOptionDetailNo(Arrays.asList(5L, 7L));
		product2.setQuantity(1L);
		
		dto.setProductOptionDto(Arrays.asList(product1, product2));
		
		resultActions = 
				mockMvc
					.perform(post("/api/order/buy")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
		
		// 비회원 이름의 형식이 틀린 경우
		dto = new OrderBuyDto();
		dto.setNonmemberMac("non1-mac-address");
		dto.setNonmemberPhone("010-1234-5678");
		dto.setNonmemberPass("1234");
		dto.setNonmemberName("가나다라마바사의이름");
		dto.setNonmemberRefundName("비회원1 은행");
		dto.setNonmemberRefundNumber("비회원1의 계좌번호");
		
		dto.setReciever("non1-mac-address");
		dto.setRecieverAddress("non1-mac-address 의 주소");
		dto.setMessage("신속한 배달 부탁해요");
		dto.setTotalPrice(30000L);
		dto.setPaymethod("무통장 입금");
		dto.setPayStatus("입금전");
		
		product1 = new ProductOptionDto();
		product1.setProductNo(1L);
		product1.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		product1.setQuantity(2L);
		
		product2 = new ProductOptionDto();
		product2.setProductNo(2L);
		product2.setProductOptionDetailNo(Arrays.asList(5L, 7L));
		product2.setQuantity(1L);
		
		dto.setProductOptionDto(Arrays.asList(product1, product2));
		
		resultActions = 
				mockMvc
					.perform(post("/api/order/buy")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 환불 계좌 은행을 적지 않은 경우
		dto = new OrderBuyDto();
		dto.setNonmemberMac("non1-mac-address");
		dto.setNonmemberPhone("010-1234-5678");
		dto.setNonmemberPass("1234");
		dto.setNonmemberName("홍길동");
		dto.setNonmemberRefundNumber("비회원1의 계좌번호");
		
		dto.setReciever("non1-mac-address");
		dto.setRecieverAddress("non1-mac-address 의 주소");
		dto.setMessage("신속한 배달 부탁해요");
		dto.setTotalPrice(30000L);
		dto.setPaymethod("무통장 입금");
		dto.setPayStatus("입금전");
		
		product1 = new ProductOptionDto();
		product1.setProductNo(1L);
		product1.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		product1.setQuantity(2L);
		
		product2 = new ProductOptionDto();
		product2.setProductNo(2L);
		product2.setProductOptionDetailNo(Arrays.asList(5L, 7L));
		product2.setQuantity(1L);
		
		dto.setProductOptionDto(Arrays.asList(product1, product2));
		
		resultActions = 
				mockMvc
					.perform(post("/api/order/buy")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 환불 계좌 은행의 이름이 255자를 넘는 경우
		dto = new OrderBuyDto();
		dto.setNonmemberMac("non1-mac-address");
		dto.setNonmemberPhone("010-1234-5678");
		dto.setNonmemberPass("1234");
		dto.setNonmemberName("홍길동");
		dto.setNonmemberRefundName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		dto.setNonmemberRefundNumber("비회원1의 계좌번호");
		
		dto.setReciever("non1-mac-address");
		dto.setRecieverAddress("non1-mac-address 의 주소");
		dto.setMessage("신속한 배달 부탁해요");
		dto.setTotalPrice(30000L);
		dto.setPaymethod("무통장 입금");
		dto.setPayStatus("입금전");
		
		product1 = new ProductOptionDto();
		product1.setProductNo(1L);
		product1.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		product1.setQuantity(2L);
		
		product2 = new ProductOptionDto();
		product2.setProductNo(2L);
		product2.setProductOptionDetailNo(Arrays.asList(5L, 7L));
		product2.setQuantity(1L);
		
		dto.setProductOptionDto(Arrays.asList(product1, product2));
		
		resultActions = 
				mockMvc
					.perform(post("/api/order/buy")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 환불 계좌번호를 적지 않은 경우
		dto = new OrderBuyDto();
		dto.setNonmemberMac("non1-mac-address");
		dto.setNonmemberPhone("010-1234-5678");
		dto.setNonmemberPass("1234");
		dto.setNonmemberName("홍길동");
		dto.setNonmemberRefundName("비회원1 은행");
		
		dto.setReciever("non1-mac-address");
		dto.setRecieverAddress("non1-mac-address 의 주소");
		dto.setMessage("신속한 배달 부탁해요");
		dto.setTotalPrice(30000L);
		dto.setPaymethod("무통장 입금");
		dto.setPayStatus("입금전");
		
		product1 = new ProductOptionDto();
		product1.setProductNo(1L);
		product1.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		product1.setQuantity(2L);
		
		product2 = new ProductOptionDto();
		product2.setProductNo(2L);
		product2.setProductOptionDetailNo(Arrays.asList(5L, 7L));
		product2.setQuantity(1L);
		
		dto.setProductOptionDto(Arrays.asList(product1, product2));
		
		resultActions = 
				mockMvc
					.perform(post("/api/order/buy")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 환불 계좌 번호가 255자가 넘은 경우
		dto = new OrderBuyDto();
		dto.setNonmemberMac("non1-mac-address");
		dto.setNonmemberPhone("010-1234-5678");
		dto.setNonmemberPass("1234");
		dto.setNonmemberName("홍길동");
		dto.setNonmemberRefundName("비회원1 은행");
		dto.setNonmemberRefundNumber("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		
		dto.setReciever("non1-mac-address");
		dto.setRecieverAddress("non1-mac-address 의 주소");
		dto.setMessage("신속한 배달 부탁해요");
		dto.setTotalPrice(30000L);
		dto.setPaymethod("무통장 입금");
		dto.setPayStatus("입금전");
		
		product1 = new ProductOptionDto();
		product1.setProductNo(1L);
		product1.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		product1.setQuantity(2L);
		
		product2 = new ProductOptionDto();
		product2.setProductNo(2L);
		product2.setProductOptionDetailNo(Arrays.asList(5L, 7L));
		product2.setQuantity(1L);
		
		dto.setProductOptionDto(Arrays.asList(product1, product2));
		
		resultActions = 
				mockMvc
					.perform(post("/api/order/buy")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 받는 사람을 입력하지 않은 경우
		dto = new OrderBuyDto();
		dto.setNonmemberMac("non1-mac-address");
		dto.setNonmemberPhone("010-1234-5678");
		dto.setNonmemberPass("1234");
		dto.setNonmemberName("홍길동");
		dto.setNonmemberRefundName("비회원1 은행");
		dto.setNonmemberRefundNumber("비회원1의 계좌번호");
		
		dto.setRecieverAddress("non1-mac-address 의 주소");
		dto.setMessage("신속한 배달 부탁해요");
		dto.setTotalPrice(30000L);
		dto.setPaymethod("무통장 입금");
		dto.setPayStatus("입금전");
		
		product1 = new ProductOptionDto();
		product1.setProductNo(1L);
		product1.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		product1.setQuantity(2L);
		
		product2 = new ProductOptionDto();
		product2.setProductNo(2L);
		product2.setProductOptionDetailNo(Arrays.asList(5L, 7L));
		product2.setQuantity(1L);
		
		dto.setProductOptionDto(Arrays.asList(product1, product2));
		
		resultActions = 
				mockMvc
					.perform(post("/api/order/buy")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 받는 사람의 길이가 255자를 넘는 경우
		dto = new OrderBuyDto();
		dto.setNonmemberMac("non1-mac-address");
		dto.setNonmemberPhone("010-1234-5678");
		dto.setNonmemberPass("1234");
		dto.setNonmemberName("홍길동");
		dto.setNonmemberRefundName("비회원1 은행");
		dto.setNonmemberRefundNumber("비회원1의 계좌번호");
		
		dto.setReciever("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		dto.setRecieverAddress("non1-mac-address 의 주소");
		dto.setMessage("신속한 배달 부탁해요");
		dto.setTotalPrice(30000L);
		dto.setPaymethod("무통장 입금");
		dto.setPayStatus("입금전");
		
		product1 = new ProductOptionDto();
		product1.setProductNo(1L);
		product1.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		product1.setQuantity(2L);
		
		product2 = new ProductOptionDto();
		product2.setProductNo(2L);
		product2.setProductOptionDetailNo(Arrays.asList(5L, 7L));
		product2.setQuantity(1L);
		
		dto.setProductOptionDto(Arrays.asList(product1, product2));
		
		resultActions = 
				mockMvc
					.perform(post("/api/order/buy")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 받는 사람의 주소를 입력하지 않은 경우
		dto = new OrderBuyDto();
		dto.setNonmemberMac("non1-mac-address");
		dto.setNonmemberPhone("010-1234-5678");
		dto.setNonmemberPass("1234");
		dto.setNonmemberName("홍길동");
		dto.setNonmemberRefundName("비회원1 은행");
		dto.setNonmemberRefundNumber("비회원1의 계좌번호");
		
		dto.setReciever("non1-mac-address");
		dto.setMessage("신속한 배달 부탁해요");
		dto.setTotalPrice(30000L);
		dto.setPaymethod("무통장 입금");
		dto.setPayStatus("입금전");
		
		product1 = new ProductOptionDto();
		product1.setProductNo(1L);
		product1.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		product1.setQuantity(2L);
		
		product2 = new ProductOptionDto();
		product2.setProductNo(2L);
		product2.setProductOptionDetailNo(Arrays.asList(5L, 7L));
		product2.setQuantity(1L);
		
		dto.setProductOptionDto(Arrays.asList(product1, product2));
		
		resultActions = 
				mockMvc
					.perform(post("/api/order/buy")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 받는 사람의 주소가 255자가 넘는 경우
		dto = new OrderBuyDto();
		dto.setNonmemberMac("non1-mac-address");
		dto.setNonmemberPhone("010-1234-5678");
		dto.setNonmemberPass("1234");
		dto.setNonmemberName("홍길동");
		dto.setNonmemberRefundName("비회원1 은행");
		dto.setNonmemberRefundNumber("비회원1의 계좌번호");
		
		dto.setReciever("non1-mac-address");
		dto.setRecieverAddress("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		dto.setMessage("신속한 배달 부탁해요");
		dto.setTotalPrice(30000L);
		dto.setPaymethod("무통장 입금");
		dto.setPayStatus("입금전");
		
		product1 = new ProductOptionDto();
		product1.setProductNo(1L);
		product1.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		product1.setQuantity(2L);
		
		product2 = new ProductOptionDto();
		product2.setProductNo(2L);
		product2.setProductOptionDetailNo(Arrays.asList(5L, 7L));
		product2.setQuantity(1L);
		
		dto.setProductOptionDto(Arrays.asList(product1, product2));
		
		resultActions = 
				mockMvc
					.perform(post("/api/order/buy")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 배송 메세지가 255자가 넘는 경우
		dto = new OrderBuyDto();
		dto.setNonmemberMac("non1-mac-address");
		dto.setNonmemberPhone("010-1234-5678");
		dto.setNonmemberPass("1234");
		dto.setNonmemberName("홍길동");
		dto.setNonmemberRefundName("비회원1 은행");
		dto.setNonmemberRefundNumber("비회원1의 계좌번호");
		
		dto.setReciever("non1-mac-address");
		dto.setRecieverAddress("non1-mac-address 의 주소");
		dto.setMessage("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		dto.setTotalPrice(30000L);
		dto.setPaymethod("무통장 입금");
		dto.setPayStatus("입금전");
		
		product1 = new ProductOptionDto();
		product1.setProductNo(1L);
		product1.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		product1.setQuantity(2L);
		
		product2 = new ProductOptionDto();
		product2.setProductNo(2L);
		product2.setProductOptionDetailNo(Arrays.asList(5L, 7L));
		product2.setQuantity(1L);
		
		dto.setProductOptionDto(Arrays.asList(product1, product2));
		
		resultActions = 
				mockMvc
					.perform(post("/api/order/buy")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
	}
	
	@Test
	public void Test_4_OrderSuccessPageConnect() throws Exception {
		
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/order/success")
							.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")));
	}
	
	@Test
	public void Test_5_ShowProductHistoryMember() throws Exception {
		
		// 정상 동작
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/order/history/user1")
							.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andDo(print());
		
		// memberId에 특수문자가 들어간 경우
		resultActions = 
				mockMvc
					.perform(get("/api/order/history/'")
							.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data").doesNotExist());
	}
	
	@Test
	public void Test_6_ConnectNonmemberHistoryPage() throws Exception {
		
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/order/history/nonmember")
							.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")));
	}
	
	@Test
	public void Test_7_ShowProductHistoryNonmember() throws Exception {
		
		NonmemberVo nonmemberVo = new NonmemberVo();
		nonmemberVo.setNonmemberPhone("010-1234-5678");
		nonmemberVo.setNonmemberPass("1234");
		
		// 정상 동작
		ResultActions resultActions = 
				mockMvc
					.perform(post("/api/order/history/nonmember")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(nonmemberVo)));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andDo(print());
		
		// 비회원이 핸드폰 형식으로 입력하지 않은 경우
		nonmemberVo = new NonmemberVo();
		nonmemberVo.setNonmemberPhone("010-asd-5678");
		nonmemberVo.setNonmemberPass("1234");
		
		resultActions = 
				mockMvc
					.perform(post("/api/order/history/nonmember")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(nonmemberVo)));
		
		// 비회원이 주문 조회 비밀번호를 입력하지 않은 경우
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		nonmemberVo = new NonmemberVo();
		nonmemberVo.setNonmemberPhone("010-1234-5678");
		
		resultActions = 
				mockMvc
					.perform(post("/api/order/history/nonmember")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(nonmemberVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
	}
	
	@Test
	public void Test_8_ProductStatusChangePageConnect() throws Exception {
		
		// 정상 동작
		OrderProductVo orderProductVo = new OrderProductVo();
		orderProductVo.setOrderNo(1L);
		orderProductVo.setProductNo(2L);
		orderProductVo.setOrderStatus("교환");
		orderProductVo.setOrderStatusChangeReason("옷의 상태가 이상해요");
		
		ResultActions resultActions = 
				mockMvc
					.perform(put("/api/order/status")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(orderProductVo)));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
		
		// 주문 번호가 비어있는 경우
		orderProductVo = new OrderProductVo();
		orderProductVo.setProductNo(2L);
		orderProductVo.setOrderStatus("교환");
		orderProductVo.setOrderStatusChangeReason("옷의 상태가 이상해요");
		
		resultActions = 
				mockMvc
					.perform(put("/api/order/status")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(orderProductVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 상품 번호가 비어있는 경우
		orderProductVo = new OrderProductVo();
		orderProductVo.setOrderNo(1L);
		orderProductVo.setOrderStatus("교환");
		orderProductVo.setOrderStatusChangeReason("옷의 상태가 이상해요");
		
		resultActions = 
				mockMvc
					.perform(put("/api/order/status")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(orderProductVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 상태가 취소, 교환, 반품, 환불이 아닌 경우
		orderProductVo = new OrderProductVo();
		orderProductVo.setOrderNo(1L);
		orderProductVo.setProductNo(2L);
		orderProductVo.setOrderStatus("바꿔주세요");
		orderProductVo.setOrderStatusChangeReason("옷의 상태가 이상해요");
		
		resultActions = 
				mockMvc
					.perform(put("/api/order/status")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(orderProductVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 상태를 변경하는 이유를 적지 않은 경우
		orderProductVo = new OrderProductVo();
		orderProductVo.setOrderNo(1L);
		orderProductVo.setProductNo(2L);
		orderProductVo.setOrderStatus("교환");
		
		resultActions = 
				mockMvc
					.perform(put("/api/order/status")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(orderProductVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
	}

}
