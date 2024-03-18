package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.AttachService;

@Controller
@RequestMapping("attach")
public class AttachController {
	
	@Autowired
	private AttachService as;
	
	@RequestMapping(value = "/uploadform", method = RequestMethod.GET)
	public String uploadForm() {
		
		return "attach/form";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file, Model model) {//파일의 업로드는 multipartfile로 받아야한다
		
		//System.out.println(file.getOriginalFilename());	//업로드한 파일의 이름
		
		//System.out.println(file.toString());	//파일은 업로드된 실제 파일이 없어도 tostring이 찍힌다
		
		String svaeName = as.exeUpload(file);
		
		model.addAttribute("saveName", svaeName);
		
		return "attach/result";
	}
}
