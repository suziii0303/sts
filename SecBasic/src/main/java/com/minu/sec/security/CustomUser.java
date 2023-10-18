package com.minu.sec.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.minu.sec.vo.MemberVO;

import lombok.Getter;

@Getter
public class CustomUser extends User {
	private static final long serialVersionUID = 1L;
	private MemberVO member;
	
	public CustomUser(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	// security에는 username, password, authorities 만 제공 하기 때문에 더 많은 정보를 추가 하기 위한 생성자
	// 시큐리티가 주는 정보가 3개밖에 안되서, 우리가 필요한 걸 더 붙임(멤버필드/속성으로)
	// 필요할 때 마다 id로 select하는 것 보다 자주쓰는 걸 그냥 붙여놓고 사용
	public CustomUser(MemberVO vo) {
		super(vo.getUserid(), vo.getUserpw(), 
			vo.getAuthList().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
		this.member = vo;		// 이 한줄이 핵심, 추가정복 붙여 놓기!
	}
}