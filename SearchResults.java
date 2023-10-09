package com.hospital.management.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchResults
 */
@WebServlet("/SearchResults")
public class SearchResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchResults() {
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
		int lowerAge=0;
		int upperAge=0;
		
		String patientName=request.getParameter("PatientName").equalsIgnoreCase("")?null:request.getParameter("PatientName");
		Integer age = (!request.getParameter("age").equalsIgnoreCase(""))?Integer.parseInt(request.getParameter("age")):null;
		String department=request.getParameter("department").equalsIgnoreCase("")?null:request.getParameter("department");
		String doctor=request.getParameter("doctor").equalsIgnoreCase("")?null:request.getParameter("doctor");
		String DateofAdmission=request.getParameter("DateofAdmission");
		String DateofDischarge=request.getParameter("DateofDischarge");
		Integer bedId=(!request.getParameter("bedNo").equalsIgnoreCase(""))?Integer.parseInt(request.getParameter("bedNo")):null;
		Date dateOfDischarge = (DateofDischarge.equalsIgnoreCase("")|| DateofDischarge.equalsIgnoreCase(null)?null:Date.valueOf(DateofDischarge));
		Date dateOfAdmission = (DateofAdmission.equalsIgnoreCase("")|| DateofAdmission.equalsIgnoreCase(null)?null:Date.valueOf(DateofAdmission));
		int medicalDetailsId=0;
		String bedNo=null;
		int ageLimit = age==null?0:age;
		
		switch(ageLimit) {
		case 18: lowerAge=1;
				upperAge=18;
				 break;
		case 40: lowerAge=19;
				upperAge=40;
				break;
		case 60: lowerAge=41;
				upperAge=60;
				 break;
		case 80: lowerAge=61;
				upperAge=80;
				break;
		case 100: lowerAge=81;
				upperAge=100;
				 break;
		default: lowerAge=0;
				upperAge=0;
				break;
		}
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/hospital?useSSL=false", "root", "root");
			
			if(bedId!=null) {
				 ps=con.prepareStatement("select bedNo from tbl_bed_details where bedId=?");
					ps.setInt(1, bedId);
					rs = ps.executeQuery();
					 while(rs.next()){
						bedNo=rs.getString("bedNo");
			         }
			 }
			
			
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("patientName", patientName);
			parameters.put("age", age);
			//parameters.put("lowerAge", lowerAge);
			//parameters.put("upperAge", upperAge);
			parameters.put("deptId", department);
			parameters.put("docId", doctor);
			parameters.put("dateOfAdmission", dateOfAdmission);
			parameters.put("dateOfDischarge", dateOfDischarge);
			parameters.put("bedNo", bedNo); // find out the bedNo and pass it on the query!!!!
			
			String query="select * from tbl_patient_details INNER JOIN tbl_medical_details ON tbl_patient_details.patientId=tbl_medical_details.patientId ";
			boolean first=true;
			for (String paramName : parameters.keySet()) {
			    Object paramValue = parameters.get(paramName);
			    if (paramValue != null) {
			    	if(paramName=="age" && first) {
			    				System.out.println("Checkpoint 1 Age==="+paramValue.toString());
				        		 query += "where " + paramName + " BETWEEN ? AND ?";
				        		 first = false;
				         }else if(paramName=="patientName" && first){
				        	 System.out.println("Checkpoint 1 name==="+paramValue.toString());
			        		 query += "where " + paramName + " LIKE ?";
			        		 first = false;
				         }else if (first){
					        	query += "where " + paramName + "=?";
					            first = false;
					     }else if(!paramName.equalsIgnoreCase("age") && paramName!="patientName" && !first && paramValue != null){
					        	query += " and " + paramName + "=?";
					     }else if(paramName=="patientName" && !first && paramValue != null){
					    	 System.out.println("Checkpoint 2 name==="+paramValue.toString());
					    	 query += " and " +paramName+ " LIKE ?";
					     }else if(paramName=="age" && !first && paramValue != null){
					    	 System.out.println("Checkpoint 2 Age==="+paramValue.toString());
					    	 query += " and " +paramName+ " BETWEEN ? AND ?";
			        
			    }
			}
		}
		
