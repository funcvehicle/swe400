package gateway;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * KeyGateway generates a new key for an object
 * 
 * @author Miles Bock
 * 
 */
public class KeyGateway extends Gateway
{
	ConnectionUtil	conn	= ConnectionUtil.getCurrent();

	// /**
	// * Increments the current key in the DB
	// *
	// * @param connection used for performing the SQL ops
	// * @param current the current key
	// */
	// private void incrementKey(Connection connection, long current)
	// {
	// try
	// {
	// Statement statement = connection.createStatement();
	// statement.execute("UPDATE keytable SET nextId=" + (current + 1) +
	// " WHERE nextId=" + current);
	// }
	// catch (SQLException e)
	// {
	// System.err.println("Couldn't get the key!");
	// }
	// }
	//
	// /**
	// * Gets the current unique key
	// *
	// * @param connection
	// * @return the current unique key
	// */
	// private long getCurrentKey(Connection connection)
	// {
	// Statement statement;
	// try
	// {
	//
	// statement = connection.createStatement();
	// ResultSet results = statement.executeQuery("SELECT * FROM keytable");
	// results.next();
	// long returnMe = results.getLong("nextId");
	// incrementKey(connection, returnMe);
	// return returnMe;
	// }
	// catch (SQLException e)
	// {
	// e.printStackTrace();
	// }
	//
	// return -1;
	// }

	/**
	 * Generate a new key to be used in the next layer
	 * 
	 * @return the newest unique ID
	 */
	public long generateKey()
	{
		conn.open();
		Connection connection = conn.getConnection();
		ResultSet results;
		long key = -1;

		try
		{
			conn.getConnection().setAutoCommit(false);
			Statement statement1 = connection.createStatement();
			Statement statement2 = connection.createStatement();
			results = statement1.executeQuery("SELECT * FROM keytable");
			statement2.execute("UPDATE keytable SET nextId=nextId + 1;");
			conn.getConnection().commit();
			results.next();
			key = results.getLong("nextId");
		}
		catch (SQLException e)
		{
//			try
//			{
//				conn.getConnection().rollback();
//			}
//			catch (SQLException e1)
//			{
//				e1.printStackTrace();
//			}
		}
		finally
		{
			try
			{
				conn.getConnection().setAutoCommit(true);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

		conn.close();
		return key;
	}
}
