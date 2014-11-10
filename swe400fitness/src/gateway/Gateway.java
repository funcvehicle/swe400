package gateway;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * 
 * @author mb8542
 * @author Connor Fox
 *
 */
public abstract class Gateway
{
	private ConnectionUtil conn = ConnectionUtil.getCurrent();
	
	/**
	 * I am a Stegosaurus!!
	 * Check to see if a record exists in the any table
	 * @param params an array of strings, the first element params[0] is the table and params[1] is the primary key
	 * @return SQLEnum denoting existence or non-existence
	 */
	protected SQLEnum recordExists(String[] params)
	{
		Connection connection = conn.getConnection();
		String query = getQueryString(params);
		try 
		{
			Statement findMe = (Statement) connection.createStatement();
			
			ResultSet set = findMe.executeQuery(query);
			if (params[0].equals("friends"))
			{
				return checkFriends(set, params);
			}
			else if (params[0].equals("people"))
			{
				return checkPeople(set, params);
			}
			else if (params[0].equals("pendingFriends"))
			{
				return checkPendingFriends(set, params);
			}
			else
			{
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
	 * Check the people table for a record
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
}
