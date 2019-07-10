package com.cafe24.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.service.UserService;
import com.cafe24.vo.MemberVo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@ApiOperation(value = "로그인 페이지")
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public Map<String, Object> login() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "로그인 시도")
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public Map<String, Object> login(MemberVo memberVo) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		boolean queryResult = userService.memberLoginTry(memberVo);
		
		return result;
		
		// 아이디 비밀번호가 비어있는 경우 처리
		
		// 악의적인 공격이 있을만한 특수문자 등의 경우 처리
		
		// 중복 로그인이 되어있는 경우 처리
		
		// member 테이블에 해당 아이디와 비밀번호가 없는 경우 처리
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
			@RequestParam(value="userid", required=true, defaultValue="") String userid){
		
		return userService.checkId(userid);
		
		// userid 에 비어있는 값이 전달 되는 경우 처리
		
		// 특수문자 혹은 한글이 입력된 경우 처리
		
		// member 테이블에 중복된 아이디가 있는 경우 처리
	}
	
	@ApiOperation(value = "회원가입 페이지")
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public Map<String, Object> join(){

		Map<String, Object> result = new HashMap<String, Object>();
		return result;
		
		// terms 프론트에서 사용자가 약관에 동의 하였는지 체크
	}
	
	@ApiOperation(value = "회원가입 요청")
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public Map<String, Object> join(@ModelAttribute MemberVo memberVo){
		Map<String, Object> result = new HashMap<String, Object>();
		
		boolean queryResult = userService.registerNewMember(memberVo);
		
		return result;
		
		// 필수 입력 항목에 데이터가 들어가 있지 않는 경우 처리
		
		// 각 입력 항목의 양식에 맞지 않은 경우 처리
		
		// 악의적인 공격이 있을만한 특수문자 등의 경우 처리
	}
}
