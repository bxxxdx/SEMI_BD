package com.web.notice.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownServlet
 */
@WebServlet("/notice/fileDown.do")
public class FileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파일 다운로드 서비스
		
		//1. 클라이언트가 보낸 파일명을 가져온다.
		String fileName = request.getParameter("fileName");
		
		//2. 지정한 저장경로에서 보낸 파일명과 일치하는 파일을 가져옴
		//InputStream을 이용해서 가져온다.
		String path = getServletContext().getRealPath("/upload/notice/");
		FileInputStream fis = new FileInputStream(path+fileName);
		//속도를 빨리하기 위해 fis에 Buffer를 씌운다.
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		//3. 클라이언트에게 보낼 파일명을 인코딩처리한다.
		// -> 파일명이 한글일 때 깨지는 현상을 방지.
		String fileReName = "";
		String header = request.getHeader("user-agent");
		boolean isMSIE = header.indexOf("MSIE")!=-1||header.indexOf("Trident")!=-1;
		if(isMSIE) {
			fileReName = URLEncoder.encode(fileName,"utf-8").replaceAll("\\+", "%20");
		} else {
			fileReName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		}
		
		//4. 응답헤더 설정하자
		//content-type에 대한 설정
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment;fileName="+fileReName);
		
		//5. 클라이언트 연결 스트림 열기
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		
		//6. 연결된 스트림으로 파일 전송하기
		int read = -1;
		while((read=bis.read()) != -1) {
			bos.write(read);
		}
		bis.close();
		bos.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
