package com.cafe24.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.dto.UserSearch;
import com.cafe24.service.UserManageService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/admin/manage/user")
public class UserManageController {
	
	@Autowired
	private UserManageService userManageService;

	@ApiOperation(value = "고객 관리 메인 페이지")
	@RequestMapping(value= "", method=RequestMethod.GET)
	public Map<String, Object> main() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "고객 목록 검색")
	@RequestMapping(value= "/list", method=RequestMethod.POST)
	public Map<String, Object> list(@ModelAttribute UserSearch searchVo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		userManageService.getUserListWithSearch(searchVo);
		
		return result;
		
		// 검색 객체의 유효성 검사
		
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@ApiOperation(value = "고객 상태 변경")
	@RequestMapping(value= "", method=RequestMethod.PUT)
	public Map<String, Object> change(
			@RequestBody Object updateInfo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		userManageService.updateCheckedUser(updateInfo);
		
		return result;
		
		// 사용자의 아이디와 사용자 상태가 제대로 넘어오는지 확인
		
		// 관리자 계정으로 접속 되어있는지 확인
	}
}
