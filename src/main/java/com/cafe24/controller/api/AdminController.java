package com.cafe24.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@ApiOperation(value = "관리자 메인 페이지")
	@RequestMapping(value= {"", "/", "/main", "/index"}, method=RequestMethod.GET)
	public Map<String, Object> index() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
}
