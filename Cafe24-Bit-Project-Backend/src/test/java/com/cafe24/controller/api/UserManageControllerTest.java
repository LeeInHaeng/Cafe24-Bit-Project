package com.cafe24.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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

import com.cafe24.dto.UserSearch;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserManageControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void Test_1_UserManageMainPageConnect() throws Exception {
		
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/admin/manage/user").contentType(MediaType.APPLICATION_JSON));
		
				resultActions
					.andExpect(status().isOk());

		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@Test
	public void Test_2_GetMemberList() throws Exception {
		
		UserSearch search = new UserSearch();
		search.setAgeStart(10L);
		search.setAgeEnd(50L);
		search.setBuyPriceStart(10000L);
		search.setBuyPriceEnd(500000L);
		
		ResultActions resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/user/list")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(search)));
		
				resultActions
					.andExpect(status().isOk());
				
		// 검색 객체의 유효성 검사
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@Test
	public void Test_3_updateCheckedUsers() throws Exception {
		
	    List<String> userId = new ArrayList<String>();
	    userId.add("user1");
	    userId.add("user2");

	    String status = "비활성";
	    
	    List<Object> params = new ArrayList<Object>();
	    params.add(userId);
	    params.add(status);
	    
		ResultActions resultActions = 
				mockMvc
					.perform(put("/api/admin/manage/user")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(params)));
		
		resultActions
			.andExpect(status().isOk());
		
		// 사용자의 아이디와 사용자 상태가 제대로 넘어오는지 확인
		
		// 관리자 계정으로 접속 되어있는지 확인
	}
}
