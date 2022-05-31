package com.naltakyan.hotelmanagement.repository;

import com.naltakyan.hotelmanagement.model.Room;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
  Optional<Room> findByIdAndDeletedIsNull(Long id);

  Page<Room> getRoomsByDeletedIsNull(Pageable pageable);
}
