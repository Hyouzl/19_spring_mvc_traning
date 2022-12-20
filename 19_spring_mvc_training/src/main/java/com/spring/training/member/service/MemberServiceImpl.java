package com.spring.training.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.training.member.dao.MemberDao;
import com.spring.training.member.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void addMember(MemberDto memberDto) throws Exception {
		memberDto.setPasswd(bCryptPasswordEncoder.encode(memberDto.getPasswd())); 
		memberDao.insertMember(memberDto);
	}

	@Override
	public MemberDto loginMember(MemberDto memberDto) throws Exception {
		
		MemberDto dbMemberDto = memberDao.login(memberDto);
		
		if (dbMemberDto != null) {
			if (bCryptPasswordEncoder.matches(memberDto.getPasswd(), dbMemberDto.getPasswd())) {
				return memberDto;
			} 
		}
		
		return null;
		
	}
	
	@Override
	public List<MemberDto> getMemberList() throws Exception {
		return memberDao.selectListMember();
	}
	
	@Override
	public MemberDto getMemberDetail(String memberId) throws Exception {
		return memberDao.selectOneMember(memberId);
	}

	@Override
	public boolean modifyMember(MemberDto memberDto) throws Exception {
		
		MemberDto dbMemberDto = memberDao.login(memberDto);
		
		if (bCryptPasswordEncoder.matches(memberDto.getPasswd(), dbMemberDto.getPasswd())) {
			memberDao.updateMember(memberDto);
			return true;
		}
		
		return false;
		
	}
	
	@Override
	public boolean removeMember(MemberDto memberDto) throws Exception {
		
		MemberDto dbMemberDto = memberDao.login(memberDto);
		
		if (bCryptPasswordEncoder.matches(memberDto.getPasswd(), dbMemberDto.getPasswd())) {
			memberDao.deleteMember(memberDto.getMemberId());
			return true;
		}
		
		return false;
		
	}

	@Override
	public String checkOverlappedId(String memberId) throws Exception {
		
		String isOverLapped = "Y";
		
		if (memberDao.overlapped(memberId) == null) {
			return "N";
		}
		
		return isOverLapped;
		
	}
	
}
