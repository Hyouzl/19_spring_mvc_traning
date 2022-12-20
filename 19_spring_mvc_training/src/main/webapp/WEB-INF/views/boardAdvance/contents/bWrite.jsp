<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bWrite</title>
</head>
<body>

	<div align="center" style="padding-top: 100px">
		<form action="${contextPath }/boardAdvance/boardWrite" method="post">
			<h2>Board Write</h2>
			<br>
			<table border="1" style="width: 700px;">
				<colgroup>
					<col width="20%" >
					<col width="80%">
				</colgroup>
				<tr>
					<th>Writer</th>
					<td><input type="text" name="writer" size="50" /></td>
				</tr>
				<tr>
					<th>Subject</th>
					<td><input type="text" name="subject" size="50" /></td>
				</tr>
				<tr>
					<th>E-mail</th>
					<td><input type="email" name="email" size="50" /></td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="password" name="password" size="50" /></td>
				</tr>
				<tr>
					<th>Content</th>
					<td><textarea rows="10" cols="50" name="content"></textarea></td>
				</tr>
				<tr align="center">
					<td colspan="2" align="center">
						<input type="submit" value="Write" />
						<input type="reset"  value="Reset" />
						<input type="button" value="Main" onclick="location.href='${contextPath }/boardAdvance/boardList'">
					</td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>