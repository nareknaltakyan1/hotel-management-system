package com.naltakyan.hotelmanagement.service;

import static org.springframework.util.Assert.notNull;

import com.naltakyan.hotelmanagement.model.Reservation;
import com.naltakyan.hotelmanagement.repository.ReservationRepository;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationService {

  private ReservationRepository reservationRepository;

  private boolean existsById(Long id) {
    return reservationRepository.existsById(id);
  }

  public Reservation findById(Long id) {
    Reservation reservation = reservationRepository.findById(id).orElse(null);
    if (reservation == null) {
      throw new EntityNotFoundException("Cannot find Reservation with id: " + id);
    } else return reservation;
  }

  public List<Reservation> findAll(final Pageable pageable) {
    return reservationRepository.findAll(pageable).getContent();
  }

  public Reservation save(Reservation reservation) {
    notNull(reservation, "Employee should not be null");
    notNull(reservation.getStatus(), "employee Status should not be null");
    notNull(reservation.getToDate(), "employee ToDate should not be null");
    notNull(reservation.getFromDate(), "employee FromDate should not be null");
    if (reservation.getId() != null && existsById(reservation.getId())) {
      throw new EntityExistsException(
          "Reservation with id: " + reservation.getId() + " already exists");
    }
    return reservationRepository.save(reservation);
  }

  public void update(Reservation reservation) {
    notNull(reservation, "Employee should not be null");
    notNull(reservation.getStatus(), "employee Status should not be null");
    notNull(reservation.getToDate(), "employee ToDate should not be null");
    notNull(reservation.getFromDate(), "employee FromDate should not be null");
    if (!existsById(reservation.getId())) {
      throw new EntityNotFoundException("Cannot find Reservation with id: " + reservation.getId());
    }
    reservationRepository.save(reservation);
  }

  public void deleteById(Long id) {
    if (!existsById(id)) {
      throw new EntityNotFoundException("Cannot find Reservation with id: " + id);
    }
    reservationRepository.deleteById(id);
  }

  public Long count() {
    return reservationRepository.count();
  }
}
