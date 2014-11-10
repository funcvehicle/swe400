package gateway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import com.mysql.jdbc.*;
import com.sun.rowset.CachedRowSetImpl;

/**
 * @author mb8542
 * @author Connor Fox
 * 
 * Handles SQL CRUD operations for the friend table
 */
public class FriendGateway extends Gateway
{
	private ConnectionUtil conn = ConnectionUtil.getCurrent();
	private String		selectStatement	= "SELECT * FROM friends WHERE personId=";
	private String		insertStatement	= "INSERT INTO friends (personId, friendId, id) VALUES (";
	private String		deleteStatement	= "DELETE FROM friends WHERE id=";

	/**
	 * Create a friendship in the DB
	 * @param personID the person's user id
	 * @param friendID the friend's user id
	 * @return SQLEnum the outcome of the method call
	 */
	public SQLEnum insert(long relationID, long personID, long friendID)
	{
		Connection connection = conn.getConnection();
		SQLEnum result = SQLEnum.SUCCESS;
		
		try
		{
			Statement insert = (Statement) connection.createStatement();
			insert.executeUpdate(insertStatement + personID + "," + friendID + "," + relationID + ");");
		}
		catch (SQLException e)
		{
			System.err.println(FriendGateway.class.getName() + " SQL ERROR: " + e.getMessage());
			result = SQLEnum.FAILED_SQL_ERROR;
		}

		return result;
	}

	/**
	 * Find all friendships for a userId
	 * @param userId the id of the user whose friends are being found.
	 * @return RecordSet of all friendships for a a given userId
	 */
	public CachedRowSet findAllForUser(long userId)
	{
		conn.open();
		Connection connection = conn.getConnection();
		ResultSet data;
		CachedRowSet results;

		try
		{
			results = new CachedRowSetImpl();
			Statement select = (Statement) connection.createStatement();
			data = select.executeQuery(selectStatement + userId + " OR friendId=" + userId);
			results.populate(data);
			data.close();
		}

		catch (SQLException e)
		{
			System.err.println(FriendGateway.class.getName() + " SQL ERROR: " + e.getMessage());
			results = null;
		}

		conn.close();
		return results;
	}

	/**
	 * Find a single friend record
	 * @param userId the id of the user whose friend we are trying to find
	 * @return RecordSet of all friendships for a a given userId
	 */
	public CachedRowSet findFriend(long id)
	{
		conn.open();
		Connection connection = conn.getConnection();
		ResultSet data;
		CachedRowSet results;

		try
		{
			results = new CachedRowSetImpl();
			Statement select = (Statement) connection.createStatement();
			data = select.executeQuery(selectStatement + id);
			results.populate(data);
			data.close();
		}

		catch (SQLException e)
		{
			System.err.println(FriendGateway.class.getName() + " SQL ERROR: " + e.getMessage());
			results = null;
		}

		conn.close();
		return results;
	}

	/**
	 * Delete a single friendship relation based on its id.
	 * @param relationId the relation id of the friendship
	 * @return the result of the operation
	 */
	public SQLEnum delete(long relationId)
	{
		Connection connection = conn.getConnection();
		SQLEnum result = SQLEnum.SUCCESS;

		try
		{
			Statement delete = (Statement) connection.createStatement();
			delete.execute(deleteStatement + relationId);
		}
		catch (SQLException e)
		{
			System.err.println(FriendGateway.class.getName() + " SQL ERROR: " + e.getMessage());
			result = SQLEnum.FAILED_SQL_ERROR;
		}

		return result;
	}
}
