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
	
	public Patient viewPatient(int patientId) {
		Patient p = new Patient();
		Connection con = DbConnection.getConnection();
		String sql = "select * from patient where patientid=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, patientId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				p.setSsnId(rs.getInt(1));
				p.setPatientId(rs.getInt(2));
				p.setPatientName(rs.getString(3));
				p.setAge(rs.getInt(4));
				p.setDateOfAdmission(rs.getDate(5));
				p.setTypeOfBed(rs.getString(6));
				p.setAddress(rs.getString(7));
				p.setCity(rs.getString(8));
				p.setState(rs.getString(9));
				p.setStatus(rs.getString(10));
			}else {
				System.out.println("no patient");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return p;
		}
		
		return p;
	
	}
	
	public ArrayList<Patient> viewAll() {
		
		ArrayList<Patient> plist = new ArrayList<Patient>();
		Connection con = DbConnection.getConnection();
		String sql = "select * from patient where status='active'";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Patient p = new Patient();
				p.setSsnId(rs.getInt(1));
				p.setPatientId(rs.getInt(2));
				p.setPatientName(rs.getString(3));
				p.setAge(rs.getInt(4));
				p.setDateOfAdmission(rs.getDate(5));
				p.setTypeOfBed(rs.getString(6));
				p.setAddress(rs.getString(7));
				p.setCity(rs.getString(8));
				p.setState(rs.getString(9));
				p.setStatus(rs.getString(10));
				plist.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return plist;
	
	}
	
	public int updatePatient(Patient patient, int patientId) {
		int x=0;
		Connection con = DbConnection.getConnection();
		String sql = "update patient set ssnid=?, name=?, age=?, doj=?, bedtype=?, address=?, city=?, state=?, status=? where patientid=?";
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
			ps.setInt(10, patientId);
			x= ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
	public int deletePatient(int patientId) {
		int x=0;
		Connection con = DbConnection.getConnection();
		String sql = "delete from patient where patientid=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, patientId);
			x=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
	public int dischargePatient(int patientId) {
		int x=0;
		System.out.println("hiiiii");
		Connection con = DbConnection.getConnection();
		String sql = "update patient set status='Discharged' where patientid=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, patientId);
			x=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
}
