package com.cafe24.service;

import java.util.ArrayList;
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
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.cafe24.BootApp;
import com.google.gson.Gson;

@Service
public class CartService {

	private static final String URI = BootApp.APIURI + "/cart";
	private RestTemplate restTemplate = null;
	
	private HttpHeaders headers = null;
	private HttpEntity<String> entity = null;
	
	public CartService() {
	    // RestTemplate 에 MessageConverter 세팅
	    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
	    converters.add(new FormHttpMessageConverter());
	    converters.add(new StringHttpMessageConverter());
	 
	    restTemplate = new RestTemplate();
	    restTemplate.setMessageConverters(converters);
	    
	    headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	}

	public String addCart(String param) {
		try {
			Map cartMap = new Gson().fromJson(param, Map.class);
			System.out.println(cartMap);
			
			//entity = new HttpEntity<String>(param, headers);
			//return restTemplate.postForObject(URI+"/register", entity, String.class);
			return null;
		}catch(BadRequest e) {
			return e.getResponseBodyAsString();
		}
	}
	
}
