package com.cafe24.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.dto.AdminCheckedUserUpdateDto;
import com.cafe24.dto.AdminUserSearchDto;
import com.cafe24.vo.MemberVo;

@Repository
public class UserManageDao {

	@Autowired
	private SqlSession sqlSession;

	public List<MemberVo> getList(AdminUserSearchDto searchVo) {
		return sqlSession.selectList("usermanage.getList", searchVo);
	}

	public boolean updateUser(AdminCheckedUserUpdateDto updateDto) {
		int queryResult = sqlSession.update("usermanage.update", updateDto);
		return queryResult==updateDto.getUserid().size();
	}
}
