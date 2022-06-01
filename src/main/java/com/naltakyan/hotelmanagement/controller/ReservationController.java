package com.naltakyan.hotelmanagement.controller;

import com.naltakyan.hotelmanagement.model.Reservation;
import com.naltakyan.hotelmanagement.service.ReservationService;
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
public class ReservationController {

    private final ReservationService reservationService;

    @Value("${msg.title}")
    private String title;

    public ReservationController(final ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(value = "/reservation-home")
    public String index(Model model) {
        model.addAttribute("title", title);
        return "reservation-home";
    }

    @GetMapping(value = "/reservations")
    public String getReservations(Model model, Pageable pageable) {
        List<Reservation> reservations = reservationService.findAll(pageable);

        long count = reservationService.count();
        boolean hasPrev = pageable.getPageNumber() > 1;
        boolean hasNext = ((long) pageable.getPageNumber() * pageable.getPageSize()) < count;
        model.addAttribute("reservations", reservations);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageable.getPageNumber() - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageable.getPageNumber() + 1);
        return "reservation-list";
    }

    @GetMapping(value = "/reservations/{reservationId}")
    public String getReservationById(Model model, @PathVariable long reservationId) {
        Reservation reservation = null;
        try {
            reservation = reservationService.findById(reservationId);
        } catch (EntityNotFoundException ex) {
            model.addAttribute("errorMessage", "Reservation not found");
        }
        model.addAttribute("reservation", reservation);
        return "reservation";
    }

    @GetMapping(value = {"/reservations/add"})
    public String showAddReservation(Model model) {
        Reservation reservation = new Reservation();
        model.addAttribute("add", true);
        model.addAttribute("reservation", reservation);

        return "reservation-edit";
    }

    @PostMapping(value = "/reservations/add")
    public String addReservation(Model model, @ModelAttribute("reservation") Reservation reservation) {
        try {
            Reservation newReservation = reservationService.save(reservation);
            return "redirect:/reservations/" + String.valueOf(newReservation.getId());
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", true);
            return "reservation-edit";
        }
    }

    @GetMapping(value = {"/reservations/{reservationId}/edit"})
    public String showEditReservation(Model model, @PathVariable long reservationId) {
        Reservation reservation = null;
        try {
            reservation = reservationService.findById(reservationId);
        } catch (EntityNotFoundException ex) {
            model.addAttribute("errorMessage", "Reservation not found");
        }
        model.addAttribute("add", false);
        model.addAttribute("reservation", reservation);
        return "reservation-edit";
    }

    @PostMapping(value = {"/reservations/{reservationId}/edit"})
    public String updateReservation(
            Model model, @PathVariable long reservationId, @ModelAttribute("reservation") Reservation reservation) {
        try {
            reservation.setId(reservationId);
            reservationService.update(reservation);
            return "redirect:/reservations/" + String.valueOf(reservation.getId());
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "reservation-edit";
        }
    }

    @GetMapping(value = {"/reservations/{reservationId}/delete"})
    public String showDeleteReservationById(Model model, @PathVariable long reservationId) {
        Reservation reservation = null;
        try {
            reservation = reservationService.findById(reservationId);
        } catch (EntityNotFoundException ex) {
            model.addAttribute("errorMessage", "Reservation not found");
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("reservation", reservation);
        return "reservation";
    }

    @PostMapping(value = {"/reservations/{reservationId}/delete"})
    public String deleteReservationById(Model model, @PathVariable long reservationId) {
        try {
            reservationService.deleteById(reservationId);
            return "redirect:/reservations";
        } catch (EntityNotFoundException ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "reservation";
        }
    }
}
