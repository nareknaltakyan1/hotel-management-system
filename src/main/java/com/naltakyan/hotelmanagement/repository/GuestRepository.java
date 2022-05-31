package com.naltakyan.hotelmanagement.repository;

import com.naltakyan.hotelmanagement.model.Guest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    Optional<Guest> findByIdAndDeletedIsNull(Long id);

    Page<Guest> getGuestsByDeletedIsNull(Pageable pageable);
}
