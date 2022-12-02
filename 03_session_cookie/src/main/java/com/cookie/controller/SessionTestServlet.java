package com.cookie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionTestServlet
 */
@WebServlet("/sessiontest.do")
public class SessionTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션을 생성할 때 매개변수로 true, false 값을 줄 수 있다.
		//false : 이미 생성된 session이 있으면 가져오고, 없으면 null
//		HttpSession session = request.getSession(false);
		
		//true : 생성된 session이 없으면 생성해서 가져오고 있으면 있는 session 가져와서 사용.
		//default가 true라 따로 설정 안해줘도 되긴 함.
		HttpSession session = request.getSession(true);
		System.out.println(session);
		session.setAttribute("data", "세션데이터");
		
		//session에 옵션
		//활동 시간에 대한, 매개변수만큼 session의 실행이 없으면 삭제
		//Server 폴더의 web.xml 보면 631번째 줄에 30초 타임아웃이 걸려 있음을 확인할 수 있다.
		session.setMaxInactiveInterval(5);
		
		response.sendRedirect(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
