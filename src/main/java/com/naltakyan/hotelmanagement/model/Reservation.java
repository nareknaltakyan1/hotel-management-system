package com.naltakyan.hotelmanagement.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Reservation extends TimeStampedDomainEntity {

  @Enumerated(EnumType.STRING)
  private ReservationStatus status;

  @Column(name = "from_date")
  private LocalDateTime fromDate;

  @Column(name = "to_date")
  private LocalDateTime toDate;

  private double price;

  @ManyToOne
  @JoinColumn(name = "guest_id", nullable = false)
  private Guest guest;

  @ManyToOne
  @JoinColumn(name = "room_id", nullable = false)
  private Room room;
  //  TODO add mappings
}
