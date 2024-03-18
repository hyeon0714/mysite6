package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<GalleryVo> list() {
		
		List<GalleryVo> gList = sqlSession.selectList("gallery.list");
		
		return gList;
	}
	
	public void add(GalleryVo gv) {
		
		sqlSession.insert("gallery.insert", gv);
	}
	
	public void delete(GalleryVo gv) {
		
		sqlSession.delete("gallery.delete", gv);
	}
}
