package com.ajax.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ajax.model.vo.Actor;
import com.google.gson.Gson;
import com.web.admin.model.service.AdminService;
import com.web.member.model.vo.Member;

/**
 * Servlet implementation class BaskcJsonServlet
 */
@WebServlet("/ajax/basicJson.do")
public class BasicJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BasicJsonServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// jsonsomple라이브러리를 이용해서 객체 전송하기

		// 단일객체 전송
		// JSONObject 객체를 생성해서 응답해줌.
		// Map방식으로 필드값을 저장함.
		Actor a = new Actor("박보검", "012345678", "parkBogum.jpg");
		// 각 필드의 값을 key:value형식으로 저장
		// JSONObject.put() 메소드 이용
		JSONObject o = new JSONObject();
		o.put("name", a.getName());
		o.put("phone", a.getPhone());
		o.put("profile", a.getProfile());
		o.put("age", 19);
		o.put("height", 180.5);
		o.put("flag", true);
		o.put("today", new Date().toString());
		// 객체를 넣으면 success가 아니라 error가 발생한다. 고로 기본 Primitive 자료형을 사용해야 함.
		// 만약 Date를 사용하고 싶다면 toString을 사용하던가 datesimpleformat을 사용해서 parsing을 해줘야 함.

		// 다수의 데이터를 받아오기
		List<Member> members = AdminService.getAdminService().searchMemberAll(1, 20);
		JSONArray jarr = new JSONArray(); // 리스트 방식으로 JSONObject를 대입
		for (Member m : members) {
			JSONObject jo = new JSONObject();
			jo.put("userId", m.getUserId());
			jo.put("userName", m.getUserName());
			jo.put("email", m.getEmail());
			jo.put("phone",m.getPhone());
			jo.put("address",m.getAddress());
			jarr.add(jo);
		}

		// 전송할때는 contentType설정,
		response.setContentType("application/json;charset=utf-8");
		//response.getWriter().print(o);
//		response.getWriter().print(jarr);
		// 다수객체 전송
		// JSONArray 객체를 생성해서 응답해줌.
		// JSONArray는 List방식으로 JSONObject를 저장
		
		//GSon 라이브러리 이용해서 데이터 전송하기
		Gson g = new Gson();
		g.toJson(members, response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
