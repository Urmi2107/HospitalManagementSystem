package com.hospital.management.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdatePatientDetails
 */
@WebServlet("/UpdatePatientDetails")
public class UpdatePatientDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePatientDetails() {
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
	    PreparedStatement ps;
	    HttpSession session=request.getSession(); 
	    PatientDetails patientDetails = new PatientDetails();
	    
	    long millis=System.currentTimeMillis();
	    String deptName="";
		String docName="";
	    
	    String patientName=request.getParameter("PatientName"); 
	    String gender= request.getParameter("gender");
	    String dateOfBirth=request.getParameter("DateofBirth");
		String patientContactNo=request.getParameter("PatientContact");  
		String patientEmail=request.getParameter("PatientEmail");  
		String patientAddress=request.getParameter("PatientAddress");  
		int patientId=Integer.parseInt(request.getParameter("patientId"));
		String department=(request.getParameter("department")!="" || request.getParameter("department")!=null)?request.getParameter("department"):request.getParameter("departmentHidden");  
		String doctor=(request.getParameter("doctor")!="" || request.getParameter("doctor")!=null)?request.getParameter("doctor"):request.getParameter("doctorHidden");
		String description=request.getParameter("description");  
		String DateofAdmission=request.getParameter("DateofAdmission");
		String DateofDischarge=request.getParameter("DateofDischarge");
		String bedNo=(request.getParameter("bedNo")!="")?request.getParameter("bedNo"):"";
		String bedNoHidden = (request.getParameter("bedNoHidden")!="")?request.getParameter("bedNoHidden"):"";
		
		Date dateOfDischarge = (DateofDischarge.equalsIgnoreCase("")|| DateofDischarge.equalsIgnoreCase(null)?null:Date.valueOf(DateofDischarge));
		int medicalDetailsId=0;
		int updateStatus=0;
		
		LocalDate today = LocalDate.now();
		LocalDate dob = LocalDate.parse(dateOfBirth);
		int age= Period.between(dob, today).getYears();
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/hospital?useSSL=false", "root", "root");
			ps=con.prepareStatement("select deptName from tbl_dept_doc_map where deptId=?");
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
				 
				 ps=con.prepareStatement(  
							"select * from tbl_patient_details where patientId=?");
						ps.setInt(1,patientId);  
					      
					rs=ps.executeQuery();  
					while (rs.next()) {
						medicalDetailsId=rs.getInt("medicalDetailsId");
					}
					
					ps=con.prepareStatement(  
		                    "update tbl_patient_details set patientName=?,gender=?,dateOfBirth=?,age=?,patientContactNo=?,patientEmail=?,patientAddress=?,modifiedBy=?,modifiedDate=? where patientId = ?");  
							ps.setString(1,patientName);  
							ps.setString(2,gender);  
							ps.setDate(3, Date.valueOf(dateOfBirth));
							ps.setInt(4, age); 
							ps.setString(5,patientContactNo);  
							ps.setString(6,patientEmail);  
							ps.setString(7,patientAddress);  
							ps.setString(8, (String)session.getAttribute("userName"));
							ps.setDate(9, new Date(millis));
							ps.setInt(10, patientId);
					
