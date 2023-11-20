package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.MemberVO;

// 서비스 : 비지니스 로직을 구현하는 부분 => 사용자 요구사항에 맞는 동작을 구현  
// => 컨트롤러와 DAO를 연결하는 다리의 역할 


public interface MemberService {
	

	// 실제로 구현해야 하는 기능 정의
	
	//회원가입 동작 실행 
	public void memberJoin(MemberVO vo);
	
	// 로그인 체크 동작 
	public MemberVO memberLogin(MemberVO vo);
	
	// 회원 정보 조회 동작 
	public MemberVO memberInfo(String id);
	
	// 회원 정보 수정 동작 
	public void memberUpdate (MemberVO updateVO);
	
	//회원 정보 삭제 동작 
	public int memberDelete (MemberVO deleteVO);
	
	//회원 리스트 조회 
	public List<MemberVO> memberList();
	
	
}
