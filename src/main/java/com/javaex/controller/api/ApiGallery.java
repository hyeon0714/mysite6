package com.javaex.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping("api")
public class ApiGallery {
	
	@Autowired
	private GalleryService gs;
	
	@ResponseBody
	@RequestMapping(value = "/gallery", method = RequestMethod.DELETE)
	public GalleryVo delete(@RequestBody GalleryVo gv) {
		
		System.out.println(gv.toString());
		
		gs.exeDelete(gv);
		
		return gv;
	}
}
