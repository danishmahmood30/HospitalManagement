package com.service;

import com.dao.LoginDao;

public class LoginService {
	
	public boolean registrationExecutiveLogin(String username,String password) {
		LoginDao ld = new LoginDao();
		boolean result = ld.verifyRegistrationExecutive(username, password);
		return result;
	}
	
	public boolean pharmacistLogin(String username,String password) {
		LoginDao ld = new LoginDao();
		boolean result = ld.verifyPharmacist(username, password);
		return result;
	}
	
	public boolean diagnosticExecutiveLogin(String username,String password) {
		LoginDao ld = new LoginDao();
		boolean result = ld.verifyDiagnosticExecutive(username, password);
		return result;
	}
}
