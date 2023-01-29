package net.javaguides.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class BusTicketWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusTicketWebappApplication.class, args);
	}

}
