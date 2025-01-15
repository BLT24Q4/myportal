package himedia.myportal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import himedia.myportal.repositories.vo.BoardVo;
import himedia.myportal.services.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
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
	public String writeForm() {
		return "board/write";
	}
	
	@PostMapping("/write")
	public String writeAction(
			@ModelAttribute BoardVo vo) {
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
	
	
}











