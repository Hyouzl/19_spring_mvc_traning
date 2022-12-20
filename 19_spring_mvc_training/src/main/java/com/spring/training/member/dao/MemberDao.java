package com.spring.training.member.dao;

import java.util.List;

import com.spring.training.member.dto.MemberDto;

public interface MemberDao {

	public void insertMember(MemberDto memberDto) throws Exception;
	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto overlapped(String memberId) throws Exception;
	public List<MemberDto> selectListMember() throws Exception;
	public MemberDto selectOneMember(String memberId) throws Exception;
	public void updateMember(MemberDto memberDto) throws Exception;
	public void deleteMember(String memberId) throws Exception;
	
}
