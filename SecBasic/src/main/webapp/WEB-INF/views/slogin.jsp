<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘 더 멋진 석일</title>
</head>
<body>
	<h1>구두가 포인트</h1>
	<form action="/sec/slogin" method="post">
		<sec:csrfInput /> <!-- 요건 서버가 해석하는 서버 태그 곧 jstl은 서버 태그 -->
		아이디 <input type="text" name="username" value=""><br>
		암호 <input type="text" name="password" value=""><br>
		<button type="submit">로그인</button>
	</form>
	
	<h1>manual login 어쩌다 필요</h1> 
	<form action="/sec/mylogin" method="post">
		<sec:csrfInput /> <!-- 요건 서버가 해석하는 서버 태그 곧 jstl은 서버 태그 -->
		아이디2 <input type="text" name="myname" value=""><br>
		암호2 <input type="text" name="mypass" value=""><br>
		<button type="submit">로그인2</button>
	</form>
</body>
</html>