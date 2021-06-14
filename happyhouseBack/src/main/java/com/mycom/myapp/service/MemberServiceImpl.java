package com.mycom.myapp.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycom.myapp.dao.MemberDao;
import com.mycom.myapp.dto.MemberDto;
import com.mycom.myapp.dto.MemberResultDto;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberDao memberDao;
	
	
//	public static MemberService getMemberService() {
//		if(memberService == null)
//			memberService = new MemberServiceImpl();
//		return memberService;
//	}
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	@Override
	public MemberResultDto registerMember(MemberDto memberDto) {
		MemberResultDto memberResultDto = new MemberResultDto();
		if( memberDao.registerMember(memberDto) == 1 ) {
			memberResultDto.setResult(SUCCESS);
		}else {
			memberResultDto.setResult(FAIL);
		}
		
		return memberResultDto;
	}

//	@Override
//	public MemberDto login(String userId, String userPwd) {
//		id, pw를 제대로 가지고 왔는지 유효성 검사....
//			return MemberDaoImpl.getMemberDao().login(userId, userPwd);
//	}

	@Override
	public MemberDto getMember(String userId) throws SQLException {
		return memberDao.getMember(userId);
	}

	@Override
	public MemberResultDto updateMember(MemberDto memberDto) throws SQLException {
		MemberResultDto memberResultDto = new MemberResultDto();
		try {
			memberDao.updateMember(memberDto);
			memberResultDto.setResult(SUCCESS);
		}catch(Exception e) {
			e.printStackTrace();
			memberResultDto.setResult(FAIL);
		}
		return memberResultDto;
	}
	@Override
	public MemberResultDto deleteMember(String userId) throws SQLException {
		MemberResultDto memberResultDto = new MemberResultDto();
		try {
			memberDao.deleteMember(userId);
			memberResultDto.setResult(SUCCESS);
		}catch(Exception e) {
			e.printStackTrace();
			memberResultDto.setResult(FAIL);
		}
		return memberResultDto;
	}
	
	@Override
	public MemberDto detailMember(String userId) throws SQLException {
		return memberDao.detailMember(userId);
	}

	@Override
	public List<MemberDto> list() {
		return memberDao.list();
	}

}
