package com.hospital.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BedNoDao {
	Connection con;
    PreparedStatement ps;
    String query;
    ResultSet rs;
    
    public List<BedNo> getAllBedNo(){
    	 List<BedNo> bedList  = new ArrayList<>();
    	try{  
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/hospital?useSSL=false", "root", "root");  
		 ps=con.prepareStatement(  
					"select * from tbl_bed_details where isVacant=?");
		 ps.setString(1, "Y");
		 rs = ps.executeQuery();
		 while(rs.next()){
			 BedNo bed = new BedNo();
             bed.setBedId(rs.getInt("bedId"));
             bed.setBedNo(rs.getString("bedNo"));
             bed.setIsVacant(rs.getString("isVacant"));
             bedList.add(bed);
         }
    	}catch (Exception ex) {
			System.out.println(ex);
		}
    	return bedList;
    }
    
    public List<BedNo> getAllBedNos(){
   	 List<BedNo> bedList  = new ArrayList<>();
   	try{  
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/hospital?useSSL=false", "root", "root");  
		 ps=con.prepareStatement(  
					"select * from tbl_bed_details");
		 rs = ps.executeQuery();
		 while(rs.next()){
			 BedNo bed = new BedNo();
            bed.setBedId(rs.getInt("bedId"));
            bed.setBedNo(rs.getString("bedNo"));
            bed.setIsVacant(rs.getString("isVacant"));
            bedList.add(bed);
        }
   	}catch (Exception ex) {
			System.out.println(ex);
		}
   	return bedList;
   }

}
