package com.cafe24.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;

	public List<CategoryVo> getList() {
		// TODO Auto-generated method stub
		return null;
	}
}
