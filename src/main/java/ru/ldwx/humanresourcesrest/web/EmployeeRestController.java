package ru.ldwx.humanresourcesrest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ldwx.humanresourcesrest.model.Employee;
import ru.ldwx.humanresourcesrest.service.EmployeeService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EmployeeRestController {
    private static final String REST_URL = "rest/employee";

    private final EmployeeService service;

    @Autowired
    public EmployeeRestController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping(value = REST_URL + "/{id}", produces = "application/json; charset=utf-8")
    public Employee get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        service.create(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = REST_URL, consumes = "application/json")
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        service.update(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = REST_URL + "/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @GetMapping(value = REST_URL, produces = "application/json; charset=utf-8")
    public List<Employee> getAll() {
        return service.getAll();
    }

    @GetMapping(value = REST_URL + "/filter", produces = "application/json; charset=utf-8")
    public List<Employee> getBetween(
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate) {
        LocalDate start = startDate == null ? LocalDate.of(1900, 1, 1) : LocalDate.parse(startDate);
        LocalDate end = endDate == null ? LocalDate.now() : LocalDate.parse(endDate);
        return service.getBetweenDates(start, end);
    }
}
