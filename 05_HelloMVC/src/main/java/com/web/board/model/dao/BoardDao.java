package com.web.board.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.web.common.JDBCTemplate.*;

import com.web.board.model.vo.Board;

public class BoardDao {
	private static BoardDao boardDao;
	private Properties sql = new Properties();
	private BoardDao() {
		try {		
			String path = BoardDao.class.getResource("/sql/board/board_sql.properties").getPath();
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static BoardDao getBoardDao() {
		if(boardDao == null) boardDao = new BoardDao();
		return boardDao;
	}
	
	public List<Board> searchBoardAll(Connection conn, int cPage, int numPerpage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("searchAll"));
			pstmt.setInt(1, (cPage-1)*numPerpage + 1);
			pstmt.setInt(2, cPage*numPerpage);
			rs = pstmt.executeQuery();
			list = new ArrayList();
			while(rs.next()) {
				list.add(getRsData(rs));
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	public int selectBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectBoardCount"));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	private Board getRsData(ResultSet rs) throws SQLException {
		return Board.builder()
				.boardNo(rs.getInt("BOARD_NO"))
				.boardTitle(rs.getString("BOARD_TITLE"))
				.boardWriter(rs.getString("BOARD_WRITER"))
				.boardContent(rs.getString("BOARD_CONTENT"))
				.boardOriginalFileName(rs.getString("BOARD_ORIGINAL_FILENAME"))
				.boardRenamedFileName(rs.getString("BOARD_RENAMED_FILENAME"))
				.boardDate(rs.getDate("BOARD_DATE"))
				.boardReadCount(rs.getInt("BOARD_READCOUNT"))
				.build();
	}
	
	
	
	
	
	
	
	
	
	
	
}
