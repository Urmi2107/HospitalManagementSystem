package com.hospital.management.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeptDocServlet
 */
@WebServlet("/DeptDocServlet")
public class DeptDocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeptDocServlet() {
        super();
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try(PrintWriter out = response.getWriter()) {
			 
	            DeptDocDao deptDoc = new DeptDocDao();
	            
	            String op = request.getParameter("operation");
	            
	            if (op.equals("department")) {
	                List<Department> deptList = deptDoc.getAllDepartment();
	                Gson json = new Gson();
	                String departmentList = json.toJson(deptList);
	                response.setContentType("text/html");
	                response.getWriter().write(departmentList);
	            }

	            if (op.equals("doctor")) {
	                String id = request.getParameter("id");
	                List<Doctor> docList = deptDoc.getDoctorByDepartment(id);
	                Gson json = new Gson();
	                String doctorList = json.toJson(docList);
	                response.setContentType("text/html");
	                response.getWriter().write(doctorList);
	            }
	            
			
			  if (op.equals("allDoctors")) { List<Doctor> docList = deptDoc.getAllDoctor();
				  Gson json = new Gson(); String doctorList = json.toJson(docList);
				 response.setContentType("text/html"); response.getWriter().write(doctorList);
			 }
			
		 }
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
