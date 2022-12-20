<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bInfo</title>
</head>
<body>

	<div align="center" style="padding-top: 100px">
		<h1>Board Detail</h1>
		<br>
		<table border="1" style="width: 700px;">
			<colgroup>
				<col width="20%" >
				<col width="80%">
			</colgroup>
			<tr>
				<th>Board Number</th>
				<td>${boardAdvanceDto.num}</td>
			</tr>
			<tr>
				<th>ReadCount</th>
				<td>${boardAdvanceDto.readCount}</td>
			</tr>
			<tr>
				<th>Writer</th>
				<td>${boardAdvanceDto.writer}</td>
			</tr>
			<tr>
				<th>RegDate</th>
				<td><fmt:formatDate value="${boardAdvanceDto.regDate}" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<th>E-mail</th>
				<td>${boardAdvanceDto.email}</td>
			</tr>
			<tr>
				<th>Subject</th>
				<td>${boardAdvanceDto.subject}</td>
			</tr>
			<tr>
				<th>Content</th>
				<td>${boardAdvanceDto.content}</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="Reply" onclick="location.href='${contextPath }/boardAdvance/boardReplyWrite?num=${boardAdvanceDto.num}'"> 
					<input type="button" value="Update" onclick="location.href='${contextPath }/boardAdvance/boardUpdate?num=${boardAdvanceDto.num}'">
					<input type="button" value="Delete" onclick="location.href='${contextPath }/boardAdvance/boardDelete?num=${boardAdvanceDto.num}'">
					<input type="button" value="Main" onclick="location.href='${contextPath }/boardAdvance/boardList'">
				</td>
			</tr>
		</table>
	</div>
	
</body>
</html>