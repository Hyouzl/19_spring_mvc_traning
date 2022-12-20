<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bReply</title>
</head>
<body>

	<div align="center" style="padding-top: 100px">
		<form action="${contextPath }/boardAdvance/boardReplyWrite" method="post">
			<h2>Write Reply</h2>
			<br>
			<table border="1" style="width: 700px;">
				<colgroup>
					<col width="20%">
					<col width="80%">
				</colgroup>
				<tr>
					<th>Base Board</th>
					<td>${boardAdvanceDto.subject}</td>
				</tr>
				<tr>
					<th>Writer</th>
					<td><input type="text" name="writer" /></td>
				</tr>
				<tr>
					<th>Subject</th>
					<td><input type="text" name="subject" /></td>
				</tr>
				<tr>
					<th>E-mail</th>
					<td><input type="email" name="email" /></td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<th>Content</th>
					<td><textarea rows="10" cols="50" name="content"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="hidden" name="ref" value="${boardAdvanceDto.ref}">
						<input type="hidden" name="reStep" value="${boardAdvanceDto.reStep}">
						<input type="hidden" name="reLevel" value="${boardAdvanceDto.reLevel}">
						<input type="submit" value="Reply" />
						<input type="reset"  value="Reset" />
						<input type="button" value="Main" onclick="location.href='${contextPath }/boardAdvance/boardList'" >
					</td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>