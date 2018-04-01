package by.ihi.ihispringboot;

import by.ihi.ihispringboot.config.JpaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IhiSpringBootApplication {

	public static void main(String[] args) {
//		SpringApplication.run(IhiSpringBootApplication.class, args);
		SpringApplication.run(new Class<?>[] {IhiSpringBootApplication.class, JpaConfig.class}, args);
	}
}
