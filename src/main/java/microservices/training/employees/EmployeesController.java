package microservices.training.employees;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping
    public List<EmployeeDTO> listEmployees(@RequestParam Optional<String> prefix)
    {
        System.out.println("input pre" + prefix);

        return employeesService.listEmployees(prefix);
    }
 //   @GetMapping("/{id}")
 //   public ResponseEntity findEmployeeById(@PathVariable("id") long id){
 //       try{
//            return ResponseEntity.ok(employeesService.findEmployeeById(id));
//        }catch(IllegalArgumentException iae){
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("/{id}")
    public ResponseEntity findEmployeeById(@PathVariable("id") long id){
            return ResponseEntity.ok(employeesService.findEmployeeById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO createEmployee(@RequestBody CreateEmployeeCommand createEmployeeCommand){
        return employeesService.createEmployee(createEmployeeCommand);
    }

    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable("id") long id, @RequestBody UpdateEmployeeCommand updateEmployeeCommand){
        return employeesService.updateEmployee(id, updateEmployeeCommand);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") long id){
        employeesService.deleteEmployee(id);

    }

/*    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException iae){
        Problem problem =
                Problem.builder()
                        .withType(URI.create("employee/not-found"))
                        .withTitle("Not found")
                        .withStatus(Status.NOT_FOUND)
                        .withDetail(iae.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }
*/
}
