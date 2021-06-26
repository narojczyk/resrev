package pl.jwn.resrev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableTransactionManagement
@EnableWebMvc
@EnableJpaRepositories
public class ResrevApplication {

	public static void main(String[] args) {

		SpringApplication.run(ResrevApplication.class, args);
	}

}
