package it.uniroma3.siw.photo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	@RequestMapping("/admin")
	public String home(Model model) {
		return "/admin/home.html";
	}
	
	@RequestMapping("/admin/uploadRequest")
	public String uploadRequest(Model model) {
		return "/admin/photoForm.html";
	}
	
}
