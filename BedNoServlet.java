package com.hospital.management.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class BedNoServlet
 */
@WebServlet("/BedNoServlet")
public class BedNoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BedNoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try(PrintWriter out = response.getWriter()) {
			 
	            BedNoDao bedNo = new BedNoDao();
	            
	            String op = request.getParameter("operation");
	            
	            if (op.equals("allBedNo")) {
	                List<BedNo> bedList = bedNo.getAllBedNos();
	                Gson json = new Gson();
	                String bedNoList = json.toJson(bedList);
	                response.setContentType("text/html");
	                response.getWriter().write(bedNoList);
	            }
	            
	            if (op.equals("bedNo")) {
	                List<BedNo> bedList = bedNo.getAllBedNo();
	                Gson json = new Gson();
	                String bedNoList = json.toJson(bedList);
	                response.setContentType("text/html");
	                response.getWriter().write(bedNoList);
	            }


		 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
