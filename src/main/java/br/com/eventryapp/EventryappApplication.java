package br.com.eventryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"br.com.eventryapp.controllers"}) 
@EnableJpaRepositories("br.com.eventryapp.persistence")
public class EventryappApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventryappApplication.class, args);
	}
}
