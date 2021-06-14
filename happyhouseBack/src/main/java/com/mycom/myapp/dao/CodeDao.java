package com.mycom.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.mycom.myapp.dto.CodeDto;

@Mapper
public interface CodeDao {
	public List<CodeDto> codeList(String groupCode);
}
