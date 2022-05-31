package com.naltakyan.hotelmanagement.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    private String area;

    @Column(name = "price_per_day")
    private String pricePerDay;

}
