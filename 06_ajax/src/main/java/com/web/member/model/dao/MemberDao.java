package com.web.member.model.dao;

import static com.web.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.web.member.model.vo.Member;

public class MemberDao {
	private static MemberDao memberDao;
	private Properties sql = new Properties();
	private MemberDao() {
		try {
			String path = MemberDao.class.getResource("/sql/member/member_sql.properties").getPath();
			sql.load(new FileReader(path));
			//sql properties 파일에 데이터를 저장할땐 서버를 끄고 하자. 캐시를 남기기 때문임
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static MemberDao getMemberDao() {
		if(memberDao==null) memberDao = new MemberDao();
		return memberDao;
	}

	
	
	public Member searchMemberLogin(Connection conn, String id,String password) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		String sql = "SELECT * FROM MEMBER WHERE USERID=? AND PASSWORD=?";
		Member m = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("searchIdPassword"));
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m = getRsData(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return m;
	}
	
	public Member searchMemberId(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		String sql = "SELECT * FROM MEMBER WHERE USERID=? AND PASSWORD=?";
		Member m = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("searchId"));
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m = getRsData(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return m;
	}
	
	
	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertMember"));
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, String.valueOf(m.getGender()));
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, String.join(",", m.getHobby()));
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("updateMember"));
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, String.valueOf(m.getGender()));
			pstmt.setInt(3, m.getAge());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getPhone());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, String.join(",", m.getHobby()));
			pstmt.setString(8, m.getUserId());
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("deleteMember"));
			pstmt.setString(1, m.getUserId());
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updatePassword(Connection conn, String userId, String newPw) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("updatePassword"));
			pstmt.setString(1, newPw);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public Member getRsData(ResultSet rs) {
		Member m = null;
		try {
			m = Member.builder()
					.userId(rs.getString("userid"))
					.password(rs.getString("password"))
					.userName(rs.getString("username"))
					.gender(rs.getString("gender").charAt(0))
					.age(rs.getInt("age"))
					.email(rs.getString("email"))
					.phone(rs.getString("phone"))
					.address(rs.getString("address"))
					.hobby(rs.getString("hobby").split(","))
					.enrollDate(rs.getDate("enrolldate"))
					.build();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return m;
	}
	
	
}
