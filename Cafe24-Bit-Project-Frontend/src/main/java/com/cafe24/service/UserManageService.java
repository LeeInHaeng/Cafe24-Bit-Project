package com.cafe24.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cafe24.BootApp;
import com.google.gson.Gson;

@Service
public class UserManageService {
	
	private static final String URI = BootApp.APIURI + "/admin/manage/user";
	private RestTemplate restTemplate = null;
	
	private HttpHeaders headers = null;
	private HttpEntity<String> entity = null;
	
	public UserManageService() {
	    // RestTemplate 에 MessageConverter 세팅
	    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
	    converters.add(new FormHttpMessageConverter());
	    converters.add(new StringHttpMessageConverter());
	 
	    restTemplate = new RestTemplate();
	    restTemplate.setMessageConverters(converters);
	    
	    headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	}

	public String getMemberListWithSearch(String param) {
		entity = new HttpEntity<String>(param, headers);
		return restTemplate.postForObject(URI+"/list", entity, String.class);
	}

}
