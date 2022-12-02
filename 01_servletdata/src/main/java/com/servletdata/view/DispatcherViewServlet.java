package com.servletdata.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherViewServlet
 */
@WebServlet("/dispatcherview.do")
public class DispatcherViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//RequestDispatcherServlet이 보낸 데이터
		String info = (String)request.getAttribute("info");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		double height = Double.parseDouble(request.getParameter("height"));
		String color = request.getParameter("color");
		String lunch = request.getParameter("lunch");
		String[] animals = request.getParameterValues("animal");
		
		response.setContentType("text/html;charset=utf8");
		PrintWriter out = response.getWriter();
		
		String html = "<html>";
		html += "<body>";
		html += "<h1>"+info+"</h1>";
		html += "<p>당신의 이름은 "+name+"이고 나이는 "+age+"살이고";
		html += "키는 "+height+"cm이네요.";
		html += "<h4>" + "<span style='color:"+color+"'>"+color+"</span>입니다.</h4>";
		html += "<h3>오늘의 점심은 "+lunch+"였습니다.</h3>";
		html += "</body></html>";
	
		out.write(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
