package microservices.training.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = EmployeesController.class)
public class EmployeeControllerWebMvIT {
    @MockBean
    EmployeesService employeesService;
    @Autowired
    MockMvc mockMvc;
    @Test
    void testListEmployees() throws Exception{
        when(employeesService.listEmployees(any()))
                .thenReturn(List.of(
                                new EmployeeDTO(1L, "Jhon Doe"),
                                new EmployeeDTO(2L, "Jack Doe")
                        )
                );
        mockMvc.perform(get("/api/employees"))
                //.andDo(print());
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name",equalTo("Jhon Doe")));
    }

}
