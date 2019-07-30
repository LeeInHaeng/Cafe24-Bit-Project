package com.cafe24.provider;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/manage/user")
public class UserManageProvider {

	@RequestMapping(value= {"", "/main", "/index"}, method=RequestMethod.GET)
	public String main() {
		return "admin/member";
	}
}
