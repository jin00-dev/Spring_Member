<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/member/memberLogin.jsp</h1>
	
	<h2>로그인 페이지</h2>
	
	<fieldset>
		<form action="" method="post">
		아이디 : <input type="text" name="userid"><br>
		비밀번호 : <input type="password" name="userpw"><hr>
		<input type="submit" value="로그인">
		<input type="button" value="회원가입"
				onclick="location.href='/member/insert';">
		
		</form>
	
	</fieldset>

</body>
</html>