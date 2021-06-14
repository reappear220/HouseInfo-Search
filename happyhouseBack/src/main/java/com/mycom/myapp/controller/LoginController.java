package com.mycom.myapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.dto.MemberDto;
import com.mycom.myapp.service.LoginService;

//BoardFileUploadSpringBootMybatis 대비 @CrossOrign 추가 <-- vue cli mode 개발 대응
@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true", allowedHeaders = "*", methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS })
@RestController
public class LoginController {

	@Autowired
	LoginService service;

	@PostMapping(value = "/login")
	public ResponseEntity<MemberDto> login(@RequestBody MemberDto dto, HttpSession session) {

		MemberDto memberDto = service.login(dto);
		if (memberDto != null) {
			session.setAttribute("memberDto", memberDto);
			return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
		}
		return new ResponseEntity<MemberDto>(memberDto, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/logout")
	public void logout(HttpSession session){
		session.invalidate();
	}
}
