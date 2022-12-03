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
 * Servlet implementation class UpdateBoardEndServlet
 */
@WebServlet("/board/updateBoardEnd.do")
public class UpdateBoardEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBoardEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Board b = Board.builder()
					.boardNo(Integer.parseInt(request.getParameter("boardNo")))
					.boardTitle(request.getParameter("boardTitle"))
					.boardWriter(request.getParameter("boardWriter"))
					.boardContent(request.getParameter("boardContent"))
					.build();
		
		int result = BoardService.getBoardService().updateBoard(b);
		
		String msg="",loc="";
		if(result>0) {
			msg = "게시물 수정이 완료되었습니다.^^";
			loc = "/board/boardList.do";
		} else {
			msg = "공지사항 수정 실패";
			loc = "/board/updateBoard.do?boardNo="+b.getBoardNo();
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
