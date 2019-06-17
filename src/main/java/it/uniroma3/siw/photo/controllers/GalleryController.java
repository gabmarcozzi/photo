package it.uniroma3.siw.photo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.photo.models.Order;
import it.uniroma3.siw.photo.services.PhotoService;

@Controller
public class GalleryController {
    @Autowired
    private PhotoService photoService;
    
    @GetMapping(value = "/galleryByPhotos")
    public String galleryByPhotos(@RequestParam(value = "filter", required = false) String filter, Model model) {
        if (filter == null) {
            model.addAttribute("photos", this.photoService.findAll());
            model.addAttribute("order", new Order());
            return "/guest/galleryByPhotos.html";
        }
        model.addAttribute("photos", this.photoService.findByName(filter));
        model.addAttribute("order", new Order());
        return "/guest/galleryByPhotos.html";
    }
}