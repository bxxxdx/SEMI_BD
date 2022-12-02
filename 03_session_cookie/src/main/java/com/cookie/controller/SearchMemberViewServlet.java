package com.cookie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cookie.model.vo.Member;

/**
 * Servlet implementation class SearchMemberViewServlet
 */
@WebServlet("/searchmemberview.do")
public class SearchMemberViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMemberViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
				String userId = request.getParameter("userId");
				Member m = (Member)request.getAttribute("searchMember");
		
				//데이터를 가져왔으면 응답페이지 작성
				response.setContentType("text/html;charset=utf-8");
				
				String html = "<html><body>";
				html+="<h1> 아이디 : " + userId + "에 대한 조회결과</h1>";
				
				if(m==null) {
					html+="<h3>조회된 데이터가 없습니다.</h3>";
				}
				else {
					html+="<ul>";
					html+="<li>" + m.getMemberId() + "</li>";
					html+="<li>" + m.getMemberPwd() + "</li>";
					html+="<li>" + m.getMemberName() + "</li>";
					html+="<li>" + m.getGender() + "</li>";
					html+="<li>" + m.getAge() + "</li>";
					html+="<li>" + m.getEmail() + "</li>";
					html+="<li>" + m.getPhone() + "</li>";
					html+="<li>" + m.getAddress() + "</li>";
					html+="<li>" + m.getHobby() + "</li>";
					html+="<li>" + m.getEnrollDate() + "</li>";
					html+="</ul>";
				}
				html+="</body></html>";
				
				response.getWriter().write(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
