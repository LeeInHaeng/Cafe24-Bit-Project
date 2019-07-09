package com.cafe24.vo;

public class TestVo {

	private int no;
	private String value;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "TestVo [no=" + no + ", value=" + value + "]";
	}
	
}
