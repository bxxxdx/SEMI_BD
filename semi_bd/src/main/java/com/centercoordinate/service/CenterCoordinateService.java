package com.centercoordinate.service;

import java.sql.Connection;
import java.util.List;

import static com.bangbang.common.JDBCTemplate.*;

import com.centercoordinate.model.dao.CenterCoordinateDao;
import com.centercoordinate.model.vo.CenterCoordinate;

public class CenterCoordinateService {
	private static CenterCoordinateService centerCoordinateService;
	private CenterCoordinateService() {}
	public static CenterCoordinateService getCenterCoordinateService() {
		if(centerCoordinateService == null) centerCoordinateService = new CenterCoordinateService();
		return centerCoordinateService;
	}
	
	public List<CenterCoordinate> searchCenterCoordinate(String gu){
		Connection conn = getConnection();
		List<CenterCoordinate> result = CenterCoordinateDao.getCenterCoordinateDao().searchCenterCoordinate(conn, gu);
		close(conn);

		return result;
	}
}
