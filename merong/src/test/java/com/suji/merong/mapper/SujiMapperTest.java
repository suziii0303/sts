package com.suji.merong.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.suji.merong.vo.SujiVO;

@SpringBootTest
public class SujiMapperTest {

	@Autowired
	private SujiMapper sujiMapper;

	@Test
    @DisplayName("예린 Insert 테스트")
	//골뱅이Disabled // 이미 테스트가 끝나서 더 안할거임
    public void insertTest() {
		
		SujiVO sujiVO =new SujiVO(); //현재는 이렇게 하는 게 좋은데 
		// 항상 좋은 것은 아님, 고객이 성능을 원하느냐? 가독성(유지보수)를 원하느냐?
	    for (int i =1; i<=10; i++) {
	        sujiVO.setSujiContent("눈안아퍼용?" + i);
	        sujiVO.setSujiFile("/merong/aaa"+i+".jpg");
	        sujiVO.setSujiName("소연님등장"+ i);

	        assertEquals(1, sujiMapper.insertSuji(sujiVO));
	    }
	}
	
	@Test
	@DisplayName("안예린Delete")
	@Disabled
	public void deleteTest() {
		SujiVO sujiVO = new SujiVO();
		
		sujiVO.setSujiNum(17);
		assertEquals(1, sujiMapper.deleteSuji(sujiVO));
	}
}