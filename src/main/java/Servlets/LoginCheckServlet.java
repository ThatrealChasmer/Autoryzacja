package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Default.ConnectionHolder;

@WebServlet("/logincheck")
public class LoginCheckServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Connection c;
	
	@Override
    public void init() throws ServletException {
    	c= ConnectionHolder.getConnection();
    }
	
	public void doPost(HttpServletRequest rq, HttpServletResponse rp) throws IOException {
		rp.setContentType("text/html");
		
		PrintWriter out = rp.getWriter();
		
		String name = rq.getParameter("login");
		String pass = rq.getParameter("password");

		try {
			PreparedStatement st = c.prepareStatement("Select name,password from users where name = ?;");
			st.clearParameters();
			st.setString(1, name);
			ResultSet userData = st.executeQuery();
			userData.next();
			if (userData.getString("name").equals(name) && userData.getString("password").equals(pass)) {
				HttpSession session = rq.getSession();
				session.setAttribute("name",name);
				session.setMaxInactiveInterval(30*60);
				
				Cookie userName=new Cookie("name", name);
				userName.setMaxAge(30*60);
				rp.sendRedirect("profil.jsp");
				
			} else {
				out.append("Login lub has³o nieprawid³owe");
				out.append("<form action=\"login.jsp\"><input type=\"submit\" value=\"Powrót\"></form>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			out.append("SQL EXCEPTION! Nie zalogowano.");
		}
		
	}

}
