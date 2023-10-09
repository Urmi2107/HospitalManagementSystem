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

/**
 * Servlet implementation class DeletePatientDetails
 */
@WebServlet("/DeletePatientDetails")
public class DeletePatientDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePatientDetails() {
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
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		RequestDispatcher rd;
		ResultSet rs;
		PreparedStatement ps;
		
		int patientId=Integer.parseInt(request.getParameter("patientId"));
		int medicalDetailsId=0;
		int count=0;
		
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
				
				if(count==0) {
					request.setAttribute("patientIdErrorMsg","Patient ID does not exist.	Please enter correct Patient ID.");
			        rd=request.getRequestDispatcher("deletePatient.jsp");  
			        rd.include(request,response);
				}else {
					String bedNo="";
				ps=con.prepareStatement(  
							"select * from tbl_medical_details where medicalDetailsId=?");
						ps.setInt(1,medicalDetailsId);  
						rs=ps.executeQuery();  
						while (rs.next()) {
							bedNo=rs.getString("bedNo");
						}		
						
				ps=con.prepareStatement(  
						"delete from tbl_patient_details WHERE patientId =?");
				ps.setInt(1,patientId);  
				      
				int statusPatient=ps.executeUpdate();  
				
				ps=con.prepareStatement(  
						"delete from tbl_medical_details WHERE medicalDetailsId =?");
				ps.setInt(1,medicalDetailsId);  
				      
				int statusMedical=ps.executeUpdate();  
				
				if(statusMedical>0 && statusPatient>0) {
					ps=con.prepareStatement("update tbl_bed_details set isVacant=? where bedNo=?");
					ps.setString(1, "Y"); 
					ps.setString(2, bedNo); 
					int bedStatus=ps.executeUpdate();
					String deletePatientDetailsMsg="Record of Patient ID : "+patientId+" has been deleted successfully.";
					request.setAttribute("deletePatientDetailsMsg", deletePatientDetailsMsg);
					rd=request.getRequestDispatcher("deletePatient.jsp");  
			        rd.include(request,response); 
				}else {
					String deletePatientDetailsMsg="Sorry, Record of Patient ID : "+patientId+" can not be deleted.";
					request.setAttribute("deletePatientDetailsMsg", deletePatientDetailsMsg);
					rd=request.getRequestDispatcher("deletePatient.jsp");  
			        rd.include(request,response); 
				}
			}
			
			con.close();	
	}catch (Exception ex) {
		System.out.println(ex);
	}  
          
    out.close();  
	}
}
