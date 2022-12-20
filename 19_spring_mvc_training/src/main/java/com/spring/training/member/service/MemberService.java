package com.spring.training.member.service;

import java.util.List;

import com.spring.training.member.dto.MemberDto;

public interface MemberService {

	public void addMember(MemberDto memberDto) throws Exception;	
	public MemberDto loginMember(MemberDto memberDto) throws Exception;
	public List<MemberDto> getMemberList() throws Exception;
	public MemberDto getMemberDetail(String memberId) throws Exception;
	public boolean modifyMember(MemberDto memberDto) throws Exception;
	public boolean removeMember(MemberDto memberDto) throws Exception;
	public String checkOverlappedId(String memberId) throws Exception; 
	
}
