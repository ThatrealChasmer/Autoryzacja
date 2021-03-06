package Filters;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Default.*;

@WebFilter("/premiumfilter")
public class PremiumFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		
		if (session!=null && session.getAttribute("name")!=null) {
			try {
				boolean isPremium=false;
				PreparedStatement st=ConnectionHolder.getConnection().prepareStatement("select name from users where status = ? or status = ?");
				st.clearParameters();
				st.setString(1, UserType.PREMIUM_USER);
				st.setString(2, UserType.ADMIN);
				ResultSet name=st.executeQuery();
				while (name.next()) {
					if (name.getString(1).equals(session.getAttribute("name"))) {
						isPremium=true;
						break;
					}
				}
				if (!isPremium) {
					res.sendRedirect("index.jsp");
				}
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}else {
			res.sendRedirect("index.jsp");
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
