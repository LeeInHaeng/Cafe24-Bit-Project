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

import com.cafe24.vo.MemberVo;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void Test_1_MemberTermsPageConnect() throws Exception {
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/user/terms").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk());
	}
	
	@Test
	public void Test_2_MemberJoinPageConnect() throws Exception {
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/user/join").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk());
		
		// terms 프론트에서 사용자가 약관에 동의 하였는지 체크 처리
	}
	
	@Test
	public void Test_3_MemberIdCheck() throws Exception {
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/user/check/user1")
					.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.result", is("success or fail")));
		
		// userid 에 비어있는 값이 전달 되는 경우 처리
		
		// userid에 특수문자 혹은 한글이 입력된 경우 처리
		
		// 입력한 userid가 member 테이블에 중복된 아이디가 있는 경우 처리
	}
	
	@Test
	public void Test_4_MemberJoinRequest() throws Exception {
		MemberVo memberVo = new MemberVo();
		
		memberVo.setId("user2");
		memberVo.setPass("user2");
		memberVo.setName("유저 이름");
		memberVo.setAddress("유저 주소");
		memberVo.setTel("02-123-4567");
		memberVo.setPhone("010-1234-5678");
		memberVo.setEmail("aaa@aaa.com");
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("123-1234-1234-1234");
		
		ResultActions resultActions = 
			mockMvc
			.perform(post("/api/user/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(memberVo)));
		
		resultActions
			.andExpect(status().isOk())
			.andDo(print());
		
		// 필수 입력 항목에 데이터가 들어가 있지 않는 경우 처리
		
		// 각 입력 항목의 양식에 맞지 않은 경우 처리
		
		// 악의적인 공격이 있을만한 특수문자 등의 경우 처리
		
	}
	
	@Test
	public void Test_5_LoginPageConnect() throws Exception {
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/user/login").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk());
	}
	
	@Test
	public void Test_6_LoginTry() throws Exception {
		
		MemberVo memberVo = new MemberVo();
		
		memberVo.setId("user1");
		memberVo.setPass("user1");
		
		ResultActions resultActions = 
			mockMvc
			.perform(post("/api/user/login")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(memberVo)));
		
		resultActions
			.andExpect(status().isOk())
			.andDo(print());
		
		// 아이디 비밀번호가 비어있는 경우 처리
		
		// 아이디와 비밀번호에 악의적인 공격이 있을만한 특수문자 등의 경우 처리
		
		// 중복 로그인이 되어있는 경우 처리
		
		// member 테이블에 해당 아이디와 비밀번호가 없는 경우 처리
	}
}
