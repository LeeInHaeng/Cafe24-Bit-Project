package com.cafe24.provider;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.service.ProductService;

@Controller
public class ProductProvider {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value= {"/detail", "/detail/{productNo}"}, method=RequestMethod.GET)
	public String detail(
			@PathVariable(value="productNo") Optional<String> productNo,
			Model model) {
		model.addAttribute("infos", productService.getInfoForProductDetailPage(productNo));
		return "client/goods/item";
	}
}
