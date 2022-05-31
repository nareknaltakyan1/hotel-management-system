package com.naltakyan.hotelmanagement.service;

import static org.springframework.util.Assert.notNull;

import com.naltakyan.hotelmanagement.model.AdditionalServices;
import com.naltakyan.hotelmanagement.repository.AdditionalServicesRepository;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AdditionalServicesService {

  private final AdditionalServicesRepository additionalServicesRepository;

  private boolean existsById(Long id) {
    return additionalServicesRepository.existsById(id);
  }

  public AdditionalServices findById(Long id) {
    AdditionalServices additionalServices =
        additionalServicesRepository.findByIdAndDeletedIsNull(id).orElse(null);
    if (additionalServices == null) {
      throw new EntityNotFoundException("Cannot find Additional Service with id: " + id);
    }
    return additionalServices;
  }

  public List<AdditionalServices> findAll(final Pageable pageable) {
    return additionalServicesRepository.getAdditionalServicesByDeletedIsNull(pageable).getContent();
  }

  public AdditionalServices save(AdditionalServices additionalServices) {
    notNull(additionalServices, "Additional Service should not be null");
    notNull(additionalServices.getName(), "Additional Service Name should not be null");
    notNull(additionalServices.getPrice(), "Additional Service Price should not be null");
    if (additionalServices.getId() != null && existsById(additionalServices.getId())) {
      throw new EntityExistsException(
          "AdditionalService with id: " + additionalServices.getId() + " already exists");
    }
    return additionalServicesRepository.save(additionalServices);
  }

  public void update(AdditionalServices additionalServices) {
    notNull(additionalServices, "AdditionalService should not be null");
    notNull(additionalServices.getName(), "AdditionalService Name should not be null");
    notNull(additionalServices.getPrice(), "Additional Service Price should not be null");
    if (!existsById(additionalServices.getId())) {
      throw new EntityNotFoundException(
          "Cannot find Additional Service with id: " + additionalServices.getId());
    }
    additionalServicesRepository.save(additionalServices);
  }

  @Transactional
  public void deleteById(Long id) {
    var additionalService = findById(id);
    additionalService.setDeleted(LocalDateTime.now());
    additionalServicesRepository.save(additionalService);
  }

  public Long count() {
    return additionalServicesRepository.count();
  }
}
