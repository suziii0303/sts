package com.minu.sec.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MyLoginController {

	@Autowired
	private AuthenticationManager authMgr; // 인증메니져

	@PostMapping("/mylogin")
	public String myLongin(HttpServletRequest req) {

		// Autowired 잘 되었는지? 확인(D.I)
		log.debug("check:" + authMgr);
		// UsernamePasswordAuthenticationToken
		// DB에 존재하는 아이디/암호여야 함!, 안 그럼 없는 사용자!
		UsernamePasswordAuthenticationToken myAuth = new UsernamePasswordAuthenticationToken("jinsu3", "jinsu3");
		
		//인증 매니져가 토큰을 사용하고, 인증정보를 만듬
		Authentication auth = authMgr.authenticate(myAuth);
		
		SecurityContext sc = SecurityContextHolder.getContext();
		//인증 정보를 등록
		sc.setAuthentication(auth);
		
		
		//Session이 없으면 생성
		HttpSession session = req.getSession(true);  
		//Session에 시큐리티 컨텍스트 등록
		//org.springframework.security.web.context.HttpSessionSecurityContextRepository.class에 
		// "SPRING_SECURITY_CONTEXT_KEY" 이 정의 되어 있음. 그냥 참고망
		session.setAttribute("SPRING_SECURITY_CONTEXT_KEY", sc);
		

		return "home";
	}
}
