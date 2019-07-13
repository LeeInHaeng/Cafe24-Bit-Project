package com.cafe24.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
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

import com.cafe24.dto.ProductSearch;
import com.cafe24.vo.CategoryVo;
import com.cafe24.vo.ProductImageVo;
import com.cafe24.vo.ProductOptionVo;
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
							.andExpect(status().isOk());
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@Test
	public void Test_2_ProductRegisterPageConnect() throws Exception {
		
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/admin/manage/product/register").contentType(MediaType.APPLICATION_JSON));
		
				resultActions
					.andExpect(status().isOk());
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@Test
	public void Test_3_NewProductRegistRequest() throws Exception {
		
		ProductVo productVo = new ProductVo();
		productVo.setProductNo(1L);
		productVo.setProductCategoryNo(1L);
		productVo.setProductManageNo(1L);
		productVo.setTitle("갈색 원피스");
		productVo.setImage("http://대표이미지.jpg");
		productVo.setPrice(10000L);
		productVo.setMileageAdd(100L);
		productVo.setDescription("갈색 원피스 설명");
		productVo.setDescriptionDetail("갈색 원피스 상세 설명");
		productVo.setShippingPrice(1000L);
		
		productVo.setIsdisplay(false);
		productVo.setIssell(false);
		
		ProductImageVo imageVo = new ProductImageVo();
		imageVo.setImageNo(1L);
		imageVo.setImage("http://추가이미지.jpg");
		imageVo.setImageTitle("추가 이미지 제목1");
		imageVo.setImageDescription("추가 이미지 설명1");
		
		CategoryVo category = new CategoryVo();
		category.setCategoryNo(1L);
		category.setBigClassifyName("상의");
		category.setMidClassifyName("원피스");
		
		ProductOptionVo option = new ProductOptionVo();
		option.setOptionNo(1L);
		option.setOptionName("색상");
		
		option.setOptionDetailNo(1L);
		option.setOptionValue("갈색");
		option.setAvailableQuantity(50L);
		option.setAvailableQuantity(50L);
		
		List<Object> params = new ArrayList<Object>();
		params.add(productVo);
		params.add(imageVo);
		params.add(category);
		params.add(option);
	    
		ResultActions resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/product/register")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(params)));
		
		resultActions
			.andExpect(status().isOk());
		
		// POST로 넘어오는 모든 객체에 대해 유효성 검사
		
		// POST로 넘어오는 객체 안에서 필수 요소들이 모두 입력되어 있는지 검사
		
		// 진열 여부, 판매 여부의 전달되는 값이 true 일 경우 진열과 판매가 가능한지 체크한다
		
		// 관리자 권한으로 요청했는지 검사
	}
	
	@Test
	public void Test_4_ProductListPageConnect() throws Exception {
		
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/admin/manage/product/list").contentType(MediaType.APPLICATION_JSON));
		
				resultActions
					.andExpect(status().isOk());
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@Test
	public void Test_5_GetProductListWithSearch() throws Exception {
		
		ProductSearch search = new ProductSearch();
		search.setCategoryName("원피스");
		search.setRegDateStart("2019-07-13");
		
		ResultActions resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/product/list")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(search)));
		
				resultActions
					.andExpect(status().isOk());
				
		// 관리자 계정으로 접속 되어있는지 확인
				
		// 모든 입력값에 대해 악의적인 공격이 있을 수 있는 데이터가 들어있는지 확인
	}
	
	@Test
	public void Test_6_ProductUpdatePageConnect() throws Exception {
		
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/admin/manage/product/1").contentType(MediaType.APPLICATION_JSON));
		
				resultActions
					.andExpect(status().isOk());
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@Test
	public void Test_7_OneProductEntierInfoUpdateRequest() throws Exception {
		
		ProductVo productVo = new ProductVo();
		productVo.setProductNo(1L);
		productVo.setProductCategoryNo(1L);
		productVo.setProductManageNo(1L);
		productVo.setTitle("빨간색 원피스");
		productVo.setImage("http://빨간색 원피스 대표이미지.jpg");
//		productVo.setPrice(10000L);
//		productVo.setMileageAdd(100L);
		productVo.setDescription("빨간색 원피스 설명");
		productVo.setDescriptionDetail("빨간색 원피스 상세 설명");
//		productVo.setShippingPrice(1000L);
		
//		productVo.setIsdisplay(false);
//		productVo.setIssell(false);
		
		ProductImageVo imageVo = new ProductImageVo();
		imageVo.setImageNo(2L);
		imageVo.setImage("http://빨간색 원피스 추가이미지.jpg");
		imageVo.setImageTitle("추가 이미지 제목2");
		imageVo.setImageDescription("추가 이미지 설명2");
		
//		CategoryVo category = new CategoryVo();
//		category.setCategoryNo(1L);
//		category.setBigClassifyName("상의");
//		category.setMidClassifyName("원피스");
		
		ProductOptionVo option = new ProductOptionVo();
		option.setOptionNo(1L);
		option.setOptionName("색상");
		
		option.setOptionDetailNo(2L);
		option.setOptionValue("빨간색");
//		option.setAvailableQuantity(50L);
//		option.setAvailableQuantity(50L);
		
		List<Object> params = new ArrayList<Object>();
		params.add(productVo);
		params.add(imageVo);
		params.add(option);
	    
		ResultActions resultActions = 
				mockMvc
					.perform(put("/api/admin/manage/product/1")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(params)));
		
		resultActions
			.andExpect(status().isOk());
		
		// 파라미터로 넘어오는 모든 객체에 대해 유효성 검사
		
		// 파라미터로 넘어오는 객체 안에서 필수 요소들이 모두 입력되어 있는지 검사
		
		// 진열 여부, 판매 여부의 전달되는 값이 true 일 경우 진열과 판매가 가능한지 체크한다
		
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
		category.setBigClassifyName("코트");
		
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
		category.setBigClassifyName("하의");
		
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
