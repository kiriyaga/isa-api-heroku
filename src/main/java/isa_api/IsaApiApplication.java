package isa_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
 
public class IsaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsaApiApplication.class, args);
	}
}
