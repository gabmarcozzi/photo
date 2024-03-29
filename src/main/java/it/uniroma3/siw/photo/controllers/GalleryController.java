package it.uniroma3.siw.photo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.photo.models.Photo;
import it.uniroma3.siw.photo.services.AlbumService;
import it.uniroma3.siw.photo.services.PhotoService;
import it.uniroma3.siw.photo.services.PhotographerService;

@Controller
public class GalleryController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private PhotographerService photographerService;

    @GetMapping(value = "/galleryByPhotos")
    public String galleryByPhotos(@RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "albumName", required = false) String albumName,
            @RequestParam(value = "photographerName", required = false) String photographerName,
            @RequestParam(value = "addedPhotoId", required = false) Long addedPhotoId, 
            Model model, HttpSession session) {
        
        @SuppressWarnings("unchecked")
        List<Photo> selectedPhotos = (List<Photo>)session.getAttribute("selectedPhotos");
        model.addAttribute("photosNumber", selectedPhotos.size());

        if (filter != null) {
            model.addAttribute("photos", this.photoService.findByName(filter));
            return "/guest/galleryByPhotos.html";
        } else if (albumName != null) {
            model.addAttribute("photos", this.albumService.findByName(albumName).getPhotos());
            return "/guest/galleryByPhotos.html";
        } else if (photographerName != null) {
            model.addAttribute("photos", this.photographerService.findByName(photographerName).getPhotos());
            return "/guest/galleryByPhotos.html";
        } else if (addedPhotoId != null) {
            Photo ph = photoService.findById(addedPhotoId);
            boolean find = false;
            for(Photo photo : selectedPhotos) {
                if(ph.getId() == photo.getId())
                find = true;
            }
            if(!find) {
                selectedPhotos.add(ph);
                session.setAttribute("selectedPhotos", selectedPhotos);
                model.addAttribute("photosNumber", selectedPhotos.size());
            }
        }
        model.addAttribute("photos", this.photoService.findAll());
        return "/guest/galleryByPhotos.html";
    }

    @GetMapping(value = "/galleryByAlbums")
    public String galleryByAlbums(Model model) {
        model.addAttribute("albums", this.albumService.findAll());
        return "/guest/galleryByAlbums.html";
    }

    @GetMapping(value = "/galleryByPhotographers")
    public String galleryByPhotographers(Model model) {
        model.addAttribute("photographers", this.photographerService.findAll());
        return "/guest/galleryByPhotographers.html";
    }

    @RequestMapping(value = "/")
    public String index(Model model, HttpSession session) {
        List<Photo> selectedPhotos = new ArrayList<Photo>();
        session.setAttribute("selectedPhotos", selectedPhotos);

        model.addAttribute("photosNumber", 0);
        model.addAttribute("photos", this.photoService.findAll());
        return "/guest/galleryByPhotos.html";
    }
}