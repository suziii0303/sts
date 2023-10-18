package com.minu.merong.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ChatController {
	
		@GetMapping("/chat")
		public String chatChat(HttpSession hSession) {
			hSession.setAttribute("myName", "King of Fashion ChaHyun");
			
			return "chat";
		}

}
