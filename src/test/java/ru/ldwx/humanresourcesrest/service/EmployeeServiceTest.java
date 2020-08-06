package ru.ldwx.humanresourcesrest.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.ldwx.humanresourcesrest.HumanResourcesRestApplication;
import ru.ldwx.humanresourcesrest.model.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.ldwx.humanresourcesrest.DepartmentTestData.DEPARTMENT_1;
import static ru.ldwx.humanresourcesrest.EmployeeTestData.*;

@SpringJUnitConfig(HumanResourcesRestApplication.class)
@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql", config = @SqlConfig(encoding = "UTF-8"))
class EmployeeServiceTest {

    @Autowired
    private EmployeeService service;

    @Test
    void create() {
        Employee created = service.create(EMPLOYEE_7);
        assertEquals(List.of(EMPLOYEE_1, EMPLOYEE_2, EMPLOYEE_3, EMPLOYEE_4, EMPLOYEE_5, EMPLOYEE_6, created), service.getAll());
    }

    @Test
    void delete() {
        service.delete(EMPLOYEE_6.getId());
        assertEquals(List.of(EMPLOYEE_1, EMPLOYEE_2, EMPLOYEE_3, EMPLOYEE_4, EMPLOYEE_5), service.getAll());
    }

    @Test
    void get() {
         assertEquals(EMPLOYEE_1, service.get(EMPLOYEE_1.getId()));
    }

    @Test
    void getAll() {
        assertEquals(List.of(EMPLOYEE_1, EMPLOYEE_2, EMPLOYEE_3, EMPLOYEE_4, EMPLOYEE_5, EMPLOYEE_6), service.getAll());
    }

    @Test
    void getBetweenDates() {
        assertEquals(List.of(EMPLOYEE_1, EMPLOYEE_5, EMPLOYEE_6), service.getBetweenDates(LocalDate.parse("1985-01-01"), LocalDate.parse("1988-01-01")));
    }

    @Test
    void update() {
        Employee employee = new Employee(EMPLOYEE_ID, "Иванов Петр", LocalDate.parse("1987-01-01"), new BigDecimal("10000.00"), DEPARTMENT_1);
        service.update(employee);
        assertEquals(employee, service.get(EMPLOYEE_ID));
    }
}