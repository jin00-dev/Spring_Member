package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;
import com.itwillbs.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	//MemberDAO 객체 필요하다 (의존한다) 
//	@Inject
//	private MemberDAO mdao; => 디비 처리 담당 MemberService 객체를 주입 
	@Inject
	private MemberService mService;
	
	// http://localhost:8088/controller/member/insertMember (X)
	// http://localhost:8088/member/insertMember (O)
	// http://localhost:8088/member/MemberJoin (O)
	
	//http://localhost:8088/member/memberJoin(x)
		//http://localhost:8088/member/insert
		@RequestMapping(value = "/insert",method = RequestMethod.GET)
		public String insertMemberGET() {
			logger.debug(" insertMemberGET() 호출 ");
			
			logger.debug(" 연결된 view 페이지 호출 (/views/member/Memberjoin.jsp) ");
			
			//      /폴더명/뷰페이지 이름
			return "/member/MemberJoin";
		}
		
		//http://localhost:8088/member/memberJoinAction (x)
		//http://localhost:8088/member/insert
		
		@RequestMapping(value = "/insert",method = RequestMethod.POST)
		public String insertMemberPOST(/* @ModelAttribute */ MemberVO vo) {
			logger.debug(" insertMemberPOST() 호출 ");
			// 전달정보 저장(회원가입 정보)
			logger.debug("vo "+vo);
			
			// ServiceImpl -> DAOImpl -> DB에 정보 저장
			//mdao.insertMember(vo);
			mService.memberJoin(vo);
			logger.debug("회원가입 완료");
			
			// 로그인 페이지로 이동(redirect)		
			return "redirect:/member/login";
		}
		
		//http://localhost:8088/member/login
		// 로그인 GET => 사용자가 정보 입력 받을 수 있는~??  
		@RequestMapping(value = "/login", method = RequestMethod.GET)
		public String loginGET() {
			logger.debug("loginGET() 호출");
			logger.debug("연결된 뷰 페이지로 이동 ");
			return "/member/memberLogin";
		}
		// 로그인 POST => 가져간거 DB에서 확인~?? 
		@RequestMapping(value = "/login", method = RequestMethod.POST)
		public String loginPOST(MemberVO vo, HttpSession session) {
			logger.debug("loginPOST() 호출");
			logger.debug("연결된 뷰 페이지로 이동 ");
			// 전달정보 저장 (ID, PW) => MemberVO vo (매개변수 사용)
			logger.debug("vo : "+vo);
			// DB에서 id,pw 맞는지 확인 but DB 직접 호출 X 서비스에서 DAO 불러서 이동 
			MemberVO resultVO = mService.memberLogin(vo);
			logger.debug("resultVO : " + resultVO);
			//로그인 처리 결과에 따른 페이지 이동
			 if(resultVO == null) {
				 //로그인 실패 -> 로그인 페이지로 이동 
				 return "redirect:/member/login";
			 }
			 
			 // 로그인 성공 
			 // 세션에 로그인 아이디 저장 => HttpSession 사용 (이전 JSP view 페이지에서 전달?) 
			 // jsp 에는 내장 객체 9개...! 기억하기... 여기서 전달하는것..! 
			 session.setAttribute("id", resultVO.getUserid()); // main 으로 갈 때 session 받아서 갈 수있음 
			 
			return "redirect:/member/main";
		}
		
		//메인 페이지 호출
		@RequestMapping(value = "/main",method = RequestMethod.GET)
		public String mainGET() {
			logger.debug("mainGET() 호출");
			
			return "/member/memberMain";
			
		}
		
		//로그아웃 처리 
		@RequestMapping(value = "/logout", method = {RequestMethod.GET,RequestMethod.POST}) // 방식 상관없이 실행
		public String logoutGET(HttpSession session) {
			logger.debug("logoutGET() 호출");
			
			// 로그아웃 처리 => 세션의 정보를 초기화
			session.invalidate(); // 세션 초기화 하는 것!!!! 
			
			// 메인 페이지로 이동 
			
			return "redirect:/member/main";			
		}
		
		//회원정보 조회
		@RequestMapping(value = "/info", method = RequestMethod.GET)
		public String infoGET(HttpSession session, Model model) {
			logger.debug("infoGET() 호출");
			// 사용자의 아이디 정보 => 세션 영역에 있는 정보 가져오기 
			String id = (String) session.getAttribute("id");
			
			//서비스 -> DB에 저장된 회원정보 
			MemberVO resultVO = mService.memberInfo(id);
			
			//연결된 View페이지에 출력 = resultVO을 연결된 View에 출력 
			//=> Controller 정보를 view페이지로 전달  
			// Model!! (컨트롤러와 뷰 페이지를 왔다 갔다 하며 정보 옮기는!)객체 사용 
			model.addAttribute("vo",resultVO); // 정보를 모델이라는 객체에 담아서 보내겠다~! 
			
			return "/member/memberInfo";
		}
		
		//회원정보 수정 - GET (update 실행시 보여지는 view 있어야 하는..?) / id,pw 기반으로 name, email 수정 
		@RequestMapping(value = "/update", method = RequestMethod.GET)
		public String updateGET(HttpSession session, Model model) {
			logger.debug("updateGET() 호출");
			
			//로그인 한 회원 아이디 조회 
			String id = (String) session.getAttribute("id");
			
			//서비스 -> DAO 접근 : 회원정보 조회 동작 호출 
			MemberVO resultVO = mService.memberInfo(id);
			
			// 정보를 view페이지로 전달하기 위해서 model 객체 사용
			model.addAttribute("vo", resultVO); 
//			model.addAttribute(mService.memberInfo(id)); // 위 아래 같은 의미 => but 이름은 memberVO(리턴타입의 클래스 첫 글자 소문자 변경)
			
			// 이름을 지정했는지 안했는지 차이 + 아래 형태의 코드를 더 많이 쓸 것 
			
			// /member/memberUpdate.jsp
			
			
			return "/member/memberUpdate";
		}
		
		//회원정보 수정 - POST
		@RequestMapping(value = "/update", method = RequestMethod.POST)
		public String updatePOST(MemberVO vo) {
			logger.debug("updatePOST() 호출");
			// 수정 정보를 받아와서 DB에서 수정 하면 됨!! 
			// 1. 수정 정보 저장 (파라메터 =>submit 했으니까) 
				logger.debug("vo : "+ vo);
				
			// 2. 서비스 -> DAO 회원정보 수정 동작 호출 
				mService.memberUpdate(vo);
				
			// 3. 메인 페이지로 이동 
			return "redirect:/member/main";
		}
	
		// 회원 정보 탈퇴 -GET 
		@RequestMapping(value = "/delete", method = RequestMethod.GET)
		public String deleteGET() {
			logger.debug("deleteGET() 호출");
			//로그인 제어 생략
			
			return "/member/memberDelete"; 
		}
	
		// 회원 정보 탈퇴 -POST
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		public String deletePOST(MemberVO vo,HttpSession session) {
			logger.debug("deletePOST() 호출");
			//전달정보 저장 (아이디, 비밀번호)
			logger.debug("vo : " + vo);

			//서비스 -> DAO 회원정보 탈퇴 메서드
			int result = mService.memberDelete(vo);
			//결과에 따른 페이지 이동
			if(result == 1) {
				// 삭제 성공 + 로그인 세션 제거
				session.invalidate();
				return "redirect:/member/main";
			}
			// 삭제 실패(result == 0) 
			return "redirect:/member/delete";
		}
		
		//회원 목록 조회 GET
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public String listGET(HttpSession session,Model model) {
			logger.debug("listGET() 호출");
			// 관리자가 아닌 경우 로그인 페이지로 이동 
				String id = (String) session.getAttribute("id");
				if(id == null || !id.equals("admin")) { // 값 비교 할 때는 null 비교-> admin 비교
					return "redirect:/member/main";
				}
			// 서비스 -> DB 회원목록 조회
				List<MemberVO> memberList = mService.memberList();
				// memberList에 저장해서 view로 전달 / 수정될 수 있는 가능성 내포 
				// Ex) memberList.add(dto); 리턴의 결과를 수정 할 수 있다. 
				
			//List로 전달 된 회원정보를 view페이지로 전달, 출력 
			//=> Model 객체 사용 해서 전달해야 함
				model.addAttribute("memberList", memberList);
				
//				model.addAttribute("memberList", mService.memberList()); 이것도 가능 + 이제는 이렇게 쓸 필요가 있다.
				// DB 에서 받은거 그대로 view로 넘기겠다. (수정 X) 
				
			
			return "/member/memberList";
		}
	
	
	
	
}// MemberController
