package himedia.myportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import himedia.myportal.repositories.vo.UserVo;
import himedia.myportal.services.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {
	@Autowired
	UserService userServiceImpl;
	
	@GetMapping({"", "/", "/join"})
	public String joinForm() {
		return "users/joinform";
	}
	
	@GetMapping("/joinsuccess")
	public String joinSuccess() {
		return "users/joinsuccess";
	}
	
	@PostMapping("/join")
	public String joinAction(@ModelAttribute UserVo userVo) {
		boolean success = userServiceImpl.join(userVo);
		
		if (!success) {
			System.err.println("회원가입 실패!");
			return "redirect:/users/join";
		} else {
			System.out.println("회원가입 성공!");
			return "redirect:/users/joinsuccess";
		}
	}
}



