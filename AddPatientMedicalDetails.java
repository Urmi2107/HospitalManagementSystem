package com.hospital.management.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddPatientMedicalDetails
 */
@WebServlet("/AddPatientMedicalDetails")
public class AddPatientMedicalDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPatientMedicalDetails() {
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
		HttpSession session=request.getSession(); 
		
		String deptName="";
		String docName="";
		String bedNo="";
		
		int patientId=Integer.parseInt(request.getParameter("patientId"));
		String department=request.getParameter("department");  
		String  doctor=request.getParameter("doctor");
		String description=request.getParameter("description");  
		String DateofAdmission=request.getParameter("DateofAdmission");
		String DateofDischarge=request.getParameter("DateofDischarge");
		System.out.println("BED NO====="+request.getParameter("bedNo"));
		int bedId=(request.getParameter("bedNo")!="")?Integer.parseInt(request.getParameter("bedNo")):0;
		long millis=System.currentTimeMillis(); 
		Date dateOfDischarge = (DateofDischarge.equalsIgnoreCase("")|| DateofDischarge.equalsIgnoreCase(null)?null:Date.valueOf(DateofDischarge));
		int medicalDetailsId=0;
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/hospital?useSSL=false", "root", "root");
			PreparedStatement ps=con.prepareStatement("select deptName from tbl_dept_doc_map where deptId=?");
			ps.setString(1, department);
			ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				deptName=rs.getString("deptName");
	         }
			
			 ps=con.prepareStatement("select docName from tbl_dept_doc_map where docId=?");
				ps.setString(1, doctor);
				rs = ps.executeQuery();
				 while(rs.next()){
					docName=rs.getString("docName");
		         }
				 if(bedId!=0) {
					 ps=con.prepareStatement("select bedNo from tbl_bed_details where bedId=?");
						ps.setInt(1, bedId);
						rs = ps.executeQuery();
						 while(rs.next()){
							bedNo=rs.getString("bedNo");
				         }
				 }
			
			ps=con.prepareStatement("insert into tbl_medical_details(deptId,medicalDept,docId,doctorName,description,DateofAdmission,DateofDischarge,bedNo,patientId,createdBy,createdDate) values(?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);  
			ps.setString(1, department);
			ps.setString(2,deptName);  
			ps.setString(3, doctor);
			ps.setString(4,docName);  
			ps.setString(5,description);  
			ps.setDate(6, Date.valueOf(DateofAdmission));
			ps.setDate(7, dateOfDischarge);
			ps.setString(8, bedNo);
			ps.setInt(9, patientId); 
			ps.setString(10, (String)session.getAttribute("userName"));
			ps.setDate(11, new Date(millis));
			int i=ps.executeUpdate();  
			
			if(i>0) {  
				rs = ps.getGeneratedKeys();
				while (rs.next()) {
				   medicalDetailsId=rs.getInt(1);
				}
				
				ps=con.prepareStatement("update tbl_patient_details set medicalDetailsId=? where patientId=?");
				ps.setInt(1, medicalDetailsId); 
				ps.setInt(2, patientId); 
				int status=ps.executeUpdate(); 
				
				ps=con.prepareStatement("update tbl_bed_details set isVacant=? where bedId=?");
				ps.setString(1, "N"); 
				ps.setInt(2, bedId); 
				int bedStatus=ps.executeUpdate();
				
				request.setAttribute("patientId",patientId);
				request.setAttribute("deptId",department);
				request.setAttribute("docId",doctor);
				
				RequestDispatcher rd=request.getRequestDispatcher("ViewPatientDetails");  
			     rd.forward(request,response);  
			}
		
		con.close();		         
			
		}catch (Exception ex) {
			System.out.println(ex);
		}  
	          
	    out.close();  
		
		
		
	}

}
