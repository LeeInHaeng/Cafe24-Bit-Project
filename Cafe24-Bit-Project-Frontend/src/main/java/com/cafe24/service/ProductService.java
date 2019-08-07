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
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.cafe24.BootApp;
import com.google.gson.Gson;

@Service
public class ProductService {

	private static final String URI = BootApp.APIURI + "/product";
	private RestTemplate restTemplate = null;
	
	public ProductService() {
	    // RestTemplate 에 MessageConverter 세팅
	    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
	    converters.add(new FormHttpMessageConverter());
	    converters.add(new StringHttpMessageConverter());
	 
	    restTemplate = new RestTemplate();
	    restTemplate.setMessageConverters(converters);
	}
	
	public Map<String, Map> getInfoForProductDetailPage(Optional<String> productNo) {
	    // REST API 호출
		Map<String, Map> result = new HashMap<String, Map>();
		
		String product = null;
		
		try {
			if(productNo.isPresent())
				product = restTemplate.getForObject(URI+"/detail/"+productNo.get(), String.class);
			else
				product = restTemplate.getForObject(URI+"/detail", String.class);
			result.put("product", new Gson().fromJson(product, Map.class));
		}catch(BadRequest e) {
			result.put("product", new Gson().fromJson(e.getResponseBodyAsString(), Map.class));
		}
		
		String categorys = restTemplate.getForObject(BootApp.APIURI+"/admin/manage/product/category", String.class);
	    result.put("categorys", new Gson().fromJson(categorys, Map.class));
	    return result;
	}
}
