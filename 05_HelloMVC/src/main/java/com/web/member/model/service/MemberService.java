package com.web.member.model.service;

import static com.web.common.JDBCTemplate.*;

import java.sql.Connection;

import com.web.member.model.dao.MemberDao;
import com.web.member.model.vo.Member;

public class MemberService {
	private static MemberService memberService;
	private MemberService() {}
	public static MemberService getMemberService() {
		if(memberService==null) memberService = new MemberService();
		return memberService;
	}
	
	public Member searchMemberLogin(String id, String password) {
		Connection conn = getConnection();
		Member result = MemberDao.getMemberDao().searchMemberLogin(conn, id, password);
		
		close(conn);
		
		return result;
	}
	
	public Member searchMemberId(String id) {
		Connection conn = getConnection();
		Member result = MemberDao.getMemberDao().searchMemberId(conn, id);
		
		close(conn);
		
		return result;
	}
	
	public int insertMember(Member m) {
		Connection conn = getConnection();
		int result = MemberDao.getMemberDao().insertMember(conn, m);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public int updateMember(Member m) {
		Connection conn = getConnection();
		int result = MemberDao.getMemberDao().updateMember(conn, m);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public int deleteMember(Member m) {
		Connection conn = getConnection();
		int result = MemberDao.getMemberDao().deleteMember(conn, m);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public int updatePassword(String userId, String newPw) {
		Connection conn = getConnection();
		int result = MemberDao.getMemberDao().updatePassword(conn, userId, newPw);

		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
}
