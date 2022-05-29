package com.naltakyan.hotelmanagement.repository;

import com.naltakyan.hotelmanagement.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Long, Room> {}
