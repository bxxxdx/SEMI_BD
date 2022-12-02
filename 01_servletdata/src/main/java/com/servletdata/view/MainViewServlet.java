package com.servletdata.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainViewServlet
 */
@WebServlet("/mainview.do")
public class MainViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		String userId = (String)session.getAttribute("userId");
		String userId = (String)request.getAttribute("userId");
		response.setContentType("text/html;charset=utf-8");
		String html = "<html><body>";
		if(userId!=null) {
			html+="<h2>로그인성공</h2>";
			html+="<h3>"+userId+"님 환영합니다.</h3>";
			html+="<a href='/01_servletdata/sharedata.do'>공유데이터확인</a>";
		}
		else {
			html+="<h2>로그인 후 이용이 가능합니다</h2>";
		}
		html+="</body></html>";
		
		PrintWriter out = response.getWriter();
		out.print(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
