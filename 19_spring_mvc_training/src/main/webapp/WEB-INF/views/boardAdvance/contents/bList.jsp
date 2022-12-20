<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>BoardList</title>
<style>
	ul {
	    list-style:none;
	    text-align: center;
	}
	
	li {
	    display: inline-block;
	}
</style>
<script>

	$().ready(function(){
		
		$("#onePageViewCount").change(function(){
			
			var url = "${contextPath}/boardAdvance/boardList?";
				url	+= "onePageViewCount=" + $("#onePageViewCount").val();
				url	+= "&searchKeyword=" + $("#searchKeyword").val();
				url	+= "&searchWord=" + $("#searchWord").val();
			
			location.href = url;
			
		}) ;
		
		
		$("#getSearchBoard").click(function(){
			
			var url = "${contextPath}/boardAdvance/boardList?";
				url += "onePageViewCount=" + $("#onePageViewCount").val();
				url += "&searchKeyword=" + $("#searchKeyword").val();
				url += "&searchWord=" + $("#searchWord").val();
			
			location.href=url;
				
		});
		
	});
	
</script>
</head>
<body>
	<div align="right">
	   <a href="${contextPath }/boardAdvance/makeDummyData">테스트용 더미파일 만들기(페이징 및 검색 테스트용도로만 사용)</a><br>
	</div>
    <div align="center" style="padding-top: 100px">
		<h1>Board List</h1>
		<br>
		Total <span style="color: red; font-weight: bold">${totalBoardCount}</span> entries &emsp; &emsp; &emsp;
		Show <select id="onePageViewCount">
                <option <c:if test="${onePageViewCount eq 5}"> selected</c:if> value="5">5</option>
				<option <c:if test="${onePageViewCount eq 7}"> selected</c:if> value="7">7</option>
				<option <c:if test="${onePageViewCount eq 10}"> selected</c:if> value="10">10</option>
           	</select> entries
			<table border="1" style="width: 700px;">
				 <colgroup>
					 <col width="5%">
					 <col width="50%">
					 <col width="20%">
					 <col width="20%">
					 <col width="5%">
				 </colgroup>
                 <tr>
                     <th>Order</th>
                     <th>Subject</th>
                     <th>Writer</th>
                     <th>RegDate</th>
                     <th>View</th>
                 </tr>
                 <c:forEach var="boardAdvanceDto" items="${boardList}">	                                        	
					<tr>
						<td>${boardAdvanceDto.num}</td>
						<td>
							 <c:if test="${boardAdvanceDto.reStep > 1}">
							 	<c:forEach var="j" begin="0" end="${(boardAdvanceDto.reLevel-1 ) * 5 }">
							 		&nbsp;
							 	</c:forEach>
							 	»
							 </c:if>
							<a href="${contextPath }/boardAdvance/boardDetail?num=${boardAdvanceDto.num}"> ${boardAdvanceDto.subject}</a>
						</td>
						<td>${boardAdvanceDto.writer}</td>
						<td><fmt:formatDate value="${boardAdvanceDto.regDate}" pattern="yyyy-MM-dd"/></td>
						<td>${boardAdvanceDto.readCount}</td>
					</tr>
				 </c:forEach>
				 <tr>
					<td colspan="5" align="center">	
						<select id="searchKeyword" style="width: 150px;">
							<option <c:if test="${searchKeyword eq 'total'}"> selected</c:if> value="total">total</option>
							<option <c:if test="${searchKeyword eq 'writer'}"> selected</c:if> value="writer">writer</option>
							<option <c:if test="${searchKeyword eq 'subject'}"> selected</c:if> value="subject">subject</option>
						</select>
                        <input type="text" style="width: 300px;" id="searchWord" name="searchWord" value="${searchWord}" >
						<input type="button" value="Search" id="getSearchBoard">
					</td>
				 </tr>
			</table>
			<c:if test="${totalBoardCount gt 0 }">
	       		<ul>
	       			<c:if test="${startPage gt 10 }">
	         			<li>
	         				<a href="${contextPath }/boardAdvance/boardList?currentPageNumber=${startPage - 10}&onePageViewCount=${onePageViewCount}&searchKeyword=${searchKeyword}&searchWord=${searchWord}" >Previous</a>
	         			</li>
	       			</c:if>
	        			<c:forEach var="i" begin="${startPage}" end="${endPage }" >
	          			<li>
	          				<a href="${contextPath }/boardAdvance/boardList?currentPageNumber=${i}&onePageViewCount=${onePageViewCount}&searchKeyword=${searchKeyword}&searchWord=${searchWord}">${i} </a>
	          			</li>
	          		</c:forEach>
	       			<c:if test="${endPage le totalBoardCount && endPage ge 10}"> 
	         			<li>
	         				<a href="${contextPath }/boardAdvance/boardList?currentPageNumber=${startPage + 10}&onePageViewCount=${onePageViewCount}&searchKeyword=${searchKeyword}&searchWord=${searchWord}" >Next</a>
	         			</li>
	       			</c:if>
	       		</ul>
	       	</c:if>
	</div>
	
</body>
</html>