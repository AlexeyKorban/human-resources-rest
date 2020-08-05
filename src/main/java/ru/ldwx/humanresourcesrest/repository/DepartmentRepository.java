package ru.ldwx.humanresourcesrest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import ru.ldwx.humanresourcesrest.model.Department;

@Controller
public interface DepartmentRepository extends CrudRepository<Department, Integer> {
}
