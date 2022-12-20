package com.spring.training.member.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.training.member.dto.MemberDto;
import com.spring.training.member.service.MemberService;

import net.coobird.thumbnailator.Thumbnails;


@Controller
@RequestMapping("/member")
public class MemberController {

	private String filePath = "C:\\spring_mvc_member_file_repo\\";
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/main" , method=RequestMethod.GET)
	public ModelAndView main() throws Exception {
		return new ModelAndView("member/main");
	}
	
	
	@RequestMapping(value="/register" , method=RequestMethod.GET)
	public ModelAndView register() throws Exception{
		return new ModelAndView("member/register");
	}
	
	
	@RequestMapping(value="/register" , method=RequestMethod.POST)
	public @ResponseBody String register(MultipartHttpServletRequest multipartRequest , HttpServletRequest request) throws Exception {
		
		Iterator<String> fileList = multipartRequest.getFileNames();			
		String fileName = "";
		if (fileList.hasNext()) {												
			MultipartFile uploadFile = multipartRequest.getFile(fileList.next()); 
			if (!uploadFile.getOriginalFilename().isEmpty()) {						
				fileName = UUID.randomUUID() + "_" + uploadFile.getOriginalFilename();	
				File file = new File(filePath + fileName);			
				uploadFile.transferTo(file); 
			}
		
		}
		
		MemberDto memberDto = new MemberDto();
		memberDto.setMemberId(multipartRequest.getParameter("memberId"));
		memberDto.setPasswd(multipartRequest.getParameter("passwd"));
		memberDto.setProfile(fileName);
		memberDto.setMemberNm(multipartRequest.getParameter("memberNm"));
		memberDto.setSex(multipartRequest.getParameter("sex"));
		memberDto.setBirthDt(multipartRequest.getParameter("birthDt"));
		memberDto.setHp(multipartRequest.getParameter("hp"));
		memberDto.setSmsstsYn(multipartRequest.getParameter("smsstsYn"));
		memberDto.setEmail(multipartRequest.getParameter("email"));
		memberDto.setEmailstsYn(multipartRequest.getParameter("emailstsYn"));
		memberDto.setZipcode(multipartRequest.getParameter("zipcode"));
		memberDto.setRoadAddress(multipartRequest.getParameter("roadAddress"));
		memberDto.setJibunAddress(multipartRequest.getParameter("jibunAddress"));
		memberDto.setNamujiAddress(multipartRequest.getParameter("namujiAddress"));
		memberDto.setEtc(multipartRequest.getParameter("etc"));
		
		memberService.addMember(memberDto);
		
		String jsScript = "<script>";
			   jsScript += "alert('You are now a member.');";
			   jsScript += "location.href='" + request.getContextPath() + "/member/main'";
			   jsScript += "</script>";
			   
		return jsScript;
		
	}
	
	
	@RequestMapping(value="/overlappedId" , method=RequestMethod.POST)
	public @ResponseBody String overlapped(@RequestParam("memberId") String memberId) throws Exception{
		return memberService.checkOverlappedId(memberId);
	}
	
	
	@RequestMapping(value = "/login" , method=RequestMethod.GET)
	public ModelAndView login() throws Exception {
		return new ModelAndView("member/login");
	}
	
	
	@RequestMapping(value="/login" , method=RequestMethod.POST)
	public @ResponseBody String login(MemberDto memberDto , HttpServletRequest request) throws Exception {
		
		String jsScript = "";
		if (memberService.loginMember(memberDto) != null) {
			
			HttpSession session = request.getSession();
			session.setAttribute("memberId", memberDto.getMemberId());
			
			jsScript += "<script>";
			jsScript += "alert('You are logged in.');";
			jsScript += "location.href='" + request.getContextPath() + "/member/main'";
			jsScript += "</script>";
			
		} 
		else {
			
			jsScript += "<script>";
			jsScript += "alert('check your Id or Password!');";
			jsScript += "history.go(-1);";
			jsScript += "</script>";
			
		}
		
		return jsScript;
		
	}
	
	
	@RequestMapping(value="/logout" , method=RequestMethod.GET)
	public @ResponseBody String logout(HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		String jsScript = "<script>";
				jsScript += "alert('You are logged out.');";
				jsScript += "location.href='" + request.getContextPath() + "/member/main'";
				jsScript += "</script>";
		
		return jsScript;
		
	}
	
	
	@RequestMapping(value="/memberList" , method=RequestMethod.GET)
	public ModelAndView memberList() throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("member/memberList");
		mv.addObject("memberList" , memberService.getMemberList());
		
