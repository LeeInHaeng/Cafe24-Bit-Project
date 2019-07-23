package com.cafe24.dto;

import java.util.List;

import com.cafe24.vo.ProductOptionVo;

public class AdminProductSearchResultDto {

	private long productNo;
	private String title;
	private String image;
	private long price;
	
	private String regDate;
	private String endDate;
	
	private boolean isdisplay;
	private boolean issell;
	private boolean isdisplayMain;
	
	private List<ProductOptionVo> productOptionVo;
	
	private String category;

	public long getProductNo() {
		return productNo;
	}

	public void setProductNo(long productNo) {
		this.productNo = productNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean isIsdisplay() {
		return isdisplay;
	}

	public void setIsdisplay(boolean isdisplay) {
		this.isdisplay = isdisplay;
	}

	public boolean isIssell() {
		return issell;
	}

	public void setIssell(boolean issell) {
		this.issell = issell;
	}

	public boolean isIsdisplayMain() {
		return isdisplayMain;
	}

	public void setIsdisplayMain(boolean isdisplayMain) {
		this.isdisplayMain = isdisplayMain;
	}

	public List<ProductOptionVo> getProductOptionVo() {
		return productOptionVo;
	}

	public void setProductOptionVo(List<ProductOptionVo> productOptionVo) {
		this.productOptionVo = productOptionVo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "AdminProductSearchResultDto [productNo=" + productNo + ", title=" + title + ", image=" + image
				+ ", price=" + price + ", regDate=" + regDate + ", endDate=" + endDate + ", isdisplay=" + isdisplay
				+ ", issell=" + issell + ", isdisplayMain=" + isdisplayMain + ", productOptionVo=" + productOptionVo
				+ ", category=" + category + "]";
	}
	
}
