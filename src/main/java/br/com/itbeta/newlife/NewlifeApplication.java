package br.com.itbeta.newlife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class NewlifeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewlifeApplication.class, args);
	}

}
