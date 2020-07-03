package com.service;

import java.util.ArrayList;

import com.bean.Medicine;
import com.bean.MedicineHistory;
import com.bean.Patient;
import com.bean.Test;
import com.bean.TestHistory;
import com.dao.MedicineDao;
import com.dao.TestDao;

public class TestService {
	public static Patient temp_patient = new Patient();
	public static ArrayList<Test> temp_test_list = new ArrayList<Test>();
	public static ArrayList<Test> temp_issued_test_list = new ArrayList<Test>();
	
	
	public ArrayList<TestHistory> retrieveTest(int patientId) {
		TestDao td = new TestDao();
		ArrayList<TestHistory> x = td.retrieveTest(patientId);
		return x;
	}
	
	public Test getTest(int testId) {
		TestDao td = new TestDao();
		Test test= td.getTest(testId);
		return test;
	}
	
	public Test getTestByName(String testName) {
		TestDao td = new TestDao();
		Test test= td.getTestByName(testName);
		return test;
	}
	
	public static ArrayList<Test> addTempTestList(Test test){
		System.out.println("bbb");
		temp_test_list.add(test);
		return temp_test_list;
	}
	
	public static void delTempTestList(){
		temp_test_list.clear();
	}
	
	public int updateTestRecords(ArrayList<Test> temp_test_list, int patientId) {
		TestDao td = new TestDao();
		int x = td.updateTestRecord(temp_test_list, patientId);
		return x;
	}
}
