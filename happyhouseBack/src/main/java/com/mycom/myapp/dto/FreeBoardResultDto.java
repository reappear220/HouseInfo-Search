package com.mycom.myapp.dto;

import java.util.List;

public class FreeBoardResultDto {
	private int result;
	private FreeBoardDto dto;
	private List<FreeBoardDto> list;
	private int count;
	private boolean isOwner;
	
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}

	public FreeBoardDto getDto() {
		return dto;
	}
	public void setDto(FreeBoardDto dto) {
		this.dto = dto;
	}
	public List<FreeBoardDto> getList() {
		return list;
	}
	public void setList(List<FreeBoardDto> list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public boolean isOwner() {
		return isOwner;
	}
	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}
		
		

	

}
