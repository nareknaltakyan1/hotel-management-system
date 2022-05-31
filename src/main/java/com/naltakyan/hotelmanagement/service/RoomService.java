package com.naltakyan.hotelmanagement.service;

import com.naltakyan.hotelmanagement.model.Guest;
import com.naltakyan.hotelmanagement.model.Room;
import com.naltakyan.hotelmanagement.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
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
        Room room = roomRepository.findByIdAndDeletedIsNull(id).orElse(null);
        if (room == null) {
            throw new EntityNotFoundException("Cannot find Room with id: " + id);
        }
        return room;
    }

    public List<Room> findAll(final Pageable pageable) {
        return roomRepository.getRoomsByDeletedIsNull(pageable).getContent();
    }

    @Transactional
    public Room save(Room room) {
        notNull(room, "Room should not be null");
        notNull(room.getType(), "Room Type should not be null");
        notNull(room.getBedNumbers(), "Room BedNumber should not be null");
        notNull(room.getPricePerDay(), "Room PricePerDay should not be null");
        if (room.getId() != null && existsById(room.getId())) {
            throw new EntityExistsException("Cannot find Room id: " + room.getId() + " already exists");
        }
        return roomRepository.save(room);
    }

    @Transactional
    public void update(Room room) {
        notNull(room, "Room should not be null");
        notNull(room.getType(), "Room Type should not be null");
        notNull(room.getBedNumbers(), "Room BedNumber should not be null");
        notNull(room.getPricePerDay(), "Room PricePerDay should not be null");
        if (!existsById(room.getId())) {
            throw new EntityNotFoundException("Cannot find Room: " + room.getId());
        }
        roomRepository.save(room);
    }

    @Transactional
    public void deleteById(Long id) {
        var room = findById(id);
        room.setDeleted(LocalDateTime.now());
        roomRepository.save(room);
    }

    public Long count() {
        return roomRepository.count();
    }

}
