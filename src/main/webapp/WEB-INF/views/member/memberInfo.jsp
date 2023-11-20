<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/member/memberInfo.jsp</h1>

	<h2>회원정보 조회</h2>

	${vo }<hr>
	
	아이디 : ${vo.userid } <hr>
	비밀번호 : ${vo.userpw }<hr>
	이름 : ${vo.username }<hr>
	이메일 : ${vo.useremail }<hr>
	회원가입 일 : ${vo.regdate } <hr>
	
	<a href = "/member/main">메인페이지로...</a>

</body>
</html>