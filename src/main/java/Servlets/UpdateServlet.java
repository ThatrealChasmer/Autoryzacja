package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Default.*;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest rq, HttpServletResponse rp)
			throws ServletException, IOException {
		PrintWriter out = rp.getWriter();
		try {
			PreparedStatement st = ConnectionHolder.getConnection()
					.prepareStatement("select status from users where name = ?");
			st.clearParameters();
			st.setString(1, rq.getParameter("nick"));
			ResultSet status = st.executeQuery();
			status.next();
			String newStatus = "";
			if (status.getString("status").equals(UserType.STANDARD_USER)) {
				newStatus = UserType.PREMIUM_USER;
			} else if (status.getString("status").equals(UserType.PREMIUM_USER)) {
				newStatus = UserType.STANDARD_USER;
			}

			if (newStatus != "") {
				out.append("setting status to " + newStatus);
				PreparedStatement setter = ConnectionHolder.getConnection()
						.prepareStatement("update users set status = ? where name = ?");
				setter.clearParameters();
				setter.setString(1, newStatus);
				setter.setString(2, rq.getParameter("nick"));
				setter.executeUpdate();
			} else {
				out.append("status remains as " + status.getString(1));
			}
		} catch (SQLException e) {
			out.append("SQLException! Status nie zosta³ zmieniony");
			e.printStackTrace();
		}
	}

}
