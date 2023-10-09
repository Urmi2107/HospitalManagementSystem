package com.hospital.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeptDocDao {
	
	Connection con;
    PreparedStatement ps;
    String query;
    ResultSet rs;
    
    public List<Department> getAllDepartment(){
    	 List<Department> deptList  = new ArrayList<>();
    	try{  
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/hospital?useSSL=false", "root", "root");  
		 ps=con.prepareStatement(  
					"select distinct deptId,deptName from tbl_dept_doc_map");
		 rs = ps.executeQuery();
		 while(rs.next()){
			 Department dept = new Department();
             dept.setDeptId(rs.getString("deptId"));
             dept.setDeptName(rs.getString("deptName"));
             deptList.add(dept);
         }
    	}catch (Exception ex) {
			System.out.println(ex);
		}
    	return deptList;
    }
    
    public List<Doctor> getDoctorByDepartment(String deptId){
        List<Doctor> docList = new ArrayList<>();
        try{
        	Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/hospital?useSSL=false", "root", "root");  
			
			if(!deptId.equalsIgnoreCase("")) {
				 ps=con.prepareStatement(  
							"select docId,docName from tbl_dept_doc_map where deptId=?");
				 ps.setString(1, deptId);
			}else {
				ps=con.prepareStatement(  
						"select docId,docName from tbl_dept_doc_map");
			}
				
		 rs = ps.executeQuery();
		 while(rs.next()){
			 Doctor doc = new Doctor();
             doc.setDocId(rs.getString("docId"));
             doc.setDocName(rs.getString("docName"));
             docList.add(doc);
         }
        }catch (Exception ex) {
			System.out.println(ex);
		}
    	return docList;
   }
    
	
	  public List<Doctor> getAllDoctor(){ List<Doctor> docList = new ArrayList<>();
			 try{ 
				 Class.forName("com.mysql.jdbc.Driver"); con=DriverManager.getConnection(
			  "jdbc:mysql://localhost:3306/hospital?useSSL=false", "root", "root");
			  ps=con.prepareStatement( "select docId,docName from tbl_dept_doc_map");
			  
			  rs = ps.executeQuery(); 
			  while(rs.next()){ Doctor doc = new Doctor();
			  doc.setDocId(rs.getString("docId"));
			  doc.setDocName(rs.getString("docName"));
			  docList.add(doc);
			  } 
		}catch (Exception ex) { 
			System.out.println(ex);
			} return docList;
			  
		}
	 
}


