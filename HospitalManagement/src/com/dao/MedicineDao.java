package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.Medicine;
import com.bean.MedicineHistory;
import com.bean.Patient;
import com.util.DateConvertor;
import com.util.DbConnection;

public class MedicineDao {
	public ArrayList<MedicineHistory> retrieveMedicine(int patientId){
		ArrayList<MedicineHistory> mhlist = new ArrayList<MedicineHistory>();
		Connection con = DbConnection.getConnection();
		String sql = "select * from medicine_records where patient_id=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, patientId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				MedicineHistory mh= new MedicineHistory();
				mh.setPatientId(rs.getInt(1));
				mh.setMedicineId(rs.getInt(2));
				mh.setQuantityIssued(rs.getInt(3));
				mhlist.add(mh);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mhlist;
	}
	
	public Medicine getMedicine(int medicineId) {
		Medicine m = new Medicine();
		Connection con = DbConnection.getConnection();
		String sql = "select * from medicine where medicineid=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, medicineId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				m.setMedicineId(rs.getInt(1));
				m.setMedicineName(rs.getString(2));
				m.setQuantityAvailable(rs.getInt(3));
				m.setRateOfMedicine(rs.getInt(4));
			}else {
				System.out.println("no patient");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return m;
		}
		
		return m;
	}
	
	public Medicine getMedicineByName(String medicineName) {
		Medicine m = new Medicine();
		Connection con = DbConnection.getConnection();
		String sql = "select * from medicine where medicine_name=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, medicineName);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				m.setMedicineId(rs.getInt(1));
				m.setMedicineName(rs.getString(2));
				m.setQuantityAvailable(rs.getInt(3));
				m.setRateOfMedicine(rs.getInt(4));
			}else {
				System.out.println("no medicine");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return m;
		}
		
		return m;
	}
	
	public int updateMedicineRecord(ArrayList<Medicine> temp_med_list, int patientId) {
		int x=0;
		Connection con = DbConnection.getConnection();
		String sql = "insert into medicine_records values(?,?,?)";
		String sql2 ="select quantity from medicine where medicineid=?";
		String sql3 ="update medicine set quantity=? where medicineid=?";
		try {
			for(Medicine medicine: temp_med_list) {
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, patientId);
				ps.setInt(2, medicine.getMedicineId());
				ps.setInt(3, medicine.getQuantityAvailable());
				x= ps.executeUpdate();
				ps.close();
			}
			for(Medicine medicine: temp_med_list) {
				
				PreparedStatement ps = con.prepareStatement(sql2);
				ps.setInt(1, medicine.getMedicineId());
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					int res=0;
					int quantity = rs.getInt(1);
					int updatedQuantity = quantity-(medicine.getQuantityAvailable());
					System.out.println(updatedQuantity);
					PreparedStatement ps2 = con.prepareStatement(sql3);
					ps2.setInt(1, updatedQuantity);
					ps2.setInt(2, medicine.getMedicineId());
					res=ps2.executeUpdate();
					ps2.close();
				}
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
}
