<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="/login.jsp">Logowanie</a><br>
<a href="/rejestracja.jsp">Rejestracja</a><br>
<a href="/profil.jsp">Profil</a><br>
<a href="/lista.jsp">Lista użytkowników</a><br>
<a href="/podstronapremium.jsp">Podstrona premium</a><br>
<a href="/nadawanie.jsp">Nadawanie premium (tylko admin)</a>

<form action="/logincheck" method="post">
	Login<input type="text" name="login" required/><br>
	Hasło<input type="password" name="password" required/><br>
	<input type="submit" name="submit" value="Login"/>
</form>
</body>
</html>