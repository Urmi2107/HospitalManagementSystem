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

import org.apache.tomcat.util.buf.StringUtils;

import com.sun.org.apache.xml.internal.utils.StringBufferPool;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();
	    
	    RequestDispatcher rd;
	    HttpSession session=request.getSession(); 
	    String name="";  
		String contactNo="";  
		String email="";
	    String userName = session.getAttribute("userName").toString();  
	    String password = session.getAttribute("password").toString();
	    System.out.println(userName+"    "+password);
	  //  String welcomeMsg="Welcome "+session.getAttribute("userName").toString() ;
	    try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/hospital?useSSL=false", "root", "root");  
			
			
			PreparedStatement ps=con.prepareStatement(  
					"select * from tbl_registeruser where userName=? and password=?");
					
					ps.setString(1,userName);  
					ps.setString(2,password);  
					      
					ResultSet rs=ps.executeQuery();  
					while (rs.next()) {
						 name=rs.getString("name");  
						contactNo=rs.getString("contactNo");  
						email=rs.getString("email");
						userName = rs.getString("userName");
					}
					request.setAttribute("name",name);
					request.setAttribute("contactNo",contactNo);
					request.setAttribute("email",email);
					request.setAttribute("userName",userName);
					
			
					rd=request.getRequestDispatcher("editProfile.jsp");
					rd.forward(request,response);
			  	//response.sendRedirect("editProfile.jsp");
				     con.close();	
			}catch(Exception ex) {
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
	    
	    RequestDispatcher rd;
	    HttpSession session=request.getSession(); 
	    int userId=0;
	    int updateStatus=0;
	    boolean emailStatus=false;
	    String emailInTable="";
	    String nameInTable="";
	    String contactInTable="";
	    String name=request.getParameter("Name"); 
		String contactNo=request.getParameter("Contact");  
		String email=request.getParameter("Email"); 
	    String userName = session.getAttribute("userName").toString();  
	    String password = session.getAttribute("password").toString();
	    System.out.println(userName+"    "+password);
	    String welcomeMsg="Welcome "+session.getAttribute("userName").toString() ;
	    try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/hospital?useSSL=false", "root", "root");  
			
			
			PreparedStatement ps=con.prepareStatement(  
					"select * from tbl_registeruser where userName=? and password=?");
					
					ps.setString(1,userName);  
					ps.setString(2,password);  
					      
					ResultSet rset=ps.executeQuery();  
						while (rset.next()) {
							userId=rset.getInt("userId");
							emailInTable=rset.getString("email");
							nameInTable=rset.getString("name");
							contactInTable=rset.getString("contactNo");
							
						}
						
					if(email.equalsIgnoreCase(emailInTable)) {
						ps=con.prepareStatement(  
			                    "update tbl_registeruser set name=? , contactNo=? where userId=?");  
						ps.setString(1,name);  
						ps.setString(2, contactNo);
						ps.setInt(3,userId); 
						
						updateStatus=ps.executeUpdate(); 
						if(updateStatus>0) {
							request.setAttribute("name",name);
							request.setAttribute("contactNo",contactNo);
							request.setAttribute("email",email);
							request.setAttribute("userName",userName);
							request.setAttribute("welcomeMsg",welcomeMsg);
					
							rd=request.getRequestDispatcher("userHome.jsp");
							rd.forward(request,response);
						}
					}else {
						ps=con.prepareStatement(  
								"select * from tbl_registeruser where email=?");
						ps.setString(1,email); 
						ResultSet rs=ps.executeQuery();  
						emailStatus=rs.next(); 
						if(emailStatus) {
							System.out.println("Checkpoint emailstatus!!");
							request.setAttribute("editProfileError","Please enter unique Email ID.");
							request.setAttribute("name",nameInTable);
							request.setAttribute("contactNo",contactInTable);
							request.setAttribute("email",emailInTable);
							request.setAttribute("userName",userName);
							rd=request.getRequestDispatcher("editProfile.jsp");  
							rd.include(request,response); 
						}else {
							ps=con.prepareStatement(  
				                    "update tbl_registeruser set name=? , contactNo=?, email=? where userId=?");  
							ps.setString(1,name);  
							ps.setString(2, contactNo);
							ps.setString(3, email);
							ps.setInt(4,userId); 
							
							updateStatus=ps.executeUpdate(); 
							if(updateStatus>0) {
								request.setAttribute("name",name);
								request.setAttribute("contactNo",contactNo);
								request.setAttribute("email",email);
								request.setAttribute("userName",userName);
								request.setAttribute("welcomeMsg",welcomeMsg);
						
								rd=request.getRequestDispatcher("userHome.jsp");
								rd.forward(request,response);
						}
					}
				}
				     con.close();	
			}catch(Exception ex) {
				System.out.println(ex);
			}
			    out.close();

	}
}
