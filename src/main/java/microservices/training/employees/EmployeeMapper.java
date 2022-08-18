package microservices.training.employees;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDTO toDto(Employee employee);
    List<EmployeeDTO> toDto(List<Employee> employees);
}
