package gateway;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.*;

import recordSet.Record;
import recordSet.RecordSet;
/**
 * 
 * @author mb8542
 *
 */
public class FriendGateway extends Gateway
{
	
	private Connection connection;
	private String friendTableQuery = "SELECT * FROM friends WHERE userName=";
	public FriendGateway()
	{
		
	}
	/**
	 * Create a pending friendship in the DB
	 * @param personID
	 * @param friendID
	 * @return SQLEnum
	 */
	public SQLEnum create(String personID, String friendID) {
		establishConnection();
		connection = getConnection();
		String[] params = new String[3];
		params[0] = "friends";
		params[1] = personID;
		params[2] = friendID;
		if(recordExists(params) == SQLEnum.DOES_NOT_EXIST)
		{
			try {
				Statement insert = (Statement) connection.createStatement();
				insert.executeUpdate("INSERT INTO pendingFriends VALUES " + personID + "," + friendID);
			} catch (SQLException e) {
				return SQLEnum.FAILED_SQL_ERROR;
			}
		}else{
			closeConnection();
			return SQLEnum.EXISTS;
		}
		closeConnection();
		return SQLEnum.SUCCESS;
	}
	/**
	 * Find all friendships/pending friendships for a username
	 * @param userName
	 * @return RecordSet of all friendships (pending or established) for a a given username
	 */
	public RecordSet find(String userName)
	{
		establishConnection();
		connection = getConnection();
		try{
			Statement select = (Statement) connection.createStatement();
			ResultSet results = select.executeQuery(friendTableQuery+userName);
			RecordSet returnSet = new RecordSet();
			while(results.next())
			{
				Record temp = new Record();
				temp.put("friendShip", results);
				returnSet.addRecord(temp);
				
			}
			closeConnection();
			return returnSet;
		} catch (SQLException e){
			closeConnection();
			return new RecordSet();
		}
		
		
	}
	/**
	 * Deletes a user's friendship with another user or all of the user's friendships
	 * @param userName the user's unique username
	 * @param friendName if this is null all of the user's friendships will be deleted
	 * @return SQLEnum denoting success/failure
	 */
	public SQLEnum delete(String userName, String friendName) {
		
		establishConnection();
		Connection connection = getConnection();
		try {
			Statement delete = (Statement) connection.createStatement();
			if(friendName == null)
			{
				delete.execute("DELETE FROM friends WHERE userName="+userName);
			}else
			{
				delete.execute("DELETE FROM friends WHERE userName="+userName+" AND friendName="+friendName);
			}
		} catch (SQLException e) {
			closeConnection();
			return SQLEnum.FAILED_SQL_ERROR;
		}
		
		
		closeConnection();
		return SQLEnum.SUCCESS;
	}
	/**
	 * Confirms a pending friendship by deleting it from the pendingFriends table
	 * and adding it to the friends table
	 * @param userName
	 * @param friendName
	 * @return SQLEnum denoting success/failure
	 */
	public SQLEnum confirmFriendship(String userName, String friendName){
		establishConnection();
		Connection connection = getConnection();
		try{
			Statement pendingStatement = (Statement) connection.createStatement();
			Statement friendStatement = (Statement) connection.createStatement();
			pendingStatement.execute("DELETE FROM pendingFriends WHERE userName="+userName+" AND friendName="+friendName);
			friendStatement.execute("INSERT INTO friends (userName,friendName) VALUES ('"+userName+"','"+friendName+"')");
			closeConnection();
			return SQLEnum.SUCCESS;
		}catch(SQLException e){
			closeConnection();
			return SQLEnum.FAILED_SQL_ERROR;
		}
	}

	

}
