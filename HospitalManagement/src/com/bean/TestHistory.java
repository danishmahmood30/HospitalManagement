package com.bean;

public class TestHistory {
	private int patientId;
	private int testId;
	
	public TestHistory() {
		
	}
	public TestHistory(int patientId, int testId) {
		super();
		this.patientId = patientId;
		this.testId = testId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	
	
}
