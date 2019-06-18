package it.uniroma3.siw.photo.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.photo.exceptions.ServiceException;
import it.uniroma3.siw.photo.models.Album;
import it.uniroma3.siw.photo.models.Photo;
import it.uniroma3.siw.photo.models.Photographer;
import it.uniroma3.siw.photo.models.UploadPhotoForm;
import it.uniroma3.siw.photo.services.AlbumService;
import it.uniroma3.siw.photo.services.PhotoService;
import it.uniroma3.siw.photo.services.PhotographerService;
import it.uniroma3.siw.photo.validators.UploadValidator;

@Controller
public class AdminController {

	@Autowired
	private PhotographerService photographerService;

	@Autowired
	private AlbumService albumService;

	@Autowired
	private PhotoService photoService;

	@Autowired
	private UploadValidator uv;


	@RequestMapping("admin")
	public String home(Model model) {
		return "admin/home.html";
	}

	@RequestMapping("admin/uploadRequest")
	public String uploadRequest(Model model) {
		model.addAttribute("upf", new UploadPhotoForm());
		return "admin/photoForm.html";
	}

	@RequestMapping(value = "admin/submitPhotoForm", method = RequestMethod.POST)
	public String submitPhotoForm(
			@Valid @ModelAttribute("upf") UploadPhotoForm upf,
			@RequestParam("file") MultipartFile file,
			BindingResult br,
			Model model) {

		// Validate form's data
		uv.validate(upf, br);

		// In case of errors, go back to the form's page
		if(br.hasErrors()) {
			return "admin/photoForm.html";
		}

		try {
			Photographer photographer = photographerService.findByName(upf.getPhotographerName());

			if(photographer == null) {
				photographer = new Photographer(upf.getPhotographerName());
				Album album = new Album(upf.getAlbumName(), photographer);
				Photo photo = new Photo(upf.getPhotoName(), album);
				try { photo.setImage(file.getBytes()); } catch (IOException e) { e.printStackTrace(); }
				photographerService.save(photographer);
			}
			else {
				Album album = albumService.findByName(upf.getAlbumName());

				if(album == null) {
					album = new Album(upf.getAlbumName(), photographer);
					Photo photo = new Photo(upf.getPhotoName(), album);
					try { photo.setImage(file.getBytes()); } catch (IOException e) { e.printStackTrace(); }
					albumService.save(album);
				}
				else {
					Photo photo = new Photo(upf.getPhotoName(), album);
					try { photo.setImage(file.getBytes()); } catch (IOException e) { e.printStackTrace(); }
					photoService.save(photo);
				}
			}
		} 
		catch (ServiceException e) {
			e.printStackTrace();
		}
		
		//		
		//		try {
		//			if(photographerService.existsByName(upf.getPhotographerName())) {
		//				Photographer photographer = photographerService
		//				if(albumService.exists(album)) {
		//					photoService.save(photo);
		//				}
		//				else {
		//					albumService.save(album);
		//				}
		//			}
		//			else {
		//				photographerService.save(photographer);
		//			}
		//		} catch (ServiceException e) {
		//			e.printStackTrace();
		//		}

		return "admin/home.html";
	}

}
