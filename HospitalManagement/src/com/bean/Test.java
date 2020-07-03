package com.bean;

public class Test {
	private int testId;
	private String testName;
	private int testRate;
	
	public Test() {
		
	}
	public Test(int testId, String testName, int testRate) {
		super();
		this.testId = testId;
		this.testName = testName;
		this.testRate = testRate;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public int getTestRate() {
		return testRate;
	}

	public void setTestRate(int testRate) {
		this.testRate = testRate;
	}
	
	
}
