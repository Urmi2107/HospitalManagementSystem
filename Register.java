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
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean status=false; 
		boolean statusEmail=false;
		boolean statusCredentials=false;
		
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		
		String name=request.getParameter("Name");  
		String contactNo=request.getParameter("Contact");  
		String email=request.getParameter("Email");  
		String userName=request.getParameter("Username");  
		String password=request.getParameter("password");  
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/hospital?useSSL=false", "root", "root");  
			
			
			PreparedStatement ps=con.prepareStatement(  
					"select * from tbl_registeruser where userName=? and password=?");
					
					ps.setString(1,userName);  
					ps.setString(2,password);  
					      
					ResultSet rs=ps.executeQuery();  
					statusCredentials=rs.next(); 
					
					if(email!="") {
						ps=con.prepareStatement(  
								"select * from tbl_registeruser where email=?");
						ps.setString(1,email); 
						rs=ps.executeQuery();  
						statusEmail=rs.next(); 
					}else {
						statusEmail=false;
					}
					
					status=statusCredentials?true:(statusEmail?true:false);
					
					if(status){ 
						request.setAttribute("registrationError","Please enter unique Email/username/password.");
						RequestDispatcher rd=request.getRequestDispatcher("register.jsp");  
						rd.include(request,response); 
			  
					}else {
						ps=con.prepareStatement(  
								"insert into tbl_registeruser(name,contactNo,email,userName,password) values(?,?,?,?,?)");  
			
						ps.setString(1,name);  
						ps.setString(2,contactNo);  
						ps.setString(3,email);  
						ps.setString(4,userName); 
						ps.setString(5,password);  
			          
						int i=ps.executeUpdate();  
						if(i>0) {  
							response.sendRedirect( "registrationSuccess.jsp" );
						}
					}
					con.close();		       
			}catch (Exception ex) {
				System.out.println(ex);
			}  
			          
			out.close();  
		}  
}
			  
		