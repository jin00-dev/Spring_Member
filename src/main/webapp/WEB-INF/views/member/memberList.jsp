<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>/member/memberList.jsp</h1>
	
	<h2> 회원 정보 목록 조회</h2>
	
<%-- 	${memberList } --%>
	
	<table border="1">
		<tr>
			<td>아이디</td>
			<td>이름</td>
			<td>이메일</td>
			<td>회원가입일</td>
		</tr>
		<c:forEach var="vo" items="${memberList }">
		<tr>
			<td>${vo.userid }</td>
			<td>${vo.username }</td>
			<td>${vo.useremail }</td>
			<td>${vo.regdate }</td>
		</tr>
		</c:forEach>
	</table>
	<br>
	<a href = "/member/main">메인페이지로 이동</a>
	
</body>
</html>