package com.minu.merong.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice // 일일이 컨트롤러에 등록하지 않고 예외처리 공통 사용!
public class CommonExceptionHandler {
	
	// NullPointerException 햔들링! 
	@ExceptionHandler(NullPointerException.class)
	//@ResponseBody
	public String nullHandle(NullPointerException e, Model model) {
		log.debug("요기로 왔나 ");
		//jsp 로보낼려면 
		model.addAttribute("exception", "예외 : "+e.getMessage());
		return "myError";
		
	}
	
	// 못찾겠다 핸들링! 
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseBody
	public String nullFoundHandle(NoHandlerFoundException e) {
		return "Not Found"+ e.getMessage();
	}
	// 별로 필요한 예외 처리들 등록
	
	// 못찾겠다 핸들링! 
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String exceptionHandle(Exception e) {
		return "관심없는 예외들"+ e.getMessage();
	}
}
