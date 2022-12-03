package com.web.notice.model.dao;

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

import com.web.admin.model.dao.AdminDao;
import com.web.notice.model.vo.Notice;

public class NoticeDao {
	private static NoticeDao noticeDao;
	private Properties sql = new Properties();
	private NoticeDao() {
		try {
			String path = NoticeDao.class.getResource("/sql/notice/notice_sql.properties").getPath();
			sql.load(new FileReader(path));
			//sql properties 파일에 데이터를 저장할땐 서버를 끄고 하자. 캐시를 남기기 때문임
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static NoticeDao getNoticeDao() {
		if(noticeDao==null) noticeDao = new NoticeDao();
		return noticeDao;
	}
	
	public int selectNoticeCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectNoticeCount"));
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
	
	public List<Notice> searchNoticeAll(Connection conn, int cPage, int numPerpage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notice> list = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("searchAll"));
			pstmt.setInt(1, (cPage-1)*numPerpage + 1);
			pstmt.setInt(2, cPage*numPerpage);
			rs = pstmt.executeQuery();
			list = new ArrayList();
			while(rs.next()) {
				list.add(getRsData(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	public int insertNotice(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertNotice"));
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeWriter());
			pstmt.setString(3, n.getNoticeContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	public Notice searchNoticeNo(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Notice n = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("searchNoticeNo"));
			pstmt.setInt(1, noticeNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				n = getRsData(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return n;	
	}
	
	public int updateNotice(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("updateNotice"));
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeWriter());
			pstmt.setString(3, n.getNoticeContent());
			pstmt.setInt(4, n.getNoticeNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	public int deleteNotice(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("deleteNotice"));
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	
	
	
	
	
	private Notice getRsData(ResultSet rs) {
		Notice n = null;
		try {
			n = Notice.builder()
					.noticeNo(rs.getInt("NOTICE_NO"))
					.noticeTitle(rs.getString("NOTICE_TITLE"))
					.noticeWriter(rs.getString("NOTICE_WRITER"))
					.noticeContent(rs.getString("NOTICE_CONTENT"))
					.noticeDate(rs.getDate("NOTICE_DATE"))
					.filePath(rs.getString("FILEPATH"))
					.status(rs.getString("STATUS").charAt(0))
					.build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
