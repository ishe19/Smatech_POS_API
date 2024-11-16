package tech.ishe.smatech_pos;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@OpenAPIDefinition(info = @Info(title = "SMATECH POS INTERVIEW API", version = "0.1", description = "REST API for POS Interview App"))
@EnableScheduling
@SpringBootApplication
public class SmatechPosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmatechPosApiApplication.class, args);
	}

}
