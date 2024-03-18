package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping("gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService gs;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		
		List<GalleryVo> gList = gs.exeList();
		
		System.out.println(gList);
		
		model.addAttribute("gList", gList);
		
		return "gallery/list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@RequestParam("file") MultipartFile file, @RequestParam("userNo")int userNo, @RequestParam("content")String content) {
		
		gs.exeAdd(file, userNo, content);
		
		return "redirect:list";
	}
}
