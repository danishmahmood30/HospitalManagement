package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Medicine;
import com.bean.MedicineHistory;
import com.bean.Patient;
import com.bean.Test;
import com.bean.TestHistory;
import com.dao.PatientDao;
import com.service.MedicineService;
import com.service.PatientService;
import com.service.TestService;
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
		MedicineService medicineService = new MedicineService();
		TestService testService = new TestService();
		
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
				
				Patient patient = new Patient(ssnId,patientName,age,date1,beds,address,city,state,"active");
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
		else if(action.equals("search")) {
			
			int patientId = Integer.parseInt(request.getParameter("patientid"));
			System.out.println("Searching for patient");
			Patient patient= patientService.searchPatient(patientId);
			System.out.println(patient.getPatientName());
			if(patient.getPatientId()==0) {
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				session.setAttribute("error", "patient not found");
				rd.forward(request, response);
			}else {
				System.out.println(patient.getPatientName());
				RequestDispatcher rd = request.getRequestDispatcher("displayPatient.jsp");
				session.setAttribute("patient", patient);
				rd.forward(request, response);
			}
			
		}
		
		else if(action.equals("searchPharma")) {
			
			int patientId = Integer.parseInt(request.getParameter("patientid"));
			System.out.println("Searching for patient");
			Patient patient= patientService.searchPatient(patientId);
			
			MedicineService.temp_patient=patient;
			MedicineService.delTempMedList();
			ArrayList<MedicineHistory> mhList = medicineService.retrieveMedicine(patientId);
			if(patient.getPatientId()==0) {
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				session.setAttribute("error", "patient not found");
				rd.forward(request, response);
			}else {
				if(!mhList.isEmpty()) {
					ArrayList<Medicine> medicineList= new ArrayList<Medicine>();
					MedicineService.temp_issued_med_list=medicineList;
					session.setAttribute("medicineFound", "yes");
					for(MedicineHistory mh : mhList) {
						Medicine medicine= medicineService.getMedicine(mh.getMedicineId());
						medicine.setQuantityAvailable(mh.getQuantityIssued());
						medicineList.add(medicine);	
					}
					session.setAttribute("medicineList", medicineList);
				}else {
					session.setAttribute("medicineFound", "no");
				}
				RequestDispatcher rd = request.getRequestDispatcher("patientPharma.jsp");
				session.setAttribute("addMed", "no");
				session.setAttribute("patient", patient);
				rd.forward(request, response);
			}
			
		}
		
		else if(action.equals("searchDiagnostics")) {
			
			int patientId = Integer.parseInt(request.getParameter("patientid"));
			System.out.println("Searching for patient");
			Patient patient= patientService.searchPatient(patientId);
			
			TestService.temp_patient=patient;
			TestService.delTempTestList();
			
			ArrayList<TestHistory> thList = testService.retrieveTest(patientId);
			if(patient.getPatientId()==0) {
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				session.setAttribute("error", "patient not found");
				rd.forward(request, response);
			}else {
				if(!thList.isEmpty()) {
					ArrayList<Test> testList= new ArrayList<Test>();
					TestService.temp_issued_test_list=testList;
					session.setAttribute("testFound", "yes");
					for(TestHistory th : thList) {
						Test test= testService.getTest(th.getTestId());
						testList.add(test);	
					}
					session.setAttribute("testList", testList);
				}else {
					session.setAttribute("testFound", "no");
				}
				RequestDispatcher rd = request.getRequestDispatcher("patientDiagnostics.jsp");
				session.setAttribute("addTest", "no");
				session.setAttribute("patient", patient);
				rd.forward(request, response);
			}
			
		}

		
		else if(action.equals("update")) {
			
			int patientId = Integer.parseInt(request.getParameter("patientid"));
			System.out.println("Searching for update");
			Patient patient= patientService.searchPatient(patientId);
			if(patient.getPatientId()==0) {
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				session.setAttribute("error", "patient not found");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("updatePatient.jsp");
				session.setAttribute("patient", patient);
				rd.forward(request, response);
			}
			
		}
		
		else if(action.equals("Issue Medicine")) {
			session.setAttribute("addMed", "no");
			session.setAttribute("availability", "search");
			RequestDispatcher rd = request.getRequestDispatcher("issueMedicine.jsp");
			rd.forward(request, response);	
		}
		
		else if(action.equals("Add Diagnostics")) {
			session.setAttribute("addTest", "no");
			session.setAttribute("availability", "search");
			RequestDispatcher rd = request.getRequestDispatcher("addDiagnostics.jsp");
			rd.forward(request, response);	
		}
		else if(action.equals("search_medicine")) {
			String medicineName = request.getParameter("medicineName");
			Medicine medicine = medicineService.getMedicineByName(medicineName);
			System.out.println(medicine.getMedicineName());
			if((medicine.getMedicineId()!=0)&&(medicine.getQuantityAvailable()!=0)) {
				session.setAttribute("availability", "available");
				session.setAttribute("med", medicine);
			}else {
				session.setAttribute("availability","unavailable");
			}
			RequestDispatcher rd = request.getRequestDispatcher("issueMedicine.jsp");
			rd.forward(request, response);
		}
		
		else if(action.equals("search_test")) {
			String testName = request.getParameter("testName");
			Test test = testService.getTestByName(testName);
			System.out.println(test.getTestName());
			if((test.getTestId()!=0)) {
				session.setAttribute("availability", "available");
				session.setAttribute("test", test);
			}else {
				session.setAttribute("availability","unavailable");
			}
			RequestDispatcher rd = request.getRequestDispatcher("addDiagnostics.jsp");
			rd.forward(request, response);
		}
		
		else if(action.equals("Add Medicine")) {
			
			int med_id = Integer.parseInt(request.getParameter("med_id"));
			String med_name = request.getParameter("med_name");
			int med_quantity = Integer.parseInt(request.getParameter("quantity"));
			int med_rate = Integer.parseInt(request.getParameter("med_rate"));
			//String med_amount = request.getParameter("med_name");
			Medicine medicine = new Medicine(med_id,med_name,med_quantity,med_rate);
			System.out.println("hi");
			ArrayList<Medicine> temp_m_list = MedicineService.addTempMedList(medicine);
			System.out.println(temp_m_list.isEmpty());
			
			session.setAttribute("addMed", "yes");
			session.setAttribute("temp_med_list",temp_m_list);
			session.setAttribute("patient",MedicineService.temp_patient);
			
			RequestDispatcher rd = request.getRequestDispatcher("patientPharma.jsp");
			rd.forward(request, response);
		}
		
		else if(action.equals("Add Test")) {
			
			int test_id = Integer.parseInt(request.getParameter("test_id"));
			String test_name = request.getParameter("test_name");
			int test_rate = Integer.parseInt(request.getParameter("test_rate"));
			Test test = new Test(test_id,test_name,test_rate);
			System.out.println("hi");
			ArrayList<Test> temp_t_list = TestService.addTempTestList(test);
			System.out.println(temp_t_list.isEmpty());
			
			session.setAttribute("addTest", "yes");
			session.setAttribute("temp_test_list",temp_t_list);
			session.setAttribute("patient",TestService.temp_patient);
			
			RequestDispatcher rd = request.getRequestDispatcher("patientDiagnostics.jsp");
			rd.forward(request, response);
		}
		
		else if(action.equals("back_to_pharma")) {
			session.setAttribute("addMed", "yes");
			session.setAttribute("temp_med_list",MedicineService.temp_med_list);
			session.setAttribute("patient",MedicineService.temp_patient);
			session.setAttribute("medicineList", MedicineService.temp_issued_med_list);
			RequestDispatcher rd = request.getRequestDispatcher("patientPharma.jsp");
			rd.forward(request, response);
		}
		
		else if(action.equals("back_to_diagnostics")) {
			session.setAttribute("addMed", "yes");
			session.setAttribute("temp_test_list",TestService.temp_test_list);
			session.setAttribute("patient",TestService.temp_patient);
			session.setAttribute("testList", TestService.temp_issued_test_list);
			RequestDispatcher rd = request.getRequestDispatcher("patientDiagnostics.jsp");
			rd.forward(request, response);
		}
		
		else if(action.equals("update_medicine")) {
			int x= medicineService.updateMedicineRecords(MedicineService.temp_med_list,MedicineService.temp_patient.getPatientId());
			if(x>0) {
				int patientId=MedicineService.temp_patient.getPatientId();
				RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
				session.setAttribute("patientId", patientId);
				session.setAttribute("status","update_medicine");
				rd.forward(request, response);
				
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				session.setAttribute("error", "Medicine could not be issued");
				rd.forward(request, response);
			}
		}
		
		else if(action.equals("update_diagnostics")) {
			int x= testService.updateTestRecords(TestService.temp_test_list,TestService.temp_patient.getPatientId());
			if(x>0) {
				int patientId=TestService.temp_patient.getPatientId();
				RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
				session.setAttribute("patientId", patientId);
				session.setAttribute("status","update_test");
				rd.forward(request, response);
				
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				session.setAttribute("error", "Diagnostics could not be issued");
				rd.forward(request, response);
			}
		}
		
		else if(action.equals("Add Another Medicine")) {
			session.setAttribute("availability", "search");
			RequestDispatcher rd = request.getRequestDispatcher("issueMedicine.jsp");
			rd.forward(request, response);	
		}
		
		else if(action.equals("Add Another Test")) {
			session.setAttribute("availability", "search");
			RequestDispatcher rd = request.getRequestDispatcher("addDiagnostics.jsp");
			rd.forward(request, response);	
		}
		
		else if(action.equals("Update Details")) {
			int x=0;
			int patientId = Integer.parseInt(request.getParameter("patientid"));
			System.out.println(patientId);
			int ssnId = Integer.parseInt(request.getParameter("ssnid"));
			String patientName = request.getParameter("name");
			int age =Integer.parseInt(request.getParameter("age"));
			String address = request.getParameter("address");
			String state = request.getParameter("stt");
			String city = request.getParameter("city");
			String date = request.getParameter("date");
			String beds = request.getParameter("beds");
			Date date1= DateConvertor.stringToJavaDate(date);
			String status = request.getParameter("status");
			System.out.println("Updating patient");
			
			Patient patient = new Patient(ssnId,patientName,age,date1,beds,address,city,state,status);
			try {
				x= patientService.updatePatient(patient,patientId);
				if(x>0) {
					System.out.println("successfully updated patient");
					RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
					session.setAttribute("patientId", patientId);
					session.setAttribute("status","update");
					rd.forward(request, response);
					System.out.println("forwarding to success page");
				}else {
					RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
					session.setAttribute("error", "cannot update patient details");
					rd.forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				session.setAttribute("error", "cannot update patient details");
				rd.forward(request, response);
			}
			
		}
		else if(action.equals("delete")) {
			
			int patientId = Integer.parseInt(request.getParameter("patientid"));
			System.out.println("Searching for delete");
			Patient patient= patientService.searchPatient(patientId);
			if(patient.getPatientId()==0) {
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				session.setAttribute("error", "patient not found");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("deletePatient.jsp");
				session.setAttribute("patient", patient);
				rd.forward(request, response);
			}	
		}
		else if(action.equals("Confirm Delete")) {
			int patientId = Integer.parseInt(request.getParameter("patientid"));
			System.out.println("confirm delete");
			int x= patientService.deletePatient(patientId);
			if(x>0) {
				System.out.println("successfully deleted patient");
				RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
				session.setAttribute("patientId", patientId);
				session.setAttribute("status","delete");
				rd.forward(request, response);
				System.out.println("forwarding to success page");
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				session.setAttribute("error", "cannot delete patient");
				rd.forward(request, response);
			}
		}
		
		else if(action.equals("viewAll")) {
			ArrayList<Patient> plist=patientService.viewAll();
			RequestDispatcher rd = request.getRequestDispatcher("viewAllPatients.jsp");
			session.setAttribute("patientList", plist);
			rd.forward(request, response);
		}
		
		else if(action.equals("search_for_bill")) {
			
			int patientId = Integer.parseInt(request.getParameter("patientid"));
			System.out.println("Searching for billing");
			Patient patient= patientService.searchPatient(patientId);
			if(patient.getPatientId()==0) {
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				session.setAttribute("error", "patient not found");
				rd.forward(request, response);
			}else {
				ArrayList<MedicineHistory> mhList = medicineService.retrieveMedicine(patientId);
				ArrayList<TestHistory> thList = testService.retrieveTest(patientId);
				
				if(!mhList.isEmpty()) {
					ArrayList<Medicine> medicineList= new ArrayList<Medicine>();
					//MedicineService.temp_issued_med_list=medicineList;
					session.setAttribute("medicineFound", "yes");
					for(MedicineHistory mh : mhList) {
						Medicine medicine= medicineService.getMedicine(mh.getMedicineId());
						medicine.setQuantityAvailable(mh.getQuantityIssued());
						medicineList.add(medicine);	
					}
					session.setAttribute("medicineList", medicineList);
				}else {
					session.setAttribute("medicineFound", "no");
				}
				
				if(!thList.isEmpty()) {
					ArrayList<Test> testList= new ArrayList<Test>();
					TestService.temp_issued_test_list=testList;
					session.setAttribute("testFound", "yes");
					for(TestHistory th : thList) {
						Test test= testService.getTest(th.getTestId());
						testList.add(test);	
					}
					session.setAttribute("testList", testList);
				}else {
					session.setAttribute("testFound", "no");
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("patientBilling.jsp");
				session.setAttribute("patient", patient);
				rd.forward(request, response);
			}
			
		}
		
		else if(action.equals("confirm_bill")) {
			int patientId = Integer.parseInt(request.getParameter("patientId"));
			int x= patientService.dischargePatient(patientId);
			if(x>0) {
				RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
				session.setAttribute("patientId", patientId);
				session.setAttribute("status","discharge");
				rd.forward(request, response);
				System.out.println("forwarding to success page");
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				session.setAttribute("error", "cannot complete patient billing");
				rd.forward(request, response);
			}
			
		}
		else if(action.equals("cancel")) {
			System.out.println("clicked cancel");
			RequestDispatcher rd = request.getRequestDispatcher("reHome.jsp");
			rd.forward(request, response);
		}
		
		if(button!=null) {
			if(button.equals("regex_home")) {
				System.out.println("clicked home");
				RequestDispatcher rd = request.getRequestDispatcher("reHome.jsp");
				rd.forward(request, response);
			}
			else if(button.equals("pharma_home")) {
				System.out.println("clicked home");
				RequestDispatcher rd = request.getRequestDispatcher("pHome.jsp");
				rd.forward(request, response);
			}
			else if(button.equals("diagnostics_home")) {
				System.out.println("clicked home");
				RequestDispatcher rd = request.getRequestDispatcher("dHome.jsp");
				rd.forward(request, response);
			}
			
			else if(button.equals("cancel")) {
				System.out.println("clicked cancel");
				RequestDispatcher rd = request.getRequestDispatcher("reHome.jsp");
				rd.forward(request, response);
			}
			else if(button.equals("add_another_patient")) {
				System.out.println("clicked add another patient");
				RequestDispatcher rd = request.getRequestDispatcher("addPatient.jsp");
				rd.forward(request, response);
			}
			else if(button.equals("update_another")) {
				RequestDispatcher rd = request.getRequestDispatcher("searchForUpdate.jsp");
				rd.forward(request, response);
			}
			else if(button.equals("update_details")) {
				int patientId = Integer.parseInt(request.getParameter("patientid"));
				System.out.println("update");
				Patient patient= patientService.searchPatient(patientId);
				if(patient.getPatientId()==0) {
					RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
					session.setAttribute("error", "patient not found");
					rd.forward(request, response);
				}else {
					RequestDispatcher rd = request.getRequestDispatcher("updatePatient.jsp");
					session.setAttribute("patient", patient);
					rd.forward(request, response);
				}
			}
			else if(button.equals("search_another")) {
				RequestDispatcher rd = request.getRequestDispatcher("searchPatient.jsp");
				rd.forward(request, response);
			}
			
			else if(button.equals("start_bill")) {
				int patientId = Integer.parseInt(request.getParameter("patientid"));
				System.out.println("Searching for billing");
				Patient patient= patientService.searchPatient(patientId);
				if(patient.getPatientId()==0) {
					RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
					session.setAttribute("error", "patient not found");
					rd.forward(request, response);
				}else {
					ArrayList<MedicineHistory> mhList = medicineService.retrieveMedicine(patientId);
					ArrayList<TestHistory> thList = testService.retrieveTest(patientId);
					
					if(!mhList.isEmpty()) {
						ArrayList<Medicine> medicineList= new ArrayList<Medicine>();
						//MedicineService.temp_issued_med_list=medicineList;
						session.setAttribute("medicineFound", "yes");
						for(MedicineHistory mh : mhList) {
							Medicine medicine= medicineService.getMedicine(mh.getMedicineId());
							medicine.setQuantityAvailable(mh.getQuantityIssued());
							medicineList.add(medicine);	
						}
						session.setAttribute("medicineList", medicineList);
					}else {
						session.setAttribute("medicineFound", "no");
					}
					
					if(!thList.isEmpty()) {
						ArrayList<Test> testList= new ArrayList<Test>();
						TestService.temp_issued_test_list=testList;
						session.setAttribute("testFound", "yes");
						for(TestHistory th : thList) {
							Test test= testService.getTest(th.getTestId());
							testList.add(test);	
						}
						session.setAttribute("testList", testList);
					}else {
						session.setAttribute("testFound", "no");
					}
					
					RequestDispatcher rd = request.getRequestDispatcher("patientBilling.jsp");
					session.setAttribute("patient", patient);
					rd.forward(request, response);
				}
				
			}
			
			else if(button.equals("delete_another")) {
				RequestDispatcher rd = request.getRequestDispatcher("searchForDelete.jsp");
				rd.forward(request, response);
			}
		}
		System.out.println(action);	
		System.out.println(button);
	}

}
