package com.mycom.myapp.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.dto.MemberDto;
import com.mycom.myapp.dto.MemberResultDto;


public interface MemberService {
	
//	회원가입
	public MemberResultDto registerMember(MemberDto memberDto) throws SQLException;
	
//	로그인
//	MemberDto login(String userId, String userPwd);
	
//	회원정보 수정을 위한 회원의 모든 정보 얻기
	public MemberDto getMember(String userId) throws SQLException;
	
//  회원 리스트
	public List<MemberDto> list();	
	
//	회원정보 수정
	//public int updateMember(MemberDto memberDto) throws SQLException;
	
	public MemberResultDto updateMember(MemberDto memberDto) throws SQLException;
	
//	회원탈퇴
	public MemberResultDto deleteMember(String userId) throws SQLException;
	
	public MemberDto detailMember(String userId) throws SQLException;
	
}
