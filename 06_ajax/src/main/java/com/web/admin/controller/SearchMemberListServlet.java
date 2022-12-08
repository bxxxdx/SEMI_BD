package com.web.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.admin.model.service.AdminService;
import com.web.member.model.vo.Member;

/**
 * Servlet implementation class SearchMemberServlet
 */
@WebServlet("/admin/searchMember")
public class SearchMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("searchType");
		String keyword = request.getParameter("searchKeyword");
		
		// 현재 페이지
		int cPage; 
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			cPage = 1;
		}
		
		// 페이지당 데이터 출력 갯수
		int numPerpage;
		try {
			numPerpage = Integer.parseInt(request.getParameter("numPerpage"));
		} catch (NumberFormatException e) {
			numPerpage = 10;
		}
		
		List<Member> list = AdminService.getAdminService().searchMemberList(type, keyword, cPage, numPerpage);
		request.setAttribute("members", list);
		//pageBar 만들어서 반환하기
		//1. totalData : 전체 페이지 수를 알기 위해
		int totalData = AdminService.getAdminService().selectMemberCount(type, keyword);
		//pageBar html코드를 저장할 수 있는 변수 선언
		String pageBar = "";
		//1. pageBar의 번호 갯수를 정한다.
		int pageBarSize = 5;
		//2. 총 페이지수 -> ceil 쓰면 나머지 발생시 무조건 올림
		int totalPage = (int)Math.ceil((double)totalData/numPerpage);
		System.out.println("totalPage = " + totalPage);
		//3. 출력할 번호 세팅
		int pageNo = (cPage-1)/pageBarSize*pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize - 1;
		
		//html코드 생성하기
		if(pageNo==1) {
			pageBar += "<span>[이전] </span>";
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/admin/searchMember?searchType="+type+"&searchKeyword="+keyword+"&cPage="+(pageNo-1)+"&numPerpage="+numPerpage+"'>[이전] </a>";
		}
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar += "<span>"+pageNo+" </span>";
			} else { 
				pageBar += "<a href='"+request.getContextPath()+"/admin/searchMember?searchType="+type+"&searchKeyword="+keyword+"&cPage="+pageNo+"&numPerpage="+numPerpage+"'>"+pageNo+" </a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar += "<span> [다음]</span>";
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/admin/searchMember?searchType="+type+"&searchKeyword="+keyword+"&cPage="+pageNo+"&numPerpage="+numPerpage+"'> [다음]</a>";
		}
		request.setAttribute("pageBar",pageBar);

		request.getRequestDispatcher("/views/member/searchMemberList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
