package it.uniroma3.siw.photo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.photo.models.Order;
import it.uniroma3.siw.photo.models.Photo;
import it.uniroma3.siw.photo.services.PhotoService;

@Controller
public class RequestController {

    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "/submitForm")
    public String newForm(HttpSession session, Model model) {
        
        if (session.getAttribute("selectedPhotos") == null) {
            System.out.println("é voto");
            List<Photo> selectedPhotos = new ArrayList<Photo>();
            session.setAttribute("selectedPhotos", selectedPhotos);
        }

        @SuppressWarnings("unchecked")
        List<Photo> selectedPhotos = (List<Photo>)session.getAttribute("selectedPhotos");

        Order order = new Order();
        order.setPhotos(selectedPhotos);
        for(Photo p : selectedPhotos)
            System.out.println(p.getName());

        model.addAttribute("order", order);
        return "guest/completeForm.html";
    }

    @RequestMapping(value = "/getImage", method = RequestMethod.GET)
    public void showImage(@RequestParam("id") Long id, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        Photo photo = photoService.findById(id);
        response.setContentType("image/jpg");
        response.getOutputStream().write(photo.getImage());
        response.getOutputStream().close();
    }
}