			System.out.println(query.toString());
			ps=con.prepareStatement(query);  
			
			int paramNumber = 0;
			for (String paramName : parameters.keySet()) {
			    Object paramValue = parameters.get(paramName);
			    if (paramName.equalsIgnoreCase("age") && paramValue != null) {
			    	System.out.println("Lower==="+lowerAge+"   Upper====="+upperAge);
			    	 paramNumber++;
			    	 ps.setInt(paramNumber, lowerAge);
			    	 paramNumber++;
			    	 ps.setInt(paramNumber, upperAge);
			    	
			    }else if (!paramName.equalsIgnoreCase("age") && paramValue != null) {
			        if (paramValue instanceof Date) {
			        	 paramNumber ++;
			            ps.setDate(paramNumber, (Date) paramValue);
			        } else if (paramValue instanceof Integer) {
			        	 paramNumber ++;
			            ps.setInt(paramNumber, (Integer) paramValue);
			        //more types here...
			        }else if(paramName.equalsIgnoreCase("patientName") && paramValue != null) {
			        	System.out.println("N.A.M.E!");
			        	 paramNumber++;
				    	 ps.setString(paramNumber, '%'+ paramValue.toString() + '%');
			        }else {
			        	 paramNumber ++;
			            ps.setString(paramNumber, paramValue.toString());
			        }
			    }
			}
			
			 rs=ps.executeQuery();  
			 List<PatientDetails> patientDetailsList = new ArrayList<PatientDetails>();
			 int index=0;
			 ResultSetMetaData rsmd = rs.getMetaData();
			 int columnsNumber = rsmd.getColumnCount();
			 while (rs.next()) {
				 PatientDetails patientDetails = new PatientDetails();
				 
			     /*for (int i = 1; i <= columnsNumber; i++) {
			         if (i > 1) System.out.print(",  ");
			         String columnValue = rs.getString(i);
			         System.out.print(columnValue + " " + rsmd.getColumnName(i));
			     }
			     System.out.println("");*/
			     
			     	patientDetails.setPatientId(rs.getInt("patientId"));
			     	patientDetails.setPatientName(rs.getString("patientName"));
					patientDetails.setGender(rs.getString("gender"));
					patientDetails.setDateOfBirth(rs.getDate("dateOfBirth"));
					patientDetails.setAge(rs.getInt("age"));
					patientDetails.setPatientContactNo(rs.getString("patientContactNo"));
					patientDetails.setPatientEmail(rs.getString("patientEmail"));
					patientDetails.setPatientAddress(rs.getString("patientAddress"));
					patientDetails.setMedicalDept(rs.getString("medicalDept"));
					patientDetails.setDoctorName(rs.getString("doctorName"));
					patientDetails.setDescription(rs.getString("description"));
					patientDetails.setDateOfAdmission(rs.getDate("dateOfAdmission"));
					patientDetails.setDateOfDischarge(rs.getDate("dateOfDischarge"));
					patientDetails.setBedNo(rs.getString("bedNo"));
					
					patientDetailsList.add(index,patientDetails);
					index++;
			 }
			 for (PatientDetails result : patientDetailsList) {
				    System.out.println(result.getPatientId()+","+result.getPatientName()+","+result.getGender()+","+result.getDateOfBirth()+","+result.getAge()+","+result.getPatientContactNo()+","+result.getPatientAddress()+","+result.getPatientEmail()+","+result.getMedicalDept()+","+result.getDoctorName()+","+result.getDateOfAdmission()+","+result.getDateOfDischarge()+","+result.getBedNo()+","+result.getDescription()); 
				}
			 
			 if (patientDetailsList == null || patientDetailsList.isEmpty()) {
				    request.setAttribute("EmptySearchResultMsg","No records found corresponding to the search criteria.");
				 }else {
					request.setAttribute("patientDetailsList",patientDetailsList);
				 }
			
			 rd=request.getRequestDispatcher("showSearchResults.jsp");  
			 rd.forward(request,response);
			
			
	}catch(Exception ex) {
		System.out.println(ex);
	}

}
}
