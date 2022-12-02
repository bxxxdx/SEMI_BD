package com.cookie.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cookie.model.vo.Member;

/**
 * Servlet implementation class SearchMemberServlet
 */
@WebServlet("/searchmember.do")
public class SearchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 보낸 데이터 가져오기
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
//		System.out.println(userId + " " + password);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_PWD=?";
		Member m = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","student");
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			
			rs=pstmt.executeQuery();
			if(rs.next()){
				m = new Member();
				m.setMemberId(rs.getString("MEMBER_ID"));
				m.setMemberPwd(rs.getString("MEMBER_PWD"));
				m.setMemberName(rs.getString("MEMBER_NAME"));
				m.setGender(rs.getString("GENDER").charAt(0));
				m.setAge(rs.getInt("AGE"));
				m.setPhone(rs.getString("PHONE"));
				m.setAddress(rs.getString("ADDRESS"));
				m.setEmail(rs.getString("EMAIL"));
				m.setHobby(rs.getString("HOBBY"));
				m.setEnrollDate(rs.getDate("ENROLL_DATE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null && !conn.isClosed()) conn.close();
				if(pstmt!=null && !pstmt.isClosed()) pstmt.close();
				if(rs!=null && !rs.isClosed()) rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("searchMember", m);
		request.getRequestDispatcher("searchmemberview.do").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
