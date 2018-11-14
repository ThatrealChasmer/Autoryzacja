package Default;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHolder {
	
	private static Connection c=null; 
	
	public static Connection getConnection() { 
		if (c==null) { 
			try { 
				
				Class.forName("org.hsqldb.jdbc.JDBCDriver"); 
				
			} catch (ClassNotFoundException exception) {
				
				exception.printStackTrace(System.out);
				
				} try { 
					c=DriverManager.getConnection("jdbc:hsqldb:file:UsersDatabase","SA",""); 
					c.createStatement().executeUpdate("create table users(name varchar(16), email varchar(32), password varchar(16),status varchar(16))"); 
					} catch (SQLException exception) { 
				exception.printStackTrace(System.out); 
			} 
		} return c; 
	} private ConnectionHolder() {} 
}
