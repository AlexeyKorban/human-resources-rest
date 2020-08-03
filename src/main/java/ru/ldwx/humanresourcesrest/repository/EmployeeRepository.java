package ru.ldwx.humanresourcesrest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import ru.ldwx.humanresourcesrest.model.Employee;

import java.time.LocalDate;
import java.util.List;

@Controller
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    List<Employee> getEmployeeByDateOfBirthAfterAndDateOfBirthBefore(LocalDate startDate, LocalDate endDate);
}
