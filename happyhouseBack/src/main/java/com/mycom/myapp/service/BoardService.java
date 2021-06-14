package com.mycom.myapp.service;


import java.util.List;

import com.mycom.myapp.dto.BoardDto;
import com.mycom.myapp.dto.BoardParamDto;
import com.mycom.myapp.dto.BoardResultDto;


public interface BoardService {
	
	public List<BoardDto> boardDetail(BoardParamDto boardParamDto);
	
//	public BoardResultDto boardDelete(int boardId);
	
//	public BoardResultDto boardUpdate(BoardDto dto, MultipartHttpServletRequest request);

//	public BoardResultDto boardInsert(BoardDto dto, MultipartHttpServletRequest request);

	public BoardResultDto boardList(BoardParamDto boardParamDto);
	//public int boardListTotalCount();	
	public BoardResultDto boardListSearchWord(BoardParamDto boardParamDto);
	//public int boardListSearchWordTotalCount(boardParamDto boardParamDto);

}
