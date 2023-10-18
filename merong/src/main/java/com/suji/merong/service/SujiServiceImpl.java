package com.suji.merong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suji.merong.mapper.SujiMapper;
import com.suji.merong.vo.SujiVO;

@Service
public class SujiServiceImpl implements SujiService{

	@Autowired	// 서비스는 매퍼를 불러야 함!
	private SujiMapper sujiMapper;
	
	@Override
	public List<SujiVO> listSuji() {
		// TODO Auto-generated method stub
		return sujiMapper.listSuji();
	}

	@Override
	public SujiVO getSuji(SujiVO sujiVO) {
		// TODO Auto-generated method stub
		return sujiMapper.getSuji(sujiVO);
	}

	@Override
	public int insertSuji(SujiVO sujiVO) {
		// TODO Auto-generated method stub
		return sujiMapper.insertSuji(sujiVO);
	}

	@Override
	public int updateSuji(SujiVO sujiVO) {
		// TODO Auto-generated method stub
		return sujiMapper.updateSuji(sujiVO);
	}

	@Override
	public int deleteSuji(SujiVO sujiVO) {
		// TODO Auto-generated method stub
		return sujiMapper.deleteSuji(sujiVO);
	}
	

	
}
