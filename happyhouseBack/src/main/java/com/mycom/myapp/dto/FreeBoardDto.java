package com.mycom.myapp.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class FreeBoardDto {
	private int boardId;
	private String userId;
	private String userName;
	private String title;
	private String content;
	private LocalDateTime regDt;
	private int readCount;

	private List<FreeBoardFileDto> fileList;

	public FreeBoardDto() {};
	public FreeBoardDto(String userId, String title, String content) {
		this.userId = userId;
		this.title = title;
		this.content = content;
	}
	
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = LocalDateTime.ofInstant(
				regDt.toInstant(), ZoneId.systemDefault()
		);	// for Mybatis Date Mapping
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public List<FreeBoardFileDto> getFileList() {
		return fileList;
	}
	public void setFileList(List<FreeBoardFileDto> fileList) {
		this.fileList = fileList;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[BoardDto - boardId : ");
		sb.append(this.boardId);
		sb.append(", userId : ");
		sb.append(this.userId);
		sb.append(", userName : ");
		sb.append(this.userName);
		sb.append(", title : ");
		sb.append(this.title);
		sb.append(", content : ");
		sb.append(this.content);
		sb.append(", regDt : ");
		sb.append(this.regDt);
		sb.append(", readCount : ");
		sb.append(this.readCount);
		sb.append("]");
		
		return sb.toString();
	}
	
}

