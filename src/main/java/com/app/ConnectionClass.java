package main.java.com.app;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.rowset.CachedRowSetImpl;

/**
 * 
 * This class has functions to connect and retrieve data from database
 *
 */
public class ConnectionClass {
	private static final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
	private static Connection connection = null;
	private static String connStr = "jdbc:mysql://localhost/JinaStudio";
	private static String USERNAME = "cs151";
	private static String PASSWORD = "cs151";
	
	/**
	 * This method is used to connect data to mysql DB
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void dbConnect() throws SQLException, ClassNotFoundException {
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
	
	/**
	 * This method is used to disconnect with DB
	 * @throws SQLException
	 */
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
	
	/**
	 * This method is for insert/delete/update operation
	 * 
	 * @param sqlStmt
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
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
	
	/**
	 * This method is for retrieve the records from the DB
	 * @param sqlQuery
	 * @return ResultSet This returns a set of data from DB
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
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
