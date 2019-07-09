package com.cafe24.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.dao.TestDao;
import com.cafe24.vo.TestVo;

@Service
public class TestService {

	@Autowired
	private TestDao testDao;
	
	public List<TestVo> getData(){
		return testDao.getData();
	}
}
