package mapper;

import domainModel.DomainObject;

/**
 * 
 * @author Connor Fox
 *
 * Database:
 * lsagroup4.cbzhjl6tpflt.us-east-1.rds.amazonaws.com:3306
 *
 */
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
		
		else if (c.getName().equals("Friend"))
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
