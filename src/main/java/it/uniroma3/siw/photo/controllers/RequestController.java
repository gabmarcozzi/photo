package it.uniroma3.siw.photo.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.photo.exceptions.ServiceException;
import it.uniroma3.siw.photo.models.Order;
import it.uniroma3.siw.photo.models.Photo;
import it.uniroma3.siw.photo.services.OrderService;
import it.uniroma3.siw.photo.services.PhotoService;
import it.uniroma3.siw.photo.validators.OrderValidator;

@Controller
public class RequestController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private OrderValidator orderValidator;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/submitForm")
    public String newForm(HttpSession session, Model model) {
        @SuppressWarnings("unchecked")
        List<Photo> selectedPhotos = (List<Photo>) session.getAttribute("selectedPhotos");

        Order order = new Order();
        order.setPhotos(selectedPhotos);

        model.addAttribute("order", order);
        return "guest/completeForm.html";
    }

    @RequestMapping(value = "/checkRequest")
    public String checkRequest(@Valid @ModelAttribute("order") Order order, HttpSession session, Model model,
            BindingResult bindingResult) {

        orderValidator.validate(order, bindingResult);
        if (!bindingResult.hasErrors()) {
            try {
                @SuppressWarnings("unchecked")
                List<Photo> selectedPhotos = (List<Photo>) session.getAttribute("selectedPhotos");
                order.setPhotos(selectedPhotos);
                orderService.save(order);
                model.addAttribute("order", order);
                return "guest/success.html";

            } catch (ServiceException e) { e.printStackTrace(); }
        }

        @SuppressWarnings("unchecked")
        List<Photo> selectedPhotos = (List<Photo>) session.getAttribute("selectedPhotos");
        order.setPhotos(selectedPhotos);

        model.addAttribute("order", order);
        return "guest/completeForm.html";
    }

    @RequestMapping(value = "/getImage", method = RequestMethod.GET)
    public void showImage(@RequestParam("id") Long id, HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {
        Photo photo = photoService.findById(id);
        response.setContentType("image/jpg");
        response.getOutputStream().write(photo.getImage());
        response.getOutputStream().close();
    }
}