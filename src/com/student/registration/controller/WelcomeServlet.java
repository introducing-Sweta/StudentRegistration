package com.student.registration.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.registration.dao.StudentDao;
import com.student.registration.model.Logins;
import com.student.registration.model.Student;

/**
 * Servlet implementation class WelcomeServlet
 */

public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao=new StudentDao();
	private Logins login;   
	private Student student;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
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
		PrintWriter out=response.getWriter();
		login=new Logins();
		student=new Student();
		System.out.println("Hello in welcome");
		String logemail = request.getParameter("email");
        String logpass = request.getParameter("password");
        login = studentDao.getlogin(logemail, logpass);  
        if(login!=null) {
        	student=studentDao.getStudent(login.getLoginId());
        	request.setAttribute("firstname", student.getFirstname());
        	request.setAttribute("studentId", student.getStudentId());
        	request.setAttribute("email", student.getEmail());
        	request.setAttribute("contact", student.getContact());
		   RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/views/welcome.jsp");
		   dispatcher.forward(request, response);
        }
        else{
            out.println("user not found");
        }
        
    }

}
