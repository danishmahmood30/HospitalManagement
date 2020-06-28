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
	
	public Patient viewPatient(int ssnId) {
		PatientDao pd =new PatientDao();
		Patient p = pd.viewPatient(ssnId);
		return p;
	}
	
	public Patient viewPatient(int ssnId, boolean update) {
		PatientDao pd =new PatientDao();
		Patient p = pd.viewPatient(ssnId,update);
		return p;
	}
	
	public ArrayList<Patient> viewAll() {
		PatientDao pd = new PatientDao();
		ArrayList<Patient> plist = pd.viewAll();
		return plist;
	}
	
	public int updatePatient(Patient patient) throws SQLException {
		PatientDao pd = new PatientDao();
		return pd.updatePatient(patient);
	}
	
	public Patient deletePatient(int ssnId) {
		Patient x=this.viewPatient(ssnId);
		return x;
	}
	
	public int deletePatient(int ssnId, boolean confirmed) {
		PatientDao cd = new PatientDao();
		int x= cd.deletePatient(ssnId);
		return x;
	}
}
