package com.naltakyan.hotelmanagement.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@MappedSuperclass
public abstract class DomainEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  public DomainEntity() {}

  public Long getId() {
    return this.id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    } else if (!(o instanceof DomainEntity)) {
      return false;
    } else {
      DomainEntity entity = (DomainEntity) o;
      return this.getId() != null && this.getId().equals(entity.getId());
    }
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).toHashCode();
  }

  @Override
  public String toString() {
    return "DomainEntity{" + "id=" + id + '}';
  }
}
