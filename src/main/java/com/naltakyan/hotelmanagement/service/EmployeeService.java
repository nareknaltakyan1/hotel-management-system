package com.naltakyan.hotelmanagement.service;

import static org.springframework.util.Assert.notNull;

import com.naltakyan.hotelmanagement.model.Employee;
import com.naltakyan.hotelmanagement.repository.EmployeeRepository;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {

  private final EmployeeRepository employeeRepository;

  private boolean existsById(Long id) {
    return employeeRepository.existsById(id);
  }

  public Employee findById(Long id) {
    Employee employee = employeeRepository.findByIdAndDeletedIsNull(id).orElse(null);
    if (employee == null) {
      throw new EntityNotFoundException("Cannot find Employee with id: " + id);
    } else return employee;
  }

  public List<Employee> findAll(final Pageable pageable) {
    return employeeRepository.getEmployeesByDeletedIsNull(pageable).getContent();
  }

  public Employee save(Employee employee) {
    notNull(employee, "Employee should not be null");
    notNull(employee.getFirstName(), "employee FirstName should not be null");
    notNull(employee.getLastName(), "employee LastName should not be null");
    notNull(employee.getPhone(), "employee Phone should not be null");
    notNull(employee.getPassport(), "employee Passport should not be null");
    notNull(employee.getEmail(), "employee Email should not be null");
    if (employee.getId() != null && existsById(employee.getId())) {
      throw new EntityExistsException("Employee with id: " + employee.getId() + " already exists");
    }
    employee.setCreated(LocalDateTime.now());
    employee.setUpdated(LocalDateTime.now());
    return employeeRepository.save(employee);
  }

  public void update(Employee employee) {
    notNull(employee, "Employee should not be null");
    notNull(employee.getFirstName(), "employee FirstName should not be null");
    notNull(employee.getLastName(), "employee LastName should not be null");
    if (!existsById(employee.getId())) {
      throw new EntityNotFoundException("Cannot find Employee with id: " + employee.getId());
    }
    employee.setCreated(LocalDateTime.now());
    employee.setUpdated(LocalDateTime.now());
    employeeRepository.save(employee);
  }

  public void deleteById(Long id) {
    if (!existsById(id)) {
      throw new EntityNotFoundException("Cannot find Employee with id: " + id);
    }
    employeeRepository.deleteById(id);
  }

  public Long count() {
    return employeeRepository.count();
  }
}
