package com.jsp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.model.service.MemberService;
import com.jsp.model.vo.Member;

/**
 * Servlet implementation class SearchMemberNameServlet
 */
@WebServlet("/searchmembername.do")
public class SearchMemberNameServlet extends HttpServlet {
	
	private MemberService memberService = new MemberService();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMemberNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("memberName");
//		System.out.println(name);
		
		List<Member> searchMember = memberService.searchMemberName(name);
		request.setAttribute("members", searchMember);
		
		//페이지 전환
		request.getRequestDispatcher("/views/member/memberName.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
