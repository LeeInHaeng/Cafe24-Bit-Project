package com.cafe24.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.dto.AdminCheckedUserUpdateDto;
import com.cafe24.dto.AdminUserSearchDto;
import com.cafe24.dto.JSONResult;
import com.cafe24.service.UserManageService;
import com.cafe24.vo.MemberVo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/admin/manage/user")
public class UserManageController {
	
	@Autowired
	private UserManageService userManageService;

	@ApiOperation(value = "고객 관리 메인 페이지")
	@RequestMapping(value= "", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> main() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(null));
	}
	
	@ApiOperation(value = "고객 목록 검색")
	@RequestMapping(value= "/list", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> list(
			@RequestBody AdminUserSearchDto searchDto) {

		if(searchDto==null)
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("잘못된 요청 입니다."));
		
		List<MemberVo> members = userManageService.getUserListWithSearch(searchDto);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(members));
		
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@ApiOperation(value = "고객 상태 변경")
	@RequestMapping(value= "", method=RequestMethod.PUT)
	public ResponseEntity<JSONResult> change(
			@RequestBody AdminCheckedUserUpdateDto updateDto) {
		
		boolean queryResult = userManageService.updateCheckedUser(updateDto);
		if(!queryResult)
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.fail("데이터베이스 쿼리 실패"));
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(queryResult));
		
		// 사용자의 아이디와 사용자 상태가 제대로 넘어오는지 확인
		
		// 관리자 계정으로 접속 되어있는지 확인
	}
}
