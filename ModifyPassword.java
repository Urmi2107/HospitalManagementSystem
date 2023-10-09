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
 * Servlet implementation class ModifyPassword
 */
@WebServlet("/ModifyPassword")
public class ModifyPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		
		int statusPassword=0;
		int userId=0;
		boolean isPresent=false;
		boolean sameUserName=false;
		boolean isEmailPresent=false;
		String userName=request.getParameter("Username");
		String email=request.getParameter("Email"); 
		String newPassword=request.getParameter("new_password");  
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/hospital?useSSL=false", "root", "root");  
			
			
			PreparedStatement ps=con.prepareStatement(  
					"select * from tbl_registeruser where email=?");
			
					ps.setString(1,email);  
					      
					ResultSet rs=ps.executeQuery();
					isEmailPresent=rs.next(); 
					if(isEmailPresent) {
						System.out.println("Here....."+isEmailPresent);
						//while(rs.next()) {
							if(userName.equals(rs.getString("username"))) {
								System.out.println("True");
								System.out.println("Username from request===="+rs.getString("username"));
								System.out.println("username------"+userName);
								sameUserName = true;
							}else {
								System.out.println("false");
								System.out.println("Username from request===="+rs.getString("username"));
								System.out.println("username------"+userName);
								sameUserName = false;
							}
								
						//}
					}else {
						request.setAttribute("newPasswordError","Email ID does not exists. Please enter valid Email ID.");
						RequestDispatcher rd=request.getRequestDispatcher("modifyPassword.jsp");  
						rd.include(request,response);
						
					}
					
			if(sameUserName) {
			
			ps=con.prepareStatement(  
					"select * from tbl_registeruser where userName=? and password=?");
					
					ps.setString(1,userName);  
					ps.setString(2,newPassword);  
					      
					rs=ps.executeQuery();  
					isPresent=rs.next(); 
					if(isPresent) {
						request.setAttribute("newPasswordError","Username-Password combination already exists.Please enter new password.");
						RequestDispatcher rd=request.getRequestDispatcher("modifyPassword.jsp");  
						rd.include(request,response);
						
					}else {
							ps=con.prepareStatement(  
									"select * from tbl_registeruser where email=?");
									
									ps.setString(1,email);  
									      
									rs=ps.executeQuery();  
									while (rs.next()) {
										userId=rs.getInt("userId");
									}
									
							ps=con.prepareStatement(  
				                    "update tbl_registeruser set password=? where userId=?");  
							ps.setString(1,newPassword);  
							ps.setInt(2,userId);  
				         
							statusPassword=ps.executeUpdate(); 
							if(statusPassword>0) {  
								HttpSession session=request.getSession();  
						        session.invalidate(); 
								response.sendRedirect("passwordChangeSuccess.jsp");
								 
							}
					}
			}else {
				request.setAttribute("newPasswordError","Please enter correct Username corresponding to Email ID.");
				RequestDispatcher rd=request.getRequestDispatcher("modifyPassword.jsp");  
				rd.include(request,response);
				
			}
			
	con.close();		
	}catch(Exception ex) {
		System.out.println(ex);
	}

}
}
