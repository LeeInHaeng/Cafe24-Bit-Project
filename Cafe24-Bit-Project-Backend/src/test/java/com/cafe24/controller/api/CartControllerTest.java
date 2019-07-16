package com.cafe24.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.cafe24.dto.ProductInfo;
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
		
		ProductInfo info = new ProductInfo();
		info.setProductNo(1L);
		info.setQuantity(2L);
		info.setProductOptionDetailNo(1L);
		
		ResultActions resultActions = 
				mockMvc
					.perform(post("/api/cart")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(info)));
		
		resultActions
			.andExpect(status().isOk());
		
		// POST로 넘어오는 파라미터인 productInfo가 비어있는 경우 처리
		
		// POST로 넘어오는 파라미터에 악의적인 공격이 있을만한 특수문자 등의 경우 처리
		
		// 회원인 경우 회원 아이디를 바탕으로 장바구니 정보를 저장
		
		// 회원인 경우 세션에 저장되어 있는 회원 아이디와 장바구니에 저장하고자 하는 회원 아이디가 같은지 확인
		
		// 비회원인 경우 접속한 맥주소를 바탕으로 장바구니 정보를 저장
		
		// 비회원인 경우 접속한 맥주소와 장바구니에 저장하고자 하는 맥주소가 같은지 확인
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
