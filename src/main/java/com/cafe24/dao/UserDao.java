package com.cafe24.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.vo.MemberVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	public void clear() {
		sqlSession.delete("member.clear");
	}

	public int checkId(String userid) {
		return 0;
	}

	public int insert(MemberVo memberVo) {
		return 0;
	}

	public MemberVo login(MemberVo memberVo) {
		return null;
	}
}
