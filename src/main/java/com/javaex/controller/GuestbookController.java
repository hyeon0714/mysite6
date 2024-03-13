package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService gs;
	
	//추가폼
	@RequestMapping(value = "/addlist", method = {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		
		List<GuestbookVo> gList = gs.exeList();
		
		model.addAttribute("gList", gList);
		
		return "guestbook/addList";
	}
	
	
	//추가
	@RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestbookVo gv) {
		
		gs.exeAdd(gv);
		
		return "redirect:/guestbook/addlist";
	}
	
	//삭제폼
	@RequestMapping(value = "/deleteform", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteFrom() {
		
		return "guestbook/deleteForm";
	}
	
	//삭제
	@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestbookVo gv) {
		
		gs.exeDelete(gv);
		
		return "redirect:/guestbook/addlist";
	}
	
	//////////////
	//ajax 방명록 메인
	@RequestMapping("/ajaxindex")
	public String ajaxIndex() {
		
		return "guestbook/ajaxIndex";
	}
	
	//ajaxlist
}
