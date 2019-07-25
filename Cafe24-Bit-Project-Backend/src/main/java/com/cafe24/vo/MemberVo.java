package com.cafe24.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.cafe24.validator.constraints.DateValid;
import com.cafe24.validator.constraints.IdValid;
import com.cafe24.validator.constraints.NameValid;
import com.cafe24.validator.constraints.PasswordValid;
import com.cafe24.validator.constraints.PhoneValid;
import com.cafe24.validator.constraints.TelValid;

// 회원 테이블
public class MemberVo {

	@IdValid
	private String id;
	
	@PasswordValid
	private String pass;
	
	@NameValid
	private String name;
	
	@NotEmpty(message="주소는 필수 입력 항목 입니다.")
	@Length(max=255, message="주소를 255자 내로 입력해 주세요.")
	private String address;
	
	@TelValid
	private String tel;
	
	@PhoneValid
	private String phone;
	private boolean ismessage;
	
	@NotEmpty(message="이메일은 필수 입력 항목 입니다.")
	@Email
	private String email;
	private boolean ismail;
	
	@DateValid
	private String birth;
	private int age;
	
	@NotEmpty(message="환불 계좌 은행명은 필수 입력 항목 입니다.")
	@Length(max=255, message="환불 계좌 은행명을 255자 내로 입력해 주세요.")
	private String refundName;
	
	@NotEmpty(message="환불 계좌 번호는 필수 입력 항목 입니다.")
	@Length(max=255, message="환불 계좌 번호를 255자 내로 입력해 주세요.")
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
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
				+ ", birth=" + birth + ", age=" + age + ", refundName=" + refundName + ", refundNumber=" + refundNumber
				+ ", mileage=" + mileage + ", role=" + role + ", regDate=" + regDate + ", status=" + status + "]";
	}
	
}
