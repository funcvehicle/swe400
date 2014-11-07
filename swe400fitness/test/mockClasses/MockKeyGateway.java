package mockClasses;

import java.sql.SQLException;

import com.sun.rowset.CachedRowSetImpl;

import gateway.KeyGateway;

/**
 * 
 * @author Emily Maust
 *
 */
public class MockKeyGateway extends KeyGateway
{
	CachedRowSetImpl set;
	
	public MockKeyGateway() throws SQLException
	{
		set = new CachedRowSetImpl();		
	}
}
