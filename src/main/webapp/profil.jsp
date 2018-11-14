<%@page import="Default.ConnectionHolder"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
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
		String name = (String) session.getAttribute("name");
		String email = null;
		String password = null;
		String status = null;

		Connection c = ConnectionHolder.getConnection();
		PreparedStatement st = c.prepareStatement("Select * from users where name = ?;");
		st.clearParameters();
		st.setString(1, name);

		ResultSet info = st.executeQuery();
		info.next();

		email = info.getString("email");
		password = info.getString("password");
		status = info.getString("status");
	%>
	<table>
		<tr>
			<th>Nick</th>
			<th>Email</th>
			<th>Hasło</th>
			<th>Status</th>
		</tr>
		<tr>
			<td><%=name%></td>
			<td><%=email%></td>
			<td><%=password%></td>
			<td><%=status%></td>
		</tr>
	</table>
</body>
</html>