package it.uniroma3.siw.photo.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @RequestMapping(value = "/submitForm", method = RequestMethod.POST)
    public String newForm(@ModelAttribute("order") Order order, Model model) {
        model.addAttribute("order", order);
        return "completeForm.html";
    }

    @RequestMapping(value = "/getImage", method = RequestMethod.GET)
    public void showImage(@RequestParam("id") Long id, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        Photo photo = photoService.findById(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(photo.getImage());
        response.getOutputStream().close();
    }
}