package com.cafe24.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.RestTemplate;

import com.cafe24.BootApp;

@Service
public class MemberService {
	
	private static final String URI = BootApp.APIURI + "/member";
	private RestTemplate restTemplate = null;
	
	private HttpHeaders headers = null;
	private HttpEntity<String> entity = null;
	
	public MemberService() {
	    // RestTemplate 에 MessageConverter 세팅
	    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
	    converters.add(new FormHttpMessageConverter());
	    converters.add(new StringHttpMessageConverter());
	 
	    restTemplate = new RestTemplate();
	    restTemplate.setMessageConverters(converters);
	    
	    headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	}

	public String loginTryRequest(String param) {
		try {
			entity = new HttpEntity<String>(param, headers);
			return restTemplate.postForObject(URI+"/login", entity, String.class);
		}catch(BadRequest e) {
			return e.getResponseBodyAsString();
		}
	}

	public String checkIdIsExist(String memberId) {
		try {
			return restTemplate.getForObject(URI+"/check/"+memberId, String.class);
		}catch(BadRequest e) {
			return e.getResponseBodyAsString();
		}
	}

	public String registNewMember(String param) {
		try {
			entity = new HttpEntity<String>(param, headers);
			return restTemplate.postForObject(URI+"/join", entity, String.class);
		}catch(BadRequest e) {
			return e.getResponseBodyAsString();
		}
	}

}
