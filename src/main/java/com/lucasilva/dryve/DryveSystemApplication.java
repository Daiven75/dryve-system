package com.lucasilva.dryve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class DryveSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DryveSystemApplication.class, args);
	}

}
