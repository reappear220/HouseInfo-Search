package com.mycom.myapp.dto;

import java.util.List;

public class BoardResultDto {
	private int result;
	private BoardDto boardDto;
	private List<BoardDto> list;
	private int count;
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public BoardDto getDto() {
		return boardDto;
	}
	public void setDto(BoardDto boardDto) {
		this.boardDto = boardDto;
	}

	public List<BoardDto> getList() {
		return list;
	}
	public void setList(List<BoardDto> list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	

}
