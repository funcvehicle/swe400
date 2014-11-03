package gateway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import com.mysql.jdbc.*;
import com.sun.rowset.CachedRowSetImpl;

/**
 * 
 * @author mb8542
 * 
 */
public class FriendGateway extends Gateway
{
	private Connection	connection;
	private String		selectStatement	= "SELECT * FROM friends WHERE personId=";
	private String		insertStatement	= "INSERT INTO friends VALUES (";
	private String		deleteStatement	= "DELETE FROM friends WHERE id=";

	/**
	 * Create a friendship in the DB
	 * 
	 * @param personID
	 * @param friendID
	 * @return SQLEnum
	 */
	public SQLEnum create(long relationID, long personID, long friendID)
	{
		establishConnection();
		connection = getConnection();
		String[] params = new String[3];
		params[0] = "friends";
		params[1] = (new Long(personID)).toString();
		params[2] = (new Long(friendID)).toString();
		SQLEnum result = SQLEnum.SUCCESS;

		if (recordExists(params) == SQLEnum.DOES_NOT_EXIST)
		{
			try
			{
				Statement insert = (Statement) connection.createStatement();
				insert.executeUpdate(insertStatement + personID + "," + friendID + "," + relationID + ");");
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				result = SQLEnum.FAILED_SQL_ERROR;
			}
		}
		else
		{
			result = SQLEnum.EXISTS;
		}

		closeConnection();
		return result;
	}

	/**
	 * Find all friendships for a userId
	 * 
	 * @param userId
	 * @return RecordSet of all friendships for a a given userId
	 */
	public CachedRowSet findAllFromUser(long userId)
	{
		establishConnection();
		connection = getConnection();
		ResultSet data;
		CachedRowSet results;

		try
		{
			results = new CachedRowSetImpl();
			Statement select = (Statement) connection.createStatement();
			data = select.executeQuery(selectStatement + userId + " OR friendId=" + userId);
			results.populate(data);
		}
		
		catch (SQLException e)
		{
			results = null;
			System.err.println("SQL ERROR: " + e.getMessage());
		}
		
		closeConnection();
		return results;
	}
	
	/**
	 * Find a single friend record
	 * TODO
	 * @param userId
	 * @return RecordSet of all friendships for a a given userId
	 */
	public CachedRowSet findFriend(long id)
	{
		establishConnection();
		connection = getConnection();
		ResultSet data;
		CachedRowSet results;

		try
		{
			results = new CachedRowSetImpl();
			Statement select = (Statement) connection.createStatement();
			data = select.executeQuery(selectStatement + id);
			results.populate(data);
		}
		
		catch (SQLException e)
		{
			results = null;
			System.err.println("SQL ERROR: " + e.getMessage());
		}
		
		closeConnection();
		return results;
	}
	
	/**
	 * Delete a single friendship relation based on its id.
	 * @param relationId
	 * @return
	 */
	public SQLEnum delete(long relationId)
	{
		establishConnection();
		Connection connection = getConnection();
		SQLEnum result = SQLEnum.SUCCESS;

		try
		{
			Statement delete = (Statement) connection.createStatement();
			delete.execute(deleteStatement + relationId);
		}
		catch (SQLException e)
		{
			result = SQLEnum.FAILED_SQL_ERROR;
		}

		closeConnection();
		return result;
	}
}
