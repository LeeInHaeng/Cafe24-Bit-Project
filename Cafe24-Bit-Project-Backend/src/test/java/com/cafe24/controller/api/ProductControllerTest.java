package com.cafe24.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import org.springframework.web.filter.CharacterEncodingFilter;

import com.cafe24.dto.ProductOptionDetailNoConvertDto;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
					.addFilters(new CharacterEncodingFilter("UTF-8", true))
					.build();
	}
	
	@Test
	public void Test_1_ProductListPageConnect() throws Exception {
		
		// 정상 동작 1페이지 리스트
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/product/코트/1")
					.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")));
		
		// 정상 동작 2페이지 리스트
		resultActions = 
				mockMvc
					.perform(get("/api/product/코트/2")
					.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")));
		
		// 카테고리와 페이지를 둘 다 적지 않은 경우
		resultActions = 
				mockMvc
					.perform(get("/api/product").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		/////////////////// 페이지 관련 테스트 /////////////////////
		
		// 페이지를 적지 않은 경우 1페이지로 간주
		resultActions = 
				mockMvc
					.perform(get("/api/product/코트").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")));
		
		// 페이지 번호가 숫자가 아닌 경우 1페이지로 간주
		resultActions = 
				mockMvc
					.perform(get("/api/product/코트/1페이지").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")));
		
		// 페이지가 0보다 작은 경우 1페이지로 간주
		resultActions = 
				mockMvc
					.perform(get("/api/product/코트/-1").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")));
		
		// 페이지 번호가 최대 페이지 번호를 넘어간 경우 마지막 페이지로 간주
		resultActions = 
				mockMvc
					.perform(get("/api/product/코트/9999").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")));
		
		// 데이터베이스에 없는 카테고리를 요청하는 경우
		resultActions = 
				mockMvc
					.perform(get("/api/product/없는 카테고리/1").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data").doesNotExist());
	}
	
	@Test
	public void Test_2_SearchProductListPageConnect() throws Exception {
		
		// 정상 동작1 페이지 리스트
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/product/search/코트/1").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk());
		
		// 정상 동작2 페이지 리스트
		resultActions = 
				mockMvc
					.perform(get("/api/product/search/주황/1").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk());
		
		// 검색 키워드와 페이지를 둘 다 적지 않은 경우
		resultActions = 
				mockMvc
					.perform(get("/api/product/search").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		/////////////////// 페이지 관련 테스트 /////////////////////
		
		// 페이지를 적지 않은 경우 1페이지로 간주
		resultActions = 
				mockMvc
					.perform(get("/api/product/search/주황").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")));
		
		// 페이지 번호가 숫자가 아닌 경우 1페이지로 간주
		resultActions = 
				mockMvc
					.perform(get("/api/product/search/주황/1페이지").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")));
		
		// 페이지가 0보다 작은 경우 1페이지로 간주
		resultActions = 
				mockMvc
					.perform(get("/api/product/search/주황/-1").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")));
		
		// 페이지 번호가 최대 페이지 번호를 넘어간 경우 마지막 페이지로 간주
		resultActions = 
				mockMvc
					.perform(get("/api/product/search/주황/9999").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")));
		
		// 데이터베이스에 없는 키워드를 요청하는 경우
		resultActions = 
				mockMvc
					.perform(get("/api/product/search/없는 키워드/1").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data").doesNotExist());
	}
	
	@Test
	public void Test_3_showProductDetailInfo() throws Exception {
		
		// 정상 동작
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/product/detail/2").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andDo(print());
		
		// 상품 번호가 DB 테이블에 없는 경우
		resultActions = 
				mockMvc
					.perform(get("/api/product/detail/9999").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data").doesNotExist());
		
		// 상품 번호가 비어있는 경우
		resultActions = 
				mockMvc
					.perform(get("/api/product/detail").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 상품 번호가 숫자가 아닌 경우
		resultActions = 
				mockMvc
					.perform(get("/api/product/detail/가나다").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));

	}
	
	@Test
	public void Test_4_getOptionDetailNo() throws Exception {
		
		// 정상 동작
		
		ProductOptionDetailNoConvertDto dto = new ProductOptionDetailNoConvertDto();
		dto.setProductNo(24L);
		dto.setOptionCode("흰색/80");
		
		ResultActions resultActions = 
				mockMvc
					.perform(post("/api/product/getOptionDetailNo")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isOk())
			.andDo(print());
	}

}
