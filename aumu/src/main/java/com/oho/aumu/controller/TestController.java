package com.oho.aumu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
@RequestMapping("/hyunhak")
public class TestController {
	
	@GetMapping("/merong")
	public String getHyunhak(Model model) {
		List<String> myList = new ArrayList<>();
		for(int i=1;i<=5; i++) {
			myList.add("임미누 누가 미누 왜 미누 "+i);
		}
		
		model.addAttribute("minuList",myList);
		return "hh";
	}
}
