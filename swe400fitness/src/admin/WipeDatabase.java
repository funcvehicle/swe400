package admin;import java.sql.Connection;import java.sql.DriverManager;import java.sql.SQLException;import com.mysql.jdbc.Statement;public class WipeDatabase
{private String driver="com.mysql.jdbc.Driver";private String url="jdbc:mysql://lsagroup4.cbzhjl6tpflt.us-east-1.rds.amazonaws.com:3306/fitness4";private String userName="lsagroup4";private String passWord="lsagroup4";private Connection connection;public static void main(String[]args)throws SQLException
{WipeDatabase wiper=new WipeDatabase();wiper.connect();Statement wipe=(Statement)wiper.connection.createStatement();wipe.executeUpdate("DELETE FROM pendingfriends;");wipe.executeUpdate("DELETE FROM friends;");wipe.executeUpdate("DELETE FROM people;");wiper.closeConnection();}
public void connect()
{try
{Class.forName(driver);}
catch(ClassNotFoundException e)
{System.err.println("Driver Not Found!");}
try
{this.connection=(Connection)DriverManager.getConnection(url,userName,passWord);}
catch(SQLException e)
{System.err.println("Failed to connect: "+e.getMessage());}}
public void closeConnection()
{try
{this.connection.close();}
catch(SQLException e)
{System.err.println("Could not close connection: "+e.getMessage());}}}