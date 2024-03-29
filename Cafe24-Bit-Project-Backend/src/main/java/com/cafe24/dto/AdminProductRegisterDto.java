package com.cafe24.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.cafe24.vo.ProductImageVo;
import com.cafe24.vo.ProductOptionVo;
import com.cafe24.vo.ProductQuantityVo;

public class AdminProductRegisterDto {

	private long productNo;
	private long productCategoryNo;
	
	@NotEmpty(message="상품명은 필수 입력 항목 입니다.")
	@Length(max=200, message="상품명을 200자 이내로 입력해 주세요.")
	private String title;
	private String image;
	
	@Min(value = 0L, message = "가격은 양수만 가능 합니다.")
	private long price;
	
	@Min(value = 0L, message = "추가되는 마일리지는 양수만 가능 합니다.")
	private long mileageAdd;
	
	@Length(max=255, message="상품 요약 설명을 255자 내로 입력해 주세요.")
	private String description;
	private String descriptionDetail;
	
	@Min(value = 0L, message = "배송 가격은 양수만 가능 합니다.")
	private long shippingPrice;
	
	private String endDate;
	
	private boolean isdisplay;
	private boolean issell;
	private boolean isdisplayMain;
	
	List<ProductImageVo> productImageVo;
	
	List<ProductOptionVo> productOptionVo;
	
	List<ProductQuantityVo> productQuantityVo;

	
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public long getProductNo() {
		return productNo;
	}

	public void setProductNo(long productNo) {
		this.productNo = productNo;
	}
	public long getProductCategoryNo() {
		return productCategoryNo;
	}

	public void setProductCategoryNo(long productCategoryNo) {
		this.productCategoryNo = productCategoryNo;
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

	public long getMileageAdd() {
		return mileageAdd;
	}

	public void setMileageAdd(long mileageAdd) {
		this.mileageAdd = mileageAdd;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionDetail() {
		return descriptionDetail;
	}

	public void setDescriptionDetail(String descriptionDetail) {
		this.descriptionDetail = descriptionDetail;
	}

	public long getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(long shippingPrice) {
		this.shippingPrice = shippingPrice;
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

	public List<ProductImageVo> getProductImageVo() {
		return productImageVo;
	}

	public void setProductImageVo(List<ProductImageVo> productImageVo) {
		this.productImageVo = productImageVo;
	}

	public List<ProductOptionVo> getProductOptionVo() {
		return productOptionVo;
	}

	public void setProductOptionVo(List<ProductOptionVo> productOptionVo) {
		this.productOptionVo = productOptionVo;
	}

	public List<ProductQuantityVo> getProductQuantityVo() {
		return productQuantityVo;
	}

	public void setProductQuantityVo(List<ProductQuantityVo> productQuantityVo) {
		this.productQuantityVo = productQuantityVo;
	}

	@Override
	public String toString() {
		return "AdminProductRegisterDto [productNo=" + productNo + ", productCategoryNo=" + productCategoryNo
				+ ", title=" + title + ", image=" + image + ", price=" + price + ", mileageAdd=" + mileageAdd
				+ ", description=" + description + ", descriptionDetail=" + descriptionDetail + ", shippingPrice="
				+ shippingPrice + ", endDate=" + endDate + ", isdisplay=" + isdisplay + ", issell=" + issell
				+ ", isdisplayMain=" + isdisplayMain + ", productImageVo=" + productImageVo + ", productOptionVo="
				+ productOptionVo + ", productQuantityVo=" + productQuantityVo + "]";
	}
	
}
