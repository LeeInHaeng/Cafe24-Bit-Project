package com.cafe24.provider.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.dto.JSONResult;
import com.cafe24.service.FileuploadService;

@Controller
@RequestMapping("/api/admin/manage/product")
public class ProductManageProviderApi {

	@Autowired
	private FileuploadService fileuploadService;
	
	@RequestMapping(value= "/image", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> imageUpload(
			MultipartHttpServletRequest request) {
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(fileuploadService.restore(request)));
		// 관리자 계정으로 접속 되어있는지 확인
	}
}
