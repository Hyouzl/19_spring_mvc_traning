package com.spring.training.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.training.board.dto.BoardDto;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertBoard(BoardDto boardDto) throws Exception {
		sqlSession.insert("board.insertBoard" , boardDto);
	}

	@Override
	public List<BoardDto> selectListBoard() throws Exception {
		return sqlSession.selectList("board.selectListBoard");
	}

	@Override
	public BoardDto selectOneBoard(int num) throws Exception {
		return sqlSession.selectOne("board.selectOneBoard" , num);
	}

	@Override
	public void updateReadCount(int num) throws Exception {
		sqlSession.update("board.updateReadCount" , num);
	}

	@Override
	public BoardDto selectOneValidateUserCheck(BoardDto boardDto) throws Exception {
		return sqlSession.selectOne("board.selectOneValidateUserCheck" , boardDto);
	}

	@Override
	public void updateBoard(BoardDto boardDto) throws Exception {
		sqlSession.update("board.updateBoard" , boardDto);
	}

	@Override
	public void deleteBoard(int num) throws Exception {
		sqlSession.delete("board.deleteBoard" , num);
	}
	
}
