package com.mycom.myapp.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.dao.FreeBoardDao;
import com.mycom.myapp.dto.FreeBoardDto;
import com.mycom.myapp.dto.FreeBoardFileDto;
import com.mycom.myapp.dto.FreeBoardParamDto;
import com.mycom.myapp.dto.FreeBoardResultDto;


	@Service
	public class FreeBoardServiceImpl implements FreeBoardService {
	
		@Autowired
		FreeBoardDao dao;
		String uploadFolder = "upload";
	
		String uploadPath = "C:" + File.separator + "Users" + File.separator + "HYOJIN" + File.separator + "Documents"
				+ File.separator + "temp"
				+ File.separator + "happyhouse" + File.separator + "src" + File.separator + "main"
				+ File.separator + "resources" + File.separator + "static";

		private static final int SUCCESS = 1;
		private static final int FAIL = -1;
		
		@Override
		@Transactional
		public FreeBoardResultDto freeboardDetail(FreeBoardParamDto freeboardParamDto) {
			FreeBoardResultDto freeboardResultDto = new FreeBoardResultDto();
			
			try {
				int userReadCnt = dao.freeboardUserReadCount(freeboardParamDto);
				if( userReadCnt == 0 ) {
					dao.freeboardUserReadInsert(freeboardParamDto.getBoardId(), freeboardParamDto.getUserId());
					dao.freeboardReadCountUpdate(freeboardParamDto.getBoardId());
				}
				
				FreeBoardDto dto = dao.freeboardDetail(freeboardParamDto);
				List<FreeBoardFileDto> fileList = dao.freeboardDetailFileList(dto.getBoardId());

				dto.setFileList(fileList);
				freeboardResultDto.setDto(dto);
				
				freeboardResultDto.setResult(SUCCESS);
				
			}catch(Exception e) {
				e.printStackTrace();
				freeboardResultDto.setResult(FAIL);
			}
			
			return freeboardResultDto;
		}
		
		@Override
		@Transactional
		public FreeBoardResultDto freeboardDelete(int boardId) {
			
			FreeBoardResultDto freeboardResultDto = new FreeBoardResultDto();
			
			try {
				List<String> fileUrlList = dao.freeboardFileUrlDeleteList(boardId);
				for(String fileUrl : fileUrlList) {
					File file = new File(uploadPath + File.separator, fileUrl);				
					if(file.exists()) {
						file.delete();
					}
				}
				
				dao.freeboardFileDelete(boardId);
				dao.freeboardReadCountDelete(boardId);
				dao.freeboardDelete(boardId);		
				freeboardResultDto.setResult(SUCCESS);
				
			}catch(Exception e) {
				e.printStackTrace();
				freeboardResultDto.setResult(FAIL);
			}
			
			return freeboardResultDto;
		}
		
		@Override
		@Transactional
		public FreeBoardResultDto freeboardUpdate(FreeBoardDto dto, MultipartHttpServletRequest request) {
			FreeBoardResultDto freeboardResultDto = new FreeBoardResultDto();

			
			try {
				dao.freeboardUpdate(dto);

				List<MultipartFile> fileList = request.getFiles("file");
				
				File uploadDir = new File(uploadPath + File.separator + uploadFolder);
				if (!uploadDir.exists()) uploadDir.mkdir();
				
				// 첨부파일 여러개 고려
		    	List<String> fileUrlList = dao.freeboardFileUrlDeleteList(dto.getBoardId());	
				for(String fileUrl : fileUrlList) {	
					File file = new File(uploadPath + File.separator, fileUrl);
					if(file.exists()) {
						file.delete();
					}
				}

		    	dao.freeboardFileDelete(dto.getBoardId()); 
		    	
		    	
				for (MultipartFile part : fileList) {
					int boardId = dto.getBoardId();
					
					String fileName = part.getOriginalFilename();
					
					UUID uuid = UUID.randomUUID();
					
					String extension = FilenameUtils.getExtension(fileName); 
				
					String savingFileName = uuid + "." + extension;
				
					File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
					
					System.out.println(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
					part.transferTo(destFile);
			    
				    // Table Insert
				    FreeBoardFileDto freeboardFileDto = new FreeBoardFileDto();
				    freeboardFileDto.setBoardId(boardId);
				    freeboardFileDto.setFileName(fileName);
				    freeboardFileDto.setFileSize(part.getSize());
				    freeboardFileDto.setFileContentType(part.getContentType());
					String freeboardFileUrl = uploadFolder + "/" + savingFileName;
					freeboardFileDto.setFileUrl(freeboardFileUrl);
					
					dao.freeboardFileInsert(freeboardFileDto);
				}

				freeboardResultDto.setResult(SUCCESS);
				
			}catch(IOException e) {
				e.printStackTrace();
				freeboardResultDto.setResult(FAIL);
			}
			
			return freeboardResultDto;
		}
		
		@Override
		@Transactional
		public FreeBoardResultDto freeboardInsert(FreeBoardDto dto, MultipartHttpServletRequest request) {
			FreeBoardResultDto freeboardResultDto = new FreeBoardResultDto();
			
			try {
				dao.freeboardInsert(dto); 
				
				List<MultipartFile> fileList = request.getFiles("file");
		
				File uploadDir = new File(uploadPath + File.separator + uploadFolder);
				if (!uploadDir.exists()) uploadDir.mkdir();

				for (MultipartFile part : fileList) {

					int boardId = dto.getBoardId();
					
					String fileName = part.getOriginalFilename();
					
					UUID uuid = UUID.randomUUID();
					
					String extension = FilenameUtils.getExtension(fileName); 
				
					String savingFileName = uuid + "." + extension;
				
					File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
					
					System.out.println(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
					part.transferTo(destFile);
			    
				    // Table Insert
				    FreeBoardFileDto freeboardFileDto = new FreeBoardFileDto();
				    freeboardFileDto.setBoardId(boardId);
				    freeboardFileDto.setFileName(fileName);
				    freeboardFileDto.setFileSize(part.getSize());
				    freeboardFileDto.setFileContentType(part.getContentType());
					String boardFileUrl = uploadFolder + "/" + savingFileName;
					freeboardFileDto.setFileUrl(boardFileUrl);
					
					dao.freeboardFileInsert(freeboardFileDto);
				}

				freeboardResultDto.setResult(SUCCESS);
				
			}catch(IOException e) {
				e.printStackTrace();
				freeboardResultDto.setResult(FAIL);
			}
			return freeboardResultDto;
		}
		
		@Override
		public FreeBoardResultDto freeboardList(FreeBoardParamDto freeboardParamDto) {
			FreeBoardResultDto freeboardResultDto = new FreeBoardResultDto();
			
			try {
				List<FreeBoardDto> list = dao.freeboardList(freeboardParamDto);
				int count = dao.freeboardListTotalCount();			
				freeboardResultDto.setList(list);
				freeboardResultDto.setCount(count);
				freeboardResultDto.setResult(SUCCESS);
				
			}catch(Exception e) {
				e.printStackTrace();
				freeboardResultDto.setResult(FAIL);
			}
			
			return freeboardResultDto;
		}
		
		@Override
		@Transactional
		public FreeBoardResultDto freeboardListSearchWord(FreeBoardParamDto freeboardParamDto) {
			FreeBoardResultDto freeboardResultDto = new FreeBoardResultDto();
			
			try {
				List<FreeBoardDto> list = dao.freeboardListSearchWord(freeboardParamDto);
				int count = dao.freeboardListSearchWordTotalCount(freeboardParamDto);
				
				freeboardResultDto.setList(list);
				freeboardResultDto.setCount(count);
				
				freeboardResultDto.setResult(SUCCESS);
			
			}catch(Exception e) {
				e.printStackTrace();
				freeboardResultDto.setResult(FAIL);
			}
			
			return freeboardResultDto;
		}
		
		
		
		
		
		
		
		
}