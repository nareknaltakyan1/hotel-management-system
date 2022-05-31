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
@Table(name = "additional_services")
public class AdditionalServices extends TimeStampedDomainEntity {

  @Column(nullable = false, length = 255)
  private String name;

  @Column(nullable = false)
  private Double price;
}
