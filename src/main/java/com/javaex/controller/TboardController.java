package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.javaex.service.TboardService;
import com.javaex.vo.TboardVo;

@Controller
public class TboardController {

	@Autowired
	private TboardService tboardService;
	
	
	//리스트(검색X,페이징 X)
	@RequestMapping(value="/tboard/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("TboardController.list()");
		
		//boardService를 통해서 리스트를 가져온다
		List<TboardVo> boardList = tboardService.exeList();
		//모델에 리스트를 담는다(포워드)
		model.addAttribute("boardList", boardList);
		
		return "tboard/list";
	}
	
	//리스트(검색X,페이징 0)
	@RequestMapping(value="/tboard/list2", method= {RequestMethod.GET, RequestMethod.POST})
	public String list2(@RequestParam(value = "crtPage", required = false, defaultValue = "1") int crtPage, Model model) {
		//파라미터에 crtPage가 없어도 기본값을 1로 처리한다
		
		//System.out.println(crtPage);
		
		//boardService를 통해서 리스트를 가져온다
		Map<String, Object> pMap = tboardService.exeList2(crtPage);
		//모델에 리스트를 담는다(포워드)
		model.addAttribute("pMap", pMap);
		
		
		
		return "tboard/list2";
	}
	
	//리스트(검색X,페이징 0)
	@RequestMapping(value="/tboard/list3", method= {RequestMethod.GET, RequestMethod.POST})
	public String list3(@RequestParam(value = "crtPage", required = false, defaultValue = "1") int crtPage, @RequestParam(value = "keyword", required = false, defaultValue = "")String keyword, Model model) {
		//파라미터에 crtPage가 없어도 기본값을 1로 처리한다
		
		//System.out.println(crtPage);
		
		//boardService를 통해서 리스트를 가져온다
		Map<String, Object> pMap = tboardService.exeList3(crtPage, keyword);
		//모델에 리스트를 담는다(포워드)
		model.addAttribute("pMap", pMap);
		
		
		
		return "tboard/list3";
	}
	
}
