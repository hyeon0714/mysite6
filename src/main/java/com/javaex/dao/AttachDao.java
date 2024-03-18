package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.javaex.vo.AttachVo;

@Repository
public class AttachDao {

	private SqlSession sqlSession;
	
	public void upload(AttachVo atVo) {
		
		sqlSession.insert("attach.insert", atVo);
	}
}
