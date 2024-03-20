package egovframework.example.sample.web;

import java.io.IOException;
import java.util.Locale;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.sample.service.MemberService;
import egovframework.example.sample.service.MemberVO;

@Controller
public class LoginChk {
	String flag;
	@Resource(name = "MemberService")
	private MemberService memberService;
	
	@GetMapping("/login-fail")
	public String login_fail(Model model, HttpSession session, Locale locale, HttpServletRequest reques)
			throws Exception {
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		flag = "1";
		model.addAttribute("flag", flag);
		return "redirect:/home";
	}

	@GetMapping("/login-success")
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
	@GetMapping("/logout-After")
	public String logout(Locale locale, Model model, HttpSession session) throws IOException {
		session.invalidate();
		return "redirect:/home";
	}
}
