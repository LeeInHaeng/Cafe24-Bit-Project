package com.cafe24.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.service.ProductManageService;

@Controller
@RequestMapping("/admin/manage/product")
public class ProductManageProvider {

	@Autowired
	private ProductManageService productManageService;
	
	@RequestMapping(value= {"", "/main", "/index"}, method=RequestMethod.GET)
	public String main() {
		
		return "admin/product";
		
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("infos", productManageService.getInfoForProductRegisterPage());
		return "admin/product-register";
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody
	public String register(@RequestBody String param) {
		return productManageService.newProductRegist(param);
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		return "admin/product-list";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public String list(@RequestBody String param) {
		return productManageService.getProductListWithSearch(param);
	}
}
