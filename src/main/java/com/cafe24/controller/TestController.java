package com.cafe24.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.service.TestService;
import com.cafe24.vo.TestVo;

@Controller
public class TestController {

	@Autowired
	private TestService testService;
	
	@ResponseBody
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public List<TestVo> test(){
		return testService.getData();	
	}

}
