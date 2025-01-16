<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
	prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>My Homepage</title>
<link type="text/css" 
	rel="stylesheet" 
	href="<%= request.getContextPath() %>/css/board.css"/>
</head>
</head>
<body>
  <div id="container">
  
    <c:import url="/WEB-INF/views/includes/header.jsp">
      <c:param name="param1" value="value1" />
      <c:param name="param1" value="value2" />
    </c:import>
	<c:import url="/WEB-INF/views/includes/navigation.jsp" />
	<div id="wrapper">
      <div id="content">
    
    
	<table border="1" width="640">
		<tr>
			<td colspan="6"><h3>게시판</h3></td>
		</tr>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>조회수</th>
			<th>작성일</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${list }" var="vo">
		<tr>
			<td>${vo.no }</td>
			<td><a href="<c:url value="/board/${vo.no }" />">${vo.title }</a></td>
			<td>${vo.userName }</td>
			<td>${vo.hit }</td>
			<td>${vo.regDate }</td>
			<td>
				<c:if test="${not empty authUser }">
					<c:if test="${authUser.no == vo.userNo }">
				<a href="<c:url value="/board/${vo.no }/delete" />">삭제</a>
					</c:if>
				</c:if>
			</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="6">
				<a href="<c:url value="/board/write" />">글쓰기</a>
			</td>
		</tr>
	</table>
	

    </div>
	</div>
	
	
	<c:import url="/WEB-INF/views/includes/footer.jsp" />
  </div>
  
</body>
</html>