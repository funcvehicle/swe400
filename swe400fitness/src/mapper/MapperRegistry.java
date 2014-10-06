package mapper;

import domainModel.DomainObject;
import domainModel.Friend;
import domainModel.Person;

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
	//TODO: make registry thread local
	static PersonMapper pm;
	static FriendMapper fm;
	
	public static Mapper getMapper(Class<? extends DomainObject> c)
	{
		Mapper m = null;
		
		if (c == Person.class)
		{
			m = pm;
		}
		
		else if (c == Friend.class)
		{
			m = fm;
		}
		
		return m;
	}
}
