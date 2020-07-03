package com.service;

import java.util.ArrayList;

import com.bean.Medicine;
import com.bean.MedicineHistory;
import com.bean.Patient;
import com.bean.Test;
import com.dao.MedicineDao;

public class MedicineService {
	public static Patient temp_patient = new Patient();
	public static ArrayList<Medicine> temp_med_list = new ArrayList<Medicine>();
	public static ArrayList<Medicine> temp_issued_med_list = new ArrayList<Medicine>();
	public static ArrayList<Test> temp_test_list;
	
	public ArrayList<MedicineHistory> retrieveMedicine(int patientId) {
		MedicineDao md = new MedicineDao();
		ArrayList<MedicineHistory> x = md.retrieveMedicine(patientId);
		return x;
	}
	
	public Medicine getMedicine(int medicineId) {
		MedicineDao md = new MedicineDao();
		Medicine medicine= md.getMedicine(medicineId);
		return medicine;
	}
	
	public Medicine getMedicineByName(String medicineName) {
		MedicineDao md = new MedicineDao();
		Medicine medicine= md.getMedicineByName(medicineName);
		return medicine;
	}
	
	public static ArrayList<Medicine> addTempMedList(Medicine medicine){
		System.out.println("bbb");
		temp_med_list.add(medicine);
		return temp_med_list;
	}
	
	public static void delTempMedList(){
		temp_med_list.clear();
	}
	
	public int updateMedicineRecords(ArrayList<Medicine> temp_med_list, int patientId) {
		MedicineDao md = new MedicineDao();
		int x = md.updateMedicineRecord(temp_med_list, patientId);
		return x;
	}
}
