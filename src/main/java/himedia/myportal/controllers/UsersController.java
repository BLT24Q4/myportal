package himedia.myportal.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import himedia.myportal.repositories.vo.UserVo;
import himedia.myportal.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {
	private static final Logger logger =
			LoggerFactory.getLogger(UsersController.class);
	@Autowired
	UserService userServiceImpl;
	
	@GetMapping({"", "/", "/join"})
	public String joinForm(@ModelAttribute UserVo userVo) {
		userVo.setGender("M");	//	기본값 설정
		return "users/joinform";
	}
	
	@GetMapping("/joinsuccess")
	public String joinSuccess() {
		return "users/joinsuccess";
	}
	
	@PostMapping("/join")
	public String joinAction(
			@ModelAttribute @Valid UserVo userVo,	
			BindingResult result,	//	검증 결과
			Model model
			) {
		logger.debug("회원 가입 액션");
		logger.debug("회원 가입 정보:" + userVo);
		
		if (result.hasErrors()) {
			//	검증이 실패했다면 
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError e: list) {
				logger.error("검증 에러:" + e);
			}
			//	에러 정보를 모델에 실어서 전송
			model.addAllAttributes(result.getModel());
			return "users/joinform";
			
		}
		boolean success = userServiceImpl.join(userVo);
		
		if (!success) {
			System.err.println("회원가입 실패!");
			return "redirect:/users/join";
		} else {
			System.out.println("회원가입 성공!");
			return "redirect:/users/joinsuccess";
		}
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "users/loginform";
	}
	
	@PostMapping("/login")
	public String loginAction(
			@RequestParam(value="email",
							required=false)
			String email, 
			@RequestParam(value="password",
							required=false)
			String password,
			HttpSession session) {
		if (email.length() == 0 || 
				password.length() == 0) {
			System.err.println("email 혹은 password가 전송되지 않음");
			return "redirect:/users/login";			
		}
		
		UserVo authUser = userServiceImpl.getUser(email, password);
		
		if (authUser != null) {
			//	세션에 사용자 정보 추가
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		} else {
			return "redirect:/users/login";
		}
	}
	
	/*
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	-> 로그아웃 인터셉터로 위임
	*/
	
	@ResponseBody
	@GetMapping("/checkEmail")
	//	JSON API
	public Object exists(@RequestParam(value="email",
			required=false,
			defaultValue="") String email) {
		//	{ "result": "success", "data": true }
		UserVo vo = userServiceImpl.getUser(email);
		boolean exists = vo != null ? true: false;
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", "success");
		map.put("exists", exists);
		
		return map;
	}
}














