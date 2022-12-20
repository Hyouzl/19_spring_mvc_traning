package com.spring.training.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.training.board.dao.BoardDao;
import com.spring.training.board.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;
	
	@Override
	public void addBoard(BoardDto boardDto) throws Exception{
		boardDao.insertBoard(boardDto);
	}

	@Override
	public List<BoardDto> getBoardList() throws Exception{
		return boardDao.selectListBoard();
	}

	@Override
	public BoardDto getBoardDetail(int num) throws Exception{
		boardDao.updateReadCount(num);
		return boardDao.selectOneBoard(num);
	}

	@Override
	public boolean modifyBoard(BoardDto boardDto) throws Exception {
		
		boolean isUpdate = false;
		
		if (boardDao.selectOneValidateUserCheck(boardDto) != null) {
			boardDao.updateBoard(boardDto);
			isUpdate = true;
		}
		
		return isUpdate;
		
	}

	@Override
	public boolean removeBoard(BoardDto boardDto) throws Exception {
		
		boolean isDelete = false;
		
		if (boardDao.selectOneValidateUserCheck(boardDto) != null) {
			boardDao.deleteBoard(boardDto.getNum());
			isDelete = true;
		}
		
		return isDelete;
		
	}

	
	
}
