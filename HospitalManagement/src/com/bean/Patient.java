package com.bean;

import java.util.Date;

public class Patient {
	private int ssnId;
	private int patientId;
	private String patientName;
	private int age;
	private Date dateOfAdmission;
	private String typeOfBed;
	private String address;
	private String city;
	private String state;
	private String status;
	
	public Patient() {
		
	}
	
	public Patient(int ssnId, String patientName, int age, Date dateOfAdmission, String typeOfBed,
			String address, String city, String state) {
		super();
		this.ssnId = ssnId;
		this.patientId = 0;
		this.patientName = patientName;
		this.age = age;
		this.dateOfAdmission = dateOfAdmission;
		this.typeOfBed = typeOfBed;
		this.address = address;
		this.city = city;
		this.state = state;
		this.status = "active";
	}
	public int getSsnId() {
		return ssnId;
	}
	public void setSsnId(int ssnId) {
		this.ssnId = ssnId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getDateOfAdmission() {
		return dateOfAdmission;
	}
	public void setDateOfAdmission(Date dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}
	public String getTypeOfBed() {
		return typeOfBed;
	}
	public void setTypeOfBed(String typeOfBed) {
		this.typeOfBed = typeOfBed;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
