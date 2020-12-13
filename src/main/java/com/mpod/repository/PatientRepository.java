package com.mpod.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.mpod.model.Patient;

public interface PatientRepository extends ReactiveMongoRepository<Patient, String> {

}
