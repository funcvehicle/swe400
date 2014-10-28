package gateway;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import com.mysql.jdbc.Statement;
import com.sun.rowset.CachedRowSetImpl;

public class PersonGateway extends Gateway
{
	private Connection	connection;
	private String		queryStringByID			= "SELECT * FROM people WHERE id=";
	private String		queryStringByUserName	= "SELECT * FROM people WHERE userName=";

	private String insertStatement = "INSERT INTO people (id,userName,displayName,password) VALUES (";
	private String deleteStatement = "DELETE FROM people WHERE id=";

	/**
	 * Find a person by their unique ID
	 * 
	 * @param id
	 * @return ResultSet containing one row from the people table
	 */
	public CachedRowSet find(long id)
	{
		establishConnection();
		connection = getConnection();
		Statement find;
		CachedRowSet results;
		
		try
		{
			results = new CachedRowSetImpl();
			find = (Statement) connection.createStatement();
			ResultSet data = find.executeQuery(queryStringByID + id);
			results.populate(data);
		}
		catch (SQLException e)
		{
			System.err.println(PersonGateway.class.getName() + " SQL ERROR: " + e.getMessage());
			results = null;
		}

		closeConnection();
		return results;

	}

	/**
	 * Find a user by their unique username
	 * 
	 * @param userName
	 * @return ResultSet containing one row from the people table
	 */
	public CachedRowSet find(String userName)
	{
		establishConnection();
		connection = getConnection();
		Statement find;
		CachedRowSet results;
		
		try
		{
			results = new CachedRowSetImpl();
			find = (Statement) connection.createStatement();
			ResultSet data = find.executeQuery(queryStringByUserName + "'" + userName + "'");
			results.populate(data);
		}
		catch (SQLException e)
		{
			results = null;
			System.err.println(PersonGateway.class.getName() + " SQL ERROR: " + e.getMessage());
		}

		closeConnection();
		return results;
	}

	/**
	 * Inserts one user into the people table
	 * 
	 * @param id
	 * @param userName
	 * @param displayName
	 * @return
	 */
	public SQLEnum insert(long id, String userName, String displayName, String password)
	{
		establishConnection();
		connection = getConnection();
		String[] params = new String[3];
		params[0] = "people";
		params[1] = userName;
		SQLEnum result = SQLEnum.SUCCESS;
		
		if (recordExists(params) == SQLEnum.DOES_NOT_EXIST)
		{
			try
			{
				Statement insert = (Statement) connection.createStatement();
				insert.executeUpdate(insertStatement + id + ",'" + userName + "','" + displayName + "','" + password + "')");
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				result = SQLEnum.FAILED_SQL_ERROR;
			}
		}
	
		return result;
	}

	public SQLEnum update(long id, String userName, String password, String displayName)
	{
		establishConnection();
		connection = getConnection();
		SQLEnum result = SQLEnum.SUCCESS;
		
		try
		{
			Statement updateStatement = (Statement) connection.createStatement();
			updateStatement.execute("UPDATE people SET userName='" + userName + "', password='" + password + "', displayName='"
					+ displayName + "' WHERE id=" + id);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			result = SQLEnum.FAILED_SQL_ERROR;
		}

		closeConnection();
		return result;
	}

	/**
	 * Delete a person by its Id
	 * @param id
	 * @return
	 */
	public SQLEnum delete(long id)
	{
		establishConnection();
		connection = getConnection();
		SQLEnum result = SQLEnum.SUCCESS;
		
		try
		{
			Statement delete = (Statement) connection.createStatement();
			if (!delete.execute(deleteStatement + id))
			{
				result = SQLEnum.DOES_NOT_EXIST;
			}
		}
		catch (SQLException e)
		{
			result = SQLEnum.FAILED_SQL_ERROR;
		}

		closeConnection();
		return result;
	}
}
