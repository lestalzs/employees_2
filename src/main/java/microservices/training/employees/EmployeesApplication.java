package microservices.training.employees;

//import org.modelmapper.ModelMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@SpringBootApplication
public class EmployeesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);
	}
//	@Bean
//    public HelloService helloService(){
//		return new HelloService();
//	}

//	@Bean
//	public ModelMapper modelMapper()
//	{
//		return new ModelMapper();
//	}
//	@Bean
//	public ObjectMapper objectMapper(){
//		return new ObjectMapper().findAndRegisterModules();
//	}
}
