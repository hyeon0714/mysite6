package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSesstion;
	
	//로그인
	public UserVo login(UserVo uv) {
		
		UserVo user = sqlSesstion.selectOne("user.login", uv);
		
		return user;
	}
	
	//수정폼
	public UserVo modifyForm(int no) {
		
		UserVo user = sqlSesstion.selectOne("user.modifyForm", no);
		
		return user;
	}
	
	//수정
	public void modify(UserVo uv) {
		
		sqlSesstion.update("user.modify", uv);
	}
	
	//회원가입
	public void join(UserVo uv) {
		
		sqlSesstion.insert("user.join", uv);
	}
}
