package com.spring.training.boardAdvance.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.training.boardAdvance.dao.BoardAdvanceDao;
import com.spring.training.boardAdvance.dto.BoardAdvanceDto;

@Service		
public class BoardAdvanceServiceImpl implements BoardAdvanceService {

	@Autowired				
	private BoardAdvanceDao boardAdvanceDao;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public List<BoardAdvanceDto> getBoardList(Map<String, Object> searchInfo) throws Exception{
		return boardAdvanceDao.selectListBoard(searchInfo);
	}
	 
	
	@Override
	public BoardAdvanceDto getBoardDetail(int num) throws Exception{
		boardAdvanceDao.updateReadCount(num);
		return boardAdvanceDao.selectOneBoard(num);
	}
	
	
	@Override
	public void addBoard(BoardAdvanceDto boardAdvanceDto) throws Exception {
		boardAdvanceDto.setPassword(bCryptPasswordEncoder.encode(boardAdvanceDto.getPassword()));
		boardAdvanceDao.insertBoard(boardAdvanceDto);
	}
	
	
	@Override
	public boolean modifyBoard(BoardAdvanceDto boardAdvanceDto) throws Exception {
		boolean isSuccess = false;
		if (bCryptPasswordEncoder.matches(boardAdvanceDto.getPassword() , boardAdvanceDao.selectOneUserCheck(boardAdvanceDto.getNum()))) {
			isSuccess = true;
			boardAdvanceDao.updateBoard(boardAdvanceDto);
		}
		return isSuccess;
	}

	
	@Override
	public boolean removeBoard(BoardAdvanceDto boardAdvanceDto) throws Exception {
		boolean isSuccess = false;
		if (bCryptPasswordEncoder.matches(boardAdvanceDto.getPassword() , boardAdvanceDao.selectOneUserCheck(boardAdvanceDto.getNum()))) {
			boardAdvanceDao.deleteBoard(boardAdvanceDto.getNum());
			isSuccess = true;
		}
		return isSuccess;
	}

	
	@Override
	public void makeDummyData() throws Exception {
		
		Random ran = new Random();
		
		List<BoardAdvanceDto> dummyDataList = new ArrayList<BoardAdvanceDto>();
	
		String[] word = {"가","나","다","라","마","바","사","아","자","차","카","타","파","하"};
		
		for (int i = 1001; i < 1301; i++) {
			
			String writer    = "";
			String password  = "1111";
			String subject   = "";
			String email     = "";
			String content   = "";
			for (int j = 0; j < 7; j++) {
				writer  += word[ran.nextInt(word.length)];
				subject += word[ran.nextInt(word.length)];
				content += word[ran.nextInt(word.length)];
				if (j < 4) {
					email += word[ran.nextInt(word.length)];
				}
			}
			email += "@gmail.com";
			
			BoardAdvanceDto temp = new BoardAdvanceDto();
			temp.setNum(i);		
			temp.setWriter(writer);
			temp.setEmail(email);
			temp.setSubject(subject);
			temp.setPassword(bCryptPasswordEncoder.encode(password));
			temp.setRef(i);
			temp.setReLevel(1);
			temp.setReStep(1);
			temp.setReadCount(0);
			temp.setContent(content);
			
			dummyDataList.add(temp);
			
		}
		
		boardAdvanceDao.insertDummyData(dummyDataList);
		
	}

	
	@Override
	public int getTotalBoardCount(Map<String, String> searchCountInfo) throws Exception {
		return boardAdvanceDao.selectBoardCnt(searchCountInfo);
	}

	
	@Override
	public void addReplyBoard(BoardAdvanceDto boardAdvanceDto) throws Exception {
		boardAdvanceDao.updateBoardReplyStep(boardAdvanceDto);
		boardAdvanceDto.setPassword(bCryptPasswordEncoder.encode(boardAdvanceDto.getPassword()));
		boardAdvanceDao.insertReplyBoard(boardAdvanceDto);
	}
	
}
