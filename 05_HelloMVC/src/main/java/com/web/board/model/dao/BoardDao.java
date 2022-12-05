package com.web.board.model.dao;

import static com.web.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.web.board.model.vo.Board;
import com.web.board.model.vo.BoardComment;

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
	
	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertBoard"));
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardWriter());
			pstmt.setString(3, b.getBoardContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public Board searchBoardNo(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board b = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("searchBoardNo"));
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				b = getRsData(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return b;
	}
	
	public int updateReadCount(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("updateReadCount"));
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("updateBoard"));
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardWriter());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setInt(4, b.getBoardNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteBoard(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("deleteBoard"));
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int insertBoardComment(Connection conn, BoardComment bc) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertBoardCommment"));
			pstmt.setInt(1,bc.getBoardCommentLevel());
			pstmt.setString(2, bc.getBoardCommentWriter());
			pstmt.setString(3, bc.getBoardCommentContent());
			pstmt.setInt(4, bc.getBoardRef());
			//pstmt.setInt(5, bc.getBoardCommentRef()==0?null:bc.getBoardCommentRef());
			pstmt.setString(5, bc.getBoardCommentRef()==0?null:String.valueOf(bc.getBoardCommentRef()));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public List<BoardComment> searchBoardComments(Connection conn, int boardRef) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardComment> bcs = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("searchBoardComments"));
			pstmt.setInt(1, boardRef);
			rs = pstmt.executeQuery();
			bcs = new ArrayList();
			while(rs.next()) {
				bcs.add(getRsData_BoardComment(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bcs;
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

	private BoardComment getRsData_BoardComment(ResultSet rs) throws SQLException {
		return BoardComment.builder()
						.boardCommentNo(rs.getInt("BOARD_COMMENT_NO"))
						.boardCommentLevel(rs.getInt("BOARD_COMMENT_LEVEL"))
						.boardCommentWriter(rs.getString("BOARD_COMMENT_WRITER"))
						.boardCommentContent(rs.getString("BOARD_COMMENT_CONTENT"))
						.boardRef(rs.getInt("BOARD_REF"))
						.boardCommentRef(rs.getInt("BOARD_COMMENT_REF"))
						.boardCommentDate(rs.getDate("BOARD_COMMENT_DATE"))
						.build();
	}
}
