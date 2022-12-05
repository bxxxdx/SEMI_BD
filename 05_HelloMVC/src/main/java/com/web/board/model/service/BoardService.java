package com.web.board.model.service;

import static com.web.common.JDBCTemplate.close;
import static com.web.common.JDBCTemplate.commit;
import static com.web.common.JDBCTemplate.getConnection;
import static com.web.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.web.board.model.dao.BoardDao;
import com.web.board.model.vo.Board;
import com.web.board.model.vo.BoardComment;

public class BoardService {
	private static BoardService boardService;
	private BoardService() {}
	public static BoardService getBoardService() {
		if(boardService == null) boardService = new BoardService();
		return boardService;
	}
	
	public List<Board> searchBoardAll(int cPage, int numPerpage){
		Connection conn = getConnection();
		List<Board> list = BoardDao.getBoardDao().searchBoardAll(conn, cPage, numPerpage);
		
		close(conn);
		
		return list;
	}
	
	public int selectBoardCount() {
		Connection conn = getConnection();
		int result = BoardDao.getBoardDao().selectBoardCount(conn);
		
		close(conn);
		
		return result;
	}
	
	public int insertBoard(Board b) {
		Connection conn = getConnection();
		int result = BoardDao.getBoardDao().insertBoard(conn, b);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public Board searchBoardNo(int boardNo, boolean readFlag) {
		Connection conn = getConnection();
		Board b = BoardDao.getBoardDao().searchBoardNo(conn, boardNo);
		if(b != null && !readFlag) {
			//조회수 증가시켜주기 !
			int result = BoardDao.getBoardDao().updateReadCount(conn, boardNo);
			if(result>0) {
				commit(conn);
				b.setBoardReadCount(b.getBoardReadCount() + 1);
			}
			else rollback(conn);
		}
		
		close(conn);
		
		return b;
	}
	
	public int updateBoard(Board b) {
		Connection conn = getConnection();
		int result = BoardDao.getBoardDao().updateBoard(conn, b);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public int deleteBoard(int boardNo) {
		Connection conn = getConnection();
		int result = BoardDao.getBoardDao().deleteBoard(conn, boardNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public int insertBoardComment(BoardComment bc) {
		Connection conn = getConnection();
		int result = BoardDao.getBoardDao().insertBoardComment(conn, bc);
	
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public List<BoardComment> searchBoardComments(int boardRef) {
		Connection conn = getConnection();
		List<BoardComment> bcs = BoardDao.getBoardDao().searchBoardComments(conn, boardRef);
		
		close(conn);
		
		return bcs;
	}
	
}
