package com.naltakyan.hotelmanagement.controller;

import com.naltakyan.hotelmanagement.model.Contact;
import com.naltakyan.hotelmanagement.model.Room;
import com.naltakyan.hotelmanagement.service.RoomService;
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
public class RoomController {

    private final RoomService roomService;

    @Value("${msg.title}")
    private String title;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping(value = "/room-home")
    public String index(Model model) {
        model.addAttribute("title", title);
        return "room-home";
    }

    @GetMapping(value = "/rooms")
    public String getRooms(Model model, Pageable pageable) {
        List<Room> rooms = roomService.findAll(pageable);

        long count = roomService.count();
        boolean hasPrev = pageable.getPageNumber() > 1;
        boolean hasNext = ((long) pageable.getPageNumber() * pageable.getPageSize()) < count;
        model.addAttribute("rooms", rooms);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageable.getPageNumber() - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageable.getPageNumber() + 1);
        return "room-list";
    }

    @GetMapping(value = "/rooms/{roomId}")
    public String getRoomById(Model model, @PathVariable long roomId) {
        Room room = null;
        try {
            room = roomService.findById(roomId);
        } catch (EntityNotFoundException ex) {
            model.addAttribute("errorMessage", "Room not found");
        }
        model.addAttribute("Room", room);
        return "room";
    }

    @GetMapping(value = {"/rooms/add"})
    public String showAddRoom(Model model) {
        Room room = new Room();
        model.addAttribute("add", true);
        model.addAttribute("room", room);

        return "room-edit";
    }

    @PostMapping(value = "/rooms/add")
    public String addRoom(Model model, @ModelAttribute("room") Room room) {
        try {
            Room newRoom = roomService.save(room);
            return "redirect:/rooms/" + String.valueOf(newRoom.getId());
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", true);
            return "room-edit";
        }
    }

    @GetMapping(value = {"/rooms/{roomId}/edit"})
    public String showEditRoom(Model model, @PathVariable long roomId) {
        Room room = null;
        try {
            room = roomService.findById(roomId);
        } catch (EntityNotFoundException ex) {
            model.addAttribute("errorMessage", "Room not found");
        }
        model.addAttribute("add", false);
        model.addAttribute("room", room);
        return "room-edit";
    }

    @PostMapping(value = {"/rooms/{roomId}/edit"})
    public String updateRoom(
            Model model, @PathVariable long roomId, @ModelAttribute("contact") Room room) {
        try {
            room.setId(roomId);
            roomService.update(room);
            return "redirect:/rooms/" + String.valueOf(room.getId());
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "room-edit";
        }
    }

    @GetMapping(value = {"/rooms/{roomId}/delete"})
    public String showDeleteRoomById(Model model, @PathVariable long roomId) {
        Room room = null;
        try {
             room = roomService.findById(roomId);
        } catch (EntityNotFoundException ex) {
            model.addAttribute("errorMessage", "Room not found");
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("room", room);
        return "room";
    }

    @PostMapping(value = {"/rooms/{roomId}/delete"})
    public String deleteRoomById(Model model, @PathVariable long roomId) {
        try {
            roomService.deleteById(roomId);
            return "redirect:/rooms";
        } catch (EntityNotFoundException ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "room";
        }
    }

}
