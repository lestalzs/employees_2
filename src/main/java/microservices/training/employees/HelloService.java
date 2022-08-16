package microservices.training.employees;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HelloService {
    public String sayHello(){
        return "Hello Spring boot service devtools 2  " + LocalDateTime.now();
    }

}
