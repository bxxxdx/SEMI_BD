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
 * Servlet implementation class SearchMemberAllServlet
 */
@WebServlet("/searchmemberall.do")
public class SearchMemberAllServlet extends HttpServlet {
	
	private MemberService memberService = new MemberService();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMemberAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DB에 저장되어 있는 회원 전체를 가져오는 기능
		List<Member> allMember = memberService.searchMemberAll();
		request.setAttribute("members",allMember);
		
		//페이지전환
		request.getRequestDispatcher("/views/member/memberAll.jsp").forward(request, response);;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
