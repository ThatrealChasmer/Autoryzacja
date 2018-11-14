<%@page import="Default.ConnectionHolder"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
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

<%
		Statement s = ConnectionHolder.getConnection().createStatement();
		ResultSet userList = s.executeQuery("select name,email,status from users");
		String ret = "";
		while (userList.next()) {
			ret += "<tr><td>" + userList.getString(1) + "</td>" + "<td>" + userList.getString(2) + "</td>" + "<td>"
					+ userList.getString(3) + "</td></tr>";
		}
	%>

	<table>
		<tr>
			<th>Nick</th>
			<th>Email</th>
			<th>Status</th>
		</tr>
		<%=ret%>
	</table>
</body>
</html>