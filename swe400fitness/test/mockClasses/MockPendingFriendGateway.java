package mockClasses;

import java.sql.SQLException;

import com.sun.rowset.CachedRowSetImpl;

import gateway.PendingFriendGateway;

/**
 * 
 * @author Emily Maust
 *
 */
public class MockPendingFriendGateway extends PendingFriendGateway
{
	CachedRowSetImpl set;
	
	public MockPendingFriendGateway() throws SQLException
	{
		set = new CachedRowSetImpl();		
	}
}
