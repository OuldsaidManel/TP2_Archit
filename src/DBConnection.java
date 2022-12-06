import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static DBConnection instance_unique = new DBConnection();
	static String BDD = "tp2";
	static String url = "jdbc:mysql://localhost:3306/" + BDD;
	static String user = "root";
	static String passwd = "";

	private DBConnection() {}

    public static DBConnection getDBConnection() {
		return instance_unique;	 //DBConnection -> Singleton 
	}

	String BDD = "tp2";
	String url = "jdbc:mysql://localhost:3306/" + BDD;
	String user = "root";
	String passwd = "";

    private static Connection conn;
    private static Connection conn = null;

    public Connection getConn() throws SQLException{
    public static Connection getConn() throws SQLException{

    	conn = DriverManager.getConnection(url, user,passwd);
    	if(conn == null) conn = DriverManager.getConnection(url, user,passwd);
		return conn;
	}

	
}