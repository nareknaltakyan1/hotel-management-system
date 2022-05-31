package com.naltakyan.hotelmanagement.repository;

import com.naltakyan.hotelmanagement.model.AdditionalServices;
import com.naltakyan.hotelmanagement.model.Room;
import com.naltakyan.hotelmanagement.service.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByIdAndDeletedIsNull(Long id);

    Page<Room> getRoomServicesByDeletedIsNull(Pageable pageable);
}
