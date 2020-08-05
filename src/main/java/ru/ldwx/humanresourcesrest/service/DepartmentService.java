package ru.ldwx.humanresourcesrest.service;

import ru.ldwx.humanresourcesrest.model.Department;

import java.util.List;

public interface DepartmentService {
    Department create(Department department);

    void delete(int id);

    List<Department> getAll();
}
