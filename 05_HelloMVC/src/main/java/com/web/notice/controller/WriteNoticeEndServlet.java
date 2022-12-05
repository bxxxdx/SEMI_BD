package com.web.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
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
		//cos.jar 라이브러리 이용해서 파일 업로드 처리하기
		//1. 요청방식 multipart/form-data 방식으로 왔는지 확인한다. -> ServletFileUpload.isMultipartContent(request)
		if(!ServletFileUpload.isMultipartContent(request)) {
			//요청방식이 아니라면~!
			response.sendRedirect(request.getContextPath());
		} else {	
			//2. 요청방식이 맞다면
			//  2.1 cos.jar에서 제공하는 클래스를 이용해서 지정된 위치에 전송된 파일을 저장
			//		MultipartRequest 클래스 -> 자동으로 전달된 파일을 지정된 위치에 저장
			//MultipartRequest 클래스 매개변수 있는 생성자를 이용하면 됨. * 매개변수가 5개..!!
			//첫번째 매개변수 : HttpServletRequest객체
			//두번째 매개변수 : 저장위치설정(String)  *절대경로로 가져와야함
			//세번째 매개변수 : 업로드된 파일의 최대크기 설정(int)
			//네번째 매개변수 : 인코딩방식(String) *UTF-8
			//다섯번째 매개변수(젤 중요!) : rename 규칙 클래스  *적용 기본클래스 제공/커스터마이징 가능
			
			//저장할 위치 설정하기
			String path = request.getServletContext().getRealPath("/upload/notice");
			//저장파일 크기 설정
			//byte -> MB -> GB -> TB : 1024는 1byte
			int maxSize = 10*1024*1024; // 10MB;
			//인코딩설정
			String encoding = "UTF-8";
			//rename클래스
			DefaultFileRenamePolicy dfr = new DefaultFileRenamePolicy();
			
			//매개변수 있는 생성자 MultipartRequest클래스를 생성 ㄱㄱ
			MultipartRequest mr = new MultipartRequest(request, path, maxSize,encoding,dfr);
			
			//클라이언트가 보낸 데이터를 DB에 저장하는 기능
			//파일을 저장하면서 재정의된 파일명을 저장해야 한다.
			String noticeTitle = mr.getParameter("noticeTitle");
			String noticeWriter = mr.getParameter("noticeWriter");
			String noticeContent = mr.getParameter("noticeContent");
			//파일이름(rename 된)
			String fileName = mr.getFilesystemName("upFile");
			String oriName = mr.getOriginalFileName("upFile");
			
			Notice n = Notice.builder()
				.noticeTitle(noticeTitle)
				.noticeWriter(noticeWriter)
				.noticeContent(noticeContent)
				.filePath(fileName)
				.build();
			System.out.println(n);
			System.out.println(oriName);
			
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
		
		

		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
