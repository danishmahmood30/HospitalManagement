package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private static final String dburl = "jdbc:mysql://localhost:3306/testdb";
	private static final String dbname="root";
	private static final String dbpassword="danish";
	private static final String dbdriver="com.mysql.cj.jdbc.Driver";
	

	
	public static Connection getConnection() {
		
		Connection con =null;
		try {
			Class.forName(dbdriver);
			con=DriverManager.getConnection(dburl,dbname,dbpassword);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
}
