package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.Medicine;
import com.bean.MedicineHistory;
import com.bean.Test;
import com.bean.TestHistory;
import com.util.DbConnection;

public class TestDao {
	public ArrayList<TestHistory> retrieveTest(int patientId){
		ArrayList<TestHistory> thlist = new ArrayList<TestHistory>();
		Connection con = DbConnection.getConnection();
		String sql = "select * from test_records where patient_id=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, patientId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				TestHistory th= new TestHistory();
				th.setPatientId(rs.getInt(1));
				th.setTestId(rs.getInt(2));
				thlist.add(th);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return thlist;
	}
	
	public Test getTest(int testId) {
		Test test = new Test();
		Connection con = DbConnection.getConnection();
		String sql = "select * from test where testid=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, testId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				test.setTestId(rs.getInt(1));
				test.setTestName(rs.getString(2));
				test.setTestRate(rs.getInt(3));
			}else {
				System.out.println("no test");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return test;
		}
		
		return test;
	}
	
	public Test getTestByName(String testName) {
		Test test = new Test();
		Connection con = DbConnection.getConnection();
		String sql = "select * from test where testname=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, testName);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				test.setTestId(rs.getInt(1));
				test.setTestName(rs.getString(2));
				test.setTestRate(rs.getInt(3));
			}else {
				System.out.println("no medicine");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return test;
		}
		
		return test;
	}
	
	public int updateTestRecord(ArrayList<Test> temp_test_list, int patientId) {
		int x=0;
		Connection con = DbConnection.getConnection();
		String sql = "insert into test_records values(?,?)";
		try {
			for(Test test: temp_test_list) {
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, patientId);
				ps.setInt(2, test.getTestId());
				x= ps.executeUpdate();
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
}


