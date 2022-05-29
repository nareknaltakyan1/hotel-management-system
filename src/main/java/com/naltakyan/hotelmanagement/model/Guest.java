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
public class Guest extends TimeStampedDomainEntity {

  @Column(nullable = false, name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(nullable = false)
  private String passport;

  @Column(nullable = false)
  private String phone;

  private String email;
}
