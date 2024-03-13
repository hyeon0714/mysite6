package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public List<GuestbookVo> selectList() {
		
		List<GuestbookVo> gList =  sqlSession.selectList("guestbook.selectList");
		
		return gList;
	}
	
	public void add(GuestbookVo gv) {
		
		sqlSession.update("guestbook.update", gv);
	}
	
	public void delete(GuestbookVo gv) {
		
		sqlSession.delete("guestbook.delete", gv);
	}
}
