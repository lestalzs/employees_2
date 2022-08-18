package microservices.training.employees;

//import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class EmployeesService {

//    private ModelMapper modelMapper;
    private EmployeeMapper employeeMapper;

    public EmployeesService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    private AtomicLong idGenerator = new AtomicLong(0);
    private List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
           new Employee(idGenerator.incrementAndGet(), "Jhon Doe"),
           new Employee(idGenerator.incrementAndGet(), "Jack Doe")
    )));

//    public EmployeesService(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }

    public List<EmployeeDTO> listEmployees(Optional<String> prefix) {
//        Type targetListType = new TypeToken<List<EmployeeDTO>>(){}.getType();
        List<Employee> filtered = employees.stream().filter(e ->prefix.isEmpty()|| e.getName().toLowerCase().startsWith(prefix.get().toLowerCase())).
                collect(Collectors.toList());
        return employeeMapper.toDto(filtered);
    }

    public EmployeeDTO findEmployeeById(long id) {
//        return modelMapper.map(employees.stream()
//                .filter(e->e.getId()==id).findAny()
//                .orElseThrow(()->new IllegalArgumentException("Employee not found" +id)), EmployeeDTO.class);
        return employeeMapper.toDto(findActEmployeeById(id));
    }

    public EmployeeDTO createEmployee(CreateEmployeeCommand createEmployeeCommand) {
        Employee employee = new Employee();
        employee.setId(idGenerator.incrementAndGet());
        employee.setName(createEmployeeCommand.getName());
        employees.add(employee);
        //return modelMapper.map(employee, EmployeeDTO.class);
        return employeeMapper.toDto(employee);
    }

    public EmployeeDTO updateEmployee(long id, UpdateEmployeeCommand updateEmployeeCommand) {
        Employee actEmployee = findActEmployeeById(id);
        actEmployee.setName(updateEmployeeCommand.getName());
        //return modelMapper.map(actEmployee,EmployeeDTO.class);
        return employeeMapper.toDto(actEmployee);
    }

    public void deleteEmployee(long id) {
        Employee actEmployee = findActEmployeeById(id);
        employees.remove(actEmployee);
    }

    private Employee findActEmployeeById(long id) throws  IllegalArgumentException{
        return employees.stream()
                .filter(e->e.getId()==id).findAny()
                .orElseThrow(()->new EmployeeNotFoundException(id));
    }
}
