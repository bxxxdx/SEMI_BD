package com.centercoordinate.model.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CenterCoordinate {
	private String centerNo;
	private String gu;
	private String dong;
	private String latitude;
	private String longitude;
}
