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

import com.cafe24.dto.LoginDto;
import com.cafe24.service.MemberService;
import com.cafe24.vo.MemberVo;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void Test_1_MemberTermsPageConnect() throws Exception {
		// 정상 동작
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/member/terms").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk());
	}
	
	@Test
	public void Test_2_MemberJoinPageConnect() throws Exception {
		// 정상 동작
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/member/join").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk());
		
		// terms 프론트에서 사용자가 약관에 동의 하였는지 체크 처리
	}
	
	@Test
	public void Test_3_MemberIdCheck() throws Exception {
		
		// 정상 동작
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/member/check/user100")
					.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk());
		
		// userid 에 비어있는 값이 전달 되는 경우 처리
		resultActions = 
				mockMvc
					.perform(get("/api/member/check/")
					.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// userid에 한글이 입력된 경우 처리
		resultActions = 
				mockMvc
					.perform(get("/api/member/check/가나다")
					.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// userid에 특수문자가 입력된 경우 처리
		resultActions = 
				mockMvc
					.perform(get("/api/member/check/!@#%'")
					.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// userid가 255자를 넘을 경우
		resultActions = 
				mockMvc
					.perform(get("/api/member/check/aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
					.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 입력한 userid가 member 테이블에 중복된 아이디가 있는 경우 처리
		resultActions = 
				mockMvc
					.perform(get("/api/member/check/user1")
					.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is("아이디가 존재합니다.")));
	}
	
	@Test
	public void Test_4_MemberJoinRequest() throws Exception {
		
		memberService.deleteTestData("user2");
		// 정상 동작
		MemberVo memberVo = new MemberVo();
		memberVo = new MemberVo();
		
		memberVo.setId("user2");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름이");
		memberVo.setAddress("유저 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		ResultActions resultActions = 
			mockMvc
			.perform(post("/api/member/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(memberVo)));
		
		resultActions
			.andExpect(status().isOk());
		
		/////////////////// 아이디 관련 테스트 /////////////////////
		
		// 아이디를 입력하지 않은 경우
		memberVo.setId("");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름이");
		memberVo.setAddress("유저 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(memberVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 중복된 아이디를 요청하는 경우
		memberVo.setId("user1");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름이");
		memberVo.setAddress("유저 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(memberVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 아이디에 한글을 입력한 경우
		memberVo.setId("유저1");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름이");
		memberVo.setAddress("유저 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(memberVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 아이디에 특수문자를 입력한 경우
		memberVo.setId("유저'%");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름이");
		memberVo.setAddress("유저 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(memberVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 아이디가 255자가 넘는 경우
		memberVo.setId("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름이");
		memberVo.setAddress("유저 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(memberVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		/////////////////// 비밀번호 관련 테스트 /////////////////////
		
		// 비밀번호를 입력하지 않은 경우
		memberVo.setId("user3");
		memberVo.setPass("");
		memberVo.setName("이름이");
		memberVo.setAddress("유저 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(memberVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 비밀번호가 8자 미만인 경우
		memberVo.setId("user3");
		memberVo.setPass("a!@#123");
		memberVo.setName("이름이");
		memberVo.setAddress("유저 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(memberVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 비밀번호가 20자 이상인 경우
		memberVo.setId("user3");
		memberVo.setPass("a!@#123Aasdasdasdasdasd");
		memberVo.setName("이름이");
		memberVo.setAddress("유저 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(memberVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 비밀번호에 숫자가 포함되지 않는 경우
		memberVo.setId("user3");
		memberVo.setPass("asdasd!@#!@#");
		memberVo.setName("이름이");
		memberVo.setAddress("유저 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(memberVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 비밀번호에 영문이 포함되지 않는 경우
		memberVo.setId("user3");
		memberVo.setPass("123123!@#!@#");
		memberVo.setName("이름이");
		memberVo.setAddress("유저 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(memberVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 비밀번호에 특수문자가 포함되지 않는 경우
		memberVo.setId("user3");
		memberVo.setPass("asd123asd123");
		memberVo.setName("이름이");
		memberVo.setAddress("유저 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(memberVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		/////////////////// 이름 관련 테스트 /////////////////////
		
		// 이름을 입력하지 않은 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("");
		memberVo.setAddress("유저 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(memberVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 한글 이름이 2글자 미만인 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("가");
		memberVo.setAddress("유저 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(memberVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 한글 이름이 4글자 초과인 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("가나다라마");
		memberVo.setAddress("유저 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(memberVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 영문 이름이 2글자 미만인 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("a");
		memberVo.setAddress("유저 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(memberVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 영문 이름이 10글자 초과인 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("asdfasdfasd");
		memberVo.setAddress("유저 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(memberVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 이름에 숫자가 포함된 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름1");
		memberVo.setAddress("유저 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(memberVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		// 이름에 특수문자가 포함된 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름!");
		memberVo.setAddress("유저 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(memberVo)));
		
		resultActions
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.result", is("fail")));
		
		/////////////////// 주소 관련 테스트 /////////////////////
		
		// 주소를 입력하지 않은 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름삼");
		memberVo.setAddress("");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
				mockMvc
				.perform(post("/api/member/join")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(memberVo)));
			
			resultActions
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")));
			
		// 주소의 길이가 255자를 초과하는 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름삼");
		memberVo.setAddress("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
			
		resultActions = 
				mockMvc
				.perform(post("/api/member/join")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(memberVo)));
				
			resultActions
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")));
			
		/////////////////// 전화번호 관련 테스트 /////////////////////
			
		// 전화번호를 입력하지 않은 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름삼");
		memberVo.setAddress("가나다라마바사 주소");
		memberVo.setTel("");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
			
		resultActions = 
				mockMvc
				.perform(post("/api/member/join")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(memberVo)));
				
			resultActions
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")));
				
		// 전화번호 형식이 맞지 않는 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름삼");
		memberVo.setAddress("가나다라마바사 주소");
		memberVo.setTel("012345");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
				
		resultActions = 
				mockMvc
				.perform(post("/api/member/join")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(memberVo)));
					
			resultActions
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")));
		
		// 전화번호에 한글, 영문, 특수문자가 포함된 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름삼");
		memberVo.setAddress("가나다라마바사 주소");
		memberVo.setTel("가나-asd-!@#$");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
					
		resultActions = 
				mockMvc
				.perform(post("/api/member/join")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(memberVo)));
						
			resultActions
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")));
			
		/////////////////// 핸드폰 번호 관련 테스트 /////////////////////
			
		// 핸드폰 번호를 입력하지 않은 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름삼");
		memberVo.setAddress("가나다라마바사 주소");
		memberVo.setTel("02-333-3333");
		memberVo.setPhone("");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
				
		resultActions = 
				mockMvc
				.perform(post("/api/member/join")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(memberVo)));
					
			resultActions
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")));
					
		// 핸드폰 번호 형식이 맞지 않는 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름삼");
		memberVo.setAddress("가나다라마바사 주소");
		memberVo.setTel("02-333-3333");
		memberVo.setPhone("01012345678");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
					
		resultActions = 
				mockMvc
				.perform(post("/api/member/join")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(memberVo)));
						
			resultActions
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")));
			
		// 핸드폰 번호에 한글, 영문, 특수문자가 포함된 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름삼");
		memberVo.setAddress("가나다라마바사 주소");
		memberVo.setTel("02-333-3333");
		memberVo.setPhone("가나다-asdf-!@#$");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user2@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
						
		resultActions = 
				mockMvc
				.perform(post("/api/member/join")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(memberVo)));
							
			resultActions
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")));
			
		/////////////////// 이메일 관련 테스트 /////////////////////
			
		// 이메일을 입력하지 않은 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름삼");
		memberVo.setAddress("가나다라마바사 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
				mockMvc
				.perform(post("/api/member/join")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(memberVo)));
							
			resultActions
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")));
			
		// 이메일 형식이 아닌 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름삼");
		memberVo.setAddress("가나다라마바사 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("asdfasdfasdf");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
			
		resultActions = 
				mockMvc
				.perform(post("/api/member/join")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(memberVo)));
								
			resultActions
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")));
			
		/////////////////// 생일 관련 테스트 /////////////////////
			
		// 생일을 입력하지 않은 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름삼");
		memberVo.setAddress("가나다라마바사 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user3@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
				mockMvc
				.perform(post("/api/member/join")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(memberVo)));
								
			resultActions
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")));
			
		// 생일의 형식이 맞지 않은 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름삼");
		memberVo.setAddress("가나다라마바사 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user3@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("930123");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");

		resultActions = 
				mockMvc
				.perform(post("/api/member/join")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(memberVo)));

		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
		
		// 생일에 한글, 영어, 특수문자가 포함된 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름삼");
		memberVo.setAddress("가나다라마바사 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user3@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("가나다라-ab-!@");
		memberVo.setRefundName("유저은행");
		memberVo.setRefundNumber("222-2222-2222-2222");

		resultActions = 
				mockMvc
				.perform(post("/api/member/join")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(memberVo)));

		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
		
		/////////////////// 환불 계좌 관련 테스트 /////////////////////
		
		// 환불 계좌 은행을 입력하지 않은 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름삼");
		memberVo.setAddress("가나다라마바사 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user3@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
				mockMvc
				.perform(post("/api/member/join")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(memberVo)));
							
			resultActions
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")));
			
		// 환불 계좌 번호를 입력하지 않은 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름삼");
		memberVo.setAddress("가나다라마바사 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user3@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저3 은행");
		memberVo.setRefundNumber("");

		resultActions = 
				mockMvc
				.perform(post("/api/member/join")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(memberVo)));

		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
		
		// 환불 계좌 은행의 길이가 255자를 초과하는 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름삼");
		memberVo.setAddress("가나다라마바사 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user3@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		memberVo.setRefundNumber("222-2222-2222-2222");
		
		resultActions = 
				mockMvc
				.perform(post("/api/member/join")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(memberVo)));
							
			resultActions
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.result", is("fail")));
			
		// 환불 계좌 번호의 길이가 255자를 초과하는 경우
		memberVo.setId("user3");
		memberVo.setPass("user2!@#$%^&*(");
		memberVo.setName("이름삼");
		memberVo.setAddress("가나다라마바사 주소");
		memberVo.setTel("02-222-2222");
		memberVo.setPhone("010-2222-2222");
		memberVo.setIsmessage(true);
		memberVo.setEmail("user3@cafe24.com");
		memberVo.setIsmail(true);
		memberVo.setBirth("2019-07-10");
		memberVo.setRefundName("유저3 은행");
		memberVo.setRefundNumber("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		resultActions = 
				mockMvc
				.perform(post("/api/member/join")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(memberVo)));

		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	@Test
	public void Test_5_LoginPageConnect() throws Exception {
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/member/login").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
			.andExpect(status().isOk());
	}
	
	@Test
	public void Test_6_LoginTry() throws Exception {
		
		// 정상 동작
		LoginDto loginDto = new LoginDto();
		
		loginDto.setId("user1");
		loginDto.setPass("user1!@#$%^&*(");
		
		ResultActions resultActions = 
			mockMvc
			.perform(post("/api/member/login")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(loginDto)));
		
		resultActions
			.andExpect(status().isOk());
		
		/////////////////// 아이디 관련 테스트 /////////////////////
		
		// 아이디에 한글을 입력한 경우
		loginDto.setId("유저1");
		loginDto.setPass("user1!@#$%^&*(");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/login")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(loginDto)));
		
		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
		
		// 아이디에 특수문자를 입력한 경우
		loginDto.setId("user%'");
		loginDto.setPass("user1!@#$%^&*(");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/login")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(loginDto)));
		
		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
		
		// 아이디가 255자가 넘는 경우
		loginDto.setId("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		loginDto.setPass("user1!@#$%^&*(");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/login")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(loginDto)));
		
		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
		
		/////////////////// 비밀번호 관련 테스트 /////////////////////
		
		// 비밀번호를 입력하지 않은 경우
		loginDto.setId("user1");
		loginDto.setPass("");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/login")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(loginDto)));
		
		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
		
		// 비밀번호가 8자 미만인 경우
		loginDto.setId("user1");
		loginDto.setPass("a123!b@");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/login")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(loginDto)));
		
		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
		
		// 비밀번호가 20자 이상인 경우
		loginDto.setId("user1");
		loginDto.setPass("asdasd123!@#asd!@#asd");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/login")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(loginDto)));
		
		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
		
		// 비밀번호에 숫자가 포함되지 않는 경우
		loginDto.setId("user1");
		loginDto.setPass("asd!@#asd!@#");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/login")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(loginDto)));
		
		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
		
		// 비밀번호에 영문이 포함되지 않는 경우
		loginDto.setId("user1");
		loginDto.setPass("123!@#123!@#");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/login")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(loginDto)));
		
		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
		
		// 비밀번호에 특수문자가 포함되지 않는 경우
		loginDto.setId("user1");
		loginDto.setPass("asd123asd123");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/login")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(loginDto)));
		
		resultActions
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")));
		
		// member 테이블에 해당 아이디와 비밀번호가 없는 경우
		loginDto.setId("user100");
		loginDto.setPass("user100!@#$%^&*(");
		
		resultActions = 
			mockMvc
			.perform(post("/api/member/login")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(loginDto)));
		
		resultActions
			.andExpect(status().isOk())
			.andDo(print());
	}
}
