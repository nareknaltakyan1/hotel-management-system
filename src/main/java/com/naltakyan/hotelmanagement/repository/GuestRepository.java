package com.naltakyan.hotelmanagement.repository;

import com.naltakyan.hotelmanagement.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Long, Guest> {}
