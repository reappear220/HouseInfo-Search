package com.mycom.myapp.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class BoardDto {
	// 공통
	private int no;
	private String aptName;
	private String dong;
	private String jibun;
	private int buildYear;
	private int code;
	
	//info만
	private float lat;
	private float lng;
	// deal만
	private String dealAmount;
	private int area;
	private String dealType;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private int floor;
	
	
	
	public BoardDto() {};

	public BoardDto(int no, String aptName, String dealAmount, int buildYear, int area, String dong, String jibun, String dealType,
			int code, float lat, float lng, int dealYear, int dealMonth, int dealDay, int floor) {
		super();
		this.no = no;
		this.aptName = aptName;
		this.dealAmount= dealAmount;
		this.buildYear = buildYear;
		this.area = area;
		this.dong = dong;
		this.jibun = jibun;
		this.dealType = dealType;
		this.code = code;
		this.lat = lat;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.floor = floor;
	}
	
	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}

	public int getDealYear() {
		return dealYear;
	}

	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}

	public int getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}

	public int getDealDay() {
		return dealDay;
	}

	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public int getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return "BoardDto [aptName=" + aptName + ", wholePrice=" + dealAmount + ", buildYear=" + buildYear + ", area="
				+ area + ", dong=" + dong + ", jibun=" + jibun + ", dealType=" + dealType + ", code=" + code + "]";
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}	
	
}

