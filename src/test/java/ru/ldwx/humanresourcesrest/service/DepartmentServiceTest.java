package ru.ldwx.humanresourcesrest.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.ldwx.humanresourcesrest.HumanResourcesRestApplication;
import ru.ldwx.humanresourcesrest.model.Department;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.ldwx.humanresourcesrest.DepartmentTestData.*;

@SpringBootTest
@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql", config = @SqlConfig(encoding = "UTF-8"))
class DepartmentServiceTest {

    @Autowired
    private DepartmentService service;


    @Test
    void delete() {
        service.delete(DEPARTMENT_7.getId());
        assertEquals(new ArrayList<>(List.of(DEPARTMENT_1, DEPARTMENT_2, DEPARTMENT_3, DEPARTMENT_4, DEPARTMENT_5, DEPARTMENT_6)), service.getAll());
    }

    @Test
    void add() {
        Department department = service.create(DEPARTMENT_8);
        assertEquals(new ArrayList<>(List.of(DEPARTMENT_1, DEPARTMENT_2, DEPARTMENT_3, DEPARTMENT_4, DEPARTMENT_5, DEPARTMENT_6, DEPARTMENT_7, department)), service.getAll());
    }

    @Test
    void getAll() {
        assertEquals(new ArrayList<>(List.of(DEPARTMENT_1, DEPARTMENT_2, DEPARTMENT_3, DEPARTMENT_4, DEPARTMENT_5, DEPARTMENT_6, DEPARTMENT_7)), service.getAll());
    }

    @Test
    void get() {
        assertEquals(DEPARTMENT_1, service.get(DEPARTMENT_1.getName()));
    }
}