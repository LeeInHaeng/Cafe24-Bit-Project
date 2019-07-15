package com.cafe24.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.vo.MemberVo;

@Repository
public class MemberDao {

	@Autowired
	private SqlSession sqlSession;

	public void clear() {
		sqlSession.delete("member.clear");
	}

	public boolean checkId(String userid) {
		String checkId = sqlSession.selectOne("member.checkId", userid);
		return checkId != null;
	}

	public boolean insert(MemberVo memberVo) {
		int queryResult = sqlSession.insert("member.insert", memberVo);
		return queryResult==1;
	}

	public MemberVo login(MemberVo memberVo) {
		return null;
	}

	public void deleteTest(String userid) {
		sqlSession.delete("member.deleteTest", userid);
	}
}
