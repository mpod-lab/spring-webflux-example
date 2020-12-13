package com.mpod.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document 
public class Patient {
	@Id
	private String patientId;
	private String name;
	
	public Patient() {
	
	}

	public Patient(String patientId, String name) {
		this.patientId = patientId;
		this.name = name;
	}

	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", name=" + name + "]";
	}
	
	
}
