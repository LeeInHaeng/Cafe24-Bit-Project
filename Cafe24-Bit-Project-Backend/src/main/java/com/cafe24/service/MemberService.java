package com.cafe24.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.dao.MemberDao;
import com.cafe24.dto.LoginDto;
import com.cafe24.vo.MemberVo;

@Service
public class MemberService {

	@Autowired
	private MemberDao memberDao;
	
	public void clearMemberTable(){
		memberDao.clear();
	}

	public boolean checkId(String userid) {
		boolean existUserId = memberDao.checkId(userid);
		return existUserId;
	}

	public MemberVo memberLoginTry(LoginDto loginDto) {
		
		MemberVo memberVo = memberDao.login(loginDto);
		return memberVo;
	}

	public void deleteTestData(String userid) {
		memberDao.deleteTest(userid);
	}

	public boolean joinUser(MemberVo memberVo) {
		boolean queryResult = memberDao.insert(memberVo);
		return queryResult;
	}

	public boolean memberInfoChange(@Valid MemberVo memberVo) {
		boolean queryResult = memberDao.update(memberVo);
		return queryResult;
	}
}
