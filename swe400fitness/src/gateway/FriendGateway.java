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
	public CachedRowSet find(long userId)
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

//	/**
//	 * Deletes a user's friendship with another user or all of the user's
//	 * friendships
//	 * 
//	 * @param userId the user's unique userId
//	 * @param friendId if this is -1 all of the user's friendships will be
//	 *        deleted
//	 * @return SQLEnum denoting success/failure
//	 */
//	public SQLEnum delete(long userId, long friendId)
//	{
//		establishConnection();
//		Connection connection = getConnection();
//		SQLEnum result = SQLEnum.SUCCESS;
//
//		try
//		{
//			Statement delete = (Statement) connection.createStatement();
//			if (friendId == -1)
//			{
//				delete.execute(deleteStatement + userId);
//			}
//			else
//			{
//				delete.execute(deleteStatement + userId + " AND friendId=" + friendId);
//			}
//		}
//		catch (SQLException e)
//		{
//			result = SQLEnum.FAILED_SQL_ERROR;
//		}
//
//		return result;
//	}

//	/**
//	 * Confirms a pending friendship by deleting it from the pendingFriends
//	 * table and adding it to the friends table
//	 * 
//	 * @param userId
//	 * @param friendId
//	 * @return SQLEnum denoting success/failure
//	 */
//	public SQLEnum confirmFriendship(long userId, long friendId)
//	{
//		establishConnection();
//		Connection connection = getConnection();
//		// Add id resolutions here
//		try
//		{
//			Statement pendingStatement = (Statement) connection.createStatement();
//			Statement friendStatement = (Statement) connection.createStatement();
//			pendingStatement.execute(deleteStatement + userId + " AND friendId=" + friendId);
//			friendStatement.execute("INSERT INTO friends (userId,friendId) VALUES ('" + userId + "','" + friendId
//					+ "')");
//			closeConnection();
//			return SQLEnum.SUCCESS;
//		}
//		catch (SQLException e)
//		{
//			closeConnection();
//			return SQLEnum.FAILED_SQL_ERROR;
//		}
//	}

}
