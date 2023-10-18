<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Model 데이타 확인할 땐 항상 해당페이지에 통째로 찍어서 디버깅</h1>
	<p> ${minuList} </p>
	<h1>나 현학적인 현학왕</h1>
	<h1> ${minuList.get(1)}</h1>
<script>
	let merong = "${minuList.get(1)}";
	alert(merong);
</script>
</body>
</html>