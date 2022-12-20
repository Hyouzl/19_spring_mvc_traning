package com.spring.training.board.dao;

import java.util.List;

import com.spring.training.board.dto.BoardDto;

public interface BoardDao {

	public void insertBoard(BoardDto boardDto) throws Exception;
	public List<BoardDto> selectListBoard() throws Exception;
	public BoardDto selectOneBoard(int num) throws Exception;
	public void updateReadCount(int num) throws Exception;
	public BoardDto selectOneValidateUserCheck(BoardDto boardDto) throws Exception;
	public void updateBoard(BoardDto boardDto) throws Exception;
	public void deleteBoard(int num) throws Exception;
	
}
