package com.cafe24.provider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderProvider {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String orderPageConnect(
			Model model,
			HttpSession session,
			@CookieValue(value="nonmemberId", required=false) Cookie nonmemberCookie,
			@RequestParam("products") String products) {
		
		if(session.getAttribute("authUser") != null) {
			model.addAttribute("infos", orderService.getOrderPagelInfo((String) session.getAttribute("authUser"), null, products));
			model.addAttribute("member", (String) session.getAttribute("authUser"));
		}else {
			model.addAttribute("infos", orderService.getOrderPagelInfo(null, nonmemberCookie.getValue(), products));
			model.addAttribute("nonmember", nonmemberCookie.getValue());
		}

		return "client/order/order";
	}
	
}
