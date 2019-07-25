package com.cafe24.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

// 상품 카테고리 테이블
public class CategoryVo {

	private long categoryNo;
	
	@NotEmpty(message="카테고리 이름은 필수 입력 항목 입니다.")
	@Length(max=255, message="카테고리 이름을 255자 내로 입력해 주세요.")
	private String categoryName;
	
	@Min(value = 0L, message = "잘못된 요청 입니다.")
	private long groupno;
	@Min(value = 0L, message = "잘못된 요청 입니다.")
	private long orderno;
	@Min(value = 0L, message = "잘못된 요청 입니다.")
	private long depth;
	@Min(value = 0L, message = "잘못된 요청 입니다.")
	private long parentno;
	
	public long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(long categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public long getGroupno() {
		return groupno;
	}
	public void setGroupno(long groupno) {
		this.groupno = groupno;
	}
	public long getOrderno() {
		return orderno;
	}
	public void setOrderno(long orderno) {
		this.orderno = orderno;
	}
	public long getDepth() {
		return depth;
	}
	public void setDepth(long depth) {
		this.depth = depth;
	}
	public long getParentno() {
		return parentno;
	}
	public void setParentno(long parentno) {
		this.parentno = parentno;
	}
	
	@Override
	public String toString() {
		return "CategoryVo [categoryNo=" + categoryNo + ", categoryName=" + categoryName + ", groupno=" + groupno
				+ ", orderno=" + orderno + ", depth=" + depth + ", parentno=" + parentno + "]";
	}
	
}
