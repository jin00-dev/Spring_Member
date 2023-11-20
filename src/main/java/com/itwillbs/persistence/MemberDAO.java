package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.MemberVO;

public interface MemberDAO {

	// 디비 서버 시간정보 조회 기능 
	public String getTime();
	
	
	// 회원가입 기능 
	public void insertMember(MemberVO vo);
	
	//로그인 기능 
	public MemberVO loginMember(MemberVO loginVO); // 객체 사용
	
	//로그인 기능 2 
	public MemberVO loginMember(String userid, String userpw); // 직접 데이터 전달 
	
	//회원정보 조회
	public MemberVO getMember(String sessionUserid);
	
	//회원정보 수정 
	public void updateMember(MemberVO changeVO);
	
	//회원정보 삭제 
	public int deleteMember(MemberVO delVO);
	
	//회원목록 조회 
	public List<MemberVO> getMemberList();
	
	
	
	
}
