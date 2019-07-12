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
public class AdminControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void Test_1_MainPageConnect() throws Exception {
		ResultActions resultActions = 
				mockMvc
					.perform(get("/api/admin").contentType(MediaType.APPLICATION_JSON));
				resultActions
					.andExpect(status().isOk());
				
				resultActions = 
						mockMvc
							.perform(get("/api/admin/").contentType(MediaType.APPLICATION_JSON));
				resultActions
					.andExpect(status().isOk());
				
				resultActions = 
						mockMvc
							.perform(get("/api/admin/index").contentType(MediaType.APPLICATION_JSON));
				resultActions
					.andExpect(status().isOk());
						
				resultActions = 
						mockMvc
							.perform(get("/api/admin/index/").contentType(MediaType.APPLICATION_JSON));
				resultActions
					.andExpect(status().isOk());
						
				resultActions = 
						mockMvc
							.perform(get("/api/admin/main").contentType(MediaType.APPLICATION_JSON));
				resultActions
					.andExpect(status().isOk());
						
				resultActions = 
						mockMvc
							.perform(get("/api/admin/main/").contentType(MediaType.APPLICATION_JSON));	
				resultActions
					.andExpect(status().isOk());
				
	}
}
