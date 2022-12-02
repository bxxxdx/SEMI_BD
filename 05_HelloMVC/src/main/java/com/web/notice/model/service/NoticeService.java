package com.web.notice.model.service;

import static com.web.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.web.notice.model.dao.NoticeDao;
import com.web.notice.model.vo.Notice;

public class NoticeService {
	private static NoticeService noticeService;
	private NoticeService() {}
	public static NoticeService getNoticeService() {
		if(noticeService == null) noticeService = new NoticeService();
		return noticeService;
	}
	
	public int selectNoticeCount() {
		Connection conn = getConnection();
		int result = NoticeDao.getNoticeDao().selectNoticeCount(conn);
		
		close(conn);
		
		return result;
	}
	
	public List<Notice> searchNoticeAll(int cPage, int numPerpage){
		Connection conn = getConnection();
		List<Notice> list = NoticeDao.getNoticeDao().searchNoticeAll(conn, cPage, numPerpage);
		
		close(conn);
	
		return list;
	}
	
	public int insertNotice(Notice n) {
		Connection conn = getConnection();
		int result = NoticeDao.getNoticeDao().insertNotice(conn, n);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public Notice searchNoticeNo(int noticeNo) {
		Connection conn = getConnection();
		Notice n = NoticeDao.getNoticeDao().searchNoticeNo(conn, noticeNo);
		
		close(conn);
		
		return n;
	}
	
	public int updateNotice(Notice n) {
		Connection conn = getConnection();
		int result = NoticeDao.getNoticeDao().updateNotice(conn, n);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}	
	
	public int deleteNotice(int noticeNo) {
		Connection conn = getConnection();
		int result = NoticeDao.getNoticeDao().deleteNotice(conn, noticeNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
}
