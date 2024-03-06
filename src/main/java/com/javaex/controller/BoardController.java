package com.javaex.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	private BoardService bs;
	
	//리스트
	@RequestMapping("/list")
	public String list(Model model) {
		
		List<BoardVo> board = bs.exeList();
		
		model.addAttribute("board", board);
		
		return "board/list";
	}
	
	//읽기
	@RequestMapping("/read")
	public String read(@Param("no")int no, Model model) {
		
		BoardVo boardOne = bs.exeRead(no);
		
		model.addAttribute("boardOne", boardOne);
		
		return "board/read";
	}
	
	//수정폼
	@RequestMapping("/modifyform")
	public String modifyForm(@Param("no")int no,Model model) {
		
		BoardVo boardOne = bs.exeModifyForm(no);
		
		model.addAttribute("boardOne", boardOne);
		
		return "board/modifyForm";
	}
	
	//수정
	@RequestMapping("/modify")
	public String modify(@ModelAttribute BoardVo bv) {
		
		bs.exeModify(bv);
		
		return "redirect:/board/list";
	}
	
	//쓰기폼
	@RequestMapping("/writeform")
	public String writeFrom() {
		
		return "board/writeForm";
	}
	
	//쓰기(등록)
	@RequestMapping("write")
	public String write(@ModelAttribute BoardVo bv) {
		
		bs.exeWrite(bv);
		
		return "redirect:/board/list";
	}
}
