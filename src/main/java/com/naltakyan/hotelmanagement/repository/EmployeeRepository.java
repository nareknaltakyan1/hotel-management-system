package com.naltakyan.hotelmanagement.repository;

import com.naltakyan.hotelmanagement.model.Employee;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  Optional<Employee> findByIdAndDeletedIsNull(Long id);

  Page<Employee> getEmployeesByDeletedIsNull(Pageable pageable);
}
