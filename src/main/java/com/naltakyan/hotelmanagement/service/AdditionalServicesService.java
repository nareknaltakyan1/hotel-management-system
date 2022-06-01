package com.naltakyan.hotelmanagement.service;

import static org.springframework.util.Assert.notNull;

import com.naltakyan.hotelmanagement.model.AdditionalService;
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

  public AdditionalService findById(Long id) {
    AdditionalService additionalService =
        additionalServicesRepository.findByIdAndDeletedIsNull(id).orElse(null);
    if (additionalService == null) {
      throw new EntityNotFoundException("Cannot find Additional Service with id: " + id);
    }
    return additionalService;
  }

  public List<AdditionalService> findAll(final Pageable pageable) {
    return additionalServicesRepository.getAdditionalServicesByDeletedIsNull(pageable).getContent();
  }

  public AdditionalService save(AdditionalService additionalService) {
    notNull(additionalService, "Additional Service should not be null");
    notNull(additionalService.getName(), "Additional Service Name should not be null");
    notNull(additionalService.getPrice(), "Additional Service Price should not be null");
    if (additionalService.getId() != null && existsById(additionalService.getId())) {
      throw new EntityExistsException(
          "AdditionalService with id: " + additionalService.getId() + " already exists");
    }
    additionalService.setCreated(LocalDateTime.now());
    additionalService.setUpdated(LocalDateTime.now());
    return additionalServicesRepository.save(additionalService);
  }

  public void update(AdditionalService additionalService) {
    notNull(additionalService, "AdditionalService should not be null");
    notNull(additionalService.getName(), "AdditionalService Name should not be null");
    notNull(additionalService.getPrice(), "Additional Service Price should not be null");
    if (!existsById(additionalService.getId())) {
      throw new EntityNotFoundException(
          "Cannot find Additional Service with id: " + additionalService.getId());
    }
    additionalService.setCreated(LocalDateTime.now());
    additionalService.setUpdated(LocalDateTime.now());
    additionalServicesRepository.save(additionalService);
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
