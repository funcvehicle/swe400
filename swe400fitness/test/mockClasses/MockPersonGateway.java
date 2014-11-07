package mockClasses;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

import gateway.PersonGateway;
import gateway.SQLEnum;

public class MockPersonGateway extends PersonGateway
{
	CachedRowSetImpl set;
	
	public MockPersonGateway() throws SQLException
	{
		set = new CachedRowSetImpl();
		
		set.setLong(1, 2);
	}
	
	@Override
	public CachedRowSet find(long id)
	{
		return null;
	}

	@Override
	public SQLEnum insert(long id, String username, String displayName, String password)
	{
		return null;
	}
	
	public SQLEnum update(long id, String username, String displayName)
	{
		return null;
	}

	@Override
	public SQLEnum delete(long id)
	{
		return null;
	}
}
