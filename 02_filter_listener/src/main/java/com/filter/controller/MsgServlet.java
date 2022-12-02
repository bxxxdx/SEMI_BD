package com.filter.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MsgServlet
 */
@WebServlet(name="msgServlet", urlPatterns ="/msgservice.do")
public class MsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MsgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = request.getParameter("msg");
		System.out.println(msg);
		
		HttpSession session = request.getSession();
		session.setAttribute("userId", "admin");
		
		
		
//		response.sendRedirect(request.getContextPath());
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write("<html>");
		out.write("<body>");
		out.write("<h1>실행하기</h1>");
		out.write("<h3>작성한 메세지 : "+msg+"</h3>");
		out.write("<body>");
		out.write("<html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
