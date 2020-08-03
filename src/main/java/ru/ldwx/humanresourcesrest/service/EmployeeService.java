package ru.ldwx.humanresourcesrest.service;

import ru.ldwx.humanresourcesrest.model.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);

    void delete(int id);

    Employee get(int id);

    void update(Employee employee);

    List<Employee> getAll();

    List<Employee> getBetweenDates(LocalDate startDate, LocalDate endDate);
}
