package com.centercoordinate.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static com.bangbang.common.JDBCTemplate.*;

import com.centercoordinate.model.vo.CenterCoordinate;

public class CenterCoordinateDao {
	private static CenterCoordinateDao centerCoordinateDao;
	private Properties sql = new Properties();
	private CenterCoordinateDao() {
		try {		
			String path = CenterCoordinateDao.class.getResource("/sql/centercoordinate/centercoordinate_sql.properties").getPath();
			sql.load(new FileReader(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static CenterCoordinateDao getCenterCoordinateDao() {
		if(centerCoordinateDao == null) centerCoordinateDao = new CenterCoordinateDao();
		return centerCoordinateDao;
	}
	
	public List<CenterCoordinate> searchCenterCoordinate(Connection conn, String gu){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CenterCoordinate> result = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("searchGu")); 
			pstmt.setString(1, gu);
			System.out.println(gu);
			System.out.println(sql.getProperty("searchGu"));
			
			rs = pstmt.executeQuery();
			
			result = new ArrayList();
			while(rs.next()) {
				CenterCoordinate cc = getRsData(rs);
				result.add(cc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	
	private CenterCoordinate getRsData(ResultSet rs) {
		CenterCoordinate cc = null;
		try {
			cc = CenterCoordinate.builder()
					.centerNo(rs.getString("CENTER_NO"))
					.gu(rs.getString("GU"))
					.dong(rs.getString("DONG"))
					.latitude(rs.getString("LATITUDE"))
					.longitude(rs.getString("LONGITUDE"))
					.build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cc;
	}
	
	
	
	
	
	
}
