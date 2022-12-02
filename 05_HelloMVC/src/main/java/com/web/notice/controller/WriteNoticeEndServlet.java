package com.web.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.notice.model.service.NoticeService;
import com.web.notice.model.vo.Notice;

/**
 * Servlet implementation class WriteNoticeEnd
 */
@WebServlet("/notice/writeNoticeEnd.do")
public class WriteNoticeEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteNoticeEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeWriter = request.getParameter("noticeWriter");
		String noticeContent = request.getParameter("noticeContent");
	
		Notice n = Notice.builder()
						.noticeTitle(noticeTitle)
						.noticeWriter(noticeWriter)
						.noticeContent(noticeContent)
						.build();
		
		int result = NoticeService.getNoticeService().insertNotice(n);
		
		String msg="",loc="";
		if(result>0) {
			msg = "공지사항 등록이 완료되었습니다.";
			loc = "/notice/noticeList.do";
		} else {
			msg = "공지사항 등록 실패";
			loc = "/notice/writeNotice.do";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
