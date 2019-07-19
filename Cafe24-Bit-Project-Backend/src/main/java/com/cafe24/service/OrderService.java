package com.cafe24.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.dao.MemberDao;
import com.cafe24.dao.OrderDao;
import com.cafe24.dto.OrderPageDto;
import com.cafe24.dto.OrderProductDto;
import com.cafe24.dto.ProductOptionDto;
import com.cafe24.dto.ProductOrder;
import com.cafe24.vo.MemberVo;
import com.cafe24.vo.ProductOptionVo;
import com.cafe24.vo.ProductQuantityVo;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private MemberDao memberDao;
	
	public List<ProductQuantityVo> getProductQuantity(List<ProductOptionDto> productOptionDto) {
		
		List<ProductQuantityVo> queryResult = new ArrayList<ProductQuantityVo>();
		
		for(ProductOptionDto dto : productOptionDto) {
			List<String> optionValue = orderDao.getOptionDetailValue(dto.getProductOptionDetailNo());
			String optionCode = "";
			for(String value : optionValue) {
				optionCode += (value+"/");
			}
			optionCode = optionCode.substring(0, optionCode.length()-1);
			
			Map<String, Object> quantityParam = new HashMap<String, Object>();
			quantityParam.put("productNo", dto.getProductNo());
			quantityParam.put("optionCode", optionCode);
			
			queryResult.add(orderDao.getProductQuantity(quantityParam));
		}
		
		return queryResult;
	}
	
	public boolean isValidProductOptionDto(List<ProductOptionDto> productOptionDto) {
		if(productOptionDto == null)
			return false;
		
		for(ProductOptionDto dto : productOptionDto) {
			if((Long)dto.getProductNo()==null || dto.getProductOptionDetailNo()==null || dto.getProductOptionDetailNo().size()==0)
				return false;
			
			if(!orderDao.isExistOptionDetailNo(dto))
				return false;
		}
		
		return true;
	}

	public Map<String, Object> orderPageConnectWithOrderProducts(OrderPageDto orderPageDto) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(orderPageDto.getMemberId() != null) {
			MemberVo member = memberDao.get(orderPageDto.getMemberId());
			result.put("member", member);
		}
		else {
			result.put("member", orderPageDto.getNonmemberMac());
		}
		
		List<OrderProductDto> products = new ArrayList<OrderProductDto>();
		for(ProductOptionDto dto : orderPageDto.getProductOptionDto()) {
			OrderProductDto product = orderDao.getProductList(dto);
			product.setQuantity(dto.getQuantity());
			
			String optionCode = "";
			for(ProductOptionVo option : product.getProductOptionVo()) {
				optionCode += (option.getOptionValue()+"/");
			}
			
			System.out.println( optionCode.substring(0, optionCode.length()-1));
			
			Map<String, Object> quantityDto = new HashMap<String, Object>();
			quantityDto.put("productNo", product.getProductNo());
			quantityDto.put("optionCode", optionCode.substring(0, optionCode.length()-1));
			quantityDto.put("quantity", product.getQuantity());
			
			orderDao.decreaseQuantity(quantityDto);
			
			products.add(product);
		}
		result.put("products", products);
		return result;
	}

	public void BuyProducts(Map<String, Object> params) {
		// TODO Auto-generated method stub
		
	}

	public void showProductHistory() {
		// TODO Auto-generated method stub
		
	}

	public boolean changeProductStatus(ProductOrder productOrder) {
		
		int queryResult = orderDao.update(productOrder);
		
		return false;
	}

}
