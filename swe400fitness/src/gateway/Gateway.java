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
	private String url = "jdbc:mysql://lsagroup4.cbzhjl6tpflt.us-east-1.rds.amazonaws.com";
	private String userName = "cf0320";
	private String passWord = "solidsnake456";
	private Connection connection;

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
	protected void closeConnection()
	{
		try {
			this.connection.close();
		} catch (SQLException e) {
			System.err.println("Could not close connection: " + e.getMessage());
		}
	}
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
	private String getQueryString(String[] params)
	{
		return "SELECT * FROM " + params[0];
	}
	public Connection getConnection() {
		return this.connection;
	}


}
