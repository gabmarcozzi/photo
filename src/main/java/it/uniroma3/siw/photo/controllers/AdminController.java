package it.uniroma3.siw.photo.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.photo.exceptions.ServiceException;
import it.uniroma3.siw.photo.models.Album;
import it.uniroma3.siw.photo.models.Photo;
import it.uniroma3.siw.photo.models.Photographer;
import it.uniroma3.siw.photo.services.PhotographerService;

@Controller
public class AdminController {

	@Autowired
	private PhotographerService ps;
	
	@RequestMapping("/admin")
	public String home(Model model) {
		return "/admin/home.html";
	}
	
	@RequestMapping("/admin/uploadRequest")
	public String uploadRequest(Model model) {
		model.addAttribute("photographer", new Photographer());
		model.addAttribute("album", new Album());
		model.addAttribute("photo", new Photo());
		return "/admin/photoForm.html";
	}
	
	@RequestMapping(value = "/admin/submitPhotoForm", method = RequestMethod.POST)
	public String submitPhotoForm(
			@RequestParam String ph_name, 
			@RequestParam String al_name,
			@RequestParam String photo_name,
			@RequestParam("file") MultipartFile file,
			Model model) {
		
		// TODO: validation
		// TODO: controlli in fase di salvataggio (se il fotografo già esiste...)
		
		Photographer ph = new Photographer(ph_name);
		Album al = new Album(al_name, ph);
		Photo photo = new Photo(photo_name, al);
		
		try {
			photo.setImage(file.getBytes());
			ps.save(ph);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		
		return "/admin/home.html";
	}
	
}
