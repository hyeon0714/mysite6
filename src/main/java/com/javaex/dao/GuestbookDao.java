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
		
		sqlSession.insert("guestbook.insert", gv);
	}
	
	public void delete(GuestbookVo gv) {
		
		sqlSession.delete("guestbook.delete", gv);
	}
	
	
	//ajax 등록
	public int addAndGuest(GuestbookVo gv) {
		
		int count = sqlSession.insert("guestbook.insert2", gv);
		
		return 1;
	}
	
	//ajax 데이터 1개 가져오기(비즈니스 로직)
	public GuestbookVo addAndGuestSelect(GuestbookVo gv) {
		
		GuestbookVo gVo = sqlSession.selectOne("guestbook.selectone", gv);
		
		return gVo;
	}
	
	//ajax삭제
	public int modalDelete(GuestbookVo guestVo) {
		
		System.out.println("....");
		System.out.println(guestVo);
		
		return sqlSession.delete("guestbook.modalDel", guestVo);
		
	}
}
