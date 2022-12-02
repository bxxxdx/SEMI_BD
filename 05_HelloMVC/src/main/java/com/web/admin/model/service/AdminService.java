package com.web.admin.model.service;

import java.sql.Connection;
import java.util.List;
import static com.web.common.JDBCTemplate.*;

import com.web.admin.model.dao.AdminDao;
import com.web.member.model.vo.Member;

public class AdminService {
	private static AdminService adminService;
	private AdminService() {}
	public static AdminService getAdminService() {
		if(adminService==null) adminService = new AdminService();
		return adminService;
	}
	
	public List<Member> searchMemberAll(int cPage, int numPerpage){
		Connection conn = getConnection();
		List<Member> list = AdminDao.getAdminDao().searchMemberAll(conn, cPage, numPerpage);
		
		close(conn);
		
		return list;
	}
	
	public int selectMemberCount() {
		Connection conn = getConnection();
		int result = AdminDao.getAdminDao().selectMemberCount(conn);
		
		close(conn);
		
		return result;
	}
	
	public int selectMemberCount(String type, String keyword) {
		Connection conn = getConnection();
		int result = AdminDao.getAdminDao().selectMemberCount(conn, type, keyword);
		
		close(conn);
		
		return result;
	}
	
	public List<Member> searchMemberList(String type, String keyword, int cPage, int numPerpage){
		Connection conn = getConnection();
		List<Member> list = AdminDao.getAdminDao().searchMemberList(conn, type, keyword, cPage, numPerpage);
		
		close(conn);
		
		return list;
	}
	
}
