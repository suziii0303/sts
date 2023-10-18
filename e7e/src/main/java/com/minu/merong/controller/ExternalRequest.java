package com.minu.merong.controller;

import java.io.IOException;

import org.apache.hc.client5.http.fluent.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/jbj")
public class ExternalRequest {
	
	
	@GetMapping("/lkj")
	public String getLkj() {
		return "lkj";
	}
	
	@GetMapping(value="/test", produces="application/json;charset=utf-8")
	@ResponseBody()
	public String getNaver() throws IOException {
		
		//일반적으로 서버에서 다른 서버로 데이터를 요청할 때는 주로 get방식만 사용
		//유료서비스나, API 서비스는 보안토큰등을 이용하여,
		//사용자를 확인하는 방식을 사용하여, 서버 우회가 필요없음!
		
		return Request.get("https://weather.naver.com").execute().returnContent().asString();
	}
}
