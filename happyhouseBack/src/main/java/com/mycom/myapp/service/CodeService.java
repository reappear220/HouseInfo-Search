package com.mycom.myapp.service;

import java.util.List;

import com.mycom.myapp.dto.CodeDto;

public interface CodeService {
	public List<CodeDto> codeList(String groupCode);
}
