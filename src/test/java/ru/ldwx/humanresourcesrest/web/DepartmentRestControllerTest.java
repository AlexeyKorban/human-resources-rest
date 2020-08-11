package ru.ldwx.humanresourcesrest.web;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ldwx.humanresourcesrest.DepartmentTestData.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql", config = @SqlConfig(encoding = "UTF-8"))
class DepartmentRestControllerTest {
    private static final String REST_URL = "http://127.0.0.1:8080/rest/department/";

    private final Gson gson = new Gson();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllDepartments() throws Exception {
        String departments = gson.toJson(List.of(DEPARTMENT_1, DEPARTMENT_2, DEPARTMENT_3, DEPARTMENT_4, DEPARTMENT_5, DEPARTMENT_6, DEPARTMENT_7));
        this.mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(departments));
    }

    @Test
    public void deleteDepartment() throws Exception {
        String departments = gson.toJson(List.of(DEPARTMENT_1, DEPARTMENT_2, DEPARTMENT_3, DEPARTMENT_4, DEPARTMENT_5, DEPARTMENT_6));
        this.mockMvc.perform(delete(REST_URL + DEPARTMENT_7.getId()))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(departments));
    }

    @Test
    public void getDepartment() throws Exception {
        String department = gson.toJson(DEPARTMENT_1);
        this.mockMvc.perform(get(REST_URL + DEPARTMENT_1.getName()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(department));
    }

    @Test
    public void createDepartment() throws Exception {
        String departmentToAdd = gson.toJson(DEPARTMENT_8);
        this.mockMvc.perform(post(REST_URL).contentType(MediaType.APPLICATION_JSON).content(departmentToAdd))
                .andDo(print())
                .andExpect(status().isOk());
    }
}