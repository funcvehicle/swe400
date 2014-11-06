package mockClasses;

import Registry.MapperRegistry;
import mapper.FriendMapper;
import mapper.PersonMapper;

public class MockMapperRegistry extends MapperRegistry 
{
	public MockMapperRegistry()
	{
		super.pm = new MockMapper();
		super.fm = new MockMapper();
	}
}
