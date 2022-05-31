package com.naltakyan.hotelmanagement.repository;

import com.naltakyan.hotelmanagement.model.Reservation;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository
    extends PagingAndSortingRepository<Reservation, Long>, JpaSpecificationExecutor<Reservation> {}
