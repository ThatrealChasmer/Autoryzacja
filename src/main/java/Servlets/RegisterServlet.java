package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Default.ConnectionHolder;
import Default.UserType;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Connection c;
	
	@Override
    public void init() throws ServletException {
    	c= ConnectionHolder.getConnection();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");		
		boolean zleDane=false;
		PrintWriter out=response.getWriter();
		String name=request.getParameter("login");
		String email=request.getParameter("email");
		String pass1=request.getParameter("pass1");
		String pass2=request.getParameter("pass2");
		
		if (!(pass1.compareTo(pass2)==0)) {
			out.append("Has³a siê nie zgadzaj¹! <br>");
			zleDane=true;
		}
		
		if (zleDane) {
			return;
		}
		
		boolean znalezione=false;
		try {
			PreparedStatement userNamesSelect=c.prepareStatement("select name from users where name = ?;");
			userNamesSelect.clearParameters();
			userNamesSelect.setString(1, name);
			ResultSet userNames=userNamesSelect.executeQuery();
			while (userNames.next() && ! znalezione) {
				znalezione=true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if(znalezione) {
			out.append("U¿ytkownik z takim samym nickiem ju¿ istnieje");
			return;
		}
		
		try {
			PreparedStatement sql=c.prepareStatement("insert into users values(?,?,?,?)");
			sql.clearParameters();
			sql.setString(1, name);
			sql.setString(2, email);
			sql.setString(3, pass1);
			sql.setString(4, UserType.STANDARD_USER);
			int alteredRows=sql.executeUpdate();
			if (alteredRows==1) {
				out.append("Zarejestrowano");
				out.append("<br><a href=\"login.jsp\">Logowanie<a>");
			}
			else {
				out.append("B³¹d rejestrowania. dodano "+alteredRows+"rekordów.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
