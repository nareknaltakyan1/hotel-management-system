package com.naltakyan.hotelmanagement.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.validation.annotation.Validated;

@Validated
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Employee extends TimeStampedDomainEntity {

  @Enumerated(EnumType.STRING)
  @NotBlank
  private EmployeeRole role;

  @NotBlank private String passport;

  @NotBlank private String name;

  @NotBlank private String phone;

  @NotBlank private String email;
}
