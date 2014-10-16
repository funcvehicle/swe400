package mapper;

import gateway.FriendGateway;
import gateway.PersonGateway;
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
	public static ThreadLocal<MapperRegistry> current = new ThreadLocal<MapperRegistry>();
	
	protected PersonMapper pm;
	protected FriendMapper fm;
	
	public MapperRegistry()
	{
		pm = new PersonMapper(new PersonGateway());
		fm = new FriendMapper(new FriendGateway());
	}
	
	public static MapperRegistry getCurrent()
	{
		return current.get();
	}
	
	public static void newCurrent()
	{
		setCurrent(new MapperRegistry());
	}
	
	public static void setCurrent(MapperRegistry mr)
	{
		current.set(mr);
	}
	
	public Mapper getMapper(Class<? extends DomainObject> c)
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
