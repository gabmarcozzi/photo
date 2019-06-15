package it.uniroma3.siw.photo.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.photo.models.Order;

public class RequestController {

    @RequestMapping(value = "/submitForm", method = RequestMethod.POST)
    public String newForm(@ModelAttribute("order") Order order, Model model) {
        model.addAttribute("order", order);
        return "completeForm.html";
    }
}