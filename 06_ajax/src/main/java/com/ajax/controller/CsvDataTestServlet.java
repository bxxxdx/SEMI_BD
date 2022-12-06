package com.ajax.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajax.model.vo.Actor;

/**
 * Servlet implementation class CsvDataTestServlet
 */
@WebServlet("/ajax/csvdata.do")
public class CsvDataTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CsvDataTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Actor> actors = List.of(
				new Actor("박보검","01012345678","parkBogum.jpg"),
				new Actor("쥴리아로버츠","01023456789","juliaRoberts.jpg"),
				new Actor("맷데이먼","01034567890","mattDamon.jpg")
				);
		String csv = "";
		for(int i=0;i<actors.size();i++) {
			if(i!=0) {
				csv += "\n";
			}
			csv += actors.get(i);
		}
		response.setContentType("text/csv;charset=utf-8");
		response.getWriter().print(csv);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
