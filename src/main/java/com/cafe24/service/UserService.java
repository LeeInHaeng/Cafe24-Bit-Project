package com.cafe24.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.dao.UserDao;
import com.cafe24.vo.MemberVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public void clearMemberTable(){
		userDao.clear();
	}

	public Map<String, Object> checkId(String userid) {
		
		int query = userDao.checkId(userid);
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("result", "success or fail");
		result.put("message", "이미 존재하는 아이디 입니다.");
		
		return result;
	}

	public boolean registerNewMember(MemberVo memberVo) {
		
		int query = userDao.insert(memberVo);
		
		return false;
	}

	public boolean memberLoginTry(MemberVo memberVo) {
		
		MemberVo query = userDao.login(memberVo);
		
		return false;
	}
}
