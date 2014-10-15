package gateway;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;


public class PersonGateway extends Gateway
{
	private Connection connection;
	private String queryStringByID = "SELECT * FROM people WHERE id=";
	private String queryStringByUserName = "SELECT * FROM people WHERE userName=";
	public ResultSet find(long id)
	{
		establishConnection();
		connection = getConnection();
		Statement find;
		try {
			find = (Statement) connection.createStatement();
			ResultSet results = find.executeQuery(queryStringByID+id);
			closeConnection();
			return results;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		closeConnection();
		return new NullSet();
		
	}
	
	public ResultSet find(String userName)
	{
		establishConnection();
		connection = getConnection();
		Statement find;
		try {
			find = (Statement) connection.createStatement();
			ResultSet results = find.executeQuery(queryStringByUserName+userName);
			closeConnection();
			return results;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		closeConnection();
		return new NullSet();
	}

	public SQLEnum insert(long id, String userName, String displayName)
	{
		establishConnection();
		connection = getConnection();
		String[] params = new String[3];
		params[0] = "people";
		params[1] = userName;
		if(recordExists(params) == SQLEnum.DOES_NOT_EXIST)
		{
			try {
				Statement insert = (Statement) connection.createStatement();
				insert.executeUpdate("INSERT INTO people (id,userName,displayName) VALUES ('"+id+"','"+userName+"','"+displayName+"')");
			} catch (SQLException e) {
				closeConnection();
				return SQLEnum.FAILED_SQL_ERROR;
			}
		}
		closeConnection();
		return SQLEnum.SUCCESS;
	}

	public SQLEnum update(long id, String userName, String displayName)
	{
		establishConnection();
		connection = getConnection();
		try {
			Statement updateStatement = (Statement) connection.createStatement();
			updateStatement.execute("UPDATE people SET id="+id+",userName="+userName+",displayName="+displayName+" WHERE id="+id);
		} catch (SQLException e) {
			return SQLEnum.FAILED_SQL_ERROR;
		}
		
		closeConnection();
		return SQLEnum.SUCCESS;
	}
	
	public SQLEnum delete(long id)
	{
		establishConnection();
		connection = getConnection();
		try {
			Statement deleteStatement = (Statement) connection.createStatement();
			deleteStatement.execute("DELETE FROM people WHERE id="+id);
		} catch (SQLException e) {
			closeConnection();
			return SQLEnum.FAILED_SQL_ERROR;
		}
		closeConnection();
		return SQLEnum.SUCCESS;
	}
}
