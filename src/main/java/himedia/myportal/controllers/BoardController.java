package himedia.myportal.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import himedia.myportal.repositories.vo.BoardVo;
import himedia.myportal.repositories.vo.UserVo;
import himedia.myportal.services.BoardService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {
	private static final Logger logger 
		= LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardServiceImpl;
	
	@RequestMapping({"", "/", "/list"})
	public String list(Model model) {
		List<BoardVo> list = 
				boardServiceImpl.getList();
		model.addAttribute("list", list);
		
		return "board/list";
	}
	
	@GetMapping("/write")
	public String writeForm(HttpSession session) {
		//	로그인 하지 않은 사용자는 홈페이지로 리다이렉트
		/*
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if (authUser == null) {
//			System.err.println("로그인 사용자 아님!");
			logger.debug("로그인 사용자 아님");
			return "redirect:/";
		}
		*/
		return "board/write";
	}
	
	@PostMapping("/write")
	public String writeAction(
			@ModelAttribute BoardVo vo,
			HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		/*
		if (authUser == null) {
//			System.err.println("로그인 사용자 아님!");
			logger.debug("로그인 사용자 아님");
			return "redirect:/";
		}
		*/
		
		vo.setUserNo(authUser.getNo());
		boardServiceImpl.write(vo);
		
		return "redirect:/board";
	}
	
	//	게시물 조회
	@GetMapping("/{no}")
	public String view(@PathVariable("no") Integer no,
			Model model) {
		BoardVo vo = boardServiceImpl.getContent(no);
		model.addAttribute("vo", vo);
		return "board/view";
	}
	
	@GetMapping("/{no}/modify")
	public String modifyForm(
		@PathVariable("no") Integer no,
		Model model, 
		HttpSession session) {
		/*
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if (authUser == null) {
//			System.err.println("로그인 사용자 아님!");
			logger.debug("로그인 사용자 아님");
			return "redirect:/";
		}
		*/
		BoardVo vo = boardServiceImpl.getContent(no);
		model.addAttribute("vo", vo);
		
		return "board/modify";
	}
	
	@PostMapping("/modify")
	public String modify(@ModelAttribute BoardVo updateVo,
			HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		/*
		if (authUser == null) {
			System.err.println("로그인 사용자 아님!");
			return "redirect:/";
		}
		*/
		
		BoardVo vo = boardServiceImpl.getContent(updateVo.getNo());
		
		if (vo.getUserNo() != authUser.getNo()) {
			System.err.println("게시물 작성자 아님!");
			return "redirect:/board";
		}
		
		vo.setTitle(updateVo.getTitle());
		vo.setContent(updateVo.getContent());
		
		boolean success = boardServiceImpl.update(vo);
		
		return "redirect:/board";
	}
	
	@GetMapping("/{no}/delete")
	public String deleteAction(@PathVariable("no") Integer no,
			HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		/*
		if (authUser == null) {
//			System.err.println("로그인 사용자 아님!");
			logger.debug("로그인 사용자 아님");
			return "redirect:/";
		}
		*/
		boardServiceImpl.deleteByNoAndUserNo(no, authUser.getNo());
		
		return "redirect:/board/list";
	}
}











