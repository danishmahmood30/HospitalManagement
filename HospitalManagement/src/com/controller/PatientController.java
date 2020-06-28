package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Patient;
import com.service.PatientService;
import com.util.DateConvertor;
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
		System.out.println("inside get");
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside post");
		HttpSession session =request.getSession(false);
		PatientService patientService = new PatientService();
		String action= request.getParameter("action");
		String button = request.getParameter("button");
		PrintWriter pw = response.getWriter();
		
		if(action==null) {
			System.out.println("null action");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}
		
		if((!action.equals("login"))&&(!SessionChecker.isValidSession(session))) {
		  request.setAttribute("errormessage","Invalid Session. Please login again");
		  request.getRequestDispatcher("error.jsp").forward(request, response); }
		 
		if(action.equals("submit")) {
				
				int ssnId = Integer.parseInt(request.getParameter("ssnid"));
				String patientName = request.getParameter("name");
				int age =Integer.parseInt(request.getParameter("age"));
				String address = request.getParameter("address");
				String state = request.getParameter("stt");
				String city = request.getParameter("city");
				String date = request.getParameter("date");
				String beds = request.getParameter("beds");
				Date date1= DateConvertor.stringToJavaDate(date);
				System.out.println("Adding patient");
				
				Patient patient = new Patient(ssnId,patientName,age,date1,beds,address,city,state);
				int patientId= patientService.createPatient(patient);
				System.out.println(patientId);
				if(patientId>0) { 
					System.out.println("successfully created new patient");
					RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
					session.setAttribute("patientId", patientId);
					session.setAttribute("status","create");
					rd.forward(request, response);
					System.out.println("forwarding to success page");
				}
				else {
					  pw.println("<h1>Cannot Create patient</h1>"); 
					  RequestDispatcher rd = request.getRequestDispatcher("addPatient.jsp"); 
					  rd.forward(request,response); }
				
			}
		
		if(button!=null) {
			if(button.equals("home")) {
				System.out.println("clicked home");
				RequestDispatcher rd = request.getRequestDispatcher("reHome.jsp");
				rd.forward(request, response);
			}
			else if(button.equals("cancel")) {
				System.out.println("clicked cancel");
				RequestDispatcher rd = request.getRequestDispatcher("reHome.jsp");
				rd.forward(request, response);
			}
			else if(button.equals(" clicked add another patient")) {
				RequestDispatcher rd = request.getRequestDispatcher("addPatient.jsp");
				rd.forward(request, response);
			}
		}
			
	}

}
