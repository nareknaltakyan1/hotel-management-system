package com.naltakyan.hotelmanagement.controller;

import com.naltakyan.hotelmanagement.model.Employee;
import com.naltakyan.hotelmanagement.service.EmployeeService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class EmployeeController {

  private final EmployeeService employeeService;

  @Value("${msg.title}")
  private String title;

  public EmployeeController(final EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping(value = "/employee-home")
  public String index(Model model) {
    model.addAttribute("title", title);
    return "employee-home";
  }

  @GetMapping(value = "/employees")
  public String getEmployees(Model model, Pageable pageable) {
    List<Employee> employees = employeeService.findAll(pageable);

    long count = employeeService.count();
    boolean hasPrev = pageable.getPageNumber() > 1;
    boolean hasNext = ((long) pageable.getPageNumber() * pageable.getPageSize()) < count;
    model.addAttribute("employees", employees);
    model.addAttribute("hasPrev", hasPrev);
    model.addAttribute("prev", pageable.getPageNumber() - 1);
    model.addAttribute("hasNext", hasNext);
    model.addAttribute("next", pageable.getPageNumber() + 1);
    return "employee-list";
  }

  @GetMapping(value = "/employees/{employeeId}")
  public String getEmployeeById(Model model, @PathVariable long employeeId) {
    Employee employee = null;
    try {
      employee = employeeService.findById(employeeId);
    } catch (EntityNotFoundException ex) {
      model.addAttribute("errorMessage", "Employee not found");
    }
    model.addAttribute("employee", employee);
    return "employee";
  }

  @GetMapping(value = {"/employees/add"})
  public String showAddEmployee(Model model) {
    Employee employee = new Employee();
    model.addAttribute("add", true);
    model.addAttribute("employee", employee);

    return "employee-edit";
  }

  @PostMapping(value = "/employees/add")
  public String addEmployee(Model model, @ModelAttribute("employee") Employee employee) {
    try {
      Employee newEmployee = employeeService.save(employee);
      return "redirect:/employees/" + String.valueOf(newEmployee.getId());
    } catch (Exception ex) {
      String errorMessage = ex.getMessage();
      log.error(errorMessage);
      model.addAttribute("errorMessage", errorMessage);
      model.addAttribute("add", true);
      return "employee-edit";
    }
  }

  @GetMapping(value = {"/employees/{employeeId}/edit"})
  public String showEditEmployee(Model model, @PathVariable long employeeId) {
    Employee employee = null;
    try {
      employee = employeeService.findById(employeeId);
    } catch (EntityNotFoundException ex) {
      model.addAttribute("errorMessage", "Employee not found");
    }
    model.addAttribute("add", false);
    model.addAttribute("employee", employee);
    return "employee-edit";
  }

  @PostMapping(value = {"/employees/{employeeId}/edit"})
  public String updateEmployee(
      Model model, @PathVariable long employeeId, @ModelAttribute("employee") Employee employee) {
    try {
      employee.setId(employeeId);
      employeeService.update(employee);
      return "redirect:/employees/" + String.valueOf(employee.getId());
    } catch (Exception ex) {
      String errorMessage = ex.getMessage();
      log.error(errorMessage);
      model.addAttribute("errorMessage", errorMessage);

      model.addAttribute("add", false);
      return "employee-edit";
    }
  }

  @GetMapping(value = {"/employees/{employeeId}/delete"})
  public String showDeleteEmployeeById(Model model, @PathVariable long employeeId) {
    Employee employee = null;
    try {
      employee = employeeService.findById(employeeId);
    } catch (EntityNotFoundException ex) {
      model.addAttribute("errorMessage", "Employee not found");
    }
    model.addAttribute("allowDelete", true);
    model.addAttribute("employee", employee);
    return "employee";
  }

  @PostMapping(value = {"/employees/{employeeId}/delete"})
  public String deleteEmployeeById(Model model, @PathVariable long employeeId) {
    try {
      employeeService.deleteById(employeeId);
      return "redirect:/employees";
    } catch (EntityNotFoundException ex) {
      String errorMessage = ex.getMessage();
      log.error(errorMessage);
      model.addAttribute("errorMessage", errorMessage);
      return "employee";
    }
  }
}
