package com.web.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.web.member.model.vo.Member;

/**
 * Servlet Filter implementation class LoginNoticeFilter
 */
@WebFilter(urlPatterns = {
		"/notice/*"
		,"/board/writeBoardEnd.do"
		,"/board/writeBoard.do"
		,"/board/updateBoard.do"
		,"/board/updateBoardEnd.do"
		,"/board/deleteBoard.do"
		
		})
public class LoginNoticeFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginNoticeFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		
		HttpSession session = ((HttpServletRequest)request).getSession(false);
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember != null) {
			chain.doFilter(request, response);
		} else {
			request.setAttribute("msg","로그인시 이용 가능합니다 -.-;;");
			request.setAttribute("loc","/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
