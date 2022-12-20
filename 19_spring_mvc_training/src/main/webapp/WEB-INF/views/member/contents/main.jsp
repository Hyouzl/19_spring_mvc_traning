<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
</head>
<body>

	<!-- 
	
		# Read Me
		
		- 4가지의 특정 유틸리티가 소스에 반영되어 있습니다.
		- 모르는 부분은 구글링 하여 이해 및 해결하여 새로운 문법을 익히는 힘과 노하우를 기릅니다.
		
		1. ckeditor			
 		
	 		 1-1) https://ckeditor.com/ckeditor-4/download에 접속하여 ckeditor 다운로드 한다.
	 		 1-2) webapp/resources 관련 경로로 다운로드한 파일을 이동한다.
	 		 1-3) <script src="경로/ckeditor.js"></script> 선언한다.
	 		 1-4) 아래와 같은 형태로 textarea태그 밑에 script를 추가하고 네임명을 같이하여 사용한다.
	 		      <textarea name="네임명"></textarea>
			      <script>CKEDITOR.replace('네임명');</script>
 		
 		2) daumpostofficecode
 			
 			1-1) <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>를 추가한다.
			1-2) 아래의 스크립트를 추가하여 사용한다.
			      <script>
						function execDaumPostcode() {
						    new daum.Postcode({
						        oncomplete: function(data) {
						            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
						
						            // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
						            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						            var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
						            var extraRoadAddr = ''; // 도로명 조합형 주소 변수
						
						            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
						            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						            if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
						                extraRoadAddr += data.bname;
						            }
						            // 건물명이 있고, 공동주택일 경우 추가한다.
						            if (data.buildingName !== '' && data.apartment === 'Y'){
						               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
						            }
						            // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						            if (extraRoadAddr !== ''){
						                extraRoadAddr = ' (' + extraRoadAddr + ')';
						            }
						            // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
						            if (fullRoadAddr !== ''){
						                fullRoadAddr += extraRoadAddr;
						            }
						
						            // 우편번호와 주소 정보를 해당 필드에 넣는다.
						            document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
						            document.getElementById('roadAddress').value = fullRoadAddr;
						            document.getElementById('jibunAddress').value = data.jibunAddress;
						
						        }
						    }).open();
						}
					</script>
		
		
		3. Excel Export
		
			3-1) pom.xml파일에 dependency를 추가한다.
		
			//EXcel export 기능
				<dependency>
				    <groupId>org.apache.poi</groupId>
				    <artifactId>poi</artifactId>
				    <version>3.17</version>
				</dependency>
			
			3-2) 컨트롤러에서 엑셀 다운로드 기능 구현한다.
			 
			   - 엑셀 다운로드 관련 기능은 구글에서 참조하여 구현한다.
			
			
		
		4. 패스워드 암호화
		
			4-1) pom.xml파일에 dependency를 추가한다.
			
				<dependency>
				    <groupId>org.springframework.security</groupId>
				    <artifactId>spring-security-web</artifactId>
				    <version>5.0.8.RELEASE</version>
				</dependency>
				
				<dependency>
				    <groupId>org.springframework.security</groupId>
				    <artifactId>spring-security-config</artifactId>
				    <version>5.0.8.RELEASE</version>
				</dependency>
			
			4-2) servlet-context.xml에 아래의 빈 선언을 추가한다.
		    
		    	<beans:bean id="bcryptPasswordEncoder" class="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder" /> 
		
			
			4-3) 서비스 로직에서 BCryptPasswordEncoder 객체를 생성한다.
		
				@Autowired
				private BCryptPasswordEncoder bCryptPasswordEncoder;
		
		    4-4) 사용법
		    
				- bCryptPasswordEncoder.encode(암호화하고 싶은 패스워드)   				// encode(평문)메서드를 통하여 패스워드를 암호화한다.
				- bCryptPasswordEncoder.matches(입력받은 패스워드, 암호화된 패스워드) 	// matches(평문,암호문) 메서드를 통하여 입력받은 패스워드와 암호화된 패스워드를 비교한다.
				- 암호화된 패스워드를 복호화하는 메서드는 존재하지 않는다.		
	
	 -->

	<div>
		<img src="${contextPath }/resources/img/member.PNG"/>
	</div>

	<c:choose>
		<c:when test="${sessionScope.memberId eq 'admin'}">
			<p><a href="${contextPath }/member/memberList">회원리스트 조회</a></p>
			<p><a href="${contextPath }/member/logout">로그아웃</a></p>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${sessionScope.memberId eq null}">
					<p><a href="${contextPath }/member/register">회원가입</a></p>
					<p><a href="${contextPath }/member/login">로그인</a></p>	
				</c:when>
				<c:otherwise>
					<p><a href="${contextPath }/member/modify">회원정보 수정</a></p>	
					<p><a href="${contextPath }/member/logout">로그아웃</a></p>
					<p><a href="${contextPath }/member/remove">회원탈퇴</a></p>	
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</body>
</html>