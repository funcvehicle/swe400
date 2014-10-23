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
	private String		selectStatement	= "SELECT * FROM friends WHERE userId=";
	private String		insertStatement	= "INSERT INTO friends VALUES (";
	private String		deleteStatement	= "DELETE FROM friends WHERE userId=";

	public FriendGateway()
	{
	}

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
				insert.executeUpdate(insertStatement + relationID + "," + personID + "," + friendID + ");");
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

		return result;
	}

	/**
	 * Find all friendships for a userId
	 * 
	 * @param userId
	 * @return RecordSet of all friendships for a a given userId
	 */
	public ResultSet find(long userId)
	{
		establishConnection();
		connection = getConnection();
		ResultSet results;

		try
		{
			Statement select = (Statement) connection.createStatement();
			results = select.executeQuery(selectStatement + userId);
		}
		catch (SQLException e)
		{
			results = new NullSet();
			e.printStackTrace();
		}
		
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
