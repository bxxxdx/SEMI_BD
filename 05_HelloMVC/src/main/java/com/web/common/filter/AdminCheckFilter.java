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

import com.web.common.exception.LoginCheckException;
import com.web.member.model.vo.Member;

/**
 * Servlet Filter implementation class AdminCheckFilter
 */
@WebFilter(urlPatterns = {
		"/admin/*"
		,"/notice/writeNotice.do"
		,"/notice/writeNoticeEnd.do"
		,"/notice/updateNotice.do"
		,"/notice/updateNoticeEnd.do"
		,"/notice/deleteNotice.do"
		})
public class AdminCheckFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AdminCheckFilter() {
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
		if(loginMember != null && loginMember.getUserId().equals("admin")) {
			chain.doFilter(request, response);
		}
		else {
			/*
			 * request.setAttribute("msg","접근할 권한이 없습니다! -.-");
			 * request.setAttribute("loc","/");
			 * request.getRequestDispatcher("/views/common/msg.jsp").forward(request,
			 * response);
			 */
			//에러를 발생 시킨다.
//			throw new IllegalAccessError("권한없는 접근입니다.");
			throw new LoginCheckException("권한 없는 접근입니다.");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
