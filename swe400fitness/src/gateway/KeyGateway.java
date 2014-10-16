package gateway;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class KeyGateway extends Gateway
{
	
	private void incrementKey(Connection connection,long current)
	{
		try {

			Statement statement = connection.createStatement();
			statement.execute("UPDATE keytable SET key="+(current+1)+" WHERE key="+current);
		} catch (SQLException e) {
			System.err.println("Couldn't get the key!");
		}
	}
	private long getCurrentKey(Connection connection)
	{
		Statement statement;
		try {
			
			statement = connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM keytable");
			long returnMe = results.getLong("key");
			incrementKey(connection, returnMe);
			return returnMe;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return -1;
	}
	public long generateKey()
	{
		establishConnection();
		Connection connection = getConnection();
		return getCurrentKey(connection);
	}
}
