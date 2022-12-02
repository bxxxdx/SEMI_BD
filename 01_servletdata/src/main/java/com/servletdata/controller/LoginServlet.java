package com.servletdata.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="login" , urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 보낸 데이터 확인하기
		//HttpServletRequest 클래스가 제공하는 메소드를 이용해서 데이터를 가져올 수 있다.
		//getParameter("name속성의 값") String으로 value값을 반환해줌
		String userId = request.getParameter("userId");
		System.out.println(userId);
		String password = request.getParameter("password");
		System.out.println(password);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//post방식으로 알파벳, 숫자를 제외한 문자를 받았을 경우,
		//데이터에 대한 인코딩 처리를 해줘야 한다.
		request.setCharacterEncoding("utf-8");
		
		
//		String userId = request.getParameter("userId");
//		System.out.println(userId);
//		String password = request.getParameter("password");
//		System.out.println(password);
		doGet(request, response);
		//post방식에선 id에 "유병승" 을 적으면 ì ë³ì¹으로 나온다. 
		//인코딩을 해주지 않으면 깨져서 들어온다. 
		//2바이트로 들어오는 값을 1바이트로 출력하려고 하니 발생하는 현상.
	}

}
