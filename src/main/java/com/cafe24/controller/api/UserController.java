package com.cafe24.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.vo.MemberVo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@ApiOperation(value = "로그인 페이지")
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public Map<String, Object> login() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "로그인 시도")
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public Map<String, Object> login(
			@RequestParam(value="userid", required=true, defaultValue="") String userid,
			@RequestParam(value="userpass", required=true, defaultValue="") String userpass) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "회원가입 약관 페이지")
	@RequestMapping(value="/terms", method=RequestMethod.GET)
	public Map<String, Object> terms(){
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "아이디 중복여부 체크")
	@RequestMapping(value="/checkId", method=RequestMethod.GET)
	public Map<String, Object> checkId(
			@PathVariable(value="userid") String userid){
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("result", "success or fail");
		result.put("message", "이미 존재하는 아이디 입니다.");
		
		return result;
	}
	
	@ApiOperation(value = "회원가입 페이지")
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public Map<String, Object> join(){
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "회원가입 요청")
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public Map<String, Object> join(@ModelAttribute MemberVo memberVo){
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
}
