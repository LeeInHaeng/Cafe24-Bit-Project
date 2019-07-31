package com.cafe24.provider;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/manage/product")
public class ProductManageProvider {

	@RequestMapping(value= {"", "/main", "/index"}, method=RequestMethod.GET)
	public String main() {
		
		return "admin/product";
		
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register() {
		return "admin/product-register";
	}
}
