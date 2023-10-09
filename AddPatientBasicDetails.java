package com.hospital.management.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddPatientBasicDetails
 */
@WebServlet("/AddPatientBasicDetails")
public class AddPatientBasicDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPatientBasicDetails() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		HttpSession session=request.getSession(); 
		RequestDispatcher rd;
		ResultSet rs;
		PreparedStatement ps;
		int count=0;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/hospital?useSSL=false", "root", "root");
			
			ps=con.prepareStatement(  
					"select * from tbl_bed_details where isVacant=?");
			
			ps.setString(1, "Y");
			      
			rs=ps.executeQuery();  
			while (rs.next()) {
				count++;
			}
			if(count==0) {
				response.sendRedirect("noBedAvailable.jsp");  
			}else {
				response.sendRedirect("addPatientBasicDetails.jsp");  
			}
			con.close();		
		}catch (Exception ex) {
			System.out.println(ex);
		}  
	          
	    out.close();  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		HttpSession session=request.getSession(); 
		
		String patientName=request.getParameter("PatientName");  
		String  gender=request.getParameter("gender");
		String dateOfBirth=request.getParameter("DateofBirth");
		String patientContactNo=request.getParameter("PatientContact");  
		String patientEmail=request.getParameter("PatientEmail");  
		String patientAddress=request.getParameter("PatientAddress");  
		
		int patientId=0;
		
		long millis=System.currentTimeMillis(); 
		
		LocalDate today = LocalDate.now();
		/*int years = dateOfBirth.getYear();
		int day = dateOfBirth.getDay();*/
		LocalDate dob = LocalDate.parse(dateOfBirth);
		int age= Period.between(dob, today).getYears();
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/hospital?useSSL=false", "root", "root");
			PreparedStatement ps=con.prepareStatement("insert into tbl_patient_details(patientName,gender,dateOfBirth,age,patientContactNo,patientEmail,patientAddress,createdBy,createdDate) values(?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);  
			
			ps.setString(1,patientName);  
			ps.setString(2,gender);  
			ps.setDate(3, Date.valueOf(dateOfBirth));
			ps.setInt(4, age); 
			ps.setString(5,patientContactNo);  
			ps.setString(6,patientEmail);  
			ps.setString(7,patientAddress);  
			ps.setString(8, (String)session.getAttribute("userName"));
			ps.setDate(9, new Date(millis));
			int i=ps.executeUpdate();  
			
			if(i>0) {  
				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) {
				   patientId=rs.getInt(1);
				}
				
				request.setAttribute("patientId",patientId);
				RequestDispatcher rd=request.getRequestDispatcher("addPatientMedicalDetails.jsp");  
			     rd.forward(request,response);  
			}
		
		con.close();		         
			
		}catch (Exception ex) {
			System.out.println(ex);
		}  
	          
	    out.close();  
	}

}
