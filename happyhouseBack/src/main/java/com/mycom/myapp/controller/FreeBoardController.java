package com.mycom.myapp.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.dto.FreeBoardDto;
import com.mycom.myapp.dto.FreeBoardParamDto;
import com.mycom.myapp.dto.FreeBoardResultDto;
import com.mycom.myapp.dto.MemberDto;
import com.mycom.myapp.service.FreeBoardService;

@CrossOrigin(
		origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
)

@RestController
public class FreeBoardController {

	@Autowired
	private FreeBoardService freeservice;
	
	private static final int SUCCESS = 1;

	@GetMapping(value="/freeboards")
	public ResponseEntity<FreeBoardResultDto> freeboardList(FreeBoardParamDto freeboardParamDto){
		
		FreeBoardResultDto freeboardResultDto;
		
		System.out.println(freeboardParamDto.getSearchWord());
		System.out.println(freeservice);
		
		if( freeboardParamDto.getSearchWord().isEmpty() ) {
			freeboardResultDto = freeservice.freeboardList(freeboardParamDto);
		}else {
			freeboardResultDto = freeservice.freeboardListSearchWord(freeboardParamDto);
		}
		
		if( freeboardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<FreeBoardResultDto>(freeboardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<FreeBoardResultDto>(freeboardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/freeboards/{boardId}")
	public ResponseEntity<FreeBoardResultDto> freeboardDetail(@PathVariable int boardId, HttpSession session){

		FreeBoardParamDto freeboardParamDto = new FreeBoardParamDto();
		freeboardParamDto.setBoardId(boardId);
		
		FreeBoardResultDto freeboardResultDto = freeservice.freeboardDetail(freeboardParamDto);
		
		// 게시글 작성자와 현 사용자가 동일
		//if( ((MemberDto) session.getAttribute("memberDto")).getUserId().equals( freeboardResultDto.getDto().getUserId()) ) {
		//	freeboardResultDto.setOwner(true);
		//}
		freeboardResultDto.setOwner(true);
		if( freeboardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<FreeBoardResultDto>(freeboardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<FreeBoardResultDto>(freeboardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
	
	@PostMapping(value="/freeboards")
	public ResponseEntity<FreeBoardResultDto> freeboardInsert(
			FreeBoardDto freeboardDto, 
			MultipartHttpServletRequest request) {
		
		freeboardDto.setUserId( ((MemberDto) request.getSession().getAttribute("memberDto")).getUserId());
		FreeBoardResultDto freeboardResultDto = freeservice.freeboardInsert(freeboardDto, request);
		
		if( freeboardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<FreeBoardResultDto>(freeboardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<FreeBoardResultDto>(freeboardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
	
	@PostMapping(value="/freeboards/{boardId}") 
	public ResponseEntity<FreeBoardResultDto> freeboardUpdate(
			FreeBoardDto freeboardDto, 
			MultipartHttpServletRequest request){

		FreeBoardResultDto freeboardResultDto = freeservice.freeboardUpdate(freeboardDto, request);
		freeboardDto.setUserId( ((MemberDto) request.getSession().getAttribute("memberDto")).getUserId());
		
		if( freeboardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<FreeBoardResultDto>(freeboardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<FreeBoardResultDto>(freeboardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@DeleteMapping(value="/freeboards/{boardId}") 
	public ResponseEntity<FreeBoardResultDto> freeboardDelete(@PathVariable(value="boardId") int boardId){
		FreeBoardResultDto freeboardResultDto = freeservice.freeboardDelete(boardId);
  
		if( freeboardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<FreeBoardResultDto>(freeboardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<FreeBoardResultDto>(freeboardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
	
	

}
