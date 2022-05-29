package com.naltakyan.hotelmanagement.model;

import javax.persistence.Column;
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

    private String passport;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String profileImageUrl;

    private String phone;

    private String email;
}
