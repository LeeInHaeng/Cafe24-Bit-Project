package com.cafe24.controller.api;

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

import com.cafe24.dto.AdminCheckedProductsDisplayUpdateDto;
import com.cafe24.dto.AdminProductRegisterDto;
import com.cafe24.dto.AdminProductSearchDto;
import com.cafe24.vo.CategoryVo;
import com.cafe24.vo.ProductImageVo;
import com.cafe24.vo.ProductOptionVo;
import com.cafe24.vo.ProductQuantityVo;
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
		dto.setTitle("업데이트된 신상 코트");
		dto.setImage("http://업데이트된 신상 코트의 대표이미지.jpg");
		dto.setPrice(10000L);
		dto.setMileageAdd(100L);
		dto.setDescription("업데이트된 신상 코트 설명");
		dto.setDescriptionDetail("업데이트된 신상 코트 상세 설명");
		dto.setShippingPrice(1000L);
		dto.setEndDate("2222-02-22");
		
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
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
		
		// 유효성 검사는 상품 등록과 동일하므로 생략
		
		// 관리자 권한으로 요청했는지 검사
	}
	
	@Test
	public void Test_8_SelectedProductsDisplayUpdate() throws Exception {
		
		// 정상 동작
		AdminCheckedProductsDisplayUpdateDto dto = new AdminCheckedProductsDisplayUpdateDto();
		dto.setProductNo(Arrays.asList(3L, 4L));
		dto.setIsdisplay(false);
		dto.setIssell(false);
		dto.setIsdisplayMain(false);
		
		ResultActions resultActions = 
				mockMvc
					.perform(put("/api/admin/manage/product/display")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
		
		// 관리자 권한으로 요청했는지 검사
	}
	
	@Test
	public void Test_90_DeleteCheckedProducts() throws Exception {

		// 정상 동작
		ResultActions resultActions = 
				mockMvc
					.perform(delete("/api/admin/manage/product")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(Arrays.asList(1L))));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
		
		// 관리자 권한으로 요청했는지 검사
	}
	
	@Test
	public void Test_91_ProductCategoryManagePageConnect() throws Exception {
		
		// 정상 동작
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/admin/manage/product/category").contentType(MediaType.APPLICATION_JSON));
		
				resultActions
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.result", is("success")))
					.andDo(print());
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@Test
	public void Test_92_ProductCategoryAddParent() throws Exception {
		
		// 정상 동작
		CategoryVo category = new CategoryVo();
		category.setCategoryName("새로운 부모 카테고리");

		
		ResultActions resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/product/category/parent")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(category)));
		
				resultActions
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.result", is("success")))
					.andExpect(jsonPath("$.data", is(true)));
				
		// 카테고리 명을 넣지 않은 경우
		category = new CategoryVo();

		resultActions = 
				mockMvc
				.perform(post("/api/admin/manage/product/category/parent")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(category)));

		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
		
		// 카테고리명이 255자가 넘는 경우
		category = new CategoryVo();
		category.setCategoryName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		resultActions = 
				mockMvc
				.perform(post("/api/admin/manage/product/category/parent")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(category)));

		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@Test
	public void Test_93_ProductCategoryAdd() throws Exception {
		
		// 정상 동작
		CategoryVo category = new CategoryVo();
		category.setCategoryNo(1L);
		category.setCategoryName("자켓");
		
		ResultActions resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/product/category")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(category)));
		
				resultActions
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.result", is("success")))
					.andExpect(jsonPath("$.data", is(true)));
				
		// 카테고리 번호가 없는 경우
		category = new CategoryVo();
		category.setCategoryName("자켓");

		resultActions = 
				mockMvc
				.perform(post("/api/admin/manage/product/category")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(category)));

		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
		
		// 카테고리 테이블에 존재하지 않는 카테고리인 경우
		category = new CategoryVo();
		category.setCategoryNo(9999L);
		category.setCategoryName("자켓");

		resultActions = 
				mockMvc
				.perform(post("/api/admin/manage/product/category")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(category)));

		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@Test
	public void Test_94_ProductCategoryModify() throws Exception {
		
		// 정상 동작
		CategoryVo category = new CategoryVo();
		category.setCategoryNo(3L);
		category.setCategoryName("수정된 카테고리 이름");
		
		ResultActions resultActions = 
				mockMvc
					.perform(put("/api/admin/manage/product/category")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(category)));
		
				resultActions
					.andExpect(status().isOk());
				
		// 유효성 검사는 위와 동일하므로 생략
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@Test
	public void Test_95_ProductCategoryDelete() throws Exception {
		
		// 정상 동작
		ResultActions resultActions = 
				mockMvc
					.perform(delete("/api/admin/manage/product/category/1")
							.contentType(MediaType.APPLICATION_JSON));
		
				resultActions
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.result", is("success")));
				
		// 삭제하고자 하는 카테고리의 번호가 숫자가 아닌 경우
		resultActions = 
				mockMvc
				.perform(delete("/api/admin/manage/product/category/asd")
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
		
		// 삭제하고자 하는 카테고리의 번호가 없는 경우
		resultActions = 
				mockMvc
				.perform(delete("/api/admin/manage/product/category/9999")
						.contentType(MediaType.APPLICATION_JSON));

		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));

		// 관리자 계정으로 접속 되어있는지 확인
	}
}
