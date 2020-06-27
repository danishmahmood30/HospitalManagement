package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.LoginService;
import com.util.DbConnection;
import com.util.SessionChecker;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public LoginController() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		HttpSession session =request.getSession(false);
		
		if(action==null) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}
		
		if((!action.equals("login"))&&(!SessionChecker.isValidSession(session))) {
			request.setAttribute("errormessage","Invalid Session. Please login again");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
		if(action.equals("login")) {
			
			String username=request.getParameter("username");
			String password =request.getParameter("password");
			String loginType = request.getParameter("loginType");
			
			LoginService l_service = new LoginService();
			
			if(loginType.equals("reg_exec")) {
				boolean isValid=l_service.registrationExecutiveLogin(username, password);
				
				if(isValid) {
					HttpSession newSession = request.getSession(true);
					RequestDispatcher rd = request.getRequestDispatcher("reHome.jsp");
					newSession.setAttribute("username",username );
					rd.forward(request, response);
				}else {
					System.out.println("login failed");
					request.setAttribute("error", "*invalid credentials");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
			
			else if(loginType.equals("pharmacist")) {
				boolean isValid=l_service.pharmacistLogin(username, password);
				if(isValid) {
					HttpSession newSession = request.getSession(true);
					RequestDispatcher rd = request.getRequestDispatcher("pHome.jsp");
					newSession.setAttribute("username",username );
					rd.forward(request, response);
				}else {
					System.out.println("login failed");
					request.setAttribute("error", "*invalid credentials");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
			
			else if(loginType.equals("diagnostic_exec")) {
				boolean isValid=l_service.diagnosticExecutiveLogin(username, password);
				if(isValid) {
					HttpSession newSession = request.getSession(true);
					RequestDispatcher rd = request.getRequestDispatcher("dHome.jsp");
					newSession.setAttribute("username",username );
					rd.forward(request, response);
				}else {
					System.out.println("login failed");
					request.setAttribute("error", "*invalid credentials");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
		}
		
		if(action.equals("logout")) {
			session.invalidate();
			request.setAttribute("errormessage", "logged out successfully");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		
		
		
		
		
		
	}

}
