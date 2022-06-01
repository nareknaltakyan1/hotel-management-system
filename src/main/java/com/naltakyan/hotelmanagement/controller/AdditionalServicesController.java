package com.naltakyan.hotelmanagement.controller;

import com.naltakyan.hotelmanagement.model.AdditionalService;
import com.naltakyan.hotelmanagement.service.AdditionalServicesService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class AdditionalServicesController {

  private final AdditionalServicesService additionalServicesService;

  @Value("${msg.title}")
  private String title;

  public AdditionalServicesController(final AdditionalServicesService additionalServicesService) {
    this.additionalServicesService = additionalServicesService;
  }

  @GetMapping(value = "/additional-services-home")
  public String index(Model model) {
    model.addAttribute("title", title);
    return "additional-services-home";
  }

  @GetMapping(value = "/additional-services")
  public String getAdditionalServices(Model model, Pageable pageable) {
    List<AdditionalService> additionalServices = additionalServicesService.findAll(pageable);

    long count = additionalServicesService.count();
    boolean hasPrev = pageable.getPageNumber() > 1;
    boolean hasNext = ((long) pageable.getPageNumber() * pageable.getPageSize()) < count;
    model.addAttribute("additionalServices", additionalServices);
    model.addAttribute("hasPrev", hasPrev);
    model.addAttribute("prev", pageable.getPageNumber() - 1);
    model.addAttribute("hasNext", hasNext);
    model.addAttribute("next", pageable.getPageNumber() + 1);
    return "additional-services-list";
  }

  @GetMapping(value = "/additional-services/{additionalServicesId}")
  public String getAdditionalServicesById(Model model, @PathVariable long additionalServicesId) {
    AdditionalService additionalService = null;
    try {
      additionalService = additionalServicesService.findById(additionalServicesId);
    } catch (EntityNotFoundException ex) {
      model.addAttribute("errorMessage", "AdditionalServices not found");
    }
    model.addAttribute("additionalService", additionalService);
    return "additional-services";
  }

  @GetMapping(value = {"/additional-services/add"})
  public String showAddAdditionalServices(Model model) {
    AdditionalService additionalService = new AdditionalService();
    model.addAttribute("add", true);
    model.addAttribute("additionalService", additionalService);

    return "additional-services-edit";
  }

  @PostMapping(value = "/additional-services/add")
  public String addAdditionalServices(
      Model model, @ModelAttribute("additionalService") AdditionalService additionalService) {
    try {
      AdditionalService newAdditionalService = additionalServicesService.save(additionalService);
      return "redirect:/additional-services/" + String.valueOf(newAdditionalService.getId());
    } catch (Exception ex) {
      String errorMessage = ex.getMessage();
      log.error(errorMessage);
      model.addAttribute("errorMessage", errorMessage);
      model.addAttribute("add", true);
      return "additional-services-edit";
    }
  }

  @GetMapping(value = {"/additional-services/{additionalServicesId}/edit"})
  public String showEditAdditionalServices(Model model, @PathVariable long additionalServicesId) {
    AdditionalService additionalService = null;
    try {
      additionalService = additionalServicesService.findById(additionalServicesId);
    } catch (EntityNotFoundException ex) {
      model.addAttribute("errorMessage", "AdditionalServices not found");
    }
    model.addAttribute("add", false);
    model.addAttribute("additionalService", additionalService);
    return "additional-services-edit";
  }

  @PostMapping(value = {"/additional-services/{additionalServicesId}/edit"})
  public String updateAdditionalServices(
      Model model,
      @PathVariable long additionalServicesId,
      @ModelAttribute("additionalServices") AdditionalService additionalService) {
    try {
      additionalService.setId(additionalServicesId);
      additionalServicesService.update(additionalService);
      return "redirect:/additional-services/" + String.valueOf(additionalService.getId());
    } catch (Exception ex) {
      String errorMessage = ex.getMessage();
      log.error(errorMessage);
      model.addAttribute("errorMessage", errorMessage);

      model.addAttribute("add", false);
      return "additional-services-edit";
    }
  }

  @GetMapping(value = {"/additional-services/{additionalServicesId}/delete"})
  public String showDeleteAdditionalServicesById(
      Model model, @PathVariable long additionalServicesId) {
    AdditionalService additionalService = null;
    try {
      additionalService = additionalServicesService.findById(additionalServicesId);
    } catch (EntityNotFoundException ex) {
      model.addAttribute("errorMessage", "AdditionalServices not found");
    }
    model.addAttribute("allowDelete", true);
    model.addAttribute("additionalService", additionalService);
    return "additional-services";
  }

  @PostMapping(value = {"/additional-services/{additionalServicesId}/delete"})
  public String deleteAdditionalServicesById(Model model, @PathVariable long additionalServicesId) {
    try {
      additionalServicesService.deleteById(additionalServicesId);
      return "redirect:/additional-services";
    } catch (EntityNotFoundException ex) {
      String errorMessage = ex.getMessage();
      log.error(errorMessage);
      model.addAttribute("errorMessage", errorMessage);
      return "additional-services";
    }
  }
}
