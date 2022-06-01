package com.naltakyan.hotelmanagement.repository;

import com.naltakyan.hotelmanagement.model.AdditionalService;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalServicesRepository extends JpaRepository<AdditionalService, Long> {
  Optional<AdditionalService> findByIdAndDeletedIsNull(Long id);

  Page<AdditionalService> getAdditionalServicesByDeletedIsNull(Pageable pageable);
}
