package com.web.notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.notice.model.service.NoticeService;

/**
 * Servlet implementation class DeleteNoticeServlet
 */
@WebServlet("/notice/deleteNotice.do")
public class DeleteNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteNoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		//upload폴더에 있는 파일도 삭제해 줘야 한다.
		String fileName = request.getParameter("fileName");
		
		int result = NoticeService.getNoticeService().deleteNotice(noticeNo);
		
		String msg="",loc="";
		if(result>0) {
			msg = "공지사항 삭제 완료^^";
			loc = "/notice/noticeList.do";
			String path = getServletContext().getRealPath("/upload/notice/");
			File delFile = new File(path+fileName);
			if(delFile.exists()) delFile.delete();
		} else {
			msg = "공지사항 삭제 실패";
			loc = "/notice/readNotice.do?noticeNo="+noticeNo;
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
