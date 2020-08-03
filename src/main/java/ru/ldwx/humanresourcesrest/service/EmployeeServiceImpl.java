package ru.ldwx.humanresourcesrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ldwx.humanresourcesrest.model.Employee;
import ru.ldwx.humanresourcesrest.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee create(Employee employee) {
        Assert.notNull(employee, "employee must not be null");
        return repository.save(employee);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Employee get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void update(Employee employee) {
        repository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getBetweenDates(LocalDate startDate, LocalDate endDate) {
        return repository.getEmployeeByDateOfBirthAfterAndDateOfBirthBefore(startDate, endDate);
    }
}
