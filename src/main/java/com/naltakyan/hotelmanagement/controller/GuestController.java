package com.naltakyan.hotelmanagement.controller;


import com.naltakyan.hotelmanagement.model.Guest;
import com.naltakyan.hotelmanagement.service.GuestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
@Slf4j
public class GuestController {

    private final GuestService guestService;

    @Value("${msg.title}")
    private String title;

    public GuestController(final GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", title);
        return "index";
    }

    @GetMapping(value = "/guests")
    public String getContacts(Model model, Pageable pageable) {
        List<Guest> guests = guestService.findAll(pageable);

        long count = guestService.count();
        boolean hasPrev = pageable.getPageNumber() > 1;
        boolean hasNext = ((long) pageable.getPageNumber() * pageable.getPageSize()) < count;
        model.addAttribute("guests", guests);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageable.getPageNumber() - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageable.getPageNumber() + 1);
        return "guest-list";
    }

    @GetMapping(value = "/guests/{guestId}")
    public String getGuestById(Model model, @PathVariable long guestId) {
        Guest guest = null;
        try {
            guest = guestService.findById(guestId);
        } catch (EntityNotFoundException ex) {
            model.addAttribute("errorMessage", "Guest not found");
        }
        model.addAttribute("contact", guest);
        return "guest";
    }

    @GetMapping(value = {"/guest/add"})
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

    @GetMapping(value = {"/guests/{guestId}/edit"})
    public String showEditGuest(Model model, @PathVariable long guestId) {
        Guest guest = null;
        try {
            guest = guestService.findById(guestId);
        } catch (EntityNotFoundException ex) {
            model.addAttribute("errorMessage", "Guest not found");
        }
        model.addAttribute("add", false);
        model.addAttribute("guest", guest);
        return "guest-edit";
    }

    @PostMapping(value = {"/contacts/{guestId}/edit"})
    public String updateGuest(
            Model model, @PathVariable long guestId, @ModelAttribute("guest") Guest guest) {
        try {
            guest.setId(guestId);
            guestService.update(guest);
            return "redirect:/guests/" + String.valueOf(guest.getId());
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "guest-edit";
        }
    }

    @GetMapping(value = {"/guests/{guestId}/delete"})
    public String showDeleteGuestById(Model model, @PathVariable long guestId) {
        Guest guest = null;
        try {
            guest = guestService.findById(guestId);
        } catch (EntityNotFoundException ex) {
            model.addAttribute("errorMessage", "Guest not found");
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("guest", guest);
        return "guest";
    }

    @PostMapping(value = {"/guests/{guestId}/delete"})
    public String deleteGuestById(Model model, @PathVariable long guestId) {
        try {
            guestService.deleteById(guestId);
            return "redirect:/guests";
        } catch (EntityNotFoundException ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "guest";
        }
    }

}
