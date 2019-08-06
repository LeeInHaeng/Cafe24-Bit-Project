package com.cafe24.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cafe24.BootApp;
import com.google.gson.Gson;

@Service
public class MainService {

	private static final String URI = BootApp.APIURI;
	private RestTemplate restTemplate = null;
	
	public MainService() {
	    // RestTemplate 에 MessageConverter 세팅
	    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
	    converters.add(new FormHttpMessageConverter());
	    converters.add(new StringHttpMessageConverter());
	 
	    restTemplate = new RestTemplate();
	    restTemplate.setMessageConverters(converters);
	}
	
	public Map<String, Map> getInfoForMainPage(Optional<String> pageNo) {
	    // REST API 호출
		Map<String, Map> result = new HashMap<String, Map>();
		
		String products = null;
		if(pageNo.isPresent())
			products = restTemplate.getForObject(URI+"/product/main/"+pageNo.get(), String.class);
		else
			products = restTemplate.getForObject(URI+"/product/main", String.class);
		
		String categorys = restTemplate.getForObject(URI+"/admin/manage/product/category", String.class);
				
	    result.put("products", new Gson().fromJson(products, Map.class));
	    result.put("categorys", new Gson().fromJson(categorys, Map.class));
	    return result;
	}
}
