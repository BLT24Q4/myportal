<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
	prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/tags" 
	prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" 
	prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Homepage</title>
<!-- TODO: 현재 페이지에 적절한 CSS를 임포트하십시오. -->
<link type="text/css" 
	rel="stylesheet" 
	href="<%= request.getContextPath() %>/css/users.css"/>
<script src="<c:url value="/javascript/users.js" />"></script>
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
      
      
	<h1>회원 가입</h1>
	
	<form:form
		modelAttribute="userVo"
		id="join-form"
		name="registerForm" 
		action="${pageContext.servletContext.contextPath }/users/join"
		method="POST">
		<input type="hidden" name="a" value="join">
		
		<label for="name">이름</label>
		<!-- input name="name" type="text" placeholder="이름을 입력하십시오" -->
		<form:input path="name" />
		<br>
		<form:errors path="name" cssClass="error" />
		<%--spring:hasBindErrors name="userVo">
			<c:if test="${errors.hasFieldErrors('name') }">
				<strong style="color:red">
				<spring:message code="${errors.getFieldError('name').codes[0] }"
					text="${errors.getFieldError('name').defaultMessage }" />
				</strong>
				<br />
			</c:if>
		</spring:hasBindErrors --%>
		
		<label for="password">비밀번호</label>
		<form:input type="password" path="password" />
		<form:errors path="password" cssClass="error" />
		<%--input name="password" type="password" placeholder="비밀번호를 입력하십시오"><br>
		<spring:hasBindErrors name="userVo">
			<c:if test="${errors.hasFieldErrors('password') }">
				<strong style="color:red">
				<spring:message code="${errors.getFieldError('password').codes[0] }"
					text="${errors.getFieldError('password').defaultMessage }" />
				</strong>
				<br />
			</c:if>
		</spring:hasBindErrors --%>
		<br />
		
		<label for="email">이메일</label>
		<%--input type="text" name="email" placeholder="이메일을 입력하십시오." --%>
		<form:input path="email" />
		<br>
	
		<input id="check-email"
			type="button"
			data-target="<c:url value="/users/checkEmail" />"
			value="id 중복 체크">
		<%--spring:hasBindErrors name="userVo">
			<c:if test="${errors.hasFieldErrors('email') }">
				<strong style="color:red">
				<spring:message code="${errors.getFieldError('email').codes[0] }"
					text="${errors.getFieldError('email').defaultMessage }" />
				</strong>
				<br />
			</c:if>
		</spring:hasBindErrors --%>
		<form:errors path="email" cssClass="error" />
		<input type="hidden" name="emailCheck" value="n">
		<br />
		<label for="gender">성별</label>
		<!-- input type="radio" name="gender" value="M" checked>남성</radio -->
		<!-- input type="radio" name="gender" value="F">여성</radio -->
		<form:radiobutton path="gender" value="M" label="남성" checked="checked"/>
		<form:radiobutton path="gender" value="F" label="여성" />
		<br>
		<input type="submit" value="전송"> 
	
	</form:form>

	</div>
	</div>
	
	<c:import url="/WEB-INF/views/includes/footer.jsp" />
  </div>
</body>
</html>