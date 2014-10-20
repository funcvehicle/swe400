package mockClasses;

import mapper.FriendMapper;
import mapper.MapperRegistry;
import mapper.PersonMapper;

public class MockMapperRegistry extends MapperRegistry 
{
	public MockMapperRegistry()
	{
		super.pm = new MockMapper();
		super.fm = new MockMapper();
	}
}
