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

	public String memberAddCart(String param) {
		try {
			entity = new HttpEntity<String>(param, headers);
			String jsonResult = restTemplate.postForObject(BootApp.APIURI+"/product/getOptionDetailNo", entity, String.class);
			List<Long> productOptionDetailNo = (List<Long>) new Gson().fromJson(jsonResult, Map.class).get("data");
			System.out.println(productOptionDetailNo);
			
			Map<String, Object> jsonParam = new Gson().fromJson(param, Map.class);
			jsonParam.put("productOptionDetailNo", productOptionDetailNo);
			
			entity = new HttpEntity<String>(new Gson().toJson(jsonParam), headers);
			return restTemplate.postForObject(URI, entity, String.class);
		}catch(BadRequest e) {
			return e.getResponseBodyAsString();
		}
	}
	
}
