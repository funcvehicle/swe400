package gateway;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class PersonGateway extends Gateway
{
	private Connection	connection;
	private String		queryStringByID			= "SELECT * FROM people WHERE id=";
	private String		queryStringByUserName	= "SELECT * FROM people WHERE userName=";
	
	private String updateStatement = "UPDATE people SET";
	private String insertStatement = "INSERT INTO people (id,userName,displayName,password) VALUES (";
	private String deleteStatement = "DELETE FROM people WHERE id=";

	/**
	 * Find a person by their unique ID
	 * 
	 * @param id
	 * @return ResultSet containing one row from the people table
	 */
	public ResultSet find(long id)
	{
		establishConnection();
		connection = getConnection();
		Statement find;
		
		try
		{
			find = (Statement) connection.createStatement();
			ResultSet results = find.executeQuery(queryStringByID + id);
			return results;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return new NullSet();

	}

	/**
	 * Find a user by their unique username
	 * 
	 * @param userName
	 * @return ResultSet containing one row from the people table
	 */
	public ResultSet find(String userName)
	{
		establishConnection();
		connection = getConnection();
		Statement find;
		ResultSet results;
		
		try
		{
			find = (Statement) connection.createStatement();
			results = find.executeQuery(queryStringByUserName + "'" + userName + "'");
		}
		catch (SQLException e)
		{
			results = new NullSet();
			e.printStackTrace();
		}

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
			updateStatement.execute(updateStatement + "userName=" + userName + ",password=" + password + ",displayName="
					+ displayName + " WHERE id=" + id);
		}
		catch (SQLException e)
		{
			result = SQLEnum.FAILED_SQL_ERROR;
		}

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

		return result;
	}
}
