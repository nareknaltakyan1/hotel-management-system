package com.naltakyan.hotelmanagement.service;

import com.naltakyan.hotelmanagement.model.Contact;
import com.naltakyan.hotelmanagement.model.Employee;
import com.naltakyan.hotelmanagement.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.springframework.util.Assert.notNull;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private boolean existsById(Long id) {
        return employeeRepository.existsById(id);
    }

    public Employee findById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            throw new EntityNotFoundException("Cannot find Contact with id: " + id);
        } else return employee;
    }

    public List<Employee> findAll(final Pageable pageable) {
        return employeeRepository.findAll(pageable).getContent();
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
        return employeeRepository.save(employee);
    }

    public void update(Employee employee) {
        notNull(employee, "Employee should not be null");
        notNull(employee.getFirstName(), "employee FirstName should not be null");
        notNull(employee.getLastName(), "employee LastName should not be null");
        if (!existsById(employee.getId())) {
            throw new EntityNotFoundException("Employee find Contact with id: " + employee.getId());
        }
        employeeRepository.save(employee);
    }

    public void deleteById(Long id) {
        if (!existsById(id)) {
            throw new EntityNotFoundException("Employee find contact with id: " + id);
        }
        employeeRepository.deleteById(id);
    }

    public Long count() {
        return employeeRepository.count();
    }

}
