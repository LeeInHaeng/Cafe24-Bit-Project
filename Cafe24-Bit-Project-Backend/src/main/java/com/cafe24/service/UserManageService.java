package com.cafe24.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.dao.UserManageDao;
import com.cafe24.dto.AdminCheckedUserUpdateDto;
import com.cafe24.dto.AdminUserSearchDto;
import com.cafe24.vo.MemberVo;

@Service
public class UserManageService {

	@Autowired
	UserManageDao userManageDao;
	
	public List<MemberVo> getUserListWithSearch(AdminUserSearchDto searchVo) {
		return userManageDao.getList(searchVo);
	}
	
	public boolean updateCheckedUser(AdminCheckedUserUpdateDto updateDto) {
		return userManageDao.updateUser(updateDto);
	}

}
