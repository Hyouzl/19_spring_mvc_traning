<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bDelete</title>
</head>
<body>

	<div align="center" style="padding-top: 100px">
		<form action="${contextPath }/boardAdvance/boardDelete" method="post">
			<h1>Delete Board</h1>
			<br>
			<table border="1" style="width:700px;">
				<tr>
					<th>Writer</th>
					<td>${boardAdvanceDto.writer}</td>
				</tr>
				<tr class="table-default">
					<th>RegDate</th>
					<td><fmt:formatDate value="${boardAdvanceDto.regDate}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th>Subject</th>
					<td>${boardAdvanceDto.subject}</td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="password" class="form-control" name="password" size="60"></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<input type="hidden" name="num" value="${boardAdvanceDto.num }">
						<input type="submit" value="delete">
						<input type="button" onclick="location.href='${contextPath }/boardAdvance/boardList'" value="Main">
					</td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>