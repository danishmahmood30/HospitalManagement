package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.Patient;
import com.util.DateConvertor;
import com.util.DbConnection;

public class PatientDao {
	public static int patientid=0;
	public int addPatient(Patient patient) {
		
		System.out.println("inside add patient");
		int x=0,patientId=0;
		Connection con = DbConnection.getConnection();
		System.out.println("connection ok!!");
		String sql = "insert into patient values(?,null,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, patient.getSsnId());
			ps.setString(2, patient.getPatientName());
			ps.setInt(3, patient.getAge());
			ps.setDate(4, DateConvertor.javaToSqlConvert(patient.getDateOfAdmission()));
			ps.setString(5, patient.getTypeOfBed());
			ps.setString(6, patient.getAddress());
			ps.setString(7, patient.getCity());
			ps.setString(8, patient.getState());
			ps.setString(9, patient.getStatus());
			x= ps.executeUpdate();
			
			if(x>0) {
				System.out.println("patient addedd");
				String fetchPatientId = "select patientid from patient where ssnid=?";
				PreparedStatement ps2 = con.prepareStatement(fetchPatientId);
				ps2.setInt(1, patient.getSsnId());
				ResultSet rs = ps2.executeQuery();
				System.out.println("fetching patient id");
				if(rs.next()) {
					patientId=rs.getInt(1);
					System.out.println(patientId);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error in add patient");
			e.printStackTrace();
		}
		
		return patientId;
	}
	
	public Patient viewPatient(int ssnId) {
		Patient c = new Patient();
		Connection con = DbConnection.getConnection();
		String sql = "select * from customer where ssnid=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, ssnId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				c.setSsnId(rs.getInt(1));
				c.setPatientId(rs.getInt(2));
				c.setPatientName(rs.getString(3));
				c.setAge(rs.getInt(4));
				c.setAddress(rs.getString(5));
				c.setState(rs.getString(6));
				c.setCity(rs.getString(7));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	
	}
	
	public Patient viewPatient(int ssnId, boolean update) {
		System.out.println(patientid);
		this.patientid=ssnId;
		Patient c= this.viewPatient(ssnId);
		return c;
	}
	
	public ArrayList<Patient> viewAll() {
		
		ArrayList<Patient> clist = new ArrayList<Patient>();
		Connection con = DbConnection.getConnection();
		String sql = "select * from customer";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Patient c = new Patient();
				c.setSsnId(rs.getInt(1));
				c.setPatientId(rs.getInt(2));
				c.setPatientName(rs.getString(3));
				c.setAge(rs.getInt(4));
				c.setAddress(rs.getString(5));
				c.setState(rs.getString(6));
				c.setCity(rs.getString(7));
				clist.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clist;
	
	}
	
	public int updatePatient(Patient patient) {
		int age, x=0;
		String name,address;
		Patient cus = this.viewPatient(patientid);
		Connection con = DbConnection.getConnection();
		String sql = "update customer set name=?, age=?, address=? where ssnid=?";
		
		if(patient.getPatientName()==null) {
			name=cus.getPatientName();
		}else {
			name=patient.getPatientName();
		}
		if(patient.getAge()==0) {
			age=cus.getAge();
		}else {
			age=patient.getAge();
		}
		if(patient.getAddress()==null) {
			address=cus.getAddress();
		}else {
			address=patient.getAddress();
		}
		PreparedStatement ps;
		try {
			ps= con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, address);
			ps.setInt(4, patientid);
			x=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
	public int deletePatient(int ssnId) {
		int x=0;
		Connection con = DbConnection.getConnection();
		String sql = "delete from customer where ssnid=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ssnId);
			x=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
}
