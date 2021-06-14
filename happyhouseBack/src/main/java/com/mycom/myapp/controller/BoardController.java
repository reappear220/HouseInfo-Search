package com.mycom.myapp.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.dto.BoardDto;
import com.mycom.myapp.dto.BoardParamDto;
import com.mycom.myapp.dto.BoardResultDto;
import com.mycom.myapp.service.BoardService;

@CrossOrigin(
		origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
)

@RestController
public class BoardController {

	@Autowired
	private BoardService service;
	
	private static final int SUCCESS = 1;
	
	@GetMapping(value="/boards")
	//@PathVariable()
	public ResponseEntity<BoardResultDto> boardList(BoardParamDto boardParamDto){
		
		BoardResultDto boardResultDto;
		
		System.out.println(boardParamDto.getLimit());

		if( boardParamDto.getSearchWord().isEmpty() ) {
			boardResultDto = service.boardList(boardParamDto);
		}else {
			boardResultDto = service.boardListSearchWord(boardParamDto);
		}
		
		if( boardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/boards/{no}")
	public ResponseEntity<List<BoardDto>> boardDetail(@PathVariable(value="no") int no, HttpSession session){
		System.out.println(no);
		BoardParamDto boardParamDto = new BoardParamDto();
		boardParamDto.setNo(no);
		
		List<BoardDto> boardResultDto = service.boardDetail(boardParamDto);
		//boardResultDto.getResult() == SUCCESS 
		if( true ) {
			return new ResponseEntity<List<BoardDto>>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<BoardDto>>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
 

}
