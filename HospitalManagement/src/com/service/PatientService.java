package com.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.Patient;
import com.dao.PatientDao;

public class PatientService {
	
	public int createPatient(Patient patient) {
		PatientDao pd = new PatientDao();
		int x=pd.addPatient(patient);
		return x;
	}
	
	public Patient searchPatient(int patientId) {
		PatientDao pd =new PatientDao();
		Patient p = pd.viewPatient(patientId);
		return p;
	}
	
	public ArrayList<Patient> viewAll() {
		PatientDao pd = new PatientDao();
		ArrayList<Patient> plist = pd.viewAll();
		return plist;
	}
	
	public int updatePatient(Patient patient, int patientid) throws SQLException {
		PatientDao pd = new PatientDao();
		return pd.updatePatient(patient, patientid);
	}
	
	public int deletePatient(int patientId) {
		PatientDao pd = new PatientDao();
		int x=pd.deletePatient(patientId);
		return x;
	}
	
	public int deletePatient(int ssnId, boolean confirmed) {
		PatientDao pd = new PatientDao();
		int x= pd.deletePatient(ssnId);
		return x;
	}
	
	public int dischargePatient(int patientId) {
		PatientDao pd = new PatientDao();
		int x= pd.dischargePatient(patientId);
		return x;
	}
}
