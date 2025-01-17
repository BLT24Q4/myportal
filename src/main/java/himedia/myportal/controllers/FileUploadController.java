package himedia.myportal.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import himedia.myportal.services.FileUploadService;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {
	private static final Logger logger = 
			LoggerFactory.getLogger(FileUploadController.class);
	@Autowired
	private FileUploadService fileUploadService;
	
	@GetMapping({"", "/form"})
	public String form() {
		return "fileupload/form";
	}
	
	@PostMapping("/upload")
	public String upload(
		@RequestParam("file1") MultipartFile file1,
		Model model) {
		if (file1 != null) {
			String saveFilename = fileUploadService.store(file1);
			model.addAttribute("imageFilename", saveFilename);
		}
		
		return "fileupload/result";
	}
}
