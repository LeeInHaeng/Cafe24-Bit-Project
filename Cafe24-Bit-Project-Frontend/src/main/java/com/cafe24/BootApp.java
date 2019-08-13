package com.cafe24;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApp {

	public static final String APIURI = "http://192.168.1.217:8080/cafe24mall-backend/api";
	//public static final String APIURI = "http://localhost:8081/api";
	
	public static void main(String[] args) {
		SpringApplication.run(BootApp.class, args);
	}
	
}
