<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bUpdate</title>
</head>
<body>

	<div align="center" style="padding-top: 100px">
		<form action="${contextPath }/boardAdvance/boardUpdate" method="post">
			<h1>Update Board</h1>
			<br>
			<table border="1" style="width: 700px;">
				<tr>
					<th>Writer</th>
					<td>${boardAdvanceDto.writer}</td>
				</tr>
				<tr>
					<th>RegDate</th>
					<td><fmt:formatDate value="${boardAdvanceDto.regDate}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th>Subject</th>
					<td><input type="text" name="subject" value="${boardAdvanceDto.subject}" /></td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<th>Content</th>
					<td><textarea rows="10" cols="60" name="content">${boardAdvanceDto.content}</textarea></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<input type="hidden" name="num" value="${boardAdvanceDto.num}" /> 
						<input type="submit" value="Modify" />
						<input type="button" value="Main" onclick="location.href='${contextPath }/boardAdvance/boardList'"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>