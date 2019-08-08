package com.cafe24.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.annotation.AuthUser;
import com.cafe24.service.UserManageService;

@Controller
@RequestMapping("/admin/manage/user")
@AuthUser
public class UserManageProvider {

	@Autowired
	private UserManageService userManageService;
	
	@RequestMapping(value= {"", "/main", "/index"}, method=RequestMethod.GET)
	public String main() {
		return "admin/member";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public String list(@RequestBody String param) {
		return userManageService.getMemberListWithSearch(param);
	}
}
