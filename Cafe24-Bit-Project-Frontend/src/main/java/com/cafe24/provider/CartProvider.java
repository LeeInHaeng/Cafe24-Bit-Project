package com.cafe24.provider;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.service.CartService;
import com.google.gson.Gson;

@Controller
public class CartProvider {

	@Autowired
	private CartService cartService;
	
	@ResponseBody
	@RequestMapping(value= "/cart", method=RequestMethod.POST)
	public String addCart(
			@RequestBody String param,
			HttpSession session) {
	
		if(session.getAttribute("authUser") != null) {
			Map<String, Object> jsonParam = new Gson().fromJson(param, Map.class);
			jsonParam.put("memberId", session.getAttribute("authUser"));
			return cartService.memberAddCart(new Gson().toJson(jsonParam));
		}
		else
			System.out.println("비회원 카트 추가");

		return null;
	}
}
