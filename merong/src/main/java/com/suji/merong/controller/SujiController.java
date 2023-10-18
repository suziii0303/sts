package com.suji.merong.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.suji.merong.service.SujiService;
import com.suji.merong.vo.SujiVO;
import com.suji.merong.vo.TestVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SujiController {
	
	@Autowired			// 서비스를 호출하기 위함
	private SujiService sujiService;
	
	@GetMapping("/rest/suji")
	//골뱅이 ResponseBody  
	public List<SujiVO> getList() {
		return sujiService.listSuji();
	}
	
	@GetMapping("/rest/suji/{num}")
	public SujiVO getSuji(@PathVariable int num){
		SujiVO sujiVO = new SujiVO();
		sujiVO.setSujiNum(num);
		return sujiService.getSuji(sujiVO);
	}
	/*
    @PostMapping("/rest/suji") public String insSuji(@RequestBody SujiVO sujiVO)
    { return Integer.toString(sujiService.insertSuji(sujiVO)); }
    */

	@PostMapping("/rest/suji")
	public String insSuji(SujiVO sujiVO) throws IllegalStateException, IOException {
		
		// DB에 저장할 파일 경로
		String sujiFile= "/kmerong/"+sujiVO.getSujiFile2().getOriginalFilename();
		sujiVO.setSujiFile(sujiFile);
		
		log.debug("ck"+sujiVO);
		
		// 실제 저장할 물리적 경로
		String savePath = "d:/merong/"+sujiVO.getSujiFile2().getOriginalFilename();;
		sujiVO.getSujiFile2().transferTo(new File(savePath));		
		
		return Integer.toString(sujiService.insertSuji(sujiVO));
	}
	

	@PutMapping("/rest/suji")
	public String upSuji(@RequestBody SujiVO sujiVO) {
	   return Integer.toString(sujiService.updateSuji(sujiVO));
	}
	
	@DeleteMapping("/rest/suji/{num}")
	public String delSuji(@PathVariable int num) {
		SujiVO sujiVO = new SujiVO();
		sujiVO.setSujiNum(num);
		return Integer.toString(sujiService.deleteSuji(sujiVO));
	}
	@PostMapping("/rest/suji2")
	public String insSuji(MultipartFile hynhak) throws IllegalStateException, IOException {	// 골뱅이RequestBody 사용하지 않음에 주의 !!
		log.debug("fileName:"+ hynhak.getOriginalFilename());
		log.debug("size:"+hynhak.getSize());
		
		//파일 저장
		String savePath = "d:/merong/"+hynhak.getOriginalFilename();
		hynhak.transferTo(new File(savePath));
		
		// savePath에 해당하는 웹 경로
		String webURL ="/kmerong/" +hynhak.getOriginalFilename();
		
		return webURL;
	}
	
	//@RepuestPart를 써야 하는 경우가 잘 생기지는 않음 !
	//JSON문자열이 일부 자바객체로 변환이 불완전하게 될 때 사용
	
	@PostMapping("/rest/jinsu")
	public String testJinsu(@RequestPart("test") TestVO test, 
							@RequestPart("jinsuFile") MultipartFile mFile){
		
		log.debug("check:"+test);
		log.debug("file:"+mFile);
		return "진수";
	}
	
}
