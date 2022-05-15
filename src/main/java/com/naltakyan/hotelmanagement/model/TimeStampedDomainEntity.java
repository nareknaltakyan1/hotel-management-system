package com.naltakyan.hotelmanagement.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import org.apache.commons.lang3.builder.ToStringBuilder;

@MappedSuperclass
public abstract class TimeStampedDomainEntity extends DomainEntity {
  @Column(nullable = false)
  private LocalDateTime created;

  @Column(nullable = false)
  private LocalDateTime updated;

  @Column private LocalDateTime deleted;

  public TimeStampedDomainEntity() {}

  public TimeStampedDomainEntity(final LocalDateTime created) {
    this.created = created;
    this.updated = created;
  }

  public LocalDateTime getCreated() {
    return this.created;
  }

  public void setCreated(final LocalDateTime created) {
    this.created = created;
  }

  public LocalDateTime getUpdated() {
    return this.updated;
  }

  public void setUpdated(final LocalDateTime updated) {
    this.updated = updated;
  }

  public LocalDateTime getDeleted() {
    return this.deleted;
  }

  public void setDeleted(final LocalDateTime deleted) {
    this.deleted = deleted;
  }

  public String toString() {
    return (new ToStringBuilder(this))
        .appendSuper(super.toString())
        .append("created", this.getCreated())
        .append("updated", this.getUpdated())
        .append("deleted", this.getDeleted())
        .toString();
  }
}
