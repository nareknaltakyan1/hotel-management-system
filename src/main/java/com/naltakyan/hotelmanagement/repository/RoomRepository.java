package com.naltakyan.hotelmanagement.repository;

import com.naltakyan.hotelmanagement.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {}
