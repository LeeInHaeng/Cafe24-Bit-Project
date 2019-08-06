package com.cafe24.provider;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.service.MemberService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/member")
public class MemberProvider {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "client/member/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public String login(
			@RequestBody String param,
			HttpSession session) {
		String result = memberService.loginTryRequest(param);
		Map jsonResult = new Gson().fromJson(result, Map.class);
		
		if("success".equals(jsonResult.get("result"))) {
			try {
				Map authUser = new Gson().fromJson(new Gson().toJson(jsonResult.get("data")), Map.class);
				session.setAttribute("authUser", authUser.get("id"));
				session.setAttribute("role", authUser.get("role"));
			}catch(Exception e) {}
		}

		return result;
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "client/member/join";
	}
	
	@RequestMapping(value="/check/{memberId}", method=RequestMethod.GET)
	@ResponseBody
	public String check(@PathVariable("memberId") String memberId) {
		return memberService.checkIdIsExist(memberId);
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	@ResponseBody
	public String join(@RequestBody String param) {
		return memberService.registNewMember(param);
	}
}
