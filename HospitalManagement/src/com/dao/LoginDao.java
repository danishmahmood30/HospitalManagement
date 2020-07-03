package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;

import com.util.DbConnection;

public class LoginDao {
	
	public boolean verifyRegistrationExecutive(String username, String password) {
		Connection con = DbConnection.getConnection();

		try {
			String query= "select password from userstore where login=? ";
			PreparedStatement ps= con.prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			System.out.println(rs);
			if(rs.next()) {
				if(rs.getString(1).equals(password)){
					System.out.println("successful login");
					return true;
				}else {
					System.out.println("login failed");
					return false;
				}
			}else {
				System.out.println("login failed");
				return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean verifyPharmacist(String username, String password) {
		Connection con = DbConnection.getConnection();

		try {
			String query= "select password from testdb.pharma_login where username=? ";
			PreparedStatement ps= con.prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			System.out.println(rs);
			if(rs.next()) {
				if(rs.getString(1).equals(password)){
					System.out.println("successful login");
					return true;
				}else {
					System.out.println("login failed");
					return false;
				}
			}else {
				System.out.println("login failed");
				return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean verifyDiagnosticExecutive(String username, String password) {
		Connection con = DbConnection.getConnection();

		try {
			String query= "select password from testdb.diagex_login where username=? ";
			PreparedStatement ps= con.prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			System.out.println(rs);
			if(rs.next()) {
				if(rs.getString(1).equals(password)){
					System.out.println("successful login");
					return true;
				}else {
					System.out.println("login failed");
					return false;
				}
			}else {
				System.out.println("login failed");
				return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
