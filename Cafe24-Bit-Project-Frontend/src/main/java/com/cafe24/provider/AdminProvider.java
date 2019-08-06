package com.cafe24.provider;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.annotation.AuthAdmin;

@Controller
@RequestMapping("/admin")
public class AdminProvider {

	@RequestMapping(value= {"", "/", "/main", "/index"}, method=RequestMethod.GET)
	@AuthAdmin
	public String main() {
		return "admin/index";
		// 관리자 계정으로 접속 되어있는지 확인
	}
}
