package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.member.model.service.MemberService;
import com.web.member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "login" ,urlPatterns = "/login.do")
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
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		//아이디 저장(체크박스) 기능 구현하기
		//클라이언트가 아이디저장 checkbox를 체크했으면 저장관리
		//checkbox를 체크하지 않았으면 저장하지 않음=> null
		//checkbox를 체크했을 경우 String 값으로 "on"이 출력된다.
		String saveId = request.getParameter("saveId");
		System.out.println(saveId);
		
		Member m = MemberService.getMemberService().searchMemberLogin(userId, password);
		if(m != null) {
			System.out.println("로그인 성공!!");
//			System.out.println(m);
		}else {
			System.out.println("로그인 실패!!");
		}
		
		//웹 애플리케이션에서 로그인처리 하기
		if(m!=null) {
			//로그인 성공
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", m);
			
			
			//아이디 저장
			//if(saveId.equals("on")) {
			if(saveId!=null) {
				//아이디 저장 체크 했을때
				Cookie c = new Cookie("saveId",userId);
				c.setMaxAge(60*60*24*7);
				response.addCookie(c);
			}else {
				Cookie c = new Cookie("saveId","");
				c.setMaxAge(0);
				response.addCookie(c);
			}
				
			
			//응답할 페이지 구성 -> jsp
			//로그인 처리에는 다양한 방식이 있을 수 있다. but 우리가 배우고 있는 ssr 방식으로 갈 예정
			response.sendRedirect(request.getContextPath());
		}
		else {
			//로그인 실패
			Cookie c = new Cookie("saveId","");
			c.setMaxAge(0);
			response.addCookie(c);
			
			request.setAttribute("msg","아이디나 비밀번호가 일치하지 않습니다!");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);	
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
