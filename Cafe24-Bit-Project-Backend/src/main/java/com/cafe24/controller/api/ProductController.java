package com.cafe24.controller.api;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.dto.JSONResult;
import com.cafe24.dto.ProductDetailDto;
import com.cafe24.service.ProductService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}
	
	@ApiOperation(value = "상품 리스트 페이지")
	@RequestMapping(value= {"", "/{categoryName}", "/{categoryName}/{pageNo}"},
		method=RequestMethod.GET)
	public ResponseEntity<JSONResult> list(
			@PathVariable(value="categoryName") Optional<String> categoryName,
			@PathVariable(value="pageNo") Optional<String> pageNo) {

		// 페이지와 카테고리를 둘 다 적지 않은 경우
		if(!categoryName.isPresent() && !pageNo.isPresent())
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("메인 페이지로 이동"));
		
		// 페이지를 적지 않은 경우 1페이지로 간주
		// 페이지 번호가 숫자가 아닌 경우 1페이지로 간주
		// 페이지가 0보다 작은 경우 1페이지로 간주
		// 페이지 번호가 최대 페이지 번호를 넘어간 경우 마지막 페이지로 간주 (service)
		if(!pageNo.isPresent() || !isNumeric(pageNo.get()) || Integer.parseInt(pageNo.get()) < 1) {
			Map<String, Object> productsWithPageInfo = productService.getProductList(categoryName.get(), 1L);
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.success(productsWithPageInfo));
		}
		
		// 사용자가 요청한 카테고리가 데이터베이스에 없는 경우 처리 (service)
		
		// 정상 동작
		Map<String, Object> productsWithPageInfo = productService.getProductList(categoryName.get(), Long.parseLong(pageNo.get()));
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(productsWithPageInfo));
	}
	
	@ApiOperation(value = "메인 페이지의 상품 리스트")
	@RequestMapping(value= {"/main", "/main/{pageNo}"},
		method=RequestMethod.GET)
	public ResponseEntity<JSONResult> list(
			@PathVariable(value="pageNo") Optional<String> pageNo) {
		
		// 페이지를 적지 않은 경우 1페이지로 간주
		// 페이지 번호가 숫자가 아닌 경우 1페이지로 간주
		// 페이지가 0보다 작은 경우 1페이지로 간주
		// 페이지 번호가 최대 페이지 번호를 넘어간 경우 마지막 페이지로 간주 (service)
		if(!pageNo.isPresent() || !isNumeric(pageNo.get()) || Integer.parseInt(pageNo.get()) < 1) {
			Map<String, Object> productsWithPageInfo = productService.getMainPageProductList(1L);
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.success(productsWithPageInfo));
		}
		
		// 정상 동작
		Map<String, Object> productsWithPageInfo = productService.getMainPageProductList(Long.parseLong(pageNo.get()));
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(productsWithPageInfo));
	}
	
	@ApiOperation(value = "상품 리스트 검색 페이지")
	@RequestMapping(value= {"/search", "/search/{keyword}", "/search/{keyword}/{pageNo}"}, method=RequestMethod.GET)
	public ResponseEntity<JSONResult> searchList(
			@PathVariable(value="keyword") Optional<String> keyword,
			@PathVariable(value="pageNo") Optional<String> pageNo) {
		
		// 페이지와 검색 키워드를 둘 다 적지 않은 경우
		if(!keyword.isPresent() && !pageNo.isPresent())
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("메인 페이지로 이동"));
		
		// 페이지를 적지 않은 경우 1페이지로 간주
		// 페이지 번호가 숫자가 아닌 경우 1페이지로 간주
		// 페이지가 0보다 작은 경우 1페이지로 간주
		// 페이지 번호가 최대 페이지 번호를 넘어간 경우 마지막 페이지로 간주 (service)
		if(!pageNo.isPresent() || !isNumeric(pageNo.get()) || Integer.parseInt(pageNo.get()) < 1) {
			Map<String, Object> productsWithPageInfo = productService.getProductListWithSearch(keyword.get(), 1L);
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(JSONResult.success(productsWithPageInfo));
		}
		
		// 사용자가 요청한 키워드가 데이터베이스에 없는 경우 처리 (service)
		
		// 정상 동작
		Map<String, Object> productsWithPageInfo = productService.getProductListWithSearch(keyword.get(), Long.parseLong(pageNo.get()));
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(productsWithPageInfo));
	}
	
	@ApiOperation(value = "상품 상세 조회")
	@RequestMapping(value= {"/detail", "/detail/{productNo}"}, method=RequestMethod.GET)
	public ResponseEntity<JSONResult> detail(
			@PathVariable(value="productNo") Optional<String> productNo){
		
		// 상품 번호가 비어있는 경우
		if(!productNo.isPresent()) {
			return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(JSONResult.fail("메인 페이지로 이동"));
		}
		
		// 상품 번호가 숫자가 아닌 경우
		if(!isNumeric(productNo.get())) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(JSONResult.fail("메인 페이지로 이동"));
		}
		
		// 정상 동작
		ProductDetailDto product = productService.getProductDetailInfoByNo(Long.parseLong(productNo.get()));
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success(product));
	}
	
}
