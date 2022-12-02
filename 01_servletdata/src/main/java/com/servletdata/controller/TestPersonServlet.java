package com.servletdata.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestPersonServlet
 */
@WebServlet("/testperson.do")
public class TestPersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestPersonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8"); // 필터를 배우기 전까지는 직접 서블릿에 설정해줘야함.
		
		//1. 단일데이터
		//request.getParameter() => 기본적인 데이터를 받아올 때 사용
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		double height = Double.parseDouble(request.getParameter("height"));
		String color = request.getParameter("color");
		String lunchMenu = request.getParameter("lunch");
		
		//2. 하나의 이름으로 여러값이 전송될때 값 받아오기
		//request.getParameterValues() => 다수의 값을 받을때 사용
		//다수의 값이기 때문에 반환형이 String[] 임
		String[] animals = request.getParameterValues("animal");
		
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
		System.out.println("키 : " + height);
		System.out.println("색상 : " + color);
		System.out.println("점심 : " + lunchMenu);
		System.out.println("좋아하는 동물 : " + Arrays.toString(animals));
		
		//단일값도 getParameterValues() 메소드를 이용해서 가져올 수 있다.
		//길이가 1인 배열로 할당된다~
		String[] names = request.getParameterValues("name");
		for(String v : names) {
			System.out.println(v);
		}
		
		//클라이언트에서 전송된 name값을 모를때 name값만 가져오기
		//getParameterNames() 메소드 이용 -> Enumeration 클래스로 반환
		Enumeration<String> paramName = request.getParameterNames();
		while(paramName.hasMoreElements()) {
			String key = paramName.nextElement();
			String[] values = request.getParameterValues(key);
			System.out.println(key + " : " + Arrays.toString(values));
		}
		
		//전송된 데이터를 key:value 형식으로 반환받기 -> Map
		//request.getParameterMap();
		Map<String, String[]> param = request.getParameterMap();
		Set<String> keys = param.keySet();
		Iterator it = keys.iterator();
		System.out.println("===== map으로 데이터 받기 =====");
		while(it.hasNext()) {
			String key = (String)it.next();
			String[] values = param.get(key);
			System.out.println(key + " : " + Arrays.toString(values));
		}
		
		//EntrySet으로 해보셈셈셈
		Set<Entry<String, String[]>> entryParam = param.entrySet();
		
		
		
		//클라이언트의 요청에 대한 응답페이지를 작성
		//HttpServletResponse 클래스를 이용한다.
		//1. 응답데이터에 대한 타입을 설정 -> MIME 타입
		response.setContentType("text/html;charset=utf-8");
		
		//2. 클라이언트의 Stream을 가져온다.
		// 1) getWriter() : html코드 전송할때, 일반 문자열데이터 보낼때
		// 2) getOutputStream() : 파일전송할때
		PrintWriter out = response.getWriter();
		//3. 연결된 스트림으로 원하는 데이터를 전송
		//write(), append() 메소드 이용
		String html = "<html>";
		html+="<head>";
		html+="<title>당신의취향분석</title>";
		html+="</head>";
		html+="<body>";
		html+="<h1>"+name+"님의 취향은</h1>";
		html+="<h2>당신의 이름은 "+name+"이고 나이는 "+age+"살 입니다.</h2>";
		html+="<h3>키는 "+height+"cm이고, 좋아하는 색은 <span style='color:"+color+"'>"+color+"</span>입니다.</h3>";
		html+="<ul> 좋아하는 동물은";
		for(String a : animals) {
			String src = "";
			html+="<li>";
			switch(a) {
			case "강아지" : src = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/af/Cara_de_quem_caiu_do_caminh%C3%A3o..._%28cropped%29.jpg/220px-Cara_de_quem_caiu_do_caminh%C3%A3o..._%28cropped%29.jpg";break;
			case "고양이" : src = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Cat_poster_1.jpg/300px-Cat_poster_1.jpg";break;
			}
			html+="<img src='"+src+"' width='200' height='200'>";
			html+="</li>";
		}
		html+="</ul>";
		html+="<h3>오늘의 점심은 "+lunchMenu+"였습니다.</h3>";
		html+="</body>";
		html+="</html>";
		
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
