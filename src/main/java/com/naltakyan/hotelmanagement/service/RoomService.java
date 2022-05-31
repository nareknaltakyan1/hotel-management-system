package com.naltakyan.hotelmanagement.service;

import com.naltakyan.hotelmanagement.model.Employee;
import com.naltakyan.hotelmanagement.model.Room;
import com.naltakyan.hotelmanagement.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.springframework.util.Assert.notNull;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    private boolean existsById(Long id) {
        return roomRepository.existsById(id);
    }

    public Room findById(Long id) {
        Room room = roomRepository.findById(id).orElse(null);
        if (room == null) {
            throw new EntityNotFoundException("Cannot find Room  with id: " + id);
        } else return room;
    }

    public List<Room> findAll(final Pageable pageable) {
        return roomRepository.findAll(pageable).getContent();
    }

    public Room save(Room room) {
        notNull(room, "Employee should not be null");
        notNull(room.getType(), "room Type should not be null");
        notNull(room.getBedNumbers(), "room BedNumber should not be null");
        notNull(room.getPricePerDay(), "room PricePerDay should not be null");
        if (room.getId() != null && existsById(room.getId())) {
            throw new EntityExistsException("Room with id: " + room.getId() + " already exists");
        }
        return roomRepository.save(room);
    }

    public void update(Room room) {
        notNull(room, "Employee should not be null");
        notNull(room.getType(), "room Type should not be null");
        notNull(room.getBedNumbers(), "room BedNumber should not be null");
        notNull(room.getPricePerDay(), "room PricePerDay should not be null");
        if (!existsById(room.getId())) {
            throw new EntityNotFoundException("Room find with id: " + room.getId());
        }
        roomRepository.save(room);
    }

    public void deleteById(Long id) {
        if (!existsById(id)) {
            throw new EntityNotFoundException("Room find with id: " + id);
        }
        roomRepository.deleteById(id);
    }

    public Long count() {
        return roomRepository.count();
    }

}
