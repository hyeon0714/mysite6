package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao bd;
	
	//리스트
	public List<BoardVo> exeList() {
		
		return bd.list();
	}
	
	//읽기(조회수 가져와서 늘리고 읽기)
	public BoardVo exeRead(int no) {
		
		//조회수 가져오기
		BoardVo board = bd.hit(no);
		
		//조회수 늘리기
		int hit = board.getHit() + 1;
		
		board.setHit(hit);
		board.setNo(no);
		
		bd.upHit(board);
		
		//읽기
		return bd.read(no);
	}
	
	//수정폼 1개 읽어오기
	public BoardVo exeModifyForm(int no) {
		
		return bd.read(no);
	}
	
	//수정
	public void exeModify(BoardVo bv) {
		
		bd.modify(bv);
	}
	
	//쓰기
	public void exeWrite(BoardVo bv) {
		
		bd.write(bv);
	}
}
