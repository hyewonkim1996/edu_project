package egovframework.example.sample.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.example.sample.service.MemberService;
import egovframework.example.sample.service.MemberVO;
import egovframework.example.sample.service.MyCourseService;
import egovframework.example.sample.service.MyCourseVO;
import egovframework.example.sample.service.PageVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Inject
	BCryptPasswordEncoder encoder;
	@Resource(name = "MemberService")
	private MemberService memberService;
	@Resource(name = "MyCourseService")
	private MyCourseService mycourseService;
	
	//회원가입 메소드
		@PostMapping("")
		@ResponseBody
		public String join(@RequestBody MemberVO mvo) {//ajax에서 전송한 data MemberVO에 매핑
			//비밀번호 암호화
			String pw = mvo.getM_pw();
			System.out.println("원래 비번 :"+pw);
			pw = encoder.encode(pw);
			mvo.setM_pw(pw);
			System.out.println("암호화된 비번 :"+pw);
			if(mvo.getM_id()!=null) {//MemberVO 객체 id가 null이 아니다 = 매핑 성공
				memberService.insertOne(mvo); //회원 insert 쿼리 진행
				return "success";
			}else {
				return "mvo is null";
			}
		}
		
		//회원 조회 메소드
		@GetMapping("/{userid}")//{userid} = ajax url에 붙인 값(ajax에서 쓴것과 이름 같아야 매핑됨)
		@ResponseBody
		public String loginPreChk(@PathVariable String userid) {//url에 붙인 값(ajax에서 쓴것과 이름 같아야 매핑됨)
			MemberVO returnmvo = memberService.selectOneforModify(userid); //회원 조회 쿼리 실행
			if(returnmvo!=null) {//MemberVO 객체 아이디가 null이 아님 = 디비에 존재하므로 이미 가입된 아이디
				return "login"; //ajax에 전달될 result
			}else {//MemberVO 객체 아이디가 null = 디비에 존재하지 않므로 가입되지 않은 아이디
				return "signup";//ajax에 전달될 result
			}
		}
		
		//회원정보 수정 메소드
		@PutMapping("")
		@ResponseBody
		public String memberModify(@RequestBody MemberVO mvo) {//ajax에서 전송한 data MemberVO에 매핑
			if(mvo.getM_id()!=null) {//MemberVO 객체 id가 null이 아니다 = 매핑 성공
				if(mvo.getM_pw()==null) {
					mvo.setM_pw("sns");
				}
				String pw = mvo.getM_pw();
				System.out.println("원래 비번 :"+pw);
				pw = encoder.encode(pw);
				mvo.setM_pw(pw);
				memberService.updateOne(mvo); //회원 insert 쿼리 진행
				return "success";
			}else {
				return "mvo is null";
			}
		}
		
	// 회원 탈퇴
//	@RequestMapping(value = "/memberDelete", method = RequestMethod.GET)
//	public String memberdel(Locale locale, Model model, @RequestParam("m_id") String id, HttpSession session)
//			throws Exception {
//		session.removeAttribute("userid");
//		session.removeAttribute("username");
//		List<MyCourseVO> clist = mycourseService.selectMyCourse(id);
//		String temp = "";
//		if (clist != null) { // 탈퇴하려는 회원이 이전에 신청한 강좌가 있다면
//			for (int i = 0; i < clist.size(); i++) {
//				temp = clist.get(i).getC_num().toString(); // i번째 강좌번호 temp에 저장
//				mycourseService.updateCntMinus(temp);// 해당강좌의 신청인원 -1
//			}
//		}
//		memberService.deleteOne(id);
//		return "redirect:/home";
//	}
	
	@DeleteMapping("")
	@ResponseBody
    public String deleteMember(HttpSession session) {
        // 회원 삭제 로직
		Object id = session.getAttribute("userid");
        boolean deleted = memberService.deleteOne(id.toString());
        System.out.println(deleted);
        if (deleted) {
            // 세션에서 사용자 정보 삭제
            session.removeAttribute("userid");
            session.removeAttribute("username");
            System.out.println("삭제 완료");
            return "success";
        } else {
            return "fail";
        }
    }

	// 회원정보 수정페이지 열기
	@GetMapping("")
	public String modInfo(Locale locale, Model model, HttpSession session) {
		System.out.println("세션 확인:" + session.getAttribute("userid"));
		Object id = session.getAttribute("userid");
		// id = id.toString();
		model.addAttribute("member", memberService.selectOneforModify(id.toString()));
		return "sample/memberModify";
	}

}
