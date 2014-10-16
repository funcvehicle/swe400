package gateway;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class KeyGateway extends Gateway
{
	/**
	 * Increments the current key in the DB
	 * @param connection used for performing the SQL ops
	 * @param current the current key
	 */
	private void incrementKey(Connection connection,long current)
	{
		try {

			Statement statement = connection.createStatement();
			statement.execute("UPDATE keytable SET key="+(current+1)+" WHERE key="+current);
		} catch (SQLException e) {
			System.err.println("Couldn't get the key!");
		}
	}
	/**
	 * Gets the current unique key
	 * @param connection
	 * @return the current unique key
	 */
	private long getCurrentKey(Connection connection)
	{
		Statement statement;
		try {
			
			statement = connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM keytable");
			long returnMe = results.getLong("key");
			incrementKey(connection, returnMe);
			closeConnection();
			return returnMe;
		} catch (SQLException e) {
			closeConnection();
			e.printStackTrace();
		} 
		return -1;
	}
	/**
	 * Generate a new key to be used in the next layer
	 * @return the newest unique ID
	 */
	public long generateKey()
	{
		establishConnection();
		Connection connection = getConnection();
		return getCurrentKey(connection);
	}
}
