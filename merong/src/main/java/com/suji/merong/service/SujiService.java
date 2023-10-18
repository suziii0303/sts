package com.suji.merong.service;

import java.util.List;

import com.suji.merong.vo.SujiVO;

public interface SujiService {
	
	//읽기 메소드
	List<SujiVO> listSuji();
	SujiVO getSuji(SujiVO sujiVO);
	
	//쓰기메소드(mutation(변경/변형) 메소드
	int insertSuji(SujiVO sujiVO);
	int updateSuji(SujiVO sujiVO);
	int deleteSuji(SujiVO sujiVO);
	

}
