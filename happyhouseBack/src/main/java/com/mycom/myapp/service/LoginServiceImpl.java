package com.mycom.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myapp.dao.LoginDao;
import com.mycom.myapp.dto.MemberDto;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDao loginDao;

	@Override
	public MemberDto login(MemberDto dto) {

		MemberDto memberDto = loginDao.login(dto.getUserId());

		if (memberDto != null && memberDto.getUserPwd().equals(dto.getUserPwd())) {
			return memberDto;
		} else {
			return null;
		}
	}

}