package ru.ldwx.humanresourcesrest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import ru.ldwx.humanresourcesrest.model.Department;

import java.util.List;

@Controller
public interface DepartmentRepository extends CrudRepository<Department, Integer> {
}
