package app;
//not implement yet
//this class is used for mysql store and retrieve
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
	public Connection connection;
	public Connection getConnection() {
		String dbName="Points_List";
		String userName ="root";
		double points = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName+userName+points);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

}
