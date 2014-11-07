package mockClasses;

import java.sql.SQLException;

import com.sun.rowset.CachedRowSetImpl;

import gateway.FriendGateway;

public class MockFriendGateway extends FriendGateway
{
	CachedRowSetImpl set;
	
	public MockFriendGateway() throws SQLException
	{
		set = new CachedRowSetImpl();		
	}
}
