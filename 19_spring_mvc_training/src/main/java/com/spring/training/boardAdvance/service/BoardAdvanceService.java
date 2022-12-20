package com.spring.training.boardAdvance.service;

import java.util.List;
import java.util.Map;

import com.spring.training.boardAdvance.dto.BoardAdvanceDto;

public interface BoardAdvanceService {
	
	public List<BoardAdvanceDto> getBoardList(Map<String, Object> searchInfo) throws Exception;
	public int getTotalBoardCount(Map<String, String> searchCountInfo) throws Exception;
	public BoardAdvanceDto getBoardDetail(int num) throws Exception;
	public void addBoard(BoardAdvanceDto boardAdvanceDto) throws Exception;
	public void addReplyBoard(BoardAdvanceDto boardAdvanceDto) throws Exception;
	public boolean modifyBoard(BoardAdvanceDto boardAdvanceDto) throws Exception;
	public boolean removeBoard(BoardAdvanceDto boardAdvanceDto) throws Exception;
	public void makeDummyData() throws Exception;
	
}
