package com.naltakyan.hotelmanagement.controller;

import com.naltakyan.hotelmanagement.model.Contact;
import com.naltakyan.hotelmanagement.model.Guest;
import com.naltakyan.hotelmanagement.service.ContactService;
import com.naltakyan.hotelmanagement.service.GuestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/guest")
@Slf4j
public class GuestController {
    private final GuestService guestService;


    public GuestController(final GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping(value = {"/contacts/add"})
    public String showAddGuest(Model model) {
        Guest guest = new Guest();
        model.addAttribute("add", true);
        model.addAttribute("guest", guest);
        return "guest-edit";
    }


    @PostMapping(value = "/guest/add")
    public String addGuest(Model model, @ModelAttribute("guest") Guest guest) {
        try {
            Guest newGuest = guestService.save(guest);
            return "redirect:/guests/" + String.valueOf(newGuest.getId());
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", true);
            return "guest-edit";
        }
    }


}
