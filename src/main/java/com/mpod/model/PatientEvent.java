package com.mpod.model;

import java.util.Date;

public class PatientEvent {

	private Patient patient;
	private Date date;
	
	public PatientEvent(Patient patient, Date date) {
		super();
		this.patient = patient;
		this.date = date;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
