package com.cafe24.controller.api;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

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

import com.cafe24.dao.CartDao;
import com.cafe24.dto.CartOptionUpdateDto;
import com.cafe24.vo.CartVo;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CartControllerTest {

	private MockMvc mockMvc;
		
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private CartDao cartDao;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void Test_1_AddCartProduct() throws Exception {
		
		cartDao.clear();
		
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
		
		// 정상 동작
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/cart")
							.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andDo(print());
	}
	
	@Test
	public void Test_3_UpdateCartProductCount() throws Exception {
		
		// 정상 동작
		ResultActions resultActions = 
				mockMvc
					.perform(put("/api/cart/count")
							.contentType(MediaType.APPLICATION_JSON)
							.param("cartNo", "2")
							.param("count", "1"));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
		
		////////// count 유효성 검사 ////////////////
		
		// count가 문자인 경우
		resultActions = 
				mockMvc
					.perform(put("/api/cart/count")
							.contentType(MediaType.APPLICATION_JSON)
							.param("cartNo", "2")
							.param("count", "asd"));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
		
		// count가 특수문자인 경우
		resultActions = 
				mockMvc
					.perform(put("/api/cart/count")
							.contentType(MediaType.APPLICATION_JSON)
							.param("cartNo", "2")
							.param("count", "''"));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
		
		// count가 0보다 작은 경우
		resultActions = 
				mockMvc
					.perform(put("/api/cart/count")
							.contentType(MediaType.APPLICATION_JSON)
							.param("cartNo", "2")
							.param("count", "-1"));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
		
		// count가 999보다 큰 경우
		resultActions = 
				mockMvc
					.perform(put("/api/cart/count")
							.contentType(MediaType.APPLICATION_JSON)
							.param("cartNo", "2")
							.param("count", "1000"));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
	}
	
	@Test
	public void Test_5_GetProductOptionListInCart() throws Exception {
		
		// 정상 동작
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/cart/option/1")
							.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andDo(print());
		
		// productNo가 문자인 경우
		resultActions = 
				mockMvc
					.perform(get("/api/cart/option/aa")
							.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// productNo가 특수문자인 경우
		resultActions = 
				mockMvc
					.perform(get("/api/cart/option/'%'")
							.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// productNo가 1보다 작은 경우
		resultActions = 
				mockMvc
					.perform(get("/api/cart/option/-1")
							.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// productNo가 존재하지 않는 상품의 경우
		resultActions = 
				mockMvc
					.perform(get("/api/cart/option/999")
							.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", hasSize(0)));
	}
	
	@Test
	public void Test_6_ProductOptionUpdateInCart() throws Exception {
		
		// 정상 동작
		// 2번 카트에 대해 싱픔 옵션을 2번, 3번으로 변경
		CartOptionUpdateDto updateDto = new CartOptionUpdateDto();
		updateDto.setCartNo(2L);
		updateDto.setProductOptionDetailNo(Arrays.asList(2L,3L));
		
		ResultActions resultActions = 
				mockMvc
					.perform(put("/api/cart/option")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(updateDto)));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
		
		// 2번 카트에 들어있는 상품이 존재하지 않는 상품 상세 옵션으로 변경을 요청하는 경우
		updateDto = new CartOptionUpdateDto();
		updateDto.setCartNo(2L);
		updateDto.setProductOptionDetailNo(Arrays.asList(99L,100L));
		
		resultActions = 
				mockMvc
					.perform(put("/api/cart/option")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(updateDto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 카트 번호가 비어있는 경우
		String json = "{\"cartNo\":,\"productOptionDetailNo\":[2,3]}";
		resultActions = 
				mockMvc
					.perform(put("/api/cart/option")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json));
		
		resultActions
			.andExpect(status().isBadRequest());
		
		// 카트 번호가 숫자가 아닌 경우
		json = "{\"cartNo\":\"a\",\"productOptionDetailNo\":[2,3]}";
		resultActions = 
				mockMvc
					.perform(put("/api/cart/option")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json));
		
		resultActions
			.andExpect(status().isBadRequest());
		
		// 상품 옵션 번호가 비어있는 경우
		json = "{\"cartNo\":2,\"productOptionDetailNo\":}";
		resultActions = 
				mockMvc
					.perform(put("/api/cart/option")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json));
		
		resultActions
			.andExpect(status().isBadRequest());
		
		// 상품 옵션 번호가 숫자가 아닌 경우
		json = "{\"cartNo\":2,\"productOptionDetailNo\":[\"a\",\"'\"]}";
		resultActions = 
				mockMvc
					.perform(put("/api/cart/option")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json));
		
		resultActions
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void Test_7_DeleteCartCheckProduct() throws Exception {

		// 정상 동작
		// 2번 3번의 카트를 삭제
		ResultActions resultActions = 
				mockMvc
					.perform(delete("/api/cart")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(Arrays.asList(2L, 3L))));
		
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(true)));
		
		// 파라미터가 비어있는 경우
		String json = "[]";
		resultActions = 
				mockMvc
					.perform(delete("/api/cart")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));

		// 파라미터가 숫자가 아닌 경우
		json = "[\"a\"]";
		resultActions = 
				mockMvc
					.perform(delete("/api/cart")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json));
		
		resultActions
			.andExpect(status().isBadRequest());
		
	}
}
