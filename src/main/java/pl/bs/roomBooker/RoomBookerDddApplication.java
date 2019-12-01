package pl.bs.roomBooker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "pl.bs.roomBooker.repository")
@SpringBootApplication
public class RoomBookerDddApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomBookerDddApplication.class, args);
	}

}
