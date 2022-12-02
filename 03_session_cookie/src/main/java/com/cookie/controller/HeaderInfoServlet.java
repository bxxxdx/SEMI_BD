package com.cookie.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HeaderInfoServlet
 */
@WebServlet(urlPatterns = "/headerdata.do")
public class HeaderInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HeaderInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpRequestHeader 정보 가져오기
		//HttpServletRequest에 해당정보가 저장되어 있음
		String accept = request.getHeader("Accept");
		System.out.println(accept);
		String cookie=request.getHeader("Cookie");
		System.out.println(cookie);
		String prevPage = request.getHeader("Referer");
		System.out.println(prevPage);
		String contextRoot = request.getContextPath();
		System.out.println(contextRoot);
		String url = prevPage.substring(prevPage.indexOf(request.getContextPath()));
		System.out.println(url);
		
		//서블릿이나 context에 고정값을 저장하고 활용하기
		//web.xml에서 context-param으로 등록된 값 이용하기 admin/teacherdev09
		ServletContext context = request.getServletContext();
		String admin = context.getInitParameter("admin");
		System.out.println(admin);
		
		//서블릿에 등록된 init-param 가져오기
		String initdata = getInitParameter("headerServlet");
		System.out.println(initdata);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
