package gateway;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

/**
 * 
 * @author Connor Fox
 * 
 */
public class ConnectionUtil
{
	private String								driver		= "com.mysql.jdbc.Driver";
	private String								url			= "jdbc:mysql://lsagroup4.cbzhjl6tpflt.us-east-1.rds.amazonaws.com:3306/fitness4";
	private String								userName	= "lsagroup4";
	private String								passWord	= "lsagroup4";

	private static ThreadLocal<ConnectionUtil>	current		= new ThreadLocal<ConnectionUtil>();

	private Connection							connection;
	
	private boolean doNotClose = false;

	public static ConnectionUtil getCurrent()
	{
		if (current.get() == null)
			newCurrent();
		return current.get();
	}

	public static void newCurrent()
	{
		setCurrent(new ConnectionUtil());
	}

	public static void setCurrent(ConnectionUtil u)
	{
		current.set(u);
	}

	public void open()
	{
		try
		{
			Class.forName(driver);
		}
		catch (ClassNotFoundException e)
		{
			System.err.println("Driver Not Found!");
		}

		try
		{
			if (this.connection == null || this.connection.isClosed())
				this.connection = (Connection) DriverManager.getConnection(url, userName, passWord);
		}
		catch (SQLException e)
		{
			System.err.println("Failed to connect: " + e.getMessage());
		}
	}

	public Connection getConnection()
	{
		return connection;
	}
	
	public void setDoNotClose(boolean shouldClose)
	{
		doNotClose = shouldClose;
	}

	public void close()
	{
//		try
//		{
//			if (connection != null && !connection.isClosed() && doNotClose == false)
//			{
//				connection.close();
//				connection == null;
//			}
//		}
//		catch (SQLException e)
//		{
//			System.err.println("Failed to close connection!");
//			e.printStackTrace();
//		}
	}
}
