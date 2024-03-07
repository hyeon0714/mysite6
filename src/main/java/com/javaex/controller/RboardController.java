package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.RboardService;
import com.javaex.vo.RboardVo;

@Controller
@RequestMapping(value = "rboard")
public class RboardController {
	
	@Autowired
	private RboardService rs;
	
	//리스트 가져오기
	@RequestMapping("list")
	public String list(Model model) {
		
		List<RboardVo> rlist = rs.exeList();
		
		System.out.println(rlist);
		
		model.addAttribute("rList", rlist);
		
		return "board/rboardList";
	}
	
	//쓰기폼
	@RequestMapping("writeform")
	public String writeForm() {
		
		return "board/rboardWriteForm";
	}
	
	//쓰기
	@RequestMapping("write")
	public String write(@ModelAttribute RboardVo vo) {
		
		rs.exeWrite(vo);
		
		return "";
	}
	
	
}
