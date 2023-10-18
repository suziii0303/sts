package com.minu.merong.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/redir")
public class RedirectController {
	
	@GetMapping("/test")
	public String test() {
		return "retest";
	}
	
	@PostMapping("/test")
	public String pTest(RedirectAttributes redirAttr) {
		//HttpServletResponse resp throws Exception
		//한번만 쓸 일회성 메세지, Session에 담기느는 부담스럽고
		// 모델에 담자니 URL을 리다이렉트해야 해서 담을 수 없을 때  -- > 새로 고침 하면 사라짐
		// post방식으로 보낼때 URL?뒤에 붙여서 보내자니 , 왠지 보안적인 데이터라는 느낌이 들때 
		// 다른 사람보다 좀 있어 보이고 싶을 때 
		// Redirect라서 실제로는 session에 담는데, 스프링이 알아서 
		// 담았다가 지워줌, 그래서 이름에 flash가 들어감 
		// 주의 사항은 직접 HttpServletResponse를 이용하면 스프링이 중간에 끼지 못해서 
		// 제대로 작동 안함!!
		
		// 가끔씩 꽤 쓸만하니까, 써보는걸 추천
		redirAttr.addFlashAttribute("oneTimeMessage","넘어지면 아픔");
		
		// 직접 Respone를 이용하면 값이 담기지 않음( 스프링이 중간에 끼어야함!, 자주 실수하는 부분)
		//resp.sendRedirect("/merong/redir/test");
		
		return "redirect:/redir/test";
	}
}
