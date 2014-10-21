package gateway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	public ResultSet findOutgoing(long inquirerID)
	{
		establishConnection();
		connection = getConnection();
		ResultSet results;
		
		try
		{
			Statement select = (Statement) connection.createStatement();
			results = select.executeQuery(findOutgoingStatement + inquirerID);
		}
		catch (SQLException e)
		{
			results = new NullSet();
		}
		
		return results;
	}
	
	/**
	 * Find all friend requests coming to a user
	 * @param recipientID
	 * @return
	 */
	public ResultSet findIncoming(long recipientID)
	{
		establishConnection();
		connection = getConnection();
		ResultSet results;
		
		try
		{
			Statement select = (Statement) connection.createStatement();
			results = select.executeQuery(findIncomingStatement + recipientID);
		}
		catch (SQLException e)
		{
			results = new NullSet();
		}
		
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
		SQLEnum result;
		
		try
		{
			Statement insert = (Statement) connection.createStatement();
			insert.executeUpdate(insertStatement + "'" + inquirerId + "','" + recipientId + "','" + id + "')");
		}
		catch (SQLException e)
		{
			result = SQLEnum.FAILED_SQL_ERROR;
		}
		
		result = SQLEnum.SUCCESS;
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
		SQLEnum result;
		
		try
		{
			Statement insert = (Statement) connection.createStatement();
			insert.executeUpdate(deleteStatement + "'" + id + "')");
		}
		catch (SQLException e)
		{
			result = SQLEnum.FAILED_SQL_ERROR;
		}
		
		result = SQLEnum.SUCCESS;
		return result;
	}
}
