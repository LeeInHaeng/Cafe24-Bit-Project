package com.cafe24.vo;

import javax.validation.constraints.NotEmpty;

import com.cafe24.validator.constraints.PhoneValid;

public class NonmemberVo {

	private String nonmemberMac;
	
	@PhoneValid
	private String nonmemberPhone;
	
	@NotEmpty
	private String nonmemberPass;
	
	private String nonmemberName;
	private String nonmemberRefundName;
	private String nonmemberRefundNumber;
	
	public String getNonmemberMac() {
		return nonmemberMac;
	}
	public void setNonmemberMac(String nonmemberMac) {
		this.nonmemberMac = nonmemberMac;
	}
	public String getNonmemberPhone() {
		return nonmemberPhone;
	}
	public void setNonmemberPhone(String nonmemberPhone) {
		this.nonmemberPhone = nonmemberPhone;
	}
	public String getNonmemberPass() {
		return nonmemberPass;
	}
	public void setNonmemberPass(String nonmemberPass) {
		this.nonmemberPass = nonmemberPass;
	}
	public String getNonmemberName() {
		return nonmemberName;
	}
	public void setNonmemberName(String nonmemberName) {
		this.nonmemberName = nonmemberName;
	}
	public String getNonmemberRefundName() {
		return nonmemberRefundName;
	}
	public void setNonmemberRefundName(String nonmemberRefundName) {
		this.nonmemberRefundName = nonmemberRefundName;
	}
	public String getNonmemberRefundNumber() {
		return nonmemberRefundNumber;
	}
	public void setNonmemberRefundNumber(String nonmemberRefundNumber) {
		this.nonmemberRefundNumber = nonmemberRefundNumber;
	}
	
	@Override
	public String toString() {
		return "NonmemberVo [nonmemberMac=" + nonmemberMac + ", nonmemberPhone=" + nonmemberPhone + ", nonmemberPass="
				+ nonmemberPass + ", nonmemberName=" + nonmemberName + ", nonmemberRefundName=" + nonmemberRefundName
				+ ", nonmemberRefundNumber=" + nonmemberRefundNumber + "]";
	}
}
