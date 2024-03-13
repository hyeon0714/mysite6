package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao gd;
	
	//리스트
	public List<GuestbookVo> exeList() {
		
		List<GuestbookVo> gList = gd.selectList();
		
		return gList;
		
	}
	
	//추가
	public void exeAdd(GuestbookVo gv) {
		
		gd.add(gv);
	}
	
	//삭제
	public void exeDelete(GuestbookVo gv) {
		
		gd.delete(gv);
	}
}
