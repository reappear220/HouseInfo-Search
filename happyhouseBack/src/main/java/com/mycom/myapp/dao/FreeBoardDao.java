package com.mycom.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.myapp.dto.FreeBoardDto;
import com.mycom.myapp.dto.FreeBoardFileDto;
import com.mycom.myapp.dto.FreeBoardParamDto;


@Mapper
public interface FreeBoardDao {
	
	public FreeBoardDto freeboardDetail(FreeBoardParamDto freeboardParamDto);
	public List<FreeBoardFileDto> freeboardDetailFileList(int boardId);
	
	// map - Dto
	public int freeboardUserReadCount(FreeBoardParamDto freeboardParamDto); 
	
	// map - @param
	public int freeboardUserReadInsert(
			@Param("boardId") int boardId, 
			@Param("userId") int userId ); 
	
	public int freeboardReadCountUpdate(int boardId);
	
	
	public int freeboardDelete(int boardId);	
	public int freeboardFileDelete(int boardId);
	public List<String> freeboardFileUrlDeleteList(int boardId);
	public int freeboardReadCountDelete(int boardId);
	
	public int freeboardInsert(FreeBoardDto dto);
	public int freeboardFileInsert(FreeBoardFileDto dto);
	
	public List<FreeBoardDto> freeboardList(FreeBoardParamDto freeboardParamDto);
	public int freeboardListTotalCount();
	
	public List<FreeBoardDto> freeboardListSearchWord(FreeBoardParamDto freeboardParamDto);
	public int freeboardListSearchWordTotalCount(FreeBoardParamDto freeboardParamDto);
	

	public int freeboardUpdate(FreeBoardDto dto);


}
