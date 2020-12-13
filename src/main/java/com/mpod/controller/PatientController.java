package com.mpod.controller;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpod.model.Patient;
import com.mpod.model.PatientEvent;
import com.mpod.repository.PatientRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired PatientRepository patientRepository;

	@GetMapping("/")
	public Flux<Patient> getAllPatients() {
		return this.patientRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<Patient> getPatientById(@PathVariable String id) {
		return this.patientRepository.findById(id);
	}
	
	@PostMapping("/")
	public void savePatient(@RequestBody Patient patient) {
		Patient newPatient = new Patient(UUID.randomUUID().toString(), patient.getName());
		this.patientRepository.save(newPatient).subscribe();
	}
	
	@GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<PatientEvent> getEvent(@PathVariable("id") final String patientId) {
		
		return this.patientRepository.findById(patientId)
				.flatMapMany(patient -> {
				  
				Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
				
				Flux<PatientEvent> patientEventFlux =
                        Flux.fromStream(
                                Stream.generate(() -> new PatientEvent(patient, new Date())));
				
						
				 return Flux.zip(interval, patientEventFlux)
                         .map(Tuple2::getT2);

                });

    }



}
