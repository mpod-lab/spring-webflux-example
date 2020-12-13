package com.mpod.webfluxdemo;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mpod.model.Patient;
import com.mpod.repository.PatientRepository;

@SpringBootApplication
@ComponentScan({"com.mpod"})
@EnableReactiveMongoRepositories("com.mpod.repository")
public class WebfluxdemoApplication {
	

	@Bean
	CommandLineRunner patients(PatientRepository patientRepository) {

		return args -> {
			patientRepository
					.deleteAll()
					
			.subscribe(null, null, () -> {

				Stream.of(new Patient(UUID.randomUUID().toString(),"Farfala"),
						new Patient(UUID.randomUUID().toString(),"Abrabak"),
						new Patient(UUID.randomUUID().toString(),"Gawron")
						)
						.forEach(patient -> {
				patientRepository
						.save(patient)
						.subscribe(System.out::println);
						});

			})
			;
		};

	}

	public static void main(String[] args) {
		SpringApplication.run(WebfluxdemoApplication.class, args);
	}

}
