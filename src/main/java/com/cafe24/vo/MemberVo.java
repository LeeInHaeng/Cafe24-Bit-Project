package com.cafe24.vo;

// 회원 테이블
public class MemberVo {

	private String id;
	private String pass;
	private String name;
	private String address;
	private String tel;
	private String phone;
	private boolean ismessage;
	private String email;
	private boolean ismail;
	private String birth;
	private String refundName;
	private String refundNumber;
	private long mileage;
	private String role;
	private String regDate;
	private String status;
	
	public String getRefundName() {
		return refundName;
	}
	public void setRefundName(String refundName) {
		this.refundName = refundName;
	}
	public String getRefundNumber() {
		return refundNumber;
	}
	public void setRefundNumber(String refundNumber) {
		this.refundNumber = refundNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isIsmessage() {
		return ismessage;
	}
	public void setIsmessage(boolean ismessage) {
		this.ismessage = ismessage;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isIsmail() {
		return ismail;
	}
	public void setIsmail(boolean ismail) {
		this.ismail = ismail;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public long getMileage() {
		return mileage;
	}
	public void setMileage(long mileage) {
		this.mileage = mileage;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "MemberVo [id=" + id + ", pass=" + pass + ", name=" + name + ", address=" + address + ", tel=" + tel
				+ ", phone=" + phone + ", ismessage=" + ismessage + ", email=" + email + ", ismail=" + ismail
				+ ", birth=" + birth + ", refundName=" + refundName + ", refundNumber=" + refundNumber + ", mileage="
				+ mileage + ", role=" + role + ", regDate=" + regDate + ", status=" + status + "]";
	}
	
}
