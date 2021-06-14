package com.mycom.myapp.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.dto.MemberDto;
import com.mycom.myapp.dto.MemberResultDto;
import com.mycom.myapp.service.LoginService;
import com.mycom.myapp.service.MemberService;

@CrossOrigin(
		origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
)

@RestController
public class MemberController {
	@Autowired
	MemberService memberService;
	
	@Autowired
	LoginService service;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	//등록
	@PostMapping(value="/register")
	public ResponseEntity<MemberResultDto> register(@RequestBody MemberDto dto, HttpSession session) throws SQLException{
		
		MemberResultDto memberResultDto = memberService.registerMember(dto);
		
		if( memberResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<MemberResultDto>(memberResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<MemberResultDto>(memberResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//수정
	
	@PutMapping(value="/members/{userId}")
	public ResponseEntity<MemberResultDto> updateMember(@PathVariable String userId, @RequestBody MemberDto dto, HttpSession session) throws SQLException {
		MemberResultDto memberResultDto = memberService.updateMember(dto);
		
		if( memberResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<MemberResultDto>(memberResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<MemberResultDto>(memberResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//삭제
	@DeleteMapping(value="/members/{userId}")
	public ResponseEntity<MemberResultDto> deleteMember(@PathVariable String userId, HttpSession session) throws SQLException {
		MemberResultDto memberResultDto = memberService.deleteMember(userId);
		if( memberResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<MemberResultDto>(memberResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<MemberResultDto>(memberResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//회원 리스트
	@GetMapping(value="/members")
	public List<MemberDto> list() {
		List<MemberDto> list = memberService.list();
		for (MemberDto memberDto : list) {
			System.out.println(memberDto.getUserId());
			System.out.println(memberDto.getUserPwd());
		}
		return memberService.list();
	}
	
	//회원정보보기
	@GetMapping(value="/members/{id}")
	public MemberDto detailMember(@PathVariable String id) throws SQLException {
		return memberService.detailMember(id);
	}
}
