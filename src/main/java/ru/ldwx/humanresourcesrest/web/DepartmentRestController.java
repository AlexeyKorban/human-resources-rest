package ru.ldwx.humanresourcesrest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ldwx.humanresourcesrest.model.Department;
import ru.ldwx.humanresourcesrest.service.DepartmentService;

import java.util.List;

@RestController
public class DepartmentRestController {
    private static final String REST_URL = "rest/department";

    private final DepartmentService service;

    @Autowired
    public DepartmentRestController(DepartmentService departmentService) {
        this.service = departmentService;
    }

    @PostMapping(value = REST_URL, consumes = "application/json")
    public ResponseEntity<Department> create(@RequestBody Department department) {
        service.create(department);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping(value = REST_URL + "/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @GetMapping(value = REST_URL)
    public List<Department> getAll() {
        return service.getAll();
    }

    @GetMapping(value = REST_URL + "/{name}")
    public Department get(@PathVariable("name") String name) {
        return service.get(name);
    }
}
