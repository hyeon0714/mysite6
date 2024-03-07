package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RboardDao;
import com.javaex.vo.RboardVo;

@Service
public class RboardService {
	
	@Autowired
	private RboardDao rd;
	
	//리스트
	public List<RboardVo> exeList() {
		
		return rd.list();
		
	}
	
	//쓰기
	public void exeWrite(RboardVo vo) {
		
		rd.write(vo);
		
		//rd.update1();
	}
}
