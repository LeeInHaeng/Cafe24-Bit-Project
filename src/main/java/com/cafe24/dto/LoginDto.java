package com.cafe24.dto;

import com.cafe24.validator.constraints.IdValid;
import com.cafe24.validator.constraints.PasswordValid;

public class LoginDto {

	@IdValid
	private String id;
	
	@PasswordValid
	private String pass;

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

	@Override
	public String toString() {
		return "LoginDto [id=" + id + ", pass=" + pass + "]";
	}
	
}
