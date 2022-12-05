package com.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.model.service.BoardService;
import com.web.board.model.vo.BoardComment;

/**
 * Servlet implementation class WriteBoardCommentServlet
 */
@WebServlet("/board/writeBoardComment.do")
public class WriteBoardCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteBoardCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardComment bc = BoardComment.builder()
							.boardRef(Integer.parseInt(request.getParameter("boardref")))
							.boardCommentContent(request.getParameter("content"))
							.boardCommentLevel(Integer.parseInt(request.getParameter("level")))
							.boardCommentWriter(request.getParameter("commentWriter"))
							.boardCommentRef(Integer.parseInt(request.getParameter("commentref")))
							.build();
		
		int result = BoardService.getBoardService().insertBoardComment(bc);
		
		String msg="", loc="";
		if(result>0) {
			msg = "댓글등록 성공";
		}
		else {
			msg = "댓글등록 실패";
		}
		loc="/board/readBoard.do?boardNo="+bc.getBoardRef();
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