					updateStatus=ps.executeUpdate(); 
					if(updateStatus>0) {
						patientDetails.setPatientName(patientName);
						patientDetails.setGender(gender);
						patientDetails.setDateOfBirth(Date.valueOf(dateOfBirth));
						patientDetails.setAge(age);
						patientDetails.setPatientContactNo(patientContactNo);
						patientDetails.setPatientEmail(patientEmail);
						patientDetails.setPatientAddress(patientAddress);
				
				}
					if(bedNo.equalsIgnoreCase(bedNoHidden)) {
						ps=con.prepareStatement(  
			                    "update tbl_medical_details set deptId=?,medicalDept=?,docId=?,doctorName=?,description=?,DateofAdmission=?,DateofDischarge=?,modifiedBy=?,modifiedDate=? where medicalDetailsId = ?");  
								ps.setString(1, department);
								ps.setString(2,deptName); 
								ps.setString(3, doctor);
								ps.setString(4,docName);  
								ps.setString(5,description);  
								ps.setDate(6, Date.valueOf(DateofAdmission));
								ps.setDate(7, dateOfDischarge);
								ps.setString(8, (String)session.getAttribute("userName"));
								ps.setDate(9, new Date(millis));
								ps.setInt(10, medicalDetailsId);
						
						updateStatus=ps.executeUpdate(); 
						if(updateStatus>0) {
							patientDetails.setMedicalDept(deptName);
							patientDetails.setDoctorName(docName);
							patientDetails.setDescription(description);
							patientDetails.setDateOfAdmission(Date.valueOf(DateofAdmission));
							patientDetails.setDateOfDischarge(dateOfDischarge);
							patientDetails.setBedNo(bedNo);
						}
					
					}else {
						int bedStatus=0;
						if(bedNo!="" && bedNoHidden!="") {
							ps=con.prepareStatement(  
				                    "update tbl_medical_details set deptId=?,medicalDept=?,docId=?,doctorName=?,description=?,DateofAdmission=?,DateofDischarge=?,bedNo=?,modifiedBy=?,modifiedDate=? where medicalDetailsId = ?");  
									ps.setString(1, department);
									ps.setString(2,deptName);  
									ps.setString(3, doctor);
									ps.setString(4,docName);  
									ps.setString(5,description);  
									ps.setDate(6, Date.valueOf(DateofAdmission));
									ps.setDate(7, dateOfDischarge);
									ps.setString(8,bedNo);
									ps.setString(9, (String)session.getAttribute("userName"));
									ps.setDate(10, new Date(millis));
									ps.setInt(11, medicalDetailsId);
							
							updateStatus=ps.executeUpdate(); 
							if(updateStatus>0) {
								patientDetails.setMedicalDept(deptName);
								patientDetails.setDoctorName(docName);
								patientDetails.setDescription(description);
								patientDetails.setDateOfAdmission(Date.valueOf(DateofAdmission));
								patientDetails.setDateOfDischarge(dateOfDischarge);
								patientDetails.setBedNo(bedNo);
							}
							
							ps=con.prepareStatement("update tbl_bed_details set isVacant=? where bedNo=?");
							ps.setString(1, "N"); 
							ps.setString(2, bedNo); 
							bedStatus=ps.executeUpdate();
							
							ps=con.prepareStatement("update tbl_bed_details set isVacant=? where bedNo=?");
							ps.setString(1, "Y"); 
							ps.setString(2, bedNoHidden); 
							bedStatus=ps.executeUpdate();
						}
						
						if(bedNo!="" && bedNoHidden=="") {
							ps=con.prepareStatement(  
				                    "update tbl_medical_details set deptId=?,medicalDept=?,docId=?,doctorName=?,description=?,DateofAdmission=?,DateofDischarge=?,bedNo=?,modifiedBy=?,modifiedDate=? where medicalDetailsId = ?");  
									ps.setString(1, department);
									ps.setString(2,deptName);  
									ps.setString(3, doctor);
									ps.setString(4,docName);  
									ps.setString(5,description);  
									ps.setDate(6, Date.valueOf(DateofAdmission));
									ps.setDate(7, dateOfDischarge);
									ps.setString(8,bedNo);
									ps.setString(9, (String)session.getAttribute("userName"));
									ps.setDate(10, new Date(millis));
									ps.setInt(11, medicalDetailsId);
							
							updateStatus=ps.executeUpdate(); 
							if(updateStatus>0) {
								patientDetails.setMedicalDept(deptName);
								patientDetails.setDoctorName(docName);
								patientDetails.setDescription(description);
								patientDetails.setDateOfAdmission(Date.valueOf(DateofAdmission));
								patientDetails.setDateOfDischarge(dateOfDischarge);
								patientDetails.setBedNo(bedNo);
							}
							
							ps=con.prepareStatement("update tbl_bed_details set isVacant=? where bedNo=?");
							ps.setString(1, "N"); 
							ps.setString(2, bedNo); 
							bedStatus=ps.executeUpdate();
							
						}
						
						if(bedNo=="" && bedNoHidden!="") {
							ps=con.prepareStatement(  
				                    "update tbl_medical_details set deptId=?,medicalDept=?,docId=?,doctorName=?,description=?,DateofAdmission=?,DateofDischarge=?,bedNo=?,modifiedBy=?,modifiedDate=? where medicalDetailsId = ?");  
									ps.setString(1, department);
									ps.setString(2,deptName);  
									ps.setString(3, doctor);
									ps.setString(4,docName);  
									ps.setString(5,description);  
									ps.setDate(6, Date.valueOf(DateofAdmission));
									ps.setDate(7, dateOfDischarge);
									ps.setString(8,bedNo);
									ps.setString(9, (String)session.getAttribute("userName"));
									ps.setDate(10, new Date(millis));
									ps.setInt(11, medicalDetailsId);
							
							updateStatus=ps.executeUpdate(); 
							if(updateStatus>0) {
								patientDetails.setMedicalDept(deptName);
								patientDetails.setDoctorName(docName);
								patientDetails.setDescription(description);
								patientDetails.setDateOfAdmission(Date.valueOf(DateofAdmission));
								patientDetails.setDateOfDischarge(dateOfDischarge);
								patientDetails.setBedNo(bedNo);
							}
							ps=con.prepareStatement("update tbl_bed_details set isVacant=? where bedNo=?");
							ps.setString(1, "Y"); 
							ps.setString(2, bedNoHidden); 
							bedStatus=ps.executeUpdate();
							
						}
						
					}
					request.setAttribute("patientId",patientId);
					request.setAttribute("deptId",department);
					request.setAttribute("docId",doctor);
					request.setAttribute("actionToBePerformed", "view");
					
					rd=request.getRequestDispatcher("ViewPatientDetails");  
				     rd.forward(request,response);  
					
				 con.close();		         
					
		}catch (Exception ex) {
			System.out.println(ex);
		}  
	          
	    out.close();
	}

}
