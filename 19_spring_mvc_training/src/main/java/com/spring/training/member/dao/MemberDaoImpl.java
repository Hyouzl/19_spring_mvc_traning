package com.spring.training.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.training.member.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao{

	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public void insertMember(MemberDto memberDto) throws Exception {
		sqlSession.insert("member.insertMember" , memberDto);
	}
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		return sqlSession.selectOne("member.loginMember" , memberDto);
	}
	
	@Override
	public MemberDto overlapped(String memberId) throws Exception {
		return sqlSession.selectOne("member.duplicatedMemberCheck" , memberId);
	}
	
	
	@Override
	public List<MemberDto> selectListMember() throws Exception {
		return sqlSession.selectList("member.selectListMember");
	}

	
	@Override
	public MemberDto selectOneMember(String memberId) throws Exception {
		return sqlSession.selectOne("member.selectOneMember" , memberId);
	}

	
	@Override
	public void deleteMember(String memberId) throws Exception {
		sqlSession.delete("member.deleteMember" , memberId);
	}

	
	@Override
	public void updateMember(MemberDto memberDto) throws Exception {
		sqlSession.update("member.updateMember" , memberDto);
	}
	
}
