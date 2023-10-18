package com.minu.sec.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.minu.sec.vo.AuthVO;
import com.minu.sec.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class) //JUnit5 테스트 프레임워크 사용 의미
@ContextConfiguration("classpath:config/spring/root-context.xml")
public class MemberTest {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MemberMapper memberMapper;
	
	@Test
	@DisplayName("UserRegister")
	@Disabled
	public void insertUsers() {
	   
		MemberVO member = new MemberVO();
		
		for(int i=1; i<=10; i++) {
			member.setUserid("jinsu" + i);
			member.setUsername("niceJinsu"+i);
			member.setUserpw(passwordEncoder.encode("jinsu"+i));
			assertEquals(1,memberMapper.insertMember(member));
		}
	}

	@Test
	@DisplayName("AuthRegister")
	@Disabled
	public void insertAuth() {	   
		AuthVO auth = new AuthVO();
		
		for(int i=1; i<=10; i++) {
			auth.setUserid("jinsu" + i);
			auth.setAuth("ROLE_SUKIL");
			assertEquals(1,memberMapper.insertAuth(auth));
		}
	}

	@Test
	@DisplayName("Select User")
	//@Disabled
	public void readUser() {
		
		MemberVO member = memberMapper.read("jinsu1");
		log.debug("check {}",member);
		member.getAuthList().forEach(authVO -> log.debug("{}",authVO));
	}
	
	
}