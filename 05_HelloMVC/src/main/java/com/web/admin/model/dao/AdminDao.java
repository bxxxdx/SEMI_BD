package com.web.admin.model.dao;

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

import com.web.member.model.vo.Member;

public class AdminDao {
	private static AdminDao adminDao;
	private Properties sql = new Properties();
	private AdminDao() {
		try {
			String path = AdminDao.class.getResource("/sql/admin/admin_sql.properties").getPath();
			sql.load(new FileReader(path));
			//sql properties 파일에 데이터를 저장할땐 서버를 끄고 하자. 캐시를 남기기 때문임
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static AdminDao getAdminDao() {
		if(adminDao==null) adminDao = new AdminDao();
		return adminDao;
	}
	
	
	public List<Member> searchMemberAll(Connection conn, int cPage, int numPerpage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> list = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("searchAll"));
			//시작값
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			//끝값
			pstmt.setInt(2, cPage*numPerpage);
			
			rs = pstmt.executeQuery();
			list = new ArrayList();
			while(rs.next()) {
				list.add(getRsData(rs));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	public int selectMemberCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectMemberCount"));			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("CNT");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
	
	public int selectMemberCount(Connection conn, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String query = sql.getProperty("selectMemberCountKeyword");
		query = query.replace("$COL", type);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, type.equals("gender")?keyword:"%"+keyword+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("CNT");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
	
	public List<Member> searchMemberList(Connection conn, String type, String keyword, int cPage, int numPerpage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> list = null;
		String query = sql.getProperty("searchMemberListKeyword");
		query = query.replace("$COL", type);
		System.out.println(query);
		try { 
			pstmt = conn.prepareStatement(query); 
			pstmt.setString(1, type.equals("gender")?keyword:"%"+keyword+"%"); 
			//시작값
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			//끝값
			pstmt.setInt(3, cPage*numPerpage);
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
		System.out.println("시작!");
		list.stream().forEach(v->System.out.println(v));
		return list;
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
					.hobby(rs.getString("hobby")!=null?rs.getString("hobby").split(","):new String[] {})
					.enrollDate(rs.getDate("enrolldate"))
					.build();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return m;
	}
	
	
}
