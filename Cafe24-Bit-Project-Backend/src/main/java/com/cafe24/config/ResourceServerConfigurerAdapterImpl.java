//package com.cafe24.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//
//@EnableResourceServer  // API 서버 인증(또는 권한 설정)
//@EnableAuthorizationServer // 자원서버 설정
//@Configuration
//public class ResourceServerConfigurerAdapterImpl extends ResourceServerConfigurerAdapter {
//	
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/api**").access("#oauth2.hasScope('read,write')");
//    }
//}
