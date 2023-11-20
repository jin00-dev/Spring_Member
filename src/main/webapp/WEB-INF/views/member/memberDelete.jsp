<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> /views/member/memberDelete.jsp</h1>
	
	<h2> 회원정보 삭제 페이지 </h2>
		
		<fieldset>
			<!-- action="" 인경우 자기자신의 경로 호출 -->
			<form action="" method="post">
				<input type="hidden" name="userid" value="${id }">
				비밀번호 : <input type="password" name="userpw"><br>
				<input type="submit" value=" 회원정보삭제">			
			</form>		
		</fieldset>

	

</body>
</html>