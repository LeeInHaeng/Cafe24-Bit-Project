package com.cafe24.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.cafe24.vo.MemberVo;
import com.cafe24.vo.ProductVo;
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
		
		ProductVo product = new ProductVo();
		product.setProductCategoryNo(1L);
		product.setProductManageNo(1L);
		product.setTitle("베이지색 롱코트");
		product.setImage("http://myhompage/asdf.jpg");
		product.setPrice(50000L);
		product.setMileageAdd(50L);
		product.setDescription("베이지색 롱코드 메인설명");
		product.setDescriptionDetail("베이지색 롱코드 상세설명");
		product.setShippingPrice(1000L);
		
		product.setIsdisplay(false);
		product.setIssell(false);
		
	    List<String> productOptionDetailNo = Arrays.asList("1", "2", "3");
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    params.addAll("productOptionDetailNo", productOptionDetailNo);
		
		ResultActions resultActions = 
				mockMvc
					.perform(post("/api/cart")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(product))
							.param("quantity", "1")
							.params(params));
		
		resultActions
			.andExpect(status().isOk());
	}
}
