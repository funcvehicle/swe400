package gateway;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * 
 * @author mb8542, cf0320
 *
 */
public abstract class Gateway
{

	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://lsagroup4.cbzhjl6tpflt.us-east-1.rds.amazonaws.com:3306";
	private String userName = "lsagroup4";
	private String passWord = "lsagroup4";
	private Connection connection;
	
	/**
	 * Establish a connection to the DB
	 */
	protected void establishConnection(){
		try
		{
			Class.forName(driver);
		}
		catch(ClassNotFoundException e)
		{
			System.err.println("Driver Not Found!");
		}
		
		try 
		{
			this.connection = (Connection) DriverManager.getConnection(url,userName,passWord);
		} 
		catch (SQLException e) 
		{
			System.err.println("Failed to connect: " + e.getMessage());
		}
	}
	/**
	 * Close the current connection
	 */
	protected void closeConnection()
	{
		try {
			this.connection.close();
		} catch (SQLException e) {
			System.err.println("Could not close connection: " + e.getMessage());
		}
	}
	/**
	 * Check to see if a record exists in the any table
	 * @param params an array of strings, the first element params[0] is the table and params[1] is the primary key
	 * @return SQLEnum denoting existence or non-existence
	 */
	protected SQLEnum recordExists(String[] params)
	{
		
		String query = getQueryString(params);
		try 
		{
			Statement findMe = (Statement) connection.createStatement();
			
			ResultSet set = findMe.executeQuery(query);
			switch(params[0]){
			case "friends":
				return checkFriends(set, params);
			case "people":
				return checkPeople(set, params);
			case "pendingFriends":
				return checkPendingFriends(set, params);
			default:
				return SQLEnum.FAILED_BAD_PARAMS;
			}

		} 
		catch (SQLException e)
		{
			System.err.println("Error: " + e.getMessage());
			return SQLEnum.FAILED_SQL_ERROR;
		}
	}
	/**
	 * Check the pending friends table for a record
	 * @param set
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	private SQLEnum checkPendingFriends(ResultSet set, String[] params) throws SQLException {
		while(set.next())
		{
			if(set.getString("inquirer").equals(params[1]) && set.getString("recipient").equals(params[2]))
			{
				return SQLEnum.EXISTS;
			}
		}
		return SQLEnum.DOES_NOT_EXIST;
	}
	/**
	 * Check the people table for ar ecord
	 * @param set
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	private SQLEnum checkPeople(ResultSet set, String[] params) throws SQLException {
		while(set.next())
		{
			if(set.getString("userName").equals(params[1]))
			{
				return SQLEnum.EXISTS;
			}
		}
		return SQLEnum.DOES_NOT_EXIST;
		
	}
	/**
	 * Check the friends table for a record
	 * @param set
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	private SQLEnum checkFriends(ResultSet set, String[] params) throws SQLException {
		while(set.next())
		{
			if(set.getString("userName").equals(params[1]) && set.getString("friendName").equals(params[2]))
			{
				return SQLEnum.EXISTS;
			}
		}
		return SQLEnum.DOES_NOT_EXIST;
		
	}
	/**
	 * Get a basic query string
	 * @param params
	 * @return
	 */
	private String getQueryString(String[] params)
	{
		return "SELECT * FROM " + params[0];
	}
	/**
	 * Return the current connection
	 * @return
	 */
	public Connection getConnection() {
		return this.connection;
	}


}
