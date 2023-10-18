package com.minu.sec;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("Welcome home! The client locale is {}.", locale);
		
		// 스프링 시큐리티에서 주로 사용하는 패스워드인코더
		// 보안상 디코딩(역산)은 지원안함!
		BCryptPasswordEncoder bCryPassEncoder = new BCryptPasswordEncoder();
		
		log.debug("angel:"+ bCryPassEncoder.encode("angel"));
		log.debug("angma:"+ bCryPassEncoder.encode("angma"));
		
		String angel = bCryPassEncoder.encode("angma");
		
		// 인코딩 되어있는 값이 같은지 아닌지 구별해줌 ( 리턴값  boolean)
		log.debug("isSame: "+bCryPassEncoder.matches("angma", angel));
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
