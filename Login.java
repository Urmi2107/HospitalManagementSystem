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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean status=false; 
		RequestDispatcher rd;
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
	          
	    String userName=request.getParameter("Username");  
	    String password=request.getParameter("password");  
	    System.out.println("Login page  "+userName+"    "+password);
	    try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/hospital?allowPublicKeyRetrieval=true&useSSL=false", "root", "root"); 
			
			PreparedStatement ps=con.prepareStatement(  
					"select * from tbl_registeruser where userName=? and password=?");  
					ps.setString(1,userName);  
					ps.setString(2,password);  
					      
					ResultSet rs=ps.executeQuery();  
					status=rs.next(); 
					
					if(status){ 
						System.out.println("Login page REQ "+request.getParameter("Username"));
				        rd=request.getRequestDispatcher("LoginSuccess");  
				        rd.forward(request,response);  
				    }  
				    else{ 
				    	request.setAttribute("loginError","Sorry username or password is incorrect. Please enter correct credentials to proceed...");
				        rd=request.getRequestDispatcher("login.jsp");  
				        rd.include(request,response);  
				    }  
					con.close();			
	    }catch (Exception ex) {
			System.out.println(ex);
		}  
		          
		out.close();  
	    
	    
	    
	}

}
