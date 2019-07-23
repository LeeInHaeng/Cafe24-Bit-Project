package com.cafe24.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.dto.AdminProductRegisterDto;
import com.cafe24.dto.AdminProductSearchDto;
import com.cafe24.vo.CategoryVo;
import com.cafe24.vo.ProductImageVo;
import com.cafe24.vo.ProductOptionVo;
import com.cafe24.vo.ProductQuantityVo;
import com.cafe24.vo.ProductVo;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductManageControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void Test_1_ProductManageMainPageConnect() throws Exception {
		
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/admin/manage/product/main").contentType(MediaType.APPLICATION_JSON));
		
				resultActions
					.andExpect(status().isOk());
				
				resultActions = 
						mockMvc
							.perform(get("/api/admin/manage/product/index").contentType(MediaType.APPLICATION_JSON));
				
						resultActions
							.andExpect(status().isOk())
							.andExpect(jsonPath("$.result", is("success")));
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@Test
	public void Test_2_ProductRegisterPageConnect() throws Exception {
		
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/admin/manage/product/register").contentType(MediaType.APPLICATION_JSON));
		
				resultActions
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.result", is("success")));
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@Test
	public void Test_3_NewProductRegistRequest() throws Exception {
		
		//////////// 정상 동작 /////////
		
		AdminProductRegisterDto dto = new AdminProductRegisterDto();
		// 상품 정보 추가
		dto.setProductCategoryNo(2L);
		dto.setTitle("신상 코트");
		dto.setImage("http://신상 코트의 대표이미지.jpg");
		dto.setPrice(10000L);
		dto.setMileageAdd(100L);
		dto.setDescription("신상 코트 설명");
		dto.setDescriptionDetail("신상 코트 상세 설명");
		dto.setShippingPrice(1000L);
		
		// 상품의 추가 이미지 여러개 추가
		ProductImageVo imageVo1 = new ProductImageVo();
		imageVo1.setImageDetail("http://신상 코트의 추가이미지1.jpg");
		imageVo1.setImageTitle("신상 코트 추가 이미지 제목1");
		imageVo1.setImageDescription("신상 코트 추가 이미지 설명1");
		
		ProductImageVo imageVo2 = new ProductImageVo();
		imageVo2.setImageDetail("http://신상 코트의 추가이미지2.jpg");
		imageVo2.setImageTitle("신상 코트 추가 이미지 제목2");
		imageVo2.setImageDescription("신상 코트 추가 이미지 설명2");

		dto.setProductImageVo(Arrays.asList(imageVo1, imageVo2));
		
		// 상품 진열정보 추가
		dto.setIsdisplay(false);
		dto.setIssell(false);
		dto.setIsdisplayMain(false);

		
		// 상품 옵션 여러개 추가
		ProductOptionVo option1 = new ProductOptionVo();
		option1.setOptionName("색상");
		option1.setOptionValue("베이지색");
		
		ProductOptionVo option2 = new ProductOptionVo();
		option2.setOptionName("사이즈");
		option2.setOptionValue("80");
		
		ProductOptionVo option3 = new ProductOptionVo();
		option3.setOptionName("사이즈");
		option3.setOptionValue("90");
		
		dto.setProductOptionVo(Arrays.asList(option1, option2, option3));

		// 상품 옵션에 따른 재고 추가
		ProductQuantityVo quantity1 = new ProductQuantityVo();
		quantity1.setOptionCode("베이지색/80");
		quantity1.setRealQuantity(50L);
		quantity1.setAvailableQuantity(50L);
		
		ProductQuantityVo quantity2 = new ProductQuantityVo();
		quantity2.setOptionCode("베이지색/90");
		quantity2.setRealQuantity(50L);
		quantity2.setAvailableQuantity(50L);
		
		dto.setProductQuantityVo(Arrays.asList(quantity1, quantity2));
		
	    
		ResultActions resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/product/register")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
		
		////////// 상품에 대한 예외 상황 //////////
		
		///// 상품명을 입력하지 않은 경우
		dto = new AdminProductRegisterDto();
		// 상품 정보 추가
		dto.setProductCategoryNo(2L);
		dto.setImage("http://신상 코트의 대표이미지.jpg");
		dto.setPrice(10000L);
		dto.setMileageAdd(100L);
		dto.setDescription("신상 코트 설명");
		dto.setDescriptionDetail("신상 코트 상세 설명");
		dto.setShippingPrice(1000L);
		
		// 상품의 추가 이미지 여러개 추가
		imageVo1 = new ProductImageVo();
		imageVo1.setImageDetail("http://신상 코트의 추가이미지1.jpg");
		imageVo1.setImageTitle("신상 코트 추가 이미지 제목1");
		imageVo1.setImageDescription("신상 코트 추가 이미지 설명1");
		
		imageVo2 = new ProductImageVo();
		imageVo2.setImageDetail("http://신상 코트의 추가이미지2.jpg");
		imageVo2.setImageTitle("신상 코트 추가 이미지 제목2");
		imageVo2.setImageDescription("신상 코트 추가 이미지 설명2");

		dto.setProductImageVo(Arrays.asList(imageVo1, imageVo2));
		
		// 상품 진열정보 추가
		dto.setIsdisplay(false);
		dto.setIssell(false);
		dto.setIsdisplayMain(false);

		
		// 상품 옵션 여러개 추가
		option1 = new ProductOptionVo();
		option1.setOptionName("색상");
		option1.setOptionValue("베이지색");
		
		option2 = new ProductOptionVo();
		option2.setOptionName("사이즈");
		option2.setOptionValue("80");
		
		option3 = new ProductOptionVo();
		option3.setOptionName("사이즈");
		option3.setOptionValue("90");
		
		dto.setProductOptionVo(Arrays.asList(option1, option2, option3));

		// 상품 옵션에 따른 재고 추가
		quantity1 = new ProductQuantityVo();
		quantity1.setOptionCode("베이지색/80");
		quantity1.setRealQuantity(50L);
		quantity1.setAvailableQuantity(50L);
		
		quantity2 = new ProductQuantityVo();
		quantity2.setOptionCode("베이지색/90");
		quantity2.setRealQuantity(50L);
		quantity2.setAvailableQuantity(50L);
		
		dto.setProductQuantityVo(Arrays.asList(quantity1, quantity2));
		
	    
		resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/product/register")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		/////// 상품명이 200자가 넘는 경우
		dto = new AdminProductRegisterDto();
		// 상품 정보 추가
		dto.setTitle("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		dto.setProductCategoryNo(2L);
		dto.setImage("http://신상 코트의 대표이미지.jpg");
		dto.setPrice(10000L);
		dto.setMileageAdd(100L);
		dto.setDescription("신상 코트 설명");
		dto.setDescriptionDetail("신상 코트 상세 설명");
		dto.setShippingPrice(1000L);
		
		// 상품의 추가 이미지 여러개 추가
		imageVo1 = new ProductImageVo();
		imageVo1.setImageDetail("http://신상 코트의 추가이미지1.jpg");
		imageVo1.setImageTitle("신상 코트 추가 이미지 제목1");
		imageVo1.setImageDescription("신상 코트 추가 이미지 설명1");
		
		imageVo2 = new ProductImageVo();
		imageVo2.setImageDetail("http://신상 코트의 추가이미지2.jpg");
		imageVo2.setImageTitle("신상 코트 추가 이미지 제목2");
		imageVo2.setImageDescription("신상 코트 추가 이미지 설명2");

		dto.setProductImageVo(Arrays.asList(imageVo1, imageVo2));
		
		// 상품 진열정보 추가
		dto.setIsdisplay(false);
		dto.setIssell(false);
		dto.setIsdisplayMain(false);

		
		// 상품 옵션 여러개 추가
		option1 = new ProductOptionVo();
		option1.setOptionName("색상");
		option1.setOptionValue("베이지색");
		
		option2 = new ProductOptionVo();
		option2.setOptionName("사이즈");
		option2.setOptionValue("80");
		
		option3 = new ProductOptionVo();
		option3.setOptionName("사이즈");
		option3.setOptionValue("90");
		
		dto.setProductOptionVo(Arrays.asList(option1, option2, option3));

		// 상품 옵션에 따른 재고 추가
		quantity1 = new ProductQuantityVo();
		quantity1.setOptionCode("베이지색/80");
		quantity1.setRealQuantity(50L);
		quantity1.setAvailableQuantity(50L);
		
		quantity2 = new ProductQuantityVo();
		quantity2.setOptionCode("베이지색/90");
		quantity2.setRealQuantity(50L);
		quantity2.setAvailableQuantity(50L);
		
		dto.setProductQuantityVo(Arrays.asList(quantity1, quantity2));
		
	    
		resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/product/register")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		/////// 가격이 음수인 경우
		dto = new AdminProductRegisterDto();
		// 상품 정보 추가
		dto.setTitle("신상 코트");
		dto.setProductCategoryNo(2L);
		dto.setImage("http://신상 코트의 대표이미지.jpg");
		dto.setPrice(-10000L);
		dto.setMileageAdd(100L);
		dto.setDescription("신상 코트 설명");
		dto.setDescriptionDetail("신상 코트 상세 설명");
		dto.setShippingPrice(1000L);
		
		// 상품의 추가 이미지 여러개 추가
		imageVo1 = new ProductImageVo();
		imageVo1.setImageDetail("http://신상 코트의 추가이미지1.jpg");
		imageVo1.setImageTitle("신상 코트 추가 이미지 제목1");
		imageVo1.setImageDescription("신상 코트 추가 이미지 설명1");
		
		imageVo2 = new ProductImageVo();
		imageVo2.setImageDetail("http://신상 코트의 추가이미지2.jpg");
		imageVo2.setImageTitle("신상 코트 추가 이미지 제목2");
		imageVo2.setImageDescription("신상 코트 추가 이미지 설명2");

		dto.setProductImageVo(Arrays.asList(imageVo1, imageVo2));
		
		// 상품 진열정보 추가
		dto.setIsdisplay(false);
		dto.setIssell(false);
		dto.setIsdisplayMain(false);

		
		// 상품 옵션 여러개 추가
		option1 = new ProductOptionVo();
		option1.setOptionName("색상");
		option1.setOptionValue("베이지색");
		
		option2 = new ProductOptionVo();
		option2.setOptionName("사이즈");
		option2.setOptionValue("80");
		
		option3 = new ProductOptionVo();
		option3.setOptionName("사이즈");
		option3.setOptionValue("90");
		
		dto.setProductOptionVo(Arrays.asList(option1, option2, option3));

		// 상품 옵션에 따른 재고 추가
		quantity1 = new ProductQuantityVo();
		quantity1.setOptionCode("베이지색/80");
		quantity1.setRealQuantity(50L);
		quantity1.setAvailableQuantity(50L);
		
		quantity2 = new ProductQuantityVo();
		quantity2.setOptionCode("베이지색/90");
		quantity2.setRealQuantity(50L);
		quantity2.setAvailableQuantity(50L);
		
		dto.setProductQuantityVo(Arrays.asList(quantity1, quantity2));
		
	    
		resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/product/register")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		
		/////// 추가되는 마일리지가 음수인 경우
		dto = new AdminProductRegisterDto();
		// 상품 정보 추가
		dto.setTitle("신상 코트");
		dto.setProductCategoryNo(2L);
		dto.setImage("http://신상 코트의 대표이미지.jpg");
		dto.setPrice(10000L);
		dto.setMileageAdd(-100L);
		dto.setDescription("신상 코트 설명");
		dto.setDescriptionDetail("신상 코트 상세 설명");
		dto.setShippingPrice(1000L);
		
		// 상품의 추가 이미지 여러개 추가
		imageVo1 = new ProductImageVo();
		imageVo1.setImageDetail("http://신상 코트의 추가이미지1.jpg");
		imageVo1.setImageTitle("신상 코트 추가 이미지 제목1");
		imageVo1.setImageDescription("신상 코트 추가 이미지 설명1");
		
		imageVo2 = new ProductImageVo();
		imageVo2.setImageDetail("http://신상 코트의 추가이미지2.jpg");
		imageVo2.setImageTitle("신상 코트 추가 이미지 제목2");
		imageVo2.setImageDescription("신상 코트 추가 이미지 설명2");

		dto.setProductImageVo(Arrays.asList(imageVo1, imageVo2));
		
		// 상품 진열정보 추가
		dto.setIsdisplay(false);
		dto.setIssell(false);
		dto.setIsdisplayMain(false);

		
		// 상품 옵션 여러개 추가
		option1 = new ProductOptionVo();
		option1.setOptionName("색상");
		option1.setOptionValue("베이지색");
		
		option2 = new ProductOptionVo();
		option2.setOptionName("사이즈");
		option2.setOptionValue("80");
		
		option3 = new ProductOptionVo();
		option3.setOptionName("사이즈");
		option3.setOptionValue("90");
		
		dto.setProductOptionVo(Arrays.asList(option1, option2, option3));

		// 상품 옵션에 따른 재고 추가
		quantity1 = new ProductQuantityVo();
		quantity1.setOptionCode("베이지색/80");
		quantity1.setRealQuantity(50L);
		quantity1.setAvailableQuantity(50L);
		
		quantity2 = new ProductQuantityVo();
		quantity2.setOptionCode("베이지색/90");
		quantity2.setRealQuantity(50L);
		quantity2.setAvailableQuantity(50L);
		
		dto.setProductQuantityVo(Arrays.asList(quantity1, quantity2));
		
	    
		resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/product/register")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		////// 상품 요약 설명이 255자가 넘는 경우
		dto = new AdminProductRegisterDto();
		// 상품 정보 추가
		dto.setTitle("신상 코트");
		dto.setProductCategoryNo(2L);
		dto.setImage("http://신상 코트의 대표이미지.jpg");
		dto.setPrice(10000L);
		dto.setMileageAdd(100L);
		dto.setDescription("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		dto.setDescriptionDetail("신상 코트 상세 설명");
		dto.setShippingPrice(1000L);
		
		// 상품의 추가 이미지 여러개 추가
		imageVo1 = new ProductImageVo();
		imageVo1.setImageDetail("http://신상 코트의 추가이미지1.jpg");
		imageVo1.setImageTitle("신상 코트 추가 이미지 제목1");
		imageVo1.setImageDescription("신상 코트 추가 이미지 설명1");
		
		imageVo2 = new ProductImageVo();
		imageVo2.setImageDetail("http://신상 코트의 추가이미지2.jpg");
		imageVo2.setImageTitle("신상 코트 추가 이미지 제목2");
		imageVo2.setImageDescription("신상 코트 추가 이미지 설명2");

		dto.setProductImageVo(Arrays.asList(imageVo1, imageVo2));
		
		// 상품 진열정보 추가
		dto.setIsdisplay(false);
		dto.setIssell(false);
		dto.setIsdisplayMain(false);

		
		// 상품 옵션 여러개 추가
		option1 = new ProductOptionVo();
		option1.setOptionName("색상");
		option1.setOptionValue("베이지색");
		
		option2 = new ProductOptionVo();
		option2.setOptionName("사이즈");
		option2.setOptionValue("80");
		
		option3 = new ProductOptionVo();
		option3.setOptionName("사이즈");
		option3.setOptionValue("90");
		
		dto.setProductOptionVo(Arrays.asList(option1, option2, option3));

		// 상품 옵션에 따른 재고 추가
		quantity1 = new ProductQuantityVo();
		quantity1.setOptionCode("베이지색/80");
		quantity1.setRealQuantity(50L);
		quantity1.setAvailableQuantity(50L);
		
		quantity2 = new ProductQuantityVo();
		quantity2.setOptionCode("베이지색/90");
		quantity2.setRealQuantity(50L);
		quantity2.setAvailableQuantity(50L);
		
		dto.setProductQuantityVo(Arrays.asList(quantity1, quantity2));
		
	    
		resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/product/register")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		////////// 배송 가격이 음수인 경우
		dto = new AdminProductRegisterDto();
		// 상품 정보 추가
		dto.setTitle("신상 코트");
		dto.setProductCategoryNo(2L);
		dto.setImage("http://신상 코트의 대표이미지.jpg");
		dto.setPrice(10000L);
		dto.setMileageAdd(100L);
		dto.setDescription("신상 코트 설명");
		dto.setDescriptionDetail("신상 코트 상세 설명");
		dto.setShippingPrice(-1000L);
		
		// 상품의 추가 이미지 여러개 추가
		imageVo1 = new ProductImageVo();
		imageVo1.setImageDetail("http://신상 코트의 추가이미지1.jpg");
		imageVo1.setImageTitle("신상 코트 추가 이미지 제목1");
		imageVo1.setImageDescription("신상 코트 추가 이미지 설명1");
		
		imageVo2 = new ProductImageVo();
		imageVo2.setImageDetail("http://신상 코트의 추가이미지2.jpg");
		imageVo2.setImageTitle("신상 코트 추가 이미지 제목2");
		imageVo2.setImageDescription("신상 코트 추가 이미지 설명2");

		dto.setProductImageVo(Arrays.asList(imageVo1, imageVo2));
		
		// 상품 진열정보 추가
		dto.setIsdisplay(false);
		dto.setIssell(false);
		dto.setIsdisplayMain(false);

		
		// 상품 옵션 여러개 추가
		option1 = new ProductOptionVo();
		option1.setOptionName("색상");
		option1.setOptionValue("베이지색");
		
		option2 = new ProductOptionVo();
		option2.setOptionName("사이즈");
		option2.setOptionValue("80");
		
		option3 = new ProductOptionVo();
		option3.setOptionName("사이즈");
		option3.setOptionValue("90");
		
		dto.setProductOptionVo(Arrays.asList(option1, option2, option3));

		// 상품 옵션에 따른 재고 추가
		quantity1 = new ProductQuantityVo();
		quantity1.setOptionCode("베이지색/80");
		quantity1.setRealQuantity(50L);
		quantity1.setAvailableQuantity(50L);
		
		quantity2 = new ProductQuantityVo();
		quantity2.setOptionCode("베이지색/90");
		quantity2.setRealQuantity(50L);
		quantity2.setAvailableQuantity(50L);
		
		dto.setProductQuantityVo(Arrays.asList(quantity1, quantity2));
		
	    
		resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/product/register")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		/////////// 상품 이미지 유효성 검사 ///////////////
		
		///// 추가 이미지의 제목이 200자가 넘는 경우
		dto = new AdminProductRegisterDto();
		// 상품 정보 추가
		dto.setTitle("신상 코트");
		dto.setProductCategoryNo(2L);
		dto.setImage("http://신상 코트의 대표이미지.jpg");
		dto.setPrice(10000L);
		dto.setMileageAdd(100L);
		dto.setDescription("신상 코트 설명");
		dto.setDescriptionDetail("신상 코트 상세 설명");
		dto.setShippingPrice(1000L);
		
		// 상품의 추가 이미지 여러개 추가
		imageVo1 = new ProductImageVo();
		imageVo1.setImageDetail("http://신상 코트의 추가이미지1.jpg");
		imageVo1.setImageTitle("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		imageVo1.setImageDescription("신상 코트 추가 이미지 설명1");
		
		imageVo2 = new ProductImageVo();
		imageVo2.setImageDetail("http://신상 코트의 추가이미지2.jpg");
		imageVo2.setImageTitle("신상 코트 추가 이미지 제목2");
		imageVo2.setImageDescription("신상 코트 추가 이미지 설명2");

		dto.setProductImageVo(Arrays.asList(imageVo1, imageVo2));
		
		// 상품 진열정보 추가
		dto.setIsdisplay(false);
		dto.setIssell(false);
		dto.setIsdisplayMain(false);

		
		// 상품 옵션 여러개 추가
		option1 = new ProductOptionVo();
		option1.setOptionName("색상");
		option1.setOptionValue("베이지색");
		
		option2 = new ProductOptionVo();
		option2.setOptionName("사이즈");
		option2.setOptionValue("80");
		
		option3 = new ProductOptionVo();
		option3.setOptionName("사이즈");
		option3.setOptionValue("90");
		
		dto.setProductOptionVo(Arrays.asList(option1, option2, option3));

		// 상품 옵션에 따른 재고 추가
		quantity1 = new ProductQuantityVo();
		quantity1.setOptionCode("베이지색/80");
		quantity1.setRealQuantity(50L);
		quantity1.setAvailableQuantity(50L);
		
		quantity2 = new ProductQuantityVo();
		quantity2.setOptionCode("베이지색/90");
		quantity2.setRealQuantity(50L);
		quantity2.setAvailableQuantity(50L);
		
		dto.setProductQuantityVo(Arrays.asList(quantity1, quantity2));
		
	    
		resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/product/register")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		/////////// 상품 옵션 유효성 검사 ///////////////
		
		///// 상품 옵션의 이름이 255자가 넘는 경우
		dto = new AdminProductRegisterDto();
		// 상품 정보 추가
		dto.setTitle("신상 코트");
		dto.setProductCategoryNo(2L);
		dto.setImage("http://신상 코트의 대표이미지.jpg");
		dto.setPrice(10000L);
		dto.setMileageAdd(100L);
		dto.setDescription("신상 코트 설명");
		dto.setDescriptionDetail("신상 코트 상세 설명");
		dto.setShippingPrice(1000L);
		
		// 상품의 추가 이미지 여러개 추가
		imageVo1 = new ProductImageVo();
		imageVo1.setImageDetail("http://신상 코트의 추가이미지1.jpg");
		imageVo1.setImageTitle("신상 코트 추가 이미지 제목1");
		imageVo1.setImageDescription("신상 코트 추가 이미지 설명1");
		
		imageVo2 = new ProductImageVo();
		imageVo2.setImageDetail("http://신상 코트의 추가이미지2.jpg");
		imageVo2.setImageTitle("신상 코트 추가 이미지 제목2");
		imageVo2.setImageDescription("신상 코트 추가 이미지 설명2");

		dto.setProductImageVo(Arrays.asList(imageVo1, imageVo2));
		
		// 상품 진열정보 추가
		dto.setIsdisplay(false);
		dto.setIssell(false);
		dto.setIsdisplayMain(false);

		
		// 상품 옵션 여러개 추가
		option1 = new ProductOptionVo();
		option1.setOptionName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		option1.setOptionValue("베이지색");
		
		option2 = new ProductOptionVo();
		option2.setOptionName("사이즈");
		option2.setOptionValue("80");
		
		option3 = new ProductOptionVo();
		option3.setOptionName("사이즈");
		option3.setOptionValue("90");
		
		dto.setProductOptionVo(Arrays.asList(option1, option2, option3));

		// 상품 옵션에 따른 재고 추가
		quantity1 = new ProductQuantityVo();
		quantity1.setOptionCode("베이지색/80");
		quantity1.setRealQuantity(50L);
		quantity1.setAvailableQuantity(50L);
		
		quantity2 = new ProductQuantityVo();
		quantity2.setOptionCode("베이지색/90");
		quantity2.setRealQuantity(50L);
		quantity2.setAvailableQuantity(50L);
		
		dto.setProductQuantityVo(Arrays.asList(quantity1, quantity2));
		
	    
		resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/product/register")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		
		///// 상품 옵션의 값이 255자가 넘는 경우
		dto = new AdminProductRegisterDto();
		// 상품 정보 추가
		dto.setTitle("신상 코트");
		dto.setProductCategoryNo(2L);
		dto.setImage("http://신상 코트의 대표이미지.jpg");
		dto.setPrice(10000L);
		dto.setMileageAdd(100L);
		dto.setDescription("신상 코트 설명");
		dto.setDescriptionDetail("신상 코트 상세 설명");
		dto.setShippingPrice(1000L);
		
		// 상품의 추가 이미지 여러개 추가
		imageVo1 = new ProductImageVo();
		imageVo1.setImageDetail("http://신상 코트의 추가이미지1.jpg");
		imageVo1.setImageTitle("신상 코트 추가 이미지 제목1");
		imageVo1.setImageDescription("신상 코트 추가 이미지 설명1");
		
		imageVo2 = new ProductImageVo();
		imageVo2.setImageDetail("http://신상 코트의 추가이미지2.jpg");
		imageVo2.setImageTitle("신상 코트 추가 이미지 제목2");
		imageVo2.setImageDescription("신상 코트 추가 이미지 설명2");

		dto.setProductImageVo(Arrays.asList(imageVo1, imageVo2));
		
		// 상품 진열정보 추가
		dto.setIsdisplay(false);
		dto.setIssell(false);
		dto.setIsdisplayMain(false);

		
		// 상품 옵션 여러개 추가
		option1 = new ProductOptionVo();
		option1.setOptionName("색상");
		option1.setOptionValue("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		
		option2 = new ProductOptionVo();
		option2.setOptionName("사이즈");
		option2.setOptionValue("80");
		
		option3 = new ProductOptionVo();
		option3.setOptionName("사이즈");
		option3.setOptionValue("90");
		
		dto.setProductOptionVo(Arrays.asList(option1, option2, option3));

		// 상품 옵션에 따른 재고 추가
		quantity1 = new ProductQuantityVo();
		quantity1.setOptionCode("베이지색/80");
		quantity1.setRealQuantity(50L);
		quantity1.setAvailableQuantity(50L);
		
		quantity2 = new ProductQuantityVo();
		quantity2.setOptionCode("베이지색/90");
		quantity2.setRealQuantity(50L);
		quantity2.setAvailableQuantity(50L);
		
		dto.setProductQuantityVo(Arrays.asList(quantity1, quantity2));
		
	    
		resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/product/register")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
	}
	
	@Test
	public void Test_4_ProductListPageConnect() throws Exception {
		
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/admin/manage/product/list").contentType(MediaType.APPLICATION_JSON));
		
				resultActions
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.result", is("success")));
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@Test
	public void Test_5_GetProductListWithSearch() throws Exception {
		
		// 정상 동작1 어떠한 검색 조건도 주지 않은 경우
		AdminProductSearchDto search = new AdminProductSearchDto();

		ResultActions resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/product/list")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(search)));
		
				resultActions
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.result", is("success")))
					.andDo(print());
				
		// 정상동작2 상품 명으로 검색
		search = new AdminProductSearchDto();
		search.setProductName("신상");

			resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/product/list")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(search)));
				
				resultActions
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.result", is("success")))
					.andDo(print());
				
		// 정상동작3 카테고리 이름으로 검색
		search = new AdminProductSearchDto();
		search.setCategoryName("코트");

			resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/product/list")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(search)));
						
				resultActions
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.result", is("success")))
					.andDo(print());
				
		//정상동작4 상품 등록일로 검색
		search = new AdminProductSearchDto();
		search.setRegDateStart("2019-01-01");

			resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/product/list")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(search)));
						
				resultActions
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.result", is("success")))
					.andDo(print());
				
		// 정상동작5 상품 마감일로 검색
		search = new AdminProductSearchDto();
		search.setRegDateEnd("2019-01-01");

			resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/product/list")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(search)));
								
				resultActions
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.result", is("success")))
					.andDo(print());
				
		// 정상동작6 상품 진열 여부, 판매 여부, 메인 진열 여부로 검색
		search = new AdminProductSearchDto();
		search.setIsdisplay(true);
		search.setIssell(true);
		search.setIsdisplayMain(true);

		resultActions = 
				mockMvc
				.perform(post("/api/admin/manage/product/list")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(search)));

		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andDo(print());
	}
	
	@Test
	public void Test_6_ProductUpdatePageConnect() throws Exception {
		
		// 정상 동작
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/admin/manage/product/2").contentType(MediaType.APPLICATION_JSON));
		
				resultActions
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.result", is("success")))
					.andDo(print());
				
		// productNo에 숫자가 아닌 값이 들어가는 경우
		resultActions = 
				mockMvc
				.perform(get("/api/admin/manage/product/asdf").contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@Test
	public void Test_7_OneProductEntierInfoUpdateRequest() throws Exception {
		
		//////////// 정상 동작 /////////
		
		AdminProductRegisterDto dto = new AdminProductRegisterDto();
		// 상품 정보 추가
		dto.setProductNo(2L);
		dto.setProductCategoryNo(2L);
		dto.setTitle("신상 코트");
		dto.setImage("http://신상 코트의 대표이미지.jpg");
		dto.setPrice(10000L);
		dto.setMileageAdd(100L);
		dto.setDescription("신상 코트 설명");
		dto.setDescriptionDetail("신상 코트 상세 설명");
		dto.setShippingPrice(1000L);
		
		// 상품의 추가 이미지 여러개 추가
		ProductImageVo imageVo1 = new ProductImageVo();
		imageVo1.setImageDetail("http://신상 코트의 추가이미지1.jpg");
		imageVo1.setImageTitle("신상 코트 추가 이미지 제목1");
		imageVo1.setImageDescription("신상 코트 추가 이미지 설명1");
		
		ProductImageVo imageVo2 = new ProductImageVo();
		imageVo2.setImageDetail("http://신상 코트의 추가이미지2.jpg");
		imageVo2.setImageTitle("신상 코트 추가 이미지 제목2");
		imageVo2.setImageDescription("신상 코트 추가 이미지 설명2");

		dto.setProductImageVo(Arrays.asList(imageVo1, imageVo2));
		
		// 상품 진열정보 추가
		dto.setIsdisplay(false);
		dto.setIssell(false);
		dto.setIsdisplayMain(false);

		
		// 상품 옵션 여러개 추가
		ProductOptionVo option1 = new ProductOptionVo();
		option1.setOptionName("색상");
		option1.setOptionValue("베이지색");
		
		ProductOptionVo option2 = new ProductOptionVo();
		option2.setOptionName("사이즈");
		option2.setOptionValue("80");
		
		ProductOptionVo option3 = new ProductOptionVo();
		option3.setOptionName("사이즈");
		option3.setOptionValue("90");
		
		dto.setProductOptionVo(Arrays.asList(option1, option2, option3));

		// 상품 옵션에 따른 재고 추가
		ProductQuantityVo quantity1 = new ProductQuantityVo();
		quantity1.setOptionCode("베이지색/80");
		quantity1.setRealQuantity(50L);
		quantity1.setAvailableQuantity(50L);
		
		ProductQuantityVo quantity2 = new ProductQuantityVo();
		quantity2.setOptionCode("베이지색/90");
		quantity2.setRealQuantity(50L);
		quantity2.setAvailableQuantity(50L);
		
		dto.setProductQuantityVo(Arrays.asList(quantity1, quantity2));
	    
		ResultActions resultActions = 
				mockMvc
					.perform(put("/api/admin/manage/product")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isOk());
		
		// 관리자 권한으로 요청했는지 검사
	}
	
	@Test
	public void Test_8_SelectedProductsDisplayUpdate() throws Exception {
		
		List<Long> selectedProduct = new ArrayList<Long>();
		selectedProduct.add(1L);
		selectedProduct.add(2L);
		
		String isdisplay = "진열함";
		String issell = "판매안함";
		
		List<Object> params = new ArrayList<Object>();
		params.add(selectedProduct);
		params.add(isdisplay);
		params.add(issell);
		
		ResultActions resultActions = 
				mockMvc
					.perform(put("/api/admin/manage/product/display")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(params)));
		
		resultActions
			.andExpect(status().isOk());
		
		// 상품 번호, 진열 여부, 판매 여부가 비어있지 않은지 확인
		
		// 상품 번호, 진열 여부, 판매 여부에 악의적인 공격이 있을 수 있는 데이터가 들어있는지 확인
		
		// 진열 여부, 판매 여부의 전달되는 값이 true 일 경우 진열과 판매가 가능한지 체크한다
		
		// 관리자 권한으로 요청했는지 검사
	}
	
	@Test
	public void Test_90_DeleteCheckedProducts() throws Exception {

	    List<String> productNo = new ArrayList<String>();
	    productNo.add("2");
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    params.addAll("productNo", productNo);
	    
		ResultActions resultActions = 
				mockMvc
					.perform(delete("/api/admin/manage/product")
							.contentType(MediaType.APPLICATION_JSON)
							.params(params));
		
		resultActions
			.andExpect(status().isOk());
		
		// 넘어오는 상품 번호가 비어있지 않은지 확인
		
		// 넘어오는 상품 번호에 악의적인 공격이 있을만한 특수문자 등의 경우 처리
		
		// 관리자 권한으로 요청했는지 검사
	}
	
	@Test
	public void Test_91_ProductCategoryManagePageConnect() throws Exception {
		
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/admin/manage/product/category").contentType(MediaType.APPLICATION_JSON));
		
				resultActions
					.andExpect(status().isOk());
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@Test
	public void Test_92_ProductCategoryAdd() throws Exception {
		
		CategoryVo category = new CategoryVo();
		category.setCategoryNo(2L);
		//category.setBigClassifyName("코트");
		
		ResultActions resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/product/category")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(category)));
		
				resultActions
					.andExpect(status().isOk());
				
		// 카테고리 객체가 제대로 넘어오는지 확인
				
		// 카테고리 객체의 내용이 비어있지 않은지 확인
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@Test
	public void Test_92_ProductCategoryModify() throws Exception {
		
		CategoryVo category = new CategoryVo();
		category.setCategoryNo(2L);
		//category.setBigClassifyName("하의");
		
		ResultActions resultActions = 
				mockMvc
					.perform(put("/api/admin/manage/product/category")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(category)));
		
				resultActions
					.andExpect(status().isOk());
				
		// 카테고리 객체가 제대로 넘어오는지 확인
				
		// 카테고리 객체의 내용이 비어있지 않은지 확인
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@Test
	public void Test_93_ProductCategoryDelete() throws Exception {
		
		ResultActions resultActions = 
				mockMvc
					.perform(delete("/api/admin/manage/product/category/1")
							.contentType(MediaType.APPLICATION_JSON));
		
				resultActions
					.andExpect(status().isOk());
				
		// 카테고리 번호가 제대로 넘어오는지 확인
				
		// 카테고리 번호가 비어있지 않은지 확인
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
}
