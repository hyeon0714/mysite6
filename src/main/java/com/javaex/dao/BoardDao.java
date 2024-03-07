package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.util.BoardUtil;
import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	
	@Autowired
	private SqlSession sqlSession;
	
	
	//리스트(5개만 가져오기)
	public List<BoardVo> list(BoardUtil util) {
		
		List<BoardVo> board = sqlSession.selectList("board.list", util);
		
		return board;
	}
	
	//총 게시판수 가져오기
	public int boardCount(BoardUtil util) {
		
		BoardUtil b = sqlSession.selectOne("board.count", util);
		
		return b.getTotalRecord();
	}
	
	//읽기1번(조회수 가져오기)
	public BoardVo hit(int no) {
		
		return sqlSession.selectOne("board.hit", no);
	}
	
	//읽기2번(조회수 올리기)
	public void upHit(BoardVo bv) {

		sqlSession.update("board.upHit", bv);
	}
	
	//읽기3번(읽기/수정폼도 같이)
	public BoardVo read(int no) {
		
		return sqlSession.selectOne("board.read", no);
	}
	
	//수정
	public void modify(BoardVo bv) {
		
		sqlSession.update("board.modify", bv);
	}
	
	//쓰기
	public void write(BoardVo bv) {
		
		sqlSession.insert("board.write", bv);
	}
}
