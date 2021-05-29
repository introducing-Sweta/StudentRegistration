package com.student.registration.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.registration.dao.StudentDao;
import com.student.registration.model.Logins;

/**
 * Servlet implementation class DetailsServlet
 */

public class DetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao=new StudentDao();    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//PrintWriter out=response.getWriter();
		//out.print("Hello22222!!!!");
		System.out.println("executed......");
		Logins login=new Logins();
		login.setUsername(request.getParameter("name"));
		login.setUseremail(request.getParameter("email"));
		login.setPassword(request.getParameter("password"));		
		try {
			
			int result=studentDao.registerStudent(login);
			int loginId=studentDao.getloginId(login);
			System.out.println("loginId="+String.valueOf(loginId));
			ServletContext servletcontext = getServletContext();
			servletcontext.setAttribute("loginId", String.valueOf(loginId));
			System.out.println(result==1?"success":"Failure");
		   } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("hello in details......");
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/views/details.jsp");
		dispatcher.forward(request, response);
	}

}
