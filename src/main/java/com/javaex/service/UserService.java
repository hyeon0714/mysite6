package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao ud;
	
	//로그인
	public UserVo exeLogin(UserVo uv) {
		
		return ud.login(uv);
	}
	
	//수정폼
	public UserVo exeUserOne(int no) {
		
		return ud.modifyForm(no);
	}
	
	//수정
	public void exeModify(UserVo uv) {
		
		ud.modify(uv);
	}
	
	//회원가입
	public void exeJoin(UserVo uv) {
		
		ud.join(uv);
	}
}
