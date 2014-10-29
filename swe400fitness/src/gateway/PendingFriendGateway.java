package gateway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;

import com.mysql.jdbc.Statement;

public class PendingFriendGateway extends Gateway
{
	/**
	 * Table name: pendingfriends
	 * columns: id BIGINT, inquirerId BIGINT, recipientId BIGINT
	 * inquiererId REFERENCES people(id)
	 * recipientId REFERENCES people(id)
	 */
	private Connection	connection;
	private String		findOutgoingStatement	= "SELECT * FROM pendingfriends WHERE inquirerId=";
	private String		findIncomingStatement	= "SELECT * FROM pendingfriends WHERE inquirerId=";
	private String		deleteStatement	= "DELETE FROM pendingfriends WHERE id=";
	private String		insertStatement	= "INSERT INTO pendingfriends (inquirerId, recipientId, id) VALUES (";
	
	
	/**
	 * Find all requests coming from a user
	 * @param userID
	 */
	public CachedRowSet findOutgoing(long inquirerID)
	{
		establishConnection();
		connection = getConnection();
		ResultSet data;
		CachedRowSet results;
		
		try
		{
			results = new CachedRowSetImpl();
			Statement select = (Statement) connection.createStatement();
			data = select.executeQuery(findOutgoingStatement + inquirerID);
			results.populate(data);
			
		}
		catch (SQLException e)
		{
			System.err.println("SQL ERROR: " + e.getMessage());
			results = null;
		}
		
		closeConnection();
		return results;
	}
	
	/**
	 * Find all friend requests coming to a user
	 * @param recipientID
	 * @return
	 */
	public CachedRowSet findIncoming(long recipientID)
	{
		establishConnection();
		connection = getConnection();
		ResultSet data;
		CachedRowSet results;
		
		try
		{
			results = new CachedRowSetImpl();
			Statement select = (Statement) connection.createStatement();
			data = select.executeQuery(findIncomingStatement + recipientID);
			results.populate(data);
		}
		catch (SQLException e)
		{
			System.err.println("SQL ERROR: " + e.getMessage());
			results = null;
		}
		
		closeConnection();
		return results;
	}
	
	/**
	 * Insert a new pending friend request
	 * @param inquirerId
	 * @param recipientId
	 * @param id
	 * @return
	 */
	public SQLEnum insert(long inquirerId, long recipientId, long id)
	{
		establishConnection();
		connection = getConnection();
		SQLEnum result = SQLEnum.SUCCESS;
		
		try
		{
			Statement insert = (Statement) connection.createStatement();
			if (!insert.execute(insertStatement + inquirerId + "," + recipientId + "," + id + ")"))
			{
				result = SQLEnum.FAILED_BAD_PARAMS;
			}
		}
		catch (SQLException e)
		{
			result = SQLEnum.FAILED_SQL_ERROR;
		}
		
		closeConnection();
		return result;
	}

	/**
	 * Remove a pending friend request
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
