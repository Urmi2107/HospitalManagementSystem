package com.hospital.management.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewPatientDetails
 */
@WebServlet("/ViewPatientDetails")
public class ViewPatientDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPatientDetails() {
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
		
		PatientDetails patientDetails = new PatientDetails();
		
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		RequestDispatcher rd;
		ResultSet rs;
		PreparedStatement ps;
		int patientId=Integer.parseInt(request.getParameter("patientId"));
		int medicalDetailsId=0;
		String actionToBePerformed=request.getParameter("actionToBePerformed")!=null?request.getParameter("actionToBePerformed"):"";
		System.out.println("actionToBePerformed::::::   "+actionToBePerformed);
		int count=0;
		
		//String deptIdHidden=request.getParameter("deptId");
		//String docIdHidden=request.getParameter("docId");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/hospital?useSSL=false", "root", "root");
			
			ps=con.prepareStatement(  
					"select * from tbl_patient_details where patientId=?");
			
				ps.setInt(1,patientId);  
			      
			rs=ps.executeQuery();  
			while (rs.next()) {
				medicalDetailsId=rs.getInt("medicalDetailsId");
				count++;
			}
			
			if(count==0 && actionToBePerformed.equalsIgnoreCase("update")) {
				request.setAttribute("patientIdError","Patient ID does not exist.	Please enter correct Patient ID.");
				rd=request.getRequestDispatcher("patientId.jsp");  
		        rd.include(request,response);
			}else if(count==0 && actionToBePerformed.equalsIgnoreCase("search")) {
				request.setAttribute("patientIdErrorMsgSearch","Patient ID does not exist.	Please enter correct Patient ID.");
				rd=request.getRequestDispatcher("search.jsp");  
		        rd.include(request,response);
			}else {
			ps=con.prepareStatement(  
					"select * from tbl_patient_details where patientId=?");
				ps.setInt(1,patientId);  
			      
			rs=ps.executeQuery();  
			while (rs.next()) {
				patientDetails.setPatientName(rs.getString("patientName"));
				patientDetails.setGender(rs.getString("gender"));
				patientDetails.setDateOfBirth(rs.getDate("dateOfBirth"));
				patientDetails.setAge(rs.getInt("age"));
				patientDetails.setPatientContactNo(rs.getString("patientContactNo"));
				patientDetails.setPatientEmail(rs.getString("patientEmail"));
				patientDetails.setPatientAddress(rs.getString("patientAddress"));
				medicalDetailsId=rs.getInt("medicalDetailsId");
			}
			 ps=con.prepareStatement(  
						"select * from tbl_medical_details where medicalDetailsId=?");
					ps.setInt(1,medicalDetailsId);  
				      
				 rs=ps.executeQuery();  
				while (rs.next()) {
					patientDetails.setMedicalDept(rs.getString("medicalDept"));
					patientDetails.setDoctorName(rs.getString("doctorName"));
					patientDetails.setDescription(rs.getString("description"));
					patientDetails.setDateOfAdmission(rs.getDate("dateOfAdmission"));
					patientDetails.setDateOfDischarge(rs.getDate("dateOfDischarge"));
					patientDetails.setBedNo(rs.getString("bedNo"));
					request.setAttribute("deptId",rs.getString("deptId"));
					request.setAttribute("docId",rs.getString("docId"));
				}
				patientDetails.setPatientId(patientId);
				request.setAttribute("patientDetails",patientDetails);
				
				if(actionToBePerformed.equalsIgnoreCase("update")) {
					 rd=request.getRequestDispatcher("updatePatientDetails.jsp");  
				     rd.forward(request,response);
				}else {
					rd=request.getRequestDispatcher("viewPatientDetails.jsp");  
				     rd.forward(request,response);  
				}
				con.close();		
				}
		}catch (Exception ex) {
			System.out.println(ex);
		}  
	          
	    out.close();  
		
	}

}
