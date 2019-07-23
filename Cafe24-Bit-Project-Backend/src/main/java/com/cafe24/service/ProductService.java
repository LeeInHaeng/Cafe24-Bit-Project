package com.cafe24.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.dao.ProductDao;
import com.cafe24.dto.ProductDetailDto;
import com.cafe24.vo.ProductVo;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;
	
	// 한 페이지에 보여줄 상품의 수
	private static final int SHOW_SIZE = 2;
	
	// 페이지 리스트의 페이지 수
	private static final int PAGE_SIZE = 3;

	public Map<String, Object> getProductList(String categoryName, long pageNo) {
		
		//1. 페이징을 위한 기본 데이터 계산
		int totalCount = productDao.getTotalCount( categoryName );
		if(totalCount==0) {
			return null;
		}
		int pageCount = (int)Math.ceil( (double)totalCount / SHOW_SIZE );
		int blockCount = (int)Math.ceil( (double)pageCount / PAGE_SIZE );
		int currentBlock = (int)Math.ceil( (double)pageNo / PAGE_SIZE );
		
		//2. 파라미터 page 값  검증
		
		// 페이지 번호가 최대 페이지 번호를 넘어간 경우 마지막 페이지로 간주
		if( pageNo > pageCount ) {
			pageNo = pageCount;
			currentBlock = (int)Math.ceil( (double)pageNo / PAGE_SIZE );
		}		
		
		//3. view에서 페이지 리스트를 렌더링 하기위한 데이터 값 계산
		int beginPage = currentBlock == 0 ? 1 : (currentBlock - 1)*PAGE_SIZE + 1;
		int prevPage = ( currentBlock > 1 ) ? ( currentBlock - 1 ) * PAGE_SIZE : 0;
		int nextPage = ( currentBlock < blockCount ) ? currentBlock * PAGE_SIZE + 1 : 0;
		int endPage = ( nextPage > 0 ) ? ( beginPage - 1 ) + SHOW_SIZE : pageCount;
		
		//4. 리스트 가져오기
		List<ProductVo> products = productDao.getList( categoryName, pageNo, SHOW_SIZE );
		
		//5. 리스트 정보를 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put( "products", products );
		map.put( "totalCount", totalCount );
		map.put( "showSize", SHOW_SIZE );
		map.put( "currentPage", pageNo );
		map.put( "beginPage", beginPage );
		map.put( "endPage", endPage );
		map.put( "prevPage", prevPage );
		map.put( "nextPage", nextPage );
		map.put( "category", categoryName );

		return map;
	}

	public Map<String, Object> getProductListWithSearch(String keyword, Long pageNo) {

		//1. 페이징을 위한 기본 데이터 계산
		int totalCount = productDao.getTotalCountWithSearch( keyword );
		if(totalCount==0) {
			return null;
		}
		int pageCount = (int)Math.ceil( (double)totalCount / SHOW_SIZE );
		int blockCount = (int)Math.ceil( (double)pageCount / PAGE_SIZE );
		int currentBlock = (int)Math.ceil( (double)pageNo / PAGE_SIZE );
		
		//2. 파라미터 page 값  검증
		
		// 페이지 번호가 최대 페이지 번호를 넘어간 경우 마지막 페이지로 간주
		if( pageNo > pageCount ) {
			pageNo = (long) pageCount;
			currentBlock = (int)Math.ceil( (double)pageNo / PAGE_SIZE );
		}		
		
		//3. view에서 페이지 리스트를 렌더링 하기위한 데이터 값 계산
		int beginPage = currentBlock == 0 ? 1 : (currentBlock - 1)*PAGE_SIZE + 1;
		int prevPage = ( currentBlock > 1 ) ? ( currentBlock - 1 ) * PAGE_SIZE : 0;
		int nextPage = ( currentBlock < blockCount ) ? currentBlock * PAGE_SIZE + 1 : 0;
		int endPage = ( nextPage > 0 ) ? ( beginPage - 1 ) + SHOW_SIZE : pageCount;
		
		//4. 리스트 가져오기
		List<ProductVo> products = productDao.getListWithSearch( keyword, pageNo, SHOW_SIZE );
		
		//5. 리스트 정보를 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put( "products", products );
		map.put( "totalCount", totalCount );
		map.put( "showSize", SHOW_SIZE );
		map.put( "currentPage", pageNo );
		map.put( "beginPage", beginPage );
		map.put( "endPage", endPage );
		map.put( "prevPage", prevPage );
		map.put( "nextPage", nextPage );
		map.put( "category", keyword );

		return map;
	}

	public ProductDetailDto getProductDetailInfoByNo(long productNo) {
		
		ProductDetailDto product = productDao.get(productNo);
		
		return product;
	}

}
