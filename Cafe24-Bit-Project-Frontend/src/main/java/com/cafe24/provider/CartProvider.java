package com.cafe24.provider;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
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
			HttpSession session,
			HttpServletResponse response,
			HttpServletRequest request,
			@CookieValue(value="nonmemberId", required=false) Cookie nonmemberCookie) {
	
		Map<String, Object> jsonParam = new Gson().fromJson(param, Map.class);
		
		if(session.getAttribute("authUser") != null) {
			jsonParam.put("memberId", session.getAttribute("authUser"));
		}else {
			if(nonmemberCookie == null) {
				String uuid = UUID.randomUUID().toString().replace("-", "");
				Cookie nonmember = new Cookie("nonmemberId", uuid);
				nonmember.setMaxAge(60*60*24*30);
				response.addCookie(nonmember);

				jsonParam.put("nonmemberMac", uuid);
			}else {
				jsonParam.put("nonmemberMac", nonmemberCookie.getValue());
			}
		}
		
		return cartService.addCart(new Gson().toJson(jsonParam));
	}
}
