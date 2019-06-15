package it.uniroma3.siw.photo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw.photo.models.Order;
import it.uniroma3.siw.photo.services.PhotoService;

@Controller
public class GalleryController {
    @Autowired
    private PhotoService photoService;
    
    @RequestMapping("/galleryByPhotos")
    public String galleryByPhotos(Model model) {
        model.addAttribute("photos", photoService.findAll());
        model.addAttribute("order", new Order());
        return "galleryByPhotos.html";
    }
}