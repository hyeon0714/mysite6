package com.javaex.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	//리스트
	@ResponseBody	//리턴의 데이터를 json으로 변경해서 응답문서의 바디에 붙여서 보내준다
	@RequestMapping(value = "/guestbooks", method = RequestMethod.GET)
	public List<GuestbookVo> list() {
		
		List<GuestbookVo> gVo = gs.exeList();
		
		return gVo;
	}
	
	//등록
	@ResponseBody
	@RequestMapping(value = "/guestbooks", method = RequestMethod.POST)
	public GuestbookVo add(@RequestBody GuestbookVo guestVo) {//Vo의 생성자를 이용해서 자바로 받아온다	
		
		System.out.println(guestVo);
		
		return gs.exeAddandGuest(guestVo); 
	}
	
	//삭제
	@ResponseBody
	@RequestMapping(value = "/guestbooks", method = RequestMethod.DELETE)
	public GuestbookVo modalDelete(@ModelAttribute GuestbookVo guestVo) {
		
		return gs.exeModalDelete(guestVo);
	}
	
	//삭제2
	@ResponseBody
	@RequestMapping(value = "/guestbooks/{no}", method = RequestMethod.DELETE)//주소의 특정 이름없이 값을 바로꺼낼수 있다
	public GuestbookVo modalDelete2(@PathVariable("no") int no, @ModelAttribute GuestbookVo guestVo) {

		guestVo.setNo(no);
		
		
		GuestbookVo gVo =  gs.exeModalDelete(guestVo);
		
		System.out.println(gVo);
		
		return gVo;
	}
	
}
