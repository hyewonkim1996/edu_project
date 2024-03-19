package egovframework.example.sample.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.example.sample.service.MemberService;
import egovframework.example.sample.service.MemberVO;
import egovframework.example.sample.service.MyCourseService;
import egovframework.example.sample.service.MyCourseVO;
import egovframework.example.sample.service.PageVO;

@Controller
public class MemberController {
	String flag;
	@Resource(name = "MemberService")
	private MemberService memberService;
	@Resource(name = "MyCourseService")
	private MyCourseService mycourseService;

	@RequestMapping(value = "/joinDone", method = RequestMethod.POST)
	public String joindone(Locale locale, @ModelAttribute MemberVO mvo) throws IOException {
		memberService.insertOne(mvo);
		return "redirect:/home";
	}

	@RequestMapping(value = "/login_fail", method = RequestMethod.GET)
	public String login_fail(Model model, HttpSession session, Locale locale, HttpServletRequest reques)
			throws Exception {
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		flag = "1";
		model.addAttribute("flag", flag);
		return "redirect:/home";
	}

	@RequestMapping(value = "/login_success", method = RequestMethod.GET)
	public String login(Model model, HttpSession session) {
		// provider의 검증 통과한 authentication 객체 불러옴
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userid = "";// 아이디
		String name = "";
		Boolean enabled = false;
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetails) {
			userid = ((UserDetails) principal).getUsername();
			System.out.println("인증 객체에서 가져온 아이디 :" + userid);
			// 인증이 처리되는 로직(아이디,암호를 스프링시큐리티 던져주고 인증은 스프링에서 알아서 해줌.)
			enabled = ((UserDetails) principal).isEnabled();
			if (userid != null) {
				name = memberService.selectOneforModify(userid).getM_name();
			}
		}
		if (enabled) { // 인증처리가 완료된 사용자
			// 로그인 세션 저장
			session.setAttribute("userid", userid);// 사용자아이디
			session.setAttribute("username", name);// 사용자아이디
			System.out.println(userid + " 세션등록");
		}
		return "redirect:/home";
	}

	// 로그아웃
	@RequestMapping(value = "/logout_After", method = RequestMethod.GET)
	public String logout(Locale locale, Model model, HttpSession session) throws IOException {
		session.invalidate();
		return "redirect:/home";
	}

	// 회원 탈퇴
	@RequestMapping(value = "/memberDelete", method = RequestMethod.GET)
	public String memberdel(Locale locale, Model model, @RequestParam("m_id") String id, HttpSession session)
			throws Exception {
		session.removeAttribute("userid");
		session.removeAttribute("username");
		List<MyCourseVO> clist = mycourseService.selectMyCourse(id);
		String temp = "";
		if (clist != null) { // 탈퇴하려는 회원이 이전에 신청한 강좌가 있다면
			for (int i = 0; i < clist.size(); i++) {
				temp = clist.get(i).getC_num().toString(); // i번째 강좌번호 temp에 저장
				mycourseService.updateCntMinus(temp);// 해당강좌의 신청인원 -1
			}
		}
		memberService.deleteOne(id);
		return "redirect:/home";
	}

	// 회원정보 수정페이지 열기
	@RequestMapping(value = "/memberModify", method = RequestMethod.GET)
	public String modInfo(Locale locale, Model model, HttpSession session) {
		System.out.println("세션 확인:" + session.getAttribute("userid"));
		Object id = session.getAttribute("userid");
		// id = id.toString();
		model.addAttribute("member", memberService.selectOneforModify(id.toString()));
		return "sample/memberModify";
	}

}
