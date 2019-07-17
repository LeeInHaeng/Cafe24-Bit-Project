package com.cafe24.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.vo.CartVo;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CartControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void Test_1_AddCartProduct() throws Exception {
		
		// 정상 동작 1 회원인 경우
		// user1 사용자가 1번 상품을 장바구니에 담음
		CartVo cartVo = new CartVo();
		cartVo.setMemberId("user1");
		cartVo.setProductNo(1L);
		cartVo.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		cartVo.setQuantity(2L);
		
		ResultActions resultActions = 
				mockMvc
					.perform(post("/api/cart")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(cartVo)));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
		
		// 정사 동작 2 회원인 경우
		// user1 사용자가 1번 상품을 장바구니에 다시 담음 (상품 상세 옵션과 수량을 변경하고)
		cartVo.setMemberId("user1");
		cartVo.setProductNo(1L);
		cartVo.setProductOptionDetailNo(Arrays.asList(1L, 2L));
		cartVo.setQuantity(3L);
		
		resultActions = 
				mockMvc
					.perform(post("/api/cart")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(cartVo)));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
		
		// 정상동작 3 회원인 경우
		// user1 사용자가 2번 상품을 장바구니에 담음
		cartVo.setMemberId("user1");
		cartVo.setProductNo(2L);
		cartVo.setProductOptionDetailNo(Arrays.asList(5L, 6L));
		cartVo.setQuantity(1L);
		
		resultActions = 
				mockMvc
					.perform(post("/api/cart")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(cartVo)));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
		
		// 정상 동작 4 비회원인 경우
		// 비회원이 1번 상품을 장바구니에 담음
		cartVo.setMemberId(null);
		cartVo.setNonmemberMac("non1-mac-address");
		cartVo.setProductNo(1L);
		cartVo.setProductOptionDetailNo(Arrays.asList(1L, 2L));
		cartVo.setQuantity(2L);
		
		resultActions = 
				mockMvc
					.perform(post("/api/cart")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(cartVo)));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
		
		// 정상 동작 5 비회원인 경우
		// 동일한 비회원이 1번 상품을 장바구니에 담음 (옵션 및 수량 변경)
		cartVo.setMemberId(null);
		cartVo.setNonmemberMac("non1-mac-address");
		cartVo.setProductNo(1L);
		cartVo.setProductOptionDetailNo(Arrays.asList(1L, 3L));
		cartVo.setQuantity(3L);
		
		resultActions = 
				mockMvc
					.perform(post("/api/cart")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(cartVo)));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
		
		// 정상 동작 6 비회원인 경우
		// 동일한 비회원이 2번 상품을 장바구니에 담음
		cartVo.setMemberId(null);
		cartVo.setNonmemberMac("non1-mac-address");
		cartVo.setProductNo(2L);
		cartVo.setProductOptionDetailNo(Arrays.asList(5L, 7L));
		cartVo.setQuantity(1L);
		
		resultActions = 
				mockMvc
					.perform(post("/api/cart")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(cartVo)));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
		
		////////// CartVo 유효성 검사 ////////////////
		
		// 존재하지 않는 사용자가 요청하는 경우
		String json = "{\"memberId\":\"user100\",\"productNo\":1,\"quantity\":2,\"productOptionDetailNo\":[1,3]}";
		
		resultActions = 
				mockMvc
					.perform(post("/api/cart")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 회원 아이디도 적혀있지 않고 비회원 맥주소도 적혀있지 않은 경우
		json = "{\"productNo\":1,\"quantity\":2,\"productOptionDetailNo\":[1,3]}";
		
		resultActions = 
				mockMvc
					.perform(post("/api/cart")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 상품 번호나 수량이 적혀있지 않은 경우
		json = "{\"memberId\":\"user1\",\"productOptionDetailNo\":[1,3]}";
		
		resultActions = 
				mockMvc
					.perform(post("/api/cart")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 상품 번호나 수량에 숫자가 들어가있지 않은 경우
		json = "{\"memberId\":\"user1\",\"productNo\":a,\"quantity\":b,\"productOptionDetailNo\":[1,3]}";
		
		resultActions = 
				mockMvc
					.perform(post("/api/cart")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json));
		
		resultActions
			.andExpect(status().isBadRequest());
		
		// 상품 옵션에 숫자가 들어있지 않은 경우
		json = "{\"memberId\":\"user1\",\"productNo\":1,\"quantity\":1,\"productOptionDetailNo\":[a,3]}";
		
		resultActions = 
				mockMvc
					.perform(post("/api/cart")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json));
		
		resultActions
			.andExpect(status().isBadRequest());
		
		// 존재하지 않은 상품이거나, 진열 가능인 상품이 아닌 경우
		json = "{\"memberId\":\"user1\",\"productNo\":100,\"quantity\":1,\"productOptionDetailNo\":[1,3]}";
		
		resultActions = 
				mockMvc
					.perform(post("/api/cart")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 상품 상세 옵션이 해당 상품의 옵션이 아닌 경우
		json = "{\"memberId\":\"user1\",\"productNo\":1,\"quantity\":1,\"productOptionDetailNo\":[99,100]}";
		
		resultActions = 
				mockMvc
					.perform(post("/api/cart")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
	}
	
	@Test
	public void Test_2_ShowCartDetail() throws Exception {
		
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/cart")
							.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk());
		
		// 회원인 경우 세션에 저장되어 있는 회원 아이디를 바탕으로 장바구니 정보를 조회
		
		// 비회원인 경우 접속한 맥주소를 바탕으로 장바구니 정보를 조회
	}
	
	@Test
	public void Test_3_UpdateCartInfo() throws Exception {
		
		CartVo cart = new CartVo();
		cart.setMemberId("user1");
		cart.setProductNo(1L);
		cart.setQuantity(2L);
		cart.setProductOptionDetailNo(Arrays.asList(1L, 2L));
		
		ResultActions resultActions = 
				mockMvc
					.perform(put("/api/cart")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(cart)));
		
		resultActions
			.andExpect(status().isOk());
		
		// 넘어오는 CartVo의 값이 정확하게 들어있는지 확인
		
		// 회원인 경우 세션에 저장되어 있는 회원 아이디를 바탕으로 장바구니 업데이트 및 정보 조회
		
		// 회원인 경우 세션에 저장되어 있는 회원 아이디와 장바구니에 업데이트 하고자 하는 회원 아이디가 같은지 확인
		
		// 비회원인 경우 접속한 맥주소를 바탕으로 장바구니 업데이트 및 정보 조회
		
		// 비회원인 경우 접속한 맥주소와 장바구니에 업데이트 하고자 하는 맥주소가 같은지 확인
	}
	
	@Test
	public void Test_4_DeleteCartCheckProduct() throws Exception {

	    List<String> cartNo = Arrays.asList("2", "3");
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    params.addAll("cartNo", cartNo);
	    
		ResultActions resultActions = 
				mockMvc
					.perform(delete("/api/cart")
							.contentType(MediaType.APPLICATION_JSON)
							.params(params));
		
		resultActions
			.andExpect(status().isOk());
		
		// 넘어오는 카트 번호가 비어있지 않은지 확인
		
		// 넘어오는 카트 번호에 악의적인 공격이 있을만한 특수문자 등의 경우 처리
		
		// 회원인 경우 세션에 저장되어 있는 회원 아이디와 장바구니를 삭제 하고자 하는 회원 아이디가 같은지 확인
		
		// 비회원인 경우 접속한 맥주소와 장바구니에 삭제하고자 하는 맥주소가 같은지 확인
	}
}
