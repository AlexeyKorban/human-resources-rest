package ru.ldwx.humanresourcesrest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ldwx.humanresourcesrest.web.DepartmentRestController;
import ru.ldwx.humanresourcesrest.web.EmployeeRestController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HumanResourcesRestApplicationTests {

	@Autowired
	EmployeeRestController employeeRestController;

	@Autowired
	DepartmentRestController departmentRestController;

	@Test
	void contextLoads() {
		assertThat(employeeRestController).isNotNull();
		assertThat(departmentRestController).isNotNull();
	}

}
