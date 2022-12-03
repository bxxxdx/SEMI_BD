package com.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.model.service.BoardService;
import com.web.board.model.vo.Board;

/**
 * Servlet implementation class WriteBoardEndServlet
 */
@WebServlet("/board/writeBoardEnd.do")
public class WriteBoardEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteBoardEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Board b = Board.builder()
				.boardTitle(request.getParameter("boardTitle"))
				.boardWriter(request.getParameter("boardWriter"))
				.boardContent(request.getParameter("boardContent"))
				.build();
		
		int result = BoardService.getBoardService().insertBoard(b);
		
		String msg="",loc="";
		if(result > 0) {
			msg = "게시물이 등록되었습니다.";
			loc = "/board/boardList.do";
		}else {
			msg = "게시물 등록 실패 -.-";
			loc = "/board/writeBoard.do";
		}
		request.setAttribute("msg",msg);
		request.setAttribute("loc",loc);
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
