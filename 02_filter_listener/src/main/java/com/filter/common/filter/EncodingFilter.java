package com.filter.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

//일반클래스를 필터로 만들려면 Filter 인터페이스를 구현
//javax.Servlet.filter를 골라야 한다~~
//alt + shift + S + V 하면 재정의 할게 나온다.
public class EncodingFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("필터 실행~~!!!");
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		
		//Wrapper 클래스 적용하기
//		HttpServletRequestTest hrt = new HttpServletRequestTest((HttpServletRequest)request);
		
		
		
		
		
		//얘를 실행해야지 다음 서블릿이나 필터가 실행된다.
		chain.doFilter(request, response);
		
	}
}
