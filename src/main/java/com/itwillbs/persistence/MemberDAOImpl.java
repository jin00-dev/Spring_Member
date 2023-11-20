package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;

// @Repository: 스프링이 해당 객체를 DAO로 인식하도록 하는 어노테이션 

@Repository
public class MemberDAOImpl implements MemberDAO{

	// 디비 연결 객체 정보를 주입 받아오겠습니다.
	@Inject 
	private SqlSessionFactory sqlFactory;
	// => 디비 연결정보만 가지고 있음 (연결은 수동으로 해야 함)
	
	@Inject
	private SqlSession sqlsession;
	// => 디비 연결정보 있음 (연결, 해제 자동) 
	
	//memberMapper의 namespace 정보 저장 
	private static final String NAMESPACE
		= "com.itwillbs.mapper.MemberMapper";
	
	@Override
	public String getTime() {
		// 디비 연결
		SqlSession SqlSession = sqlFactory.openSession(); // connection 같은거.../ 수동으로 연결 한 것 
		// sql 작성 + pstmt 객체
		// sql 실행 
		String  time = SqlSession.selectOne("com.itwillbs.mapper.MemberMapper.getTime");
		
		return time;
	}


	@Override
	public void insertMember(MemberVO vo) {
		// 디비연결 - sqlSession 으로 완료 
		//sql 작성 (mapper에 가서) - memberMapper로 가면 있음
		//sql 실행 (mapper 호출) 
		//sqlsession.insert(sql 구문)
		//sqlsession.insert(sql구문, sql 전달 할 구문)
		sqlsession.insert(NAMESPACE + ".insertMember", vo);
		
	}


	@Override
	public MemberVO loginMember(MemberVO loginVO) {
		
		System.out.println("DAOImpl : loginMember() 실행");
		System.out.println("DAOImpl : 해당 sql구문 실행");
		
		MemberVO resultVO = sqlsession.selectOne(NAMESPACE + ".loginMember", loginVO);
		
		System.out.println("DAOImpl : 결과"+ resultVO);
		return resultVO;
	}
// 객체 하나로 저장될 때 그냥 객체 하나만 들고오는게 best!! 

	@Override // login2
	public MemberVO loginMember(String userid, String userpw) {
		System.out.println("DAOImpl : loginMember(userid, userpw) 호출");
		
//		MemberVO vo = new MemberVO();
//		vo.setUserid(userid);
//		vo.setUserpw(userpw);
//		
//		// 해당 sql 구문 호출 
//		sqlsession.selectOne(NAMESPACE+".loginMember",vo); 이렇게 하는건 의미가 없지
		
		// Map => 데이터 저장 시 key, value 쌍으로 값을 저장 / 데이터 넣는만큼 자동으로 늘어나  
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
//		paramMap.put("mapper에서 사용되는 이름#{}안에 들어간거", 값); 
//		왜 쓰나요? => 하나의 객체로 저장이 불가능 한 경우 사용해요. Ex) join 할때...!! 
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		
//		MemberVO vo = sqlsession.selectOne(NAMESPACE+".loginMember", paramMap);
//		return vo;  => vo 객체에 정보를 수정(추가로 저장, 변경) 작업을 포함 
//		vo.setUserid("아이디수정"); 같은 코드 추가 할 수도 있다는 의미~? 
		
		// vo 객체를 받은 그대로 리턴한다. 
		return sqlsession.selectOne(NAMESPACE+".loginMember", paramMap);
	}


	@Override
	public MemberVO getMember(String sessionUserid) {
		System.out.println("DAOImpl : getMember(String sessionUserid) 호출");
		
		return sqlsession.selectOne(NAMESPACE + ".getMember", sessionUserid);
	}


	@Override
	public void updateMember(MemberVO updateVO) {
		System.out.println("DAOImpl : updateMember(MemberVO updateVO) 호출");
		
		// sql 구문 호출 
		sqlsession.update(NAMESPACE + ".updateMember", updateVO);
		
		System.out.println("DAOImpl : 수정 완료 ");
	}


	@Override
	public int deleteMember(MemberVO delVO) {
		System.out.println("DAOImpl : 삭제 시작 ");
		
		//sql 구문 호출
		// 정상삭제 1, 삭제실패 0 => id는 PK 이니까!! 
		
		System.out.println("DAOImpl : 삭제 끝 ");
		
		return sqlsession.delete(NAMESPACE+ ".deleteMember", delVO);
	}


	@Override
	public List<MemberVO> getMemberList() {
		System.out.println("DAOImpl : getMemberList() 호출");
		
		return sqlsession.selectList(NAMESPACE + ".getMemberList"); //test로 보내는~~ 모든 리턴~~
	}



	
	

	
}//DAOImpl
