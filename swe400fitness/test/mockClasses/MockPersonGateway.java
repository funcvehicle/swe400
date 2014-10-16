package mockClasses;

import java.sql.ResultSet;

import gateway.NullSet;
import gateway.PersonGateway;
import gateway.SQLEnum;

public class MockPersonGateway extends PersonGateway
{
	@Override
	public ResultSet find(long id)
	{
		return new NullSet();
	}
	
	@Override
	public SQLEnum insert(long id, String username, String displayName, String password)
	{
		return null;
	}
	
	@Override
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
