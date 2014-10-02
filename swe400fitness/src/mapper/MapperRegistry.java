package mapper;

import domainModel.DomainObject;

public class MapperRegistry
{
	static PersonMapper pm;
	static FriendMapper fm;
	static PendingFriendMapper pfm;
	
	public static Mapper getMapper(Class<? extends DomainObject> c)
	{
		Mapper m = null;
		
		if (c.getName().equals("Person"))
		{
			m = pm;
		}
		
		else if (c.getName().equals("FriendList"))
		{
			m = fm;
		}
		
		else if (c.getName().equals("PendingFriendList"))
		{
			m = pfm;
		}
		
		return m;
	}
}
