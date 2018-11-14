package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest rq, HttpServletResponse rp)
			throws ServletException, IOException {
		rp.setContentType("text/html");
		PrintWriter out = rp.getWriter();
		
		Cookie[] cookies = rq.getCookies();

		if (cookies != null) {
			Cookie c=new Cookie("name", "");
			c.setMaxAge(0);
			rp.addCookie(c);
			c=new Cookie("JSESSIONID", "");
			c.setMaxAge(0);
			rp.addCookie(c);
			
		}
		HttpSession session = rq.getSession(false);
		if (session != null) {
			session.invalidate();
			rp.getWriter().append("Wylogowano");
			out.append("<a href=\"index.jsp\">Start<a>");
		} else {
			rp.getWriter().append("Sesja nie istnieje");
			out.append("<a href=\\\"index.jsp\\\">Start<a>");
		}
	}

}