		return mv;
	
	}	
	
	
	@RequestMapping(value="/modify" , method=RequestMethod.GET)
	public ModelAndView modifyMember(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/modify");
		mv.addObject("memberDto" , memberService.getMemberDetail((String)session.getAttribute("memberId")));
		
		return mv;
		
	}	
	
	
	@RequestMapping(value="/modify" , method=RequestMethod.POST)
	public @ResponseBody String modify(MultipartHttpServletRequest multipartRequest , HttpServletRequest request) throws Exception {
		
		Iterator<String> fileList = multipartRequest.getFileNames();			
		String fileName = "";
		if (fileList.hasNext()) {												
			
			MultipartFile uploadFile = multipartRequest.getFile(fileList.next()); 
			if (!uploadFile.getOriginalFilename().isEmpty()) {						
				fileName =  UUID.randomUUID() + "_" + uploadFile.getOriginalFilename();	
				
				uploadFile.transferTo(new File(filePath + fileName)); 
				new File(filePath + multipartRequest.getParameter("beforeFileName")).delete();		
				
			}
		
		}
		
		MemberDto memberDto = new MemberDto();
		memberDto.setMemberId(multipartRequest.getParameter("memberId"));
		memberDto.setPasswd(multipartRequest.getParameter("passwd"));
		memberDto.setProfile(fileName);
		memberDto.setMemberNm(multipartRequest.getParameter("memberNm"));
		memberDto.setSex(multipartRequest.getParameter("sex"));
		memberDto.setBirthDt(multipartRequest.getParameter("birthDt"));
		memberDto.setHp(multipartRequest.getParameter("hp"));
		memberDto.setSmsstsYn(multipartRequest.getParameter("smsstsYn"));
		memberDto.setEmail(multipartRequest.getParameter("email"));
		memberDto.setEmailstsYn(multipartRequest.getParameter("emailstsYn"));
		memberDto.setZipcode(multipartRequest.getParameter("zipcode"));
		memberDto.setRoadAddress(multipartRequest.getParameter("roadAddress"));
		memberDto.setJibunAddress(multipartRequest.getParameter("jibunAddress"));
		memberDto.setNamujiAddress(multipartRequest.getParameter("namujiAddress"));
		memberDto.setEtc(multipartRequest.getParameter("etc"));
		
		String jsScript = "";
		if (memberService.modifyMember(memberDto)) {
			jsScript = "<script>";
			jsScript += "alert('It is changed.');";
			jsScript += "location.href='" + request.getContextPath() + "/member/main'";
			jsScript += "</script>";
		} 
		else {
			jsScript = "<script>";
			jsScript += "alert('check your password!');";
			jsScript += "history.back(-1);";
			jsScript += "</script>";
		}
		
		return  jsScript;
		
	}
	
	
	@RequestMapping(value="/remove" , method=RequestMethod.GET)
	public ModelAndView removeMember() throws Exception{
		return new ModelAndView("member/remove");
	}
	
	
	@RequestMapping(value="/remove" , method=RequestMethod.POST)
	public @ResponseBody String removeMember(MemberDto memberDto , HttpServletRequest request) throws Exception {
		
		String jsScript = "";
		
		if (memberService.removeMember(memberDto)) {
			
			HttpSession session = request.getSession();
			session.invalidate();
			
			jsScript += "<script>";
			jsScript += "alert('Your account has been deleted successfully!');";
			jsScript += "location.href='" + request.getContextPath() + "/member/main'";
			jsScript += "</script>";
			
		} 
		else {
			
			jsScript += "<script>";
			jsScript += "alert('Check your Id or Password');";
			jsScript += "history.go(-1);";
			jsScript += "</script>";
	
		}
		
		return jsScript;
		
	}
	
	
	@RequestMapping(value="/memberExcelExport" , method=RequestMethod.GET)
	public void memberExcelExport(HttpServletResponse response , @RequestParam Map<String, String> dateMap) throws Exception {
		  
		SimpleDateFormat fileSdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm");
		String makeFileTime = fileSdf.format(new Date());
		String makeFileName = makeFileTime + "_memberList.xls";
		
	    // 워크북 생성
	    Workbook wb = new HSSFWorkbook();
	    Sheet sheet = wb.createSheet("회원리스트");
	    Row row = null;
	    Cell cell = null;

	    int rowNo = 0;

	    // 테이블 헤더용 스타일
	    CellStyle headStyle = wb.createCellStyle();
	    // 가는 경계선
	    headStyle.setBorderTop(BorderStyle.THIN);
	    headStyle.setBorderBottom(BorderStyle.THIN);
	    headStyle.setBorderLeft(BorderStyle.THIN);
	    headStyle.setBorderRight(BorderStyle.THIN);


	    // 노란색 배경
	    headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
	    headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	    // 가운데 정렬
	    headStyle.setAlignment(HorizontalAlignment.CENTER);


	    // 데이터용 경계 스타일 테두리만 지정
	    CellStyle bodyStyle = wb.createCellStyle();
	    bodyStyle.setBorderTop(BorderStyle.THIN);
	    bodyStyle.setBorderBottom(BorderStyle.THIN);
	    bodyStyle.setBorderLeft(BorderStyle.THIN);
	    bodyStyle.setBorderRight(BorderStyle.THIN);

	    // 헤더 생성
	    row = sheet.createRow(rowNo++);
	    cell = row.createCell(0);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("회원아이디");
	    cell = row.createCell(1);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("회원이름");
	    cell = row.createCell(2);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("성별");
	    cell = row.createCell(3);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("휴대폰번호");
	    cell = row.createCell(4);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("문자수신여부");
	    cell = row.createCell(5);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("주소");
	    cell = row.createCell(6);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("이메일");
	    cell = row.createCell(7);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("이메일수신여부");
	    
		for (MemberDto memberDto :  memberService.getMemberList()) {
			row = sheet.createRow(rowNo++);
	        cell = row.createCell(0);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(memberDto.getMemberId());
	        cell = row.createCell(1);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(memberDto.getMemberNm());
	        cell = row.createCell(2);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(memberDto.getSex());
	        cell = row.createCell(3);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(memberDto.getHp());
	        cell = row.createCell(4);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(memberDto.getSmsstsYn());
	        cell = row.createCell(5);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(memberDto.getRoadAddress() + " " + memberDto.getJibunAddress() + " " + memberDto.getNamujiAddress());
	        cell = row.createCell(6);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(memberDto.getEmail());
	        cell = row.createCell(7);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(memberDto.getEmailstsYn());

		} 

	    response.setContentType("ms-vnd/excel");
	    response.setHeader("Content-Disposition", "attachment;filename="+makeFileName);

	    // 엑셀 출력
	    wb.write(response.getOutputStream());
	    wb.close();
		
	}
	

	// 썸네일 컨트롤러 : 이미지 파일을 읽어들여서 화면에 출력한다.
	@RequestMapping(value="/thumbnails" , method=RequestMethod.GET)
	public void thumbnails(@RequestParam("fileName") String fileName , HttpServletResponse response) throws Exception {
	
		OutputStream out = response.getOutputStream();
		
		File image = new File(filePath + fileName);
		if (image.exists()) { 
			Thumbnails.of(image).size(121,154).outputFormat("png").toOutputStream(out);
		}
		byte[] buffer = new byte[1024 * 8];
		out.write(buffer);
		out.close();
		
	}
	
	
}
