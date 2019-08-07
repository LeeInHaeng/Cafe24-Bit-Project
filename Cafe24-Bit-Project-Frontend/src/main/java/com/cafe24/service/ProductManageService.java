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
public class ProductManageService {
	
	private static final String URI = BootApp.APIURI + "/admin/manage/product";
	private RestTemplate restTemplate = null;
	
	private HttpHeaders headers = null;
	private HttpEntity<String> entity = null;
	
	public ProductManageService() {
	    // RestTemplate 에 MessageConverter 세팅
	    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
	    converters.add(new FormHttpMessageConverter());
	    converters.add(new StringHttpMessageConverter());
	 
	    restTemplate = new RestTemplate();
	    restTemplate.setMessageConverters(converters);
	    
	    headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	}
	
	public Map getInfoForProductRegisterPage() {
	    // REST API 호출
		Map<String, Map> result = new HashMap<String, Map>();
	    String categorys = restTemplate.getForObject(URI+"/category", String.class);
	    result.put("categorys", new Gson().fromJson(categorys, Map.class));
	    return result;
	}

	public String newProductRegist(String param) {
		entity = new HttpEntity<String>(param, headers);
		return restTemplate.postForObject(URI+"/register", entity, String.class);
	}

}
