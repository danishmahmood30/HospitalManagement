package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.bean.Patient;
import com.service.PatientService;
import com.util.SessionChecker;

/**
 * Servlet implementation class PatientController
 */
@WebServlet("/PatientController")
public class PatientController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PatientController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session =request.getSession(false);
		
		PatientService patientService = new PatientService();
		String action= request.getParameter("action");
		String button = request.getParameter("button");
		PrintWriter pw = response.getWriter();
		
		if(action==null) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}
		if((!action.equals("login"))&&(!SessionChecker.isValidSession(session))) {
			request.setAttribute("errormessage","Invalid Session. Please login again");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		if(action.equals("submit")) {
			
			int ssnId = Integer.parseInt(request.getParameter("ssnid"));
			String patientName = request.getParameter("name");
			int age =Integer.parseInt(request.getParameter("age"));
			String address = request.getParameter("address");
			String state = request.getParameter("state");
			String city = request.getParameter("city");
			String date = request.getParameter("date");
			String beds = request.getParameter("beds");
			
			System.out.println(ssnId);
			System.out.println(patientName);
			System.out.println(age);
			System.out.println(address);
			System.out.println(state);
			System.out.println(city);
			System.out.println(date);
			System.out.println(beds);
			//Patient patient = new Patient(ssnId,patientName,age,address,state,city);
			//int x= cs.createCustomer(customer);
			
			/*
			 * if(x>0) { RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
			 * request.setAttribute("ssnId", ssnId); request.setAttribute("status",
			 * "create"); rd.forward(request, response); }else {
			 * pw.println("<h1>Cannot Create Customer</h1>"); RequestDispatcher rd =
			 * request.getRequestDispatcher("createCustomer.jsp"); rd.forward(request,
			 * response); }
			 */
		}
	}

}
