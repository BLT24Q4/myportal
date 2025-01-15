<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
	prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>My Homepage</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="/myportal/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	
	<table border="1" width="640">
		<tr>
			<td colspan="2"><h3>게시판</h3></td>
		</tr>
		<tr>
			<th colspan="2">글보기</th>
		</tr>
		<tr>
			<td>제목</td>
			<td>${vo.title }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<div>
					${vo.content }
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="<c:url value="/board" />">글목록</a>
				<a href="">글수정</a>
			</td>
		</tr>
	</table>

</body>
</html>
