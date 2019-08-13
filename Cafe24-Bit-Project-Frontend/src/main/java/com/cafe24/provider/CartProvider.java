package com.cafe24.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.service.CartService;

@Controller
public class CartProvider {

	@Autowired
	private CartService cartService;
	
	@RequestMapping(value= "/cart", method=RequestMethod.POST)
	public String addCart(@RequestBody String param) {
		cartService.addCart(param);
		return null;
	}
}
