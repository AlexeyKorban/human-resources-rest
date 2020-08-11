package ru.ldwx.humanresourcesrest.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;
import ru.ldwx.humanresourcesrest.model.Employee;
import ru.ldwx.humanresourcesrest.util.LocalDateAdapter;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ldwx.humanresourcesrest.EmployeeTestData.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql", config = @SqlConfig(encoding = "UTF-8"))
class EmployeeRestControllerTest {
    private static final String REST_URL = "http://127.0.0.1:8080/rest/employee/";
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllEmployees() throws Exception {
        String employees = gson.toJson(List.of(EMPLOYEE_1, EMPLOYEE_2, EMPLOYEE_3, EMPLOYEE_4, EMPLOYEE_5, EMPLOYEE_6));
        this.mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(employees));
    }

    @Test
    public void getEmployee() throws Exception {
        String employee = gson.toJson(EMPLOYEE_1);
        this.mockMvc.perform(get(REST_URL + EMPLOYEE_1.getId()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(employee));
    }

    @Test
    public void deleteEmployee() throws Exception {
        String employees = gson.toJson(List.of(EMPLOYEE_1, EMPLOYEE_2, EMPLOYEE_3, EMPLOYEE_4, EMPLOYEE_5));
        this.mockMvc.perform(delete(REST_URL + EMPLOYEE_6.getId()))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(employees));
    }

    @Test
    public void getBetween() throws Exception {
        String employees = gson.toJson(List.of(EMPLOYEE_1, EMPLOYEE_5, EMPLOYEE_6));
        this.mockMvc.perform(get(REST_URL + "filter?startDate=1985-01-01&endDate=1988-01-01"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(employees));
    }

    @Test
    public void getBetweenNullDates() throws Exception {
        String employees = gson.toJson(List.of(EMPLOYEE_1, EMPLOYEE_2, EMPLOYEE_3, EMPLOYEE_4, EMPLOYEE_5, EMPLOYEE_6));
        this.mockMvc.perform(get(REST_URL + "filter"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(employees));
    }

    @Test
    public void createEmployee() throws Exception {
        String employeeToAdd = gson.toJson(EMPLOYEE_7);
        this.mockMvc.perform(post(REST_URL).contentType(MediaType.APPLICATION_JSON).content(employeeToAdd))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateEmployee() throws Exception {
        Employee editedEmployee = new Employee(EMPLOYEE_1.getId(), "Новое Имя", EMPLOYEE_1.getDateOfBirth(), EMPLOYEE_1.getSalary(), EMPLOYEE_1.getDepartment());
        String expectedEmployee = gson.toJson(editedEmployee);
        this.mockMvc.perform(post(REST_URL).contentType(MediaType.APPLICATION_JSON).content(expectedEmployee))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get(REST_URL + EMPLOYEE_1.getId()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(expectedEmployee));
    }
}