package com.javaex.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("api")
public class AqiGuestbookController {
	
	@Autowired
	private GuestbookService gs;
	
	@ResponseBody	//리턴의 데이터를 json으로 변경해서 응답문서의 바디에 붙여서 보내준다
	@RequestMapping(value = "/guestbooks", method = RequestMethod.GET)
	public List<GuestbookVo> list() {
		
		List<GuestbookVo> gVo = gs.exeList();
		
		System.out.println(gVo);
		
		return gVo;
	}
}
