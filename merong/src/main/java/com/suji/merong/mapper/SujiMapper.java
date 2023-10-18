package com.suji.merong.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.suji.merong.vo.SujiVO;

@Mapper
public interface SujiMapper {
	 // Get List
	  List<SujiVO> listSuji();
	  // Get One
	  SujiVO getSuji(SujiVO sujiVO);
	  // insert
	  int  insertSuji(SujiVO sujiVO);
	  // update
	  int  updateSuji(SujiVO sujiVO);
	  // delete
	  int  deleteSuji(SujiVO sujiVO);
	int getCurrentSujiNum();
}
