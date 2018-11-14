package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Default.ConnectionHolder;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

public final class profil_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<a href=\"/login.jsp\">Logowanie</a><br>\r\n");
      out.write("<a href=\"/rejestracja.jsp\">Rejestracja</a><br>\r\n");
      out.write("<a href=\"/profil.jsp\">Profil</a><br>\r\n");
      out.write("<a href=\"/lista.jsp\">Lista użytkowników</a><br>\r\n");
      out.write("<a href=\"/podstronapremium.jsp\">Podstrona premium</a><br>\r\n");
      out.write("<a href=\"/nadawanie.jsp\">Nadawanie premium (tylko admin)</a>\r\n");
      out.write("\r\n");

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
	
      out.write("\r\n");
      out.write("\t<table>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>Nick</th>\r\n");
      out.write("\t\t\t<th>Email</th>\r\n");
      out.write("\t\t\t<th>Hasło</th>\r\n");
      out.write("\t\t\t<th>Status</th>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td>");
      out.print(name);
      out.write("</td>\r\n");
      out.write("\t\t\t<td>");
      out.print(email);
      out.write("</td>\r\n");
      out.write("\t\t\t<td>");
      out.print(password);
      out.write("</td>\r\n");
      out.write("\t\t\t<td>");
      out.print(status);
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
