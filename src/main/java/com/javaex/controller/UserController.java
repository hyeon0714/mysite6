package com.javaex.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "user")
public class UserController {
	
	@Autowired
	private UserService us;
	
	//로그인폼
	@RequestMapping("/loginform")
	public String loginForm() {
		
		return "user/loginForm";
	}
	
	//로그인
	@RequestMapping("/login")
	public String login(@ModelAttribute UserVo uv, HttpSession session) {
		
		UserVo user = us.exeLogin(uv);
		
		session.setAttribute("user", user);
		
		return "redirect:/main";
	}
	
	//수정폼
	@RequestMapping("/modifyform")
	public String modifyForm(@Param("no")int no, Model model) {
		
		UserVo user = us.exeUserOne(no);
		
		model.addAttribute("user", user);
		
		return "user/modifyForm";
	}
	
	//수정
	@RequestMapping("/modify")
	public String modify(@ModelAttribute UserVo uv) {
		
		us.exeModify(uv);
		
		return "redirect:/main";
	}
	
	//로그아웃
	@RequestMapping("/logout")
	public String logOut(HttpSession session) {
		
		session.removeAttribute("user");
		
		return "redirect:/user/loginform";
	}
	
	//회원가입폼
	@RequestMapping("/joinform")
	public String joinForm() {
		
		return "user/joinForm";
	}
	
	//회원가입
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo uv) {
		
		us.exeJoin(uv);
		
		return "user/joinOk";
	}
}
