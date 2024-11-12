package tugas1.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	static private String jdbcURL ;
	static private String jdbcUser;
	static private String jdbcPassword;
	
	public static Connection getConn() throws SQLException{
		Connection conn = null;
		// Load JDBC driver
        try {
			Class.forName("com.mysql.jdbc.Driver");
			// Establish connection
	        conn = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPassword);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return conn;

	}
	
	public static void closeConn(Connection conn) throws SQLException {
    	if(conn!=null) {    		
    			conn.close();    		
    	}
    }
	
	public static void setJdbcURL(String jdbcURL) {
		DBUtils.jdbcURL = jdbcURL;
	}
	
	public static void setJdbcUser(String jdbcUser) {
		DBUtils.jdbcUser = jdbcUser;
	}
	
	public static void setJdbcPassword(String jdbcPassword) {
		DBUtils.jdbcPassword = jdbcPassword;
	}
}
