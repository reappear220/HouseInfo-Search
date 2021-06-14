package com.mycom.myapp.service;


import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.dto.FreeBoardDto;
import com.mycom.myapp.dto.FreeBoardParamDto;
import com.mycom.myapp.dto.FreeBoardResultDto;



public interface FreeBoardService {
	
	public FreeBoardResultDto freeboardDetail(FreeBoardParamDto freeboardParamDto);
	
	public  FreeBoardResultDto freeboardDelete(int boardId);
	
	public  FreeBoardResultDto freeboardUpdate(FreeBoardDto dto, MultipartHttpServletRequest request);

	public  FreeBoardResultDto freeboardInsert(FreeBoardDto dto, MultipartHttpServletRequest request);

	public  FreeBoardResultDto freeboardList(FreeBoardParamDto freeboardParamDto);

	public  FreeBoardResultDto freeboardListSearchWord(FreeBoardParamDto freeboardParamDto);


}
