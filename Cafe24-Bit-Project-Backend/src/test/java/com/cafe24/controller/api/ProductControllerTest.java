package com.cafe24.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void Test_1_ProductListPageConnect() throws Exception {
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/product/Coat/1").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk());
		
		// 카테고리를 적지 않은 경우 처리
		
		// 사용자가 요청한 카테고리가 데이터베이스에 없는 경우 처리
		
		// 페이지번호를 적지 않은 경우 처리
		
		// 페이지 번호가 숫자가 아닌 경우 처리
		
		// 페이지의 숫자가 1보다 작은 경우 처리
		
		// 페이지의 숫자가 최대 페이지의 값보다 큰 경우 처리
	}
	
	@Test
	public void Test_2_SearchProductListPageConnect() throws Exception {
		
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/product/search/Coat/1").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk());
		
		// 검색 키워드를 적지 않은 경우 처리
		
		// 검색 키워드에 악의적인 공격이 있을만한 특수문자 등의 경우 처리
		
		// 페이지번호를 적지 않은 경우 처리
		
		// 페이지 번호가 숫자가 아닌 경우 처리
		
		// 페이지의 숫자가 1보다 작은 경우 처리
		
		// 페이지의 숫자가 최대 페이지의 값보다 큰 경우 처리
	}
	
	@Test
	public void Test_3_showProductDetailInfo() throws Exception {
		
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/product/detail/1").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk());
		
		// 상품 번호가 비어있는 경우 처리
		
		// 상품 번호에 악의적인 공격이 있을만한 특수문자 등의 경우 처리
	}
}
