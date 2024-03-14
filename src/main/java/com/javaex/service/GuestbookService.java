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
	
	
	//ajax 등록
	public GuestbookVo exeAddandGuest(GuestbookVo gv) {
		
		//등록
		gd.addAndGuest(gv);
		
		//가져오기
		return gd.addAndGuestSelect(gv);
		
	}
	
	//ajax삭제
	public GuestbookVo exeModalDelete(GuestbookVo guestVo) {
		
		int count = gd.modalDelete(guestVo);
		
		if(count == 1) {
			return guestVo;
		}else {
			guestVo.setNo(0);
			
			return guestVo;
		}
	}
}
