package com.cafe24.controller.api;

import java.util.Optional;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.dto.JSONResult;
import com.cafe24.dto.LoginDto;
import com.cafe24.service.MemberService;
import com.cafe24.vo.MemberVo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@ApiOperation(value = "로그인 페이지")
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> login() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(null));
	}
	
	@ApiOperation(value = "로그인 시도")
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> login(
			@RequestBody @Valid LoginDto loginDto,
			BindingResult br) {
		
		// 객체 Validation에 맞지 않는 경우
		if(br.hasErrors())
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail(br.getAllErrors().get(0).getDefaultMessage()));
		
		MemberVo memberVo = memberService.memberLoginTry(loginDto);
		// member 테이블에 해당 아이디와 비밀번호가 없는 경우 처리
		if(memberVo == null)
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.success("아이디 혹은 비밀번호가 틀렸습니다."));
		
		// 로그인 성공
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(memberVo));
		
		// 중복 로그인이 되어있는 경우 처리
		// 일반 Controller에서 세션으로 검사
	}
	
	@ApiOperation(value = "회원가입 약관 페이지")
	@RequestMapping(value="/terms", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> terms(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(null));
	}
	
	@ApiOperation(value = "아이디 중복여부 체크")
	@RequestMapping(value= {"/check", "/check/{userId}"}, method=RequestMethod.GET)
	public ResponseEntity<JSONResult> checkId(
			@PathVariable(value="userId") Optional<String> userid){
		
		// userid가 비어있는 경우
		if(!userid.isPresent())
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("ID를 입력해 주세요."));
		
		// userid에 특수문자 혹은 한글, 255글자 이상이 입력된 경우 처리
		if(!Pattern.matches("^[a-z0-9_]{1,255}$", userid.get()))
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("ID를 영문과 숫자로 1~255자 사이로 입력해 주세요."));
		
		boolean existUserId = memberService.checkId(userid.get());
		// 입력한 userid가 member 테이블에 중복된 아이디가 있는 경우 처리
		if(existUserId)
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.success("아이디가 존재합니다."));

		// 정상 동작
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(memberService.checkId(userid.get())));
	}
	
	@ApiOperation(value = "회원가입 페이지")
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> join(){
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(null));
		
		// terms 프론트에서 사용자가 약관에 동의 하였는지 체크
	}
	
	@ApiOperation(value = "회원가입 요청")
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> join(
			@RequestBody @Valid MemberVo memberVo,
			BindingResult br){
		
		// 객체 Validation에 맞지 않는 경우
		if(br.hasErrors())
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail(br.getAllErrors().get(0).getDefaultMessage()));
		
		// 중복된 아이디를 요청하는 경우
		boolean existUserId = memberService.checkId(memberVo.getId());
		if(existUserId)
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("아이디가 존재합니다."));
		
		
		boolean queryResult = memberService.joinUser(memberVo);
		// DB 쿼리문 실패
		if(!queryResult)
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("회원가입에 실패했습니다."));
		
		// 정상 동작
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(null));
	}
	
	@ApiOperation(value = "회원 정보 수정 페이지")
	@RequestMapping(value="/change", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> change(){
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(null));

	}
	
	@ApiOperation(value = "회원 정보 수정 요청")
	@RequestMapping(value="/change", method=RequestMethod.PUT)
	public ResponseEntity<JSONResult> change(
			@RequestBody @Valid MemberVo memberVo,
			BindingResult br){
		
		// 객체 Validation에 맞지 않는 경우
		if(br.hasErrors())
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail(br.getAllErrors().get(0).getDefaultMessage()));
		
		// 쿼리로 변경 가능 목록
		// 비밀번호, 이름, 주소, 전화번호, 핸드폰번호, 문자수신여부, 이메일, 메일 수신 여부, 환불 계좌 이름, 환불 계좌 번호
		boolean queryResult = memberService.memberInfoChange(memberVo);
		if(!queryResult)
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.fail("데이터베이스 쿼리 실패"));
		// 정상 동작
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(queryResult));

	}
}
