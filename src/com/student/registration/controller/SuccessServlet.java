package com.student.registration.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.registration.dao.StudentDao;
import com.student.registration.model.Student;

/**
 * Servlet implementation class SuccessServlet
 */

public class SuccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao=new StudentDao();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuccessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		System.out.println("Hello in success");
		Student student=new Student();
		student.setFirstname(request.getParameter("first_name"));
		student.setLastname(request.getParameter("last_name"));
		student.setEmail(request.getParameter("email"));
		student.setDOB(request.getParameter("dob"));
		student.setContact(request.getParameter("phone"));		
		student.setSubject(request.getParameter("subject"));
		ServletContext servletcontext = getServletContext();
		String loginId = (String)servletcontext.getAttribute("loginId");
		student.setLoginId(Integer.parseInt(loginId));
		System.out.println("loginId="+student.getLoginId());
		int result=studentDao.updateStudentDetails(student);
		System.out.println(result==1?"success2":"Failure2");
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/views/success.jsp");
		dispatcher.forward(request, response);
	}

}
