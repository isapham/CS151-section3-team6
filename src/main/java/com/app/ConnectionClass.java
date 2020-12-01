package main.java.com.app;
//this class is used for mysql store and retrieve
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.rowset.CachedRowSetImpl;

public class ConnectionClass {
	private static final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
	private static Connection connection = null;
	private static String connStr = "jdbc:mysql://localhost/JinaStudio";
	private static String USERNAME = "cs151";
	private static String PASSWORD = "cs151";
	
	public static void dbConnect() throws SQLException, ClassNotFoundException {
		//String dbName="Points_List";
		//String userName ="root";
		//String gamePoint = "";
		//String totalScore = "";
		try {
			Class.forName(JDBC_Driver);			
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			throw e;
		}
		
		//System.out.println("JDBC driver has been registered!");
		
		try {
			
			connection = DriverManager.getConnection(connStr,USERNAME, PASSWORD);
		}
		catch(SQLException e) {
			System.out.println("Connection Failed! Check output console" + e);
			throw e;
		}
	}
	
	public static void dbDisconnect() throws SQLException{
		try {
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	//this is for insert/delete/update operation
	public static void dbExecuteQuery(String sqlStmt) throws SQLException, ClassNotFoundException{
		Statement stmt = null;
		try {
			dbConnect();
			stmt = connection.createStatement();
			stmt.executeUpdate(sqlStmt);
			
		}
		catch(SQLException e) {
			System.out.println("Problem occured at sbExecuteQuery operation "+e);
			throw e;
		}
		finally {
			if (stmt!=null) {
				stmt.close();
			}
			dbDisconnect();
		}
	}
	
	//this is for retrieve the records from the db
	public static ResultSet dbExecute(String sqlQuery) throws SQLException, ClassNotFoundException{
		Statement stmt = null;
		ResultSet rs = null;
		CachedRowSetImpl crs = null;
		
		try {
			dbConnect();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			crs = new CachedRowSetImpl();
			crs.populate(rs);
		}
		catch(SQLException e) {
			System.out.println("Error occured in dbExecute operation "+e);
			throw e;
		}
		finally {
			if (rs!=null) {
				rs.close();
			
			}
			if(stmt!=null) {
				stmt.close();
			}
			dbDisconnect();
		}
		return crs;
	}

}
