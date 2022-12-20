package com.spring.training.boardAdvance.dao;

import java.util.List;
import java.util.Map;

import com.spring.training.boardAdvance.dto.BoardAdvanceDto;

public interface BoardAdvanceDao {

	public List<BoardAdvanceDto> selectListBoard(Map<String, Object> searchInfo) throws Exception;
	public int selectBoardCnt(Map<String, String> searchCountInfo) throws Exception;
	public BoardAdvanceDto selectOneBoard(int num) throws Exception;
	public void updateReadCount(int readCount) throws Exception;
	public void insertBoard(BoardAdvanceDto boardAdvanceDto) throws Exception;
	public void insertReplyBoard(BoardAdvanceDto boardAdvanceDto) throws Exception;
	public void updateBoard(BoardAdvanceDto boardAdvanceDto) throws Exception;
	public void deleteBoard(int num) throws Exception;
	public void updateBoardReplyStep(BoardAdvanceDto boardAdvanceDto) throws Exception;
	public String selectOneUserCheck(int num) throws Exception;
	public void insertDummyData(List<BoardAdvanceDto> dataLists) throws Exception;

}
