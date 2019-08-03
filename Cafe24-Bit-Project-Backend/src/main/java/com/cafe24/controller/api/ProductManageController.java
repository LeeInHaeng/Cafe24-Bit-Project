package com.cafe24.controller.api;

import java.util.List;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.dto.AdminCheckedProductsDisplayUpdateDto;
import com.cafe24.dto.AdminProductRegisterDto;
import com.cafe24.dto.AdminProductSearchDto;
import com.cafe24.dto.AdminProductSearchResultDto;
import com.cafe24.dto.JSONResult;
import com.cafe24.service.FileuploadService;
import com.cafe24.service.ProductManageService;
import com.cafe24.validator.ClassInListValidator;
import com.cafe24.vo.CategoryVo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/admin/manage/product")
public class ProductManageController {
	
	@Autowired
	private ProductManageService productManageService;
	
	@Autowired
	ClassInListValidator classInListValidator;
	
	@Autowired
	private FileuploadService fileuploadService;
	
	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}
	
	@ApiOperation(value = "상품 관리 메인 페이지")
	@RequestMapping(value= {"", "/main", "/index"}, method=RequestMethod.GET)
	public ResponseEntity<JSONResult> main() {
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(null));
		
		// 관리자 계정으로 접속 되어있는지 확인
	}

	@ApiOperation(value = "상품 등록 페이지")
	@RequestMapping(value= "/register", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> register() {
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(null));
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@ApiOperation(value = "이미지 업로드")
	@RequestMapping(value= "/image", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> imageUpload(
			@RequestBody MultipartHttpServletRequest request) {
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(fileuploadService.restore(request)));
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@ApiOperation(value = "상품 등록 페이지에서 상품 등록")
	@RequestMapping(value= "/register", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> register(
			@RequestBody @Valid AdminProductRegisterDto adminProductRegisterDto,
			BindingResult br) {
		
		if(adminProductRegisterDto==null)
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("잘못된 요청 입니다."));

		// dto 안의 list 객체들 Validation 체크
		classInListValidator.validate(adminProductRegisterDto.getProductImageVo(), br);
		classInListValidator.validate(adminProductRegisterDto.getProductOptionVo(), br);
		classInListValidator.validate(adminProductRegisterDto.getProductQuantityVo(), br);
		
		// 객체 Validation에 맞지 않는 경우
		if(br.hasErrors())
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail(br.getAllErrors().get(0).getDefaultMessage()));
		
		// 정상 동작
		boolean queryResult = productManageService.registerNewProduct(adminProductRegisterDto);
		if(!queryResult)
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.fail("데이터베이스 쿼리 실패"));
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(queryResult));
		
	}
	
	@ApiOperation(value = "상품 목록 페이지")
	@RequestMapping(value= "/list", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> list() {
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(null));
		
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@ApiOperation(value = "상품 목록 페이지 검색")
	@RequestMapping(value= "/list", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> list(
			@RequestBody AdminProductSearchDto adminProductSearchDto) {

		if(adminProductSearchDto==null)
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("잘못된 요청 입니다."));
		
		List<AdminProductSearchResultDto> products = productManageService.getProductListWithSearch(adminProductSearchDto);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(products));
		// 관리자 계정으로 접속 되어있는지 확인

	}
	
	@ApiOperation(value = "하나의 상품의 전체 정보 업데이트 페이지")
	@RequestMapping(value= "/{productNo}", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> update(
			@PathVariable(value="productNo") String productNo) {

		if(!isNumeric(productNo))
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("잘못된 요청 입니다."));
		
		AdminProductRegisterDto product = productManageService.getOneProductEntierInfo(Long.parseLong(productNo));
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(product));
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@ApiOperation(value = "하나의 상품의 전체 정보 업데이트")
	@RequestMapping(value= "", method=RequestMethod.PUT)
	public ResponseEntity<JSONResult> update(
			@RequestBody @Valid AdminProductRegisterDto adminProductRegisterDto,
			BindingResult br) {
		
		if(adminProductRegisterDto==null)
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("잘못된 요청 입니다."));

		// dto 안의 list 객체들 Validation 체크
		classInListValidator.validate(adminProductRegisterDto.getProductImageVo(), br);
		classInListValidator.validate(adminProductRegisterDto.getProductOptionVo(), br);
		classInListValidator.validate(adminProductRegisterDto.getProductQuantityVo(), br);
		
		// 객체 Validation에 맞지 않는 경우
		if(br.hasErrors())
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail(br.getAllErrors().get(0).getDefaultMessage()));
		
		// 정상 동작
		boolean queryResult = productManageService.updateOneProductEntierInfo(adminProductRegisterDto);
		if(!queryResult)
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.fail("데이터베이스 쿼리 실패"));
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(queryResult));
		
		// 관리자 권한으로 요청했는지 검사
	}
	
	@ApiOperation(value = "여러개의 상품 진열 설정 정보 업데이트")
	@RequestMapping(value= "/display", method=RequestMethod.PUT)
	public ResponseEntity<JSONResult> updateMultiple(
			@RequestBody AdminCheckedProductsDisplayUpdateDto dto) {

		if(dto==null)
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("잘못된 요청 입니다."));
		
		boolean queryResult = productManageService.updateDisplaySelectedProducts(dto);
		if(!queryResult)
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.fail("데이터베이스 쿼리 실패"));
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(queryResult));
		
		// 관리자 권한으로 요청했는지 검사
	}
	
	@ApiOperation(value = "여러개의 상품 삭제")
	@RequestMapping(value= "", method=RequestMethod.DELETE)
	public ResponseEntity<JSONResult> delete(@RequestBody List<Long> productNo) {
		
		if(productNo==null || productNo.size()==0)
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("잘못된 요청 입니다."));
		
		boolean queryResult = productManageService.deleteSelectedProducts(productNo);
		if(!queryResult)
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.fail("데이터베이스 쿼리 실패"));
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(queryResult));
		
		// 관리자 권한으로 요청했는지 검사
	}
	
	@ApiOperation(value = "상품 분류 관리 페이지")
	@RequestMapping(value= "/category", method=RequestMethod.GET)
	public ResponseEntity<JSONResult> category() {
		
		List<CategoryVo> categorys = productManageService.getCategoryList();
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(categorys));
		
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@ApiOperation(value = "상품 분류 관리 페이지에서 대분류 카테고리 추가")
	@RequestMapping(value= "/category/parent", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> categoryAddParent(
			@RequestBody @Valid CategoryVo categoryVo,
			BindingResult br) {
		
		if(categoryVo==null)
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("잘못된 요청 입니다."));
		
		// 객체 Validation에 맞지 않는 경우
		if(br.hasErrors())
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail(br.getAllErrors().get(0).getDefaultMessage()));
		
		boolean queryResult = productManageService.addCategoryParent(categoryVo);
		if(!queryResult)
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.fail("데이터베이스 쿼리 실패"));
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(queryResult));
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@ApiOperation(value = "상품 분류 관리 페이지에서 하위 카테고리 추가")
	@RequestMapping(value= "/category", method=RequestMethod.POST)
	public ResponseEntity<JSONResult> categoryAdd(
			@RequestBody @Valid CategoryVo categoryVo,
			BindingResult br) {
		
		if(categoryVo==null)
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("잘못된 요청 입니다."));
		
		// 객체 Validation에 맞지 않는 경우
		if(br.hasErrors())
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail(br.getAllErrors().get(0).getDefaultMessage()));
		
		boolean queryResult = productManageService.addCategory(categoryVo);
		if(!queryResult)
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.fail("데이터베이스 쿼리 실패"));
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(queryResult));
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@ApiOperation(value = "상품 분류 관리 페이지에서 카테고리 수정")
	@RequestMapping(value= "/category", method=RequestMethod.PUT)
	public ResponseEntity<JSONResult> categoryUpdate(
			@RequestBody @Valid CategoryVo categoryVo,
			BindingResult br) {
		
		if(categoryVo==null)
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("잘못된 요청 입니다."));
		
		// 객체 Validation에 맞지 않는 경우
		if(br.hasErrors())
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail(br.getAllErrors().get(0).getDefaultMessage()));
		
		boolean queryResult = productManageService.updateCategory(categoryVo);
		if(!queryResult)
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.fail("데이터베이스 쿼리 실패"));
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(queryResult));
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
	
	@ApiOperation(value = "상품 분류 관리 페이지에서 카테고리 삭제")
	@RequestMapping(value= "/category/{categoryNo}", method=RequestMethod.DELETE)
	public ResponseEntity<JSONResult> categoryDelete(@PathVariable(value="categoryNo") String categoryNo) {
		
		if(!isNumeric(categoryNo))
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("잘못된 요청 입니다."));
		
		boolean queryResult = productManageService.deleteCategory(categoryNo);
		if(!queryResult)
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.fail("데이터베이스 쿼리 실패"));
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(null));
		
				
		// 관리자 계정으로 접속 되어있는지 확인
	}
}
