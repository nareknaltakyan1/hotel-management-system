package com.naltakyan.hotelmanagement.controller;

import com.naltakyan.hotelmanagement.model.Contact;
import com.naltakyan.hotelmanagement.service.ContactService;
import java.util.List;

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

@Controller
@Slf4j
public class ContactController {

    private final ContactService contactService;

    @Value("${msg.title}")
    private String title;

    public ContactController(final ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", title);
        return "index";
    }

    @GetMapping(value = "/contacts")
    public String getContacts(Model model, Pageable pageable) {
        List<Contact> contacts = contactService.findAll(pageable);

        long count = contactService.count();
        boolean hasPrev = pageable.getPageNumber() > 1;
        boolean hasNext = ((long) pageable.getPageNumber() * pageable.getPageSize()) < count;
        model.addAttribute("contacts", contacts);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageable.getPageNumber() - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageable.getPageNumber() + 1);
        return "contact-list";
    }

    @GetMapping(value = "/contacts/{contactId}")
    public String getContactById(Model model, @PathVariable long contactId) {
        Contact contact = null;
        try {
            contact = contactService.findById(contactId);
        } catch (EntityNotFoundException ex) {
            model.addAttribute("errorMessage", "Contact not found");
        }
        model.addAttribute("contact", contact);
        return "contact";
    }

    @GetMapping(value = {"/contacts/add"})
    public String showAddContact(Model model) {
        Contact contact = new Contact();
        model.addAttribute("add", true);
        model.addAttribute("contact", contact);

        return "contact-edit";
    }

    @PostMapping(value = "/contacts/add")
    public String addContact(Model model,
                             @ModelAttribute("contact") Contact contact) {
        try {
            Contact newContact = contactService.save(contact);
            return "redirect:/contacts/" + String.valueOf(newContact.getId());
        } catch (Exception ex) {
            // log com.naltakyan.hotelmanagment.exception first,
            // then show error
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);

            //com.naltakyan.hotelmanagment.model.addAttribute("contact", contact);
            model.addAttribute("add", true);
            return "contact-edit";
        }
    }

    @GetMapping(value = {"/contacts/{contactId}/edit"})
    public String showEditContact(Model model, @PathVariable long contactId) {
        Contact contact = null;
        try {
            contact = contactService.findById(contactId);
        } catch (EntityNotFoundException ex) {
            model.addAttribute("errorMessage", "Contact not found");
        }
        model.addAttribute("add", false);
        model.addAttribute("contact", contact);
        return "contact-edit";
    }

    @PostMapping(value = {"/contacts/{contactId}/edit"})
    public String updateContact(Model model,
                                @PathVariable long contactId,
                                @ModelAttribute("contact") Contact contact) {
        try {
            contact.setId(contactId);
            contactService.update(contact);
            return "redirect:/contacts/" + String.valueOf(contact.getId());
        } catch (Exception ex) {
            // log com.naltakyan.hotelmanagment.exception first,
            // then show error
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "contact-edit";
        }
    }

    @GetMapping(value = {"/contacts/{contactId}/delete"})
    public String showDeleteContactById(
            Model model, @PathVariable long contactId) {
        Contact contact = null;
        try {
            contact = contactService.findById(contactId);
        } catch (EntityNotFoundException ex) {
            model.addAttribute("errorMessage", "Contact not found");
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("contact", contact);
        return "contact";
    }

    @PostMapping(value = {"/contacts/{contactId}/delete"})
    public String deleteContactById(
            Model model, @PathVariable long contactId) {
        try {
            contactService.deleteById(contactId);
            return "redirect:/contacts";
        } catch (EntityNotFoundException ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "contact";
        }
    }
}
