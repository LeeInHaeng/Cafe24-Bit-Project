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
import com.cafe24.dto.ProductOptionDetailNoConvertDto;
import com.google.gson.Gson;

@Service
public class OrderService {

	private static final String URI = BootApp.APIURI + "/order";
	private RestTemplate restTemplate = null;
	
	private HttpHeaders headers = null;
	private HttpEntity<String> entity = null;
	
	public OrderService() {
	    // RestTemplate 에 MessageConverter 세팅
	    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
	    converters.add(new FormHttpMessageConverter());
	    converters.add(new StringHttpMessageConverter());
	 
	    restTemplate = new RestTemplate();
	    restTemplate.setMessageConverters(converters);
	    
	    headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	}

	public Map<String, Map> getOrderPagelInfo(String memberId, String nonmemberId, String products) {
		
		Map<String, Map> result = new HashMap<String, Map>();
		
		String categorys = restTemplate.getForObject(BootApp.APIURI+"/admin/manage/product/category", String.class);
		result.put("categorys", new Gson().fromJson(categorys, Map.class));

		Map jsonParam = new Gson().fromJson(products, Map.class);
		List<Map> productOptionDto = (List<Map>) jsonParam.get("productOptionDto");
		
		List<Map> resProductOptionDto = new ArrayList<Map>();
		
		for(Map dto : productOptionDto) {
			ProductOptionDetailNoConvertDto convertDto = new ProductOptionDetailNoConvertDto();
			convertDto.setProductNo((new Double((double)dto.get("productNo"))).longValue());
			convertDto.setOptionCode((String)dto.get("optionCode"));
			entity = new HttpEntity<String>(new Gson().toJson(convertDto), headers);
			String jsonResult = restTemplate.postForObject(BootApp.APIURI+"/product/getOptionDetailNo", entity, String.class);
			List<Long> productOptionDetailNo = (List<Long>) new Gson().fromJson(jsonResult, Map.class).get("data");
			dto.put("productOptionDetailNo", productOptionDetailNo);
			resProductOptionDto.add(dto);
		}
		
		if(memberId != null)
			jsonParam.put("memberId", memberId);
		else
			jsonParam.put("nonmemberMac", nonmemberId);
		
		jsonParam.put("productOptionDto", resProductOptionDto);
		
		entity = new HttpEntity<String>(new Gson().toJson(jsonParam), headers);
		String apiProducts = restTemplate.postForObject(URI, entity, String.class);
		result.put("products", new Gson().fromJson(apiProducts, Map.class));
		
		return result;
	}
}
