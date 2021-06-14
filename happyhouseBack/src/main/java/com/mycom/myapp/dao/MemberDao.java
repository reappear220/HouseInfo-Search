package com.mycom.myapp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.dto.MemberDto;

@Mapper 
public interface MemberDao {

//	회원가입
	public int registerMember(MemberDto memberDto);

//	로그인
//	MemberDto login(String userId, String userPwd);

//	회원정보 수정을 위한 회원의 모든 정보 얻기
	public MemberDto getMember(String userId) throws SQLException;
	
//  회원 리스트 
	public List<MemberDto> list();
//	회원정보 수정
	public int updateMember(MemberDto memberDto) throws SQLException;

//	회원탈퇴
	public int deleteMember(String userId) throws SQLException;
	
//	회원정보 확인 
	public MemberDto detailMember(String userId);


}
