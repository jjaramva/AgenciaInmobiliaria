package co.com.udem.crudagenciaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import co.com.udem.crudagenciaclient.entities.UserToken;

@SpringBootApplication
public class CrudagenciaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudagenciaClientApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	UserToken userToken() {
		return new UserToken();
	}
}
