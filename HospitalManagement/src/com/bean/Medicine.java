package com.bean;

public class Medicine {
	private int medicineId;
	private String medicineName;
	private int quantityAvailable;
	private int rateOfMedicine;
	
	public Medicine(int medicineId, String medicineName, int quantityAvailable, int rateOfMedicine) {
		super();
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.quantityAvailable = quantityAvailable;
		this.rateOfMedicine = rateOfMedicine;
	}

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public int getRateOfMedicine() {
		return rateOfMedicine;
	}

	public void setRateOfMedicine(int rateOfMedicine) {
		this.rateOfMedicine = rateOfMedicine;
	}
	
	
}
