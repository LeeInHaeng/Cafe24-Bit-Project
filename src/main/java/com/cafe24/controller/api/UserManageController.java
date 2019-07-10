package com.cafe24.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.vo.AdminSearchVo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/admin/manage/user")
public class UserManageController {

	@ApiOperation(value = "고객 관리 메인 페이지")
	@RequestMapping(value= "/main", method=RequestMethod.GET)
	public Map<String, Object> main() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "고객 목록 검색")
	@RequestMapping(value= "/list", method=RequestMethod.POST)
	public Map<String, Object> list(@ModelAttribute AdminSearchVo searchVo) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@ApiOperation(value = "고객 상태 변경")
	@RequestMapping(value= "/change", method=RequestMethod.PUT)
	public Map<String, Object> change(List<String> userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
}
