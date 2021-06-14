package com.mycom.myapp.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.dto.MemberDto;

@Mapper
public interface LoginDao {
	public MemberDto login(String userId);
}
