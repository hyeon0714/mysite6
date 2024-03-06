package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	
	@Autowired
	private SqlSession sqlSession;
	
	
	//리스트
	public List<BoardVo> list() {
		
		List<BoardVo> board = sqlSession.selectList("board.list");
		
		return board;
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
