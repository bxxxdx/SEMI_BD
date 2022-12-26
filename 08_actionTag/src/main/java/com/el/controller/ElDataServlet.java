package com.el.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.el.model.vo.Student;

/**
 * Servlet implementation class ElDataServlet
 */
@WebServlet("/el/data.do")
public class ElDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ElDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("email","teacherdev09@gmail.com");
		request.setAttribute("names", List.of("유병승", "최유리", "구예지", "이동민"));
		
		Student s = new Student("유병승",19,180.5,'남');
		request.setAttribute("s", s);
		Student s1 = new Student("지유림",28,165.5,'여');
		Student s2 = new Student("이병도",30,185.5,'남');
		List<Student> list = List.of(s,s1,s2);
		request.setAttribute("students", list);
		
		
		request.setAttribute("userId", "아이디");
		
		HttpSession session = request.getSession();
		session.setAttribute("userId","admin");
		
	
		
		request.getRequestDispatcher("/views/el/resultData.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
