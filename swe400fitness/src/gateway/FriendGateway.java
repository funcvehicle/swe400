package gateway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.*;

/**
 * 
 * @author mb8542
 * 
 */
public class FriendGateway extends Gateway
{

	private Connection	connection;
	private String		friendTableQuery	= "SELECT * FROM friends WHERE userId=";

	public FriendGateway()
	{

	}

	/**
	 * Create a pending friendship in the DB
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
		if (recordExists(params) == SQLEnum.DOES_NOT_EXIST)
		{
			try
			{
				Statement insert = (Statement) connection.createStatement();
				insert.executeUpdate("INSERT INTO pendingfriends VALUES (" + relationID + "," + personID + ","
						+ friendID + ");");
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				return SQLEnum.FAILED_SQL_ERROR;
			}
		}
		else
		{
			closeConnection();
			return SQLEnum.EXISTS;
		}
		closeConnection();
		return SQLEnum.SUCCESS;
	}

	/**
	 * Find all friendships/pending friendships for a userId
	 * 
	 * @param userId
	 * @return RecordSet of all friendships (pending or established) for a a
	 *         given userId
	 */
	public ResultSet find(long userId)
	{
		establishConnection();
		connection = getConnection();
		try
		{
			Statement select = (Statement) connection.createStatement();
			ResultSet results = select.executeQuery(friendTableQuery + userId);
			/*
			 * RecordSet returnSet = new RecordSet(); while(results.next()) {
			 * Record temp = new Record(); temp.put("friendShip", results);
			 * returnSet.addRecord(temp);
			 * 
			 * }
			 */
			closeConnection();
			return results;
		}
		catch (SQLException e)
		{
			closeConnection();
			return new NullSet();
		}

	}

	/**
	 * Deletes a user's friendship with another user or all of the user's
	 * friendships
	 * 
	 * @param userId the user's unique userId
	 * @param friendId if this is -1 all of the user's friendships will be
	 *        deleted
	 * @return SQLEnum denoting success/failure
	 */
	public SQLEnum delete(long userId, long friendId)
	{

		establishConnection();
		// Add id resolutions here
		Connection connection = getConnection();
		try
		{
			Statement delete = (Statement) connection.createStatement();
			if (friendId == -1)
			{
				delete.execute("DELETE FROM friends WHERE userId=" + userId);
			}
			else
			{
				delete.execute("DELETE FROM friends WHERE userId=" + userId + " AND friendId=" + friendId);
			}
		}
		catch (SQLException e)
		{
			closeConnection();
			return SQLEnum.FAILED_SQL_ERROR;
		}

		closeConnection();
		return SQLEnum.SUCCESS;
	}

	/**
	 * Confirms a pending friendship by deleting it from the pendingFriends
	 * table and adding it to the friends table
	 * 
	 * @param userId
	 * @param friendId
	 * @return SQLEnum denoting success/failure
	 */
	public SQLEnum confirmFriendship(long userId, long friendId)
	{
		establishConnection();
		Connection connection = getConnection();
		// Add id resolutions here
		try
		{
			Statement pendingStatement = (Statement) connection.createStatement();
			Statement friendStatement = (Statement) connection.createStatement();
			pendingStatement.execute("DELETE FROM pendingfriends WHERE userId=" + userId + " AND friendId=" + friendId);
			friendStatement.execute("INSERT INTO friends (userId,friendId) VALUES ('" + userId + "','" + friendId
					+ "')");
			closeConnection();
			return SQLEnum.SUCCESS;
		}
		catch (SQLException e)
		{
			closeConnection();
			return SQLEnum.FAILED_SQL_ERROR;
		}
	}

}
