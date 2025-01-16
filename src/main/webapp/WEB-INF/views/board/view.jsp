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
			<td colspan="2"><h3>게시판</h3></td>
		</tr>
		<tr>
			<th colspan="2">글보기</th>
		</tr>
		<tr>
			<th>작성자</th><td>${vo.userName }</td>
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
				<c:if test="${not empty authUser }">
					<c:if test="${authUser.no == vo.userNo }">
				<a href="<c:url value="/board/${vo.no }/modify" />">글수정</a>
					</c:if>
				</c:if>
			</td>
		</tr>
	</table>

	
	</div>
	</div>
	
	<c:import url="/WEB-INF/views/includes/footer.jsp" />
  </div>
</body>
</html>