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
 * Servlet implementation class LoginSuccess
 */
@WebServlet("/LoginSuccess")
public class LoginSuccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginSuccess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();
	    
	    RequestDispatcher rd;
	    
	    String name="";  
		String contactNo="";  
		String email="";
	    String userName = request.getParameter("Username");  
	    String password = request.getParameter("password");
	    System.out.println(userName+"    "+password);
	    String welcomeMsg="Welcome "+request.getParameter("Username"); 
	    try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/hospital?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");  
			
			
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
	    
					request.setAttribute("welcomeMsg",welcomeMsg);
					request.setAttribute("name",name);
					request.setAttribute("contactNo",contactNo);
					request.setAttribute("email",email);
					request.setAttribute("userName",userName);
					
					 HttpSession session=request.getSession();  
				     session.setAttribute("userName",userName);
				     session.setAttribute("password", password);
					 rd=request.getRequestDispatcher("userHome.jsp");  
				     rd.forward(request,response);  
				     con.close();		
					
	    }catch (Exception ex) {
			System.out.println(ex);
		}  
	          
	    out.close();  
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");  
	    PrintWriter out = resp.getWriter();
	    
	    RequestDispatcher rd;
	    HttpSession session=req.getSession(); 
	    String name="";  
		String contactNo="";  
		String email="";
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
					      
					ResultSet rs=ps.executeQuery();  
					while (rs.next()) {
						 name=rs.getString("name");  
						contactNo=rs.getString("contactNo");  
						email=rs.getString("email");
						userName = rs.getString("username");
					}
	    
					req.setAttribute("welcomeMsg",welcomeMsg);
					req.setAttribute("name",name);
					req.setAttribute("contactNo",contactNo);
					req.setAttribute("email",email);
					req.setAttribute("userName",userName);
					
			/*
			 * HttpSession session=request.getSession();
			 * session.setAttribute("userName",userName); session.setAttribute("password",
			 * password);
			 */
					 rd=req.getRequestDispatcher("userHome.jsp");  
				     rd.forward(req,resp);  
				     con.close();		
					
	    }catch (Exception ex) {
			System.out.println(ex);
		}  
	          
	    out.close();  
	}
	
	

}
