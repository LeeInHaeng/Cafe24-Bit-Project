package com.cafe24.controller.api;

import static org.hamcrest.Matchers.is;
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

import com.cafe24.dto.AdminCheckedUserUpdateDto;
import com.cafe24.dto.AdminUserSearchDto;
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
		
		// 정상동작1 - 검색 조건에 아무것도 없는 경우
		AdminUserSearchDto search = new AdminUserSearchDto();
		
		ResultActions resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/user/list")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(search)));
		
				resultActions
					.andExpect(status().isOk())
					.andDo(print());
		
		// 정상동작2 - 복합 옵션을 통한 검색
		search = new AdminUserSearchDto();
		search.setAgeStart(1L);
		search.setAgeEnd(50L);
		search.setBuyPriceStart(10000L);
		search.setBuyPriceEnd(500000L);
		
		resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/user/list")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(search)));
		
				resultActions
					.andExpect(status().isOk())
					.andDo(print());
				
		// 정상동작3
		search = new AdminUserSearchDto();
		search.setIsmail(true);
		search.setIsmessage(true);
				
		resultActions = 
				mockMvc
					.perform(post("/api/admin/manage/user/list")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(search)));
				
				resultActions
					.andExpect(status().isOk())
					.andDo(print());	
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@Test
	public void Test_3_updateCheckedUsers() throws Exception {
		
		// 정상동작
		AdminCheckedUserUpdateDto dto = new AdminCheckedUserUpdateDto();
		dto.setUserid(Arrays.asList("user1"));
		dto.setMileage(100L);
		dto.setStatus("비활성");
	    
		ResultActions resultActions = 
				mockMvc
					.perform(put("/api/admin/manage/user")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(true)));
		
		// 존재하지 않는 사용자에 대한 처리
		dto = new AdminCheckedUserUpdateDto();
		dto.setUserid(Arrays.asList("user99, user100"));
		dto.setMileage(100L);
		dto.setStatus("비활성");
	    
		resultActions = 
				mockMvc
					.perform(put("/api/admin/manage/user")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new Gson().toJson(dto)));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 관리자 계정으로 접속 되어있는지 확인
	}
}
