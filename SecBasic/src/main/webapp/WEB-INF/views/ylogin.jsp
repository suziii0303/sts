<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>이것은 YOU Login 페이지</h1>
	<form action="/sec/angel/login" method="post">
		<sec:csrfInput />
		ID<input type="text" name="username" value=""><br>
		PASSWORD<input type="password" name="password" value=""><br>
		<input type="submit" value="Login">
	</form>

</body>
</html>