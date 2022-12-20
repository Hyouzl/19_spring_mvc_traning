<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<script>

	$().ready(function() {
		
		var hp = "${memberDto.hp}".split("-");
		$("#hp1").val(hp[0]);
		$("#hp2").val(hp[1]);
		$("#hp3").val(hp[2]);
		
		var birthDt = "${memberDto.birthDt}".split("-");
		$("#birthY").val(birthDt[0]);
		$("#birthM").val(birthDt[1]);
		$("#birthD").val(birthDt[2]);
		
		$("form").submit(function(){
			
			
			$("[name='hp']").val($("#hp1").val() +"-" + $("#hp2").val() +"-" + $("#hp3").val());
			$("[name='birthDt']").val($("#birthY").val() +"-" + $("#birthM").val() +"-" + $("#birthD").val());
			
			if (!$("[name='smsstsYn']").prop("checked"))  {
				$("[name='smsstsYn']").val("N");
				$("[name='smsstsYn']").prop("checked", true);
			}
			if (!$("[name='emailstsYn']").prop("checked")) {
				$("[name='emailstsYn']").val("N");
				$("[name='emailstsYn']").prop("checked", true);
			}
			
		});
		
		
	});
	
</script>
</head>
<body>

	<h3>'${memberDto.memberId }'님의 정보 수정</h3>
	<form action="${contextPath}/member/modify" method="post" enctype="multipart/form-data">
		<table border="1">
	        <tr>
		        <td><img src="${contextPath }/member/thumbnails?fileName=${memberDto.profile}" width="50" height="50" alt="사진"></td>
		        <td>
		        	<input type="file" name="profile"/>
		        	<input type="hidden" name="beforeFileName" value="${memberDto.profile}"/>
		        </td>
	        </tr>
			<tr>
				<td>아이디</td>
				<td>
		            <input type="text" name="memberId" id="memberId" maxlength="15" value="${memberDto.memberId }" readonly/>&emsp;
		        </td>
		    </tr>
	        <tr>
		        <td>비밀번호</td>
		        <td><input type="password" name="passwd" /></td>
	        </tr>
	        <tr>
		        <td>이름</td>
		        <td><input type="text" name="memberNm" maxlength="15" value="${memberDto.memberNm }"/></td>
	        </tr>                
		    <tr>
		        <td>성별</td>
		        <td>
		        	<input type="radio" name="sex" value="m" <c:if test="${memberDto.sex eq 'm'}">checked </c:if> />남성&emsp;&emsp;&emsp;
					<input type="radio" name="sex" value="w" <c:if test="${memberDto.sex eq 'w'}">checked </c:if> />여성
		        </td>
	        </tr>                              
	        <tr>
		        <td>생년월일</td>
		        <td>
	                <select id="birthY" >
						<c:forEach var="year" begin="1" end="100">
							<option value="${1922 + year}">${1922 + year}</option>
						</c:forEach>
					</select> 년 
					<select id="birthM">
					  	<c:forEach var="month" begin="1" end="12">
						   <c:choose>
								<c:when test="${month < 10 }">
									<option value="0${month}">0${month}</option>
								</c:when>
								<c:otherwise>
									<option value="${month}">${month}</option>
								</c:otherwise>
							</c:choose>
					  	</c:forEach>
					</select> 월  
					<select id="birthD">
						<c:forEach var="day" begin="1" end="31">
							<c:choose>
								<c:when test="${day < 10 }">
									<option value="0${day}">0${day}</option>
								</c:when>
								<c:otherwise>
									<option value="${day}">${day}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select> 일 &emsp;
					<input type="hidden" name="birthDt" value="${memberDto.birthDt }">
		        </td>
	        </tr>                        
	        <tr>
		        <td>핸드폰 번호</td>
		        <td>
		        	<select id="hp1" >
						<option>없음</option>
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="019">019</option>
					</select> - 
					<input size="10px" type="text" id="hp2"> - 
					<input size="10px" type="text" id="hp3">
					<input type="checkbox" id="smsstsYn" name="smsstsYn"  value="Y" <c:if test="${memberDto.smsstsYn == 'Y'}"> checked</c:if>/>
					<input type="hidden" name="hp" value="${memberDto.hp}">
	                스프링에서 발송하는 SMS 소식을 수신합니다.
		        </td>
	        </tr>                         
	        <tr>
		        <td>이메일</td>
		        <td>
		        	<input type="email" name="email" value="${memberDto.email}">  
	                <input type="checkbox" id="emailstsYn" name="emailstsYn" value="Y" <c:if test="${memberDto.emailstsYn == 'Y'}"> checked</c:if>/>
	                스프링에서 발송하는 E-mail을 수신합니다.
		        </td>
	        </tr>                              
	        <tr>
		        <td>주소</td>
		        <td>
		        	<input type="text" placeholder="우편번호 입력" id="zipcode" name="zipcode" value="${memberDto.zipcode }">
	                <input type="button" onclick="javascript:execDaumPostcode()" value="검색">
	                <br><br>
	                            도로명 주소 : <input type="text" name="roadAddress" id="roadAddress" value="${memberDto.roadAddress }" size="60"> <br>
					지번 주소 : <input type="text"  name="jibunAddress" id="jibunAddress" value="${memberDto.jibunAddress }" size="60"> <br>
					나머지 주소: <input type="text" name="namujiAddress" id="namujiAddress" value="${memberDto.namujiAddress }" size="60"/>
		        </td>
	        </tr>
	        <tr>
		        <td>기타</td>
		        <td>
		        	<textarea rows="10" cols="10" name="etc">${memberDto.etc }</textarea>
		        	<script>CKEDITOR.replace('etc');</script>
		        </td>
	        </tr>                                     
	        <tr>
		        <td colspan="2" align="center">
		        	<input type="submit" value="수정하기"  >
		        </td>
		    </tr>
	     </table>
     </form>
     <a href="${contextPath }/member/main">메인으로 이동</a>
</body>
</html>