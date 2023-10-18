package com.minu.merong.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.minu.merong.vo.YerinVO;

/*
 * 테스트는 spring 버전 또는 boot냐 아니냐 여부에 따라 어노태이션이 달라진다.
 */

//Junit5 사용
@ExtendWith(SpringExtension.class)
//Context설정파일 지정 classpath를 사용해도 됨
@ContextConfiguration("classpath:config/spring/database-context.xml")
public class YerinTest {
	
	@Autowired
	private Yerin yerin;
	
	@Test
	@DisplayName("merong")
	public void insertTest() {
		
		YerinVO yerinVO;
		for(int i=3;i<=103; i++) {
			yerinVO = new YerinVO();
			yerinVO.setTitle("merong"+i);
			yerinVO.setScont("content"+i);
			yerinVO.setSname("gidukOta"+i);
			assertEquals(1, yerin.insertData(yerinVO));
			
		}
		
		
		
	}
}
