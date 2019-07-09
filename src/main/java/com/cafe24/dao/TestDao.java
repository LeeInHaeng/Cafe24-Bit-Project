package com.cafe24.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.vo.TestVo;

@Repository
public class TestDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<TestVo> getData(){
		return sqlSession.selectList("test.getData");
	}
}
