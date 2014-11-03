package mapper;

import gateway.FriendGateway;
import gateway.PendingFriendGateway;
import gateway.PersonGateway;
import domainModel.DomainObject;
import domainModel.Friend;
import domainModel.PendingRequest;
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
	protected PendingFriendMapper pfm;
	
	public MapperRegistry()
	{
		pm = new PersonMapper();
		fm = new FriendMapper();
		pfm = new PendingFriendMapper(new PendingFriendGateway());
	}
	
	public static MapperRegistry getCurrent()
	{
		if (current.get() == null)
		{
			newCurrent();
		}
		
		return current.get();
	}
	
	private static void newCurrent()
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
		
		else if (c == PendingRequest.class)
		{
			m = pfm;
		}
		
		return m;
	}
}
