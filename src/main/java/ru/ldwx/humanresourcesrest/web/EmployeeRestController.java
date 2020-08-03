package ru.ldwx.humanresourcesrest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ldwx.humanresourcesrest.model.Employee;
import ru.ldwx.humanresourcesrest.service.EmployeeService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EmployeeRestController {
    static final String REST_URL = "rest/employee";

    private final EmployeeService service;

    @Autowired
    public EmployeeRestController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping(value = REST_URL + "/{id}")
    public Employee get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        service.create(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value =  REST_URL, consumes = "application/json")
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        service.update(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = REST_URL + "/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @GetMapping (value = REST_URL)
    public List<Employee> getAll() {
        return service.getAll();
    }

    @GetMapping(value = REST_URL + "/filter")
    public List<Employee> getBetween(
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        startDate = startDate == null ? LocalDate.MIN : startDate;
        endDate = endDate == null ? LocalDate.MAX : endDate;
        return service.getBetweenDates(startDate, endDate);
    }
}
