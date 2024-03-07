package com.javaex.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.util.BoardUtil;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao bd;
	
	//리스트
	public Map<String, Object> exeList(BoardUtil util) {
		
		//util의 읽기 시작할 게시물과 읽기를 멈출 게시물 계산
		util.setPage((util.getPage()-1)*5);
		
				
		//검색조건의 리스트 5개 가져오기
		List<BoardVo> vo = bd.list(util);
		
		//총 게시물의 수
		util.setTotalRecord(bd.boardCount(util));
		
		
		
		//최대페이지 수 계산
		if(util.getTotalRecord()%5==0) {
			util.setPageSize(util.getTotalRecord()/5);
		}else {
			util.setPageSize(util.getTotalRecord()/5+1);
		}
		
		List<Integer> pSize = new ArrayList();
		
		for(int i = 0; i<util.getPageSize(); i++) {
			pSize.add(i);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("vo", vo);
		map.put("util", util);
		map.put("pSize", pSize);
		
		
		
		return map;
		
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
