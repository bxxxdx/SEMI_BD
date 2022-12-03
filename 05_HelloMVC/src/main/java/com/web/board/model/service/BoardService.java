package com.web.board.model.service;

import java.sql.Connection;
import java.util.List;

import com.web.board.model.dao.BoardDao;
import com.web.board.model.vo.Board;
import static com.web.common.JDBCTemplate.*;

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
	
	public Board searchBoardNo(int boardNo) {
		Connection conn = getConnection();
		Board b = BoardDao.getBoardDao().searchBoardNo(conn, boardNo);
		
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
}