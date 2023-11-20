package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Inject
	private MemberDAO mdao;
	
	@Override
	public void memberJoin(MemberVO vo) {
		logger.debug("DAO에 있는 회원가입 메서드 호출");
		mdao.insertMember(vo);
	}

	@Override
	public MemberVO memberLogin(MemberVO vo) {
		logger.debug("컨트롤러 호출로 DAO 메서드를 호출하겠다.");
		MemberVO resultVO =  mdao.loginMember(vo); 
		
		logger.debug("DAO의 처리결과를 컨트롤러로 전달");
		
		return resultVO;
	}

	@Override
	public MemberVO memberInfo(String id) {
		logger.debug("memberInfo(String id) 호출");
		
		//DB동작 (DAO) 중에서 회원정보 조회 (컨트롤러로 받은 정보 전달)  
		MemberVO resultVO = mdao.getMember(id);
		
		return resultVO;
		//return mdao.getMember(id); 위와 같은 의미 
	}

	@Override
	public void memberUpdate(MemberVO updateVO) {
		logger.debug("memberUpdate(MemberVO updateVO) 호출");
		
		mdao.updateMember(updateVO);
		
	}

	@Override
	public int memberDelete(MemberVO deleteVO) {
		logger.debug("memberDelete(MemberVO deleteVO) 호출");
		
		//DAO 동작 부르기 
		int result = mdao.deleteMember(deleteVO);
		
		return result;
	}

	@Override
	public List<MemberVO> memberList() {
		logger.debug("memberList() 호출");
		
		List<MemberVO> memberList = mdao.getMemberList();
		
		return memberList;
	}


	



	

	

}//MemberServiceImpl
