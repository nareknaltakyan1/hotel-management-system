package com.naltakyan.hotelmanagement.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Room extends TimeStampedDomainEntity {

  @Enumerated(EnumType.STRING)
  private RoomType type;

  @Column(name = "bed_numbers")
  private String bedNumbers;

  private double area;

  @Column(name = "price_per_day")
  private String pricePerDay;
}